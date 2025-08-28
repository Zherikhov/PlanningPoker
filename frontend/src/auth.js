// Simple authentication utilities using web storage
// In non-browser test environments, fallback to in-memory storage
const memoryStorage = () => {
  let store = {}
  return {
    getItem: key => store[key],
    setItem: (key, value) => {
      store[key] = value
    },
    removeItem: key => {
      delete store[key]
    }
  }
}

if (typeof globalThis.localStorage === 'undefined') {
  globalThis.localStorage = memoryStorage()
}
if (typeof globalThis.sessionStorage === 'undefined') {
  globalThis.sessionStorage = memoryStorage()
}

const TOKEN_KEY = 'accessToken'
const EXP_KEY = 'expiresAt'

export function login(token, expiresIn, remember = false) {
  const storage = remember ? localStorage : sessionStorage
  const expiresAt = Date.now() + expiresIn * 1000
  storage.setItem(TOKEN_KEY, token)
  storage.setItem(EXP_KEY, String(expiresAt))
}

export function clearAuth() {
  localStorage.removeItem(TOKEN_KEY)
  localStorage.removeItem(EXP_KEY)
  sessionStorage.removeItem(TOKEN_KEY)
  sessionStorage.removeItem(EXP_KEY)
}

export async function logout() {
  let error = null
  try {
    const res = await fetch('/api/auth/logout', {
      method: 'POST',
      credentials: 'include'
    })
    if (!res.ok && res.status !== 401) {
      error = new Error('logout failed')
    }
  } catch (e) {
    error = e
  } finally {
    clearAuth()
  }
  if (error) throw error
}

function getStoredAuth() {
  const localToken = localStorage.getItem(TOKEN_KEY)
  if (localToken) return { token: localToken, exp: parseInt(localStorage.getItem(EXP_KEY), 10), storage: localStorage }
  const sessionToken = sessionStorage.getItem(TOKEN_KEY)
  if (sessionToken) return { token: sessionToken, exp: parseInt(sessionStorage.getItem(EXP_KEY), 10), storage: sessionStorage }
  return null
}

export async function isAuthenticated() {
  const auth = getStoredAuth()
  if (!auth || !auth.token) return false
  if (!auth.exp || Date.now() >= auth.exp) {
    try {
      const res = await fetch('/api/auth/refresh', { method: 'POST', credentials: 'include' })
      if (res.ok) {
        const data = await res.json()
        login(data.accessToken, data.expiresIn, auth.storage === localStorage)
        return true
      }
    } catch (e) {
      // ignore network errors
    }
    clearAuth()
    return false
  }
  return true
}

// Guard helper: returns redirect path when not authenticated
export async function ensureAuth(targetPath) {
  return (await isAuthenticated())
    ? null
    : `/login?redirectTo=${encodeURIComponent(targetPath)}`
}

export async function loginUser({ email, password, remember, navigate, redirectTo }, fetchImpl = fetch) {
  const res = await fetchImpl('/api/auth/login', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ email, password }),
    credentials: 'include'
  })
  if (!res.ok) {
    const err = await res.json().catch(() => ({ message: 'Login failed' }))
    throw err
  }
  const data = await res.json()
  login(data.accessToken, data.expiresIn, remember)
  navigate(redirectTo || '/boards')
}
