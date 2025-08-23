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

export function login(remember = false) {
  const storage = remember ? localStorage : sessionStorage
  storage.setItem('auth', 'true')
}

export function logout() {
  localStorage.removeItem('auth')
  sessionStorage.removeItem('auth')
}

export function isAuthenticated() {
  return (
    localStorage.getItem('auth') === 'true' ||
    sessionStorage.getItem('auth') === 'true'
  )
}

// Guard helper: returns redirect path when not authenticated
export function ensureAuth(targetPath) {
  return isAuthenticated()
    ? null
    : `/login?redirectTo=${encodeURIComponent(targetPath)}`
}
