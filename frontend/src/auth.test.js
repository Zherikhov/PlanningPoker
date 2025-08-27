import { test } from 'node:test'
import assert from 'node:assert'
import { login, logout, isAuthenticated, ensureAuth } from './auth.js'

// Test authentication utilities

test('login and isAuthenticated', async () => {
  const realFetch = globalThis.fetch
  globalThis.fetch = async () => ({ ok: true, status: 204 })
  await logout().catch(() => {})
  assert.strictEqual(isAuthenticated(), false)
  login()
  assert.strictEqual(isAuthenticated(), true)
  globalThis.fetch = realFetch
})

test('ensureAuth builds redirect path', async () => {
  const realFetch = globalThis.fetch
  globalThis.fetch = async () => ({ ok: true, status: 204 })
  await logout().catch(() => {})
  const redirect = ensureAuth('/boards')
  assert.strictEqual(redirect, '/login?redirectTo=%2Fboards')
  globalThis.fetch = realFetch
})

test('logout clears auth even on failure', async () => {
  login()
  const realFetch = globalThis.fetch
  globalThis.fetch = async () => { throw new Error('net') }
  await logout().catch(() => {})
  assert.strictEqual(isAuthenticated(), false)
  globalThis.fetch = realFetch
})
