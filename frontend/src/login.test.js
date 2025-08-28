import test from 'node:test'
import assert from 'node:assert'
import { loginUser, logout, isAuthenticated } from './auth.js'

// Test login handler behaviour

test('loginUser does not navigate on failure', async () => {
  const fetchImpl = async () => ({ ok: false, json: async () => ({ error: 'INVALID_CREDENTIALS' }) })
  let navCalled = false
  const navigate = () => { navCalled = true }
  await logout().catch(() => {})
  await assert.rejects(() => loginUser({ email: 'a', password: 'b', remember: false, navigate, redirectTo: '/boards' }, fetchImpl))
  assert.strictEqual(navCalled, false)
  assert.strictEqual(await isAuthenticated(), false)
})

test('loginUser navigates on success', async () => {
  const fetchImpl = async () => ({ ok: true, json: async () => ({ accessToken: 'tok', expiresIn: 60 }) })
  let dest = null
  const navigate = (to) => { dest = to }
  await logout().catch(() => {})
  await loginUser({ email: 'a', password: 'b', remember: false, navigate, redirectTo: '/boards' }, fetchImpl)
  assert.strictEqual(dest, '/boards')
  assert.strictEqual(await isAuthenticated(), true)
})
