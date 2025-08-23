import assert from 'assert'
import test from 'node:test'
import { validateRegister } from './registerValidation.js'

test('validate register form', () => {
  assert.deepStrictEqual(validateRegister({ name: '', email: '', password: '' }), { name: 'invalid', email: 'invalid', password: 'invalid' })
  assert.deepStrictEqual(validateRegister({ name: 'A', email: 'bad', password: '123' }).email, 'invalid')
  assert.deepStrictEqual(validateRegister({ name: 'John', email: 'john@example.com', password: 'Secret123' }), {})
})
