import { test } from 'node:test'
import assert from 'node:assert'
import { login, logout, isAuthenticated, ensureAuth } from './auth.js'

// Test authentication utilities

test('login and isAuthenticated', () => {
  logout()
  assert.strictEqual(isAuthenticated(), false)
  login()
  assert.strictEqual(isAuthenticated(), true)
})

test('ensureAuth builds redirect path', () => {
  logout()
  const redirect = ensureAuth('/boards')
  assert.strictEqual(redirect, '/login?redirectTo=%2Fboards')
})
