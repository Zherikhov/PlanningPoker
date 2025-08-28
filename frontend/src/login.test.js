import test from 'node:test'
import assert from 'node:assert'
import { loginUser, logout, isAuthenticated } from './auth.js'

// Test login helper behaviour

test('loginUser rejects on failure', async () => {
  const fetchImpl = async () => ({ ok: false, json: async () => ({ error: 'INVALID_CREDENTIALS' }) })
  await logout().catch(() => {})
  await assert.rejects(() => loginUser({ email: 'a', password: 'b', remember: false }, fetchImpl))
  assert.strictEqual(await isAuthenticated(), false)
})

test('loginUser authenticates on success', async () => {
  const fetchImpl = async () => ({ ok: true, json: async () => ({ accessToken: 'tok', expiresIn: 60 }) })
  await logout().catch(() => {})
  const data = await loginUser({ email: 'a', password: 'b', remember: false }, fetchImpl)
  assert.strictEqual(data.accessToken, 'tok')
  assert.strictEqual(await isAuthenticated(), true)
})
