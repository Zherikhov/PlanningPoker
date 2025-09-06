import test from 'node:test'
import assert from 'node:assert'
import { fetchBoards, createBoard } from './boardsApi.js'

test('fetchBoards returns list', async () => {
  const fetchImpl = async () => ({ ok: true, json: async () => ([{ id: '1', name: 'A' }]) })
  const data = await fetchBoards(fetchImpl)
  assert.strictEqual(Array.isArray(data), true)
  assert.strictEqual(data.length, 1)
  assert.strictEqual(data[0].name, 'A')
})

test('createBoard resolves on 201', async () => {
  const fetchImpl = async () => ({ status: 201, json: async () => ({ id: '1', code: 'ABC123' }) })
  const res = await createBoard({ name: 'Sprint', description: '' }, fetchImpl)
  assert.strictEqual(res.code, 'ABC123')
})

test('createBoard rejects on error', async () => {
  const fetchImpl = async () => ({ status: 400, json: async () => ({ error: 'bad' }) })
  await assert.rejects(() => createBoard({ name: '', description: '' }, fetchImpl))
})
