import { useState, useEffect } from 'react'
import { navigate } from './router.js'
import { validateRegister } from './registerValidation.js'

export default function Register() {
  const messages = {
    en: {
      title: 'Register',
      name: 'Name',
      email: 'Email',
      password: 'Password',
      submit: 'Submit',
      back: 'Back to login',
      emailExists: 'Email already registered',
      rateLimit: 'Too many attempts'
    },
    ru: {
      title: 'Регистрация',
      name: 'Имя',
      email: 'Email',
      password: 'Пароль',
      submit: 'Создать аккаунт',
      back: 'Назад к входу',
      emailExists: 'Email уже зарегистрирован',
      rateLimit: 'Слишком много попыток'
    }
  }
  const lang = navigator.language.startsWith('ru') ? 'ru' : 'en'
  const t = messages[lang]
  useEffect(() => {
    document.title = 'Register · Planning Poker'
  }, [])

  const [name, setName] = useState('')
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [errors, setErrors] = useState({})
  const [alert, setAlert] = useState('')
  const [loading, setLoading] = useState(false)

  const handleSubmit = async e => {
    e.preventDefault()
    const errs = validateRegister({ name, email, password })
    if (Object.keys(errs).length) {
      setErrors(errs)
      return
    }
    setLoading(true)
    setErrors({})
    try {
      const res = await fetch('/api/auth/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ displayName: name, email, password })
      })
      if (res.status === 201) {
        navigate('/login?registered=1', { replace: true })
        return
      }
      if (res.status === 409) {
        setAlert(t.emailExists)
      } else if (res.status === 429) {
        setAlert(t.rateLimit)
      } else if (res.status === 400) {
        const data = await res.json()
        setErrors(data.fields || {})
      } else {
        setAlert('Error')
      }
    } catch {
      setAlert('Error')
    } finally {
      setLoading(false)
    }
  }

  return (
    <section className="auth-layout min-h-screen flex items-center justify-center" style={{ backgroundColor: 'var(--color-bg-dark)', color: 'var(--color-text-dark)' }}>
      <div className="auth-card p-6 rounded-xl shadow-lg w-full max-w-md" style={{ backgroundColor: 'var(--color-surface-dark)' }}>
        <h1 className="text-2xl font-semibold mb-4 text-center">{t.title}</h1>
        {alert && (
          <div role="alert" className="mb-4 p-3 rounded bg-red-600 text-sm" aria-live="assertive">{alert}</div>
        )}
        <form onSubmit={handleSubmit} noValidate>
          <label htmlFor="name" className="block text-sm mb-1">{t.name}</label>
          <input id="name" value={name} onChange={e => setName(e.target.value)} className={`w-full p-2 rounded border ${errors.name ? 'border-red-500' : 'border-gray-600'} bg-gray-800 focus:outline-none focus:ring-2 focus:ring-blue-500`} aria-invalid={errors.name ? 'true' : 'false'} disabled={loading} />
          {errors.name && <p className="text-red-500 text-xs mt-1">{errors.name}</p>}

          <label htmlFor="email" className="block text-sm mt-4 mb-1">{t.email}</label>
          <input id="email" type="email" value={email} onChange={e => setEmail(e.target.value)} className={`w-full p-2 rounded border ${errors.email ? 'border-red-500' : 'border-gray-600'} bg-gray-800 focus:outline-none focus:ring-2 focus:ring-blue-500`} aria-invalid={errors.email ? 'true' : 'false'} autoComplete="email" disabled={loading} />
          {errors.email && <p className="text-red-500 text-xs mt-1">{errors.email}</p>}

          <label htmlFor="password" className="block text-sm mt-4 mb-1">{t.password}</label>
          <input id="password" type="password" value={password} onChange={e => setPassword(e.target.value)} className={`w-full p-2 rounded border ${errors.password ? 'border-red-500' : 'border-gray-600'} bg-gray-800 focus:outline-none focus:ring-2 focus:ring-blue-500`} aria-invalid={errors.password ? 'true' : 'false'} autoComplete="new-password" disabled={loading} />
          {errors.password && <p className="text-red-500 text-xs mt-1">{errors.password}</p>}

          <button type="submit" className="mt-6 w-full py-2 rounded bg-blue-600 hover:bg-blue-500 disabled:bg-gray-600 focus:ring-2 focus:ring-blue-400" disabled={loading || Object.keys(validateRegister({ name, email, password })).length > 0}>{loading ? '...' : t.submit}</button>
        </form>
        <button type="button" onClick={() => navigate('/login')} className="mt-4 w-full py-2 rounded border border-gray-600 hover:bg-gray-700" disabled={loading}>{t.back}</button>
      </div>
    </section>
  )
}
