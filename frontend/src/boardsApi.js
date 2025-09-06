export async function fetchBoards(fetchImpl = fetch) {
  const res = await fetchImpl('/api/rooms')
  if (!res.ok) throw new Error('failed')
  return res.json()
}

export async function createBoard({ name, description }, fetchImpl = fetch) {
  const res = await fetchImpl('/api/rooms', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ name, description })
  })
  if (res.status !== 201) {
    const err = await res.json().catch(() => ({}))
    throw err
  }
  return res.json()
}
