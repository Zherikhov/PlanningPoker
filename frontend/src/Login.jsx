import { useState, useEffect } from 'react'
import { login } from './auth.js'
import { navigate } from './router.js'

// Login page component with email/password form
export default function Login() {
  const messages = {
    en: {
      title: 'Sign in',
      email: 'Email',
      password: 'Password',
      remember: 'Remember me',
      forgot: 'Forgot password?',
      signin: 'Sign in',
      errorInvalid: 'Email or password is incorrect',
      errorLocked: 'Account is locked',
      emailRequired: 'Email is required',
      emailInvalid: 'Invalid email format',
      passwordRequired: 'Password is required',
      passwordMin: 'Password must be at least 8 characters'
    },
    ru: {
      title: 'Вход',
      email: 'Email',
      password: 'Пароль',
      remember: 'Запомнить меня',
      forgot: 'Забыли пароль?',
      signin: 'Войти',
      errorInvalid: 'Неверный email или пароль',
      errorLocked: 'Учетная запись заблокирована',
      emailRequired: 'Введите email',
      emailInvalid: 'Неверный формат email',
      passwordRequired: 'Введите пароль',
      passwordMin: 'Пароль должен быть не менее 8 символов'
    }
  }
  const lang = navigator.language.startsWith('ru') ? 'ru' : 'en'
  const t = messages[lang]
  useEffect(() => {
    document.title = 'Login · Planning Poker'
  }, [])

  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [remember, setRemember] = useState(false)
  const [showPass, setShowPass] = useState(false)
  const [errors, setErrors] = useState({})
  const [alert, setAlert] = useState('')
  const [loading, setLoading] = useState(false)

  const emailRegex = /^(?:[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*|"(?:["]|\\")+")@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,}$/

  const validate = () => {
    const errs = {}
    if (!email) {
      errs.email = t.emailRequired
    } else if (!emailRegex.test(email)) {
      errs.email = t.emailInvalid
    }
    if (!password) {
      errs.password = t.passwordRequired
    } else if (password.length < 8) {
      errs.password = t.passwordMin
    }
    return errs
  }

  const handleSubmit = e => {
    e.preventDefault()
    const errs = validate()
    if (Object.keys(errs).length) {
      setErrors(errs)
      return
    }
    setLoading(true)
    setErrors({})
    const params = new URLSearchParams(window.location.search)
    const redirect = params.get('redirectTo') || '/boards'
    login(remember)
    setLoading(false)
    navigate(redirect, { replace: true })
  }

  return (
    <section
      className="auth-layout min-h-screen flex items-center justify-center"
      style={{ backgroundColor: 'var(--color-bg-dark)', color: 'var(--color-text-dark)' }}
    >
      <div
        className="auth-card p-6 rounded-xl shadow-lg w-full max-w-md"
        style={{ backgroundColor: 'var(--color-surface-dark)' }}
      >
        <h1 className="text-2xl font-semibold mb-4 text-center">{t.title}</h1>
        {alert && (
          <div role="alert" className="mb-4 p-3 rounded bg-red-600 text-sm" aria-live="assertive">
            {alert}
          </div>
        )}
        <form onSubmit={handleSubmit} noValidate>
          <label htmlFor="email" className="block text-sm mb-1">
            {t.email}
          </label>
          <input
            id="email"
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            className={`w-full p-2 rounded border ${errors.email ? 'border-red-500' : 'border-gray-600'} bg-gray-800 focus:outline-none focus:ring-2 focus:ring-blue-500`}
            aria-invalid={errors.email ? 'true' : 'false'}
            autoComplete="email"
            disabled={loading}
          />
          {errors.email && <p className="text-red-500 text-xs mt-1" id="emailErr">{errors.email}</p>}

          <label htmlFor="password" className="block text-sm mt-4 mb-1">
            {t.password}
          </label>
          <div className="relative">
            <input
              id="password"
              type={showPass ? 'text' : 'password'}
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className={`w-full p-2 rounded border ${errors.password ? 'border-red-500' : 'border-gray-600'} bg-gray-800 focus:outline-none focus:ring-2 focus:ring-blue-500`}
              aria-invalid={errors.password ? 'true' : 'false'}
              autoComplete="current-password"
              disabled={loading}
            />
            <button
              type="button"
              onClick={() => setShowPass(!showPass)}
              className="absolute inset-y-0 right-2 flex items-center text-sm" aria-label="Show password"
            >
              {showPass ? 'Hide' : 'Show'}
            </button>
          </div>
          {errors.password && <p className="text-red-500 text-xs mt-1" id="passwordErr">{errors.password}</p>}

          <div className="flex items-center justify-between mt-4">
            <label className="flex items-center text-sm">
              <input
                type="checkbox"
                className="mr-2"
                checked={remember}
                onChange={(e) => setRemember(e.target.checked)}
                disabled={loading}
              />
              {t.remember}
            </label>
            <a href="#" className="text-sm opacity-50 pointer-events-none" aria-disabled="true">
              {t.forgot}
            </a>
          </div>

          <button
            type="submit"
            className="mt-6 w-full py-2 rounded bg-blue-600 hover:bg-blue-500 disabled:bg-gray-600 focus:ring-2 focus:ring-blue-400"
            disabled={loading || Object.keys(validate()).length > 0}
          >
            {loading ? '...' : t.signin}
          </button>
        </form>
      </div>
    </section>
  )
}
