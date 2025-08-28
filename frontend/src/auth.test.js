import { test } from 'node:test'
import assert from 'node:assert'
import { login, logout, isAuthenticated, ensureAuth } from './auth.js'

// Test authentication utilities

test('login and isAuthenticated', async () => {
  const realFetch = globalThis.fetch
  globalThis.fetch = async () => ({ ok: true, status: 204 })
  await logout().catch(() => {})
  assert.strictEqual(await isAuthenticated(), false)
  login('t', 60)
  assert.strictEqual(await isAuthenticated(), true)
  globalThis.fetch = realFetch
})

test('ensureAuth builds redirect path', async () => {
  const realFetch = globalThis.fetch
  globalThis.fetch = async () => ({ ok: true, status: 204 })
  await logout().catch(() => {})
  const redirect = await ensureAuth('/boards')
  assert.strictEqual(redirect, '/login?redirectTo=%2Fboards')
  globalThis.fetch = realFetch
})

test('logout clears auth even on failure', async () => {
  login('t', 60)
  const realFetch = globalThis.fetch
  globalThis.fetch = async () => { throw new Error('net') }
  await logout().catch(() => {})
  assert.strictEqual(await isAuthenticated(), false)
  globalThis.fetch = realFetch
})
