import { useEffect, useState } from 'react'
import { navigate } from '../router.js'
import { fetchBoards, createBoard } from '../boardsApi.js'

export default function Boards({ api = { fetchBoards, createBoard } }) {
  const [status, setStatus] = useState('loading')
  const [boards, setBoards] = useState([])
  const [show, setShow] = useState(false)
  const [form, setForm] = useState({ name: '', description: '' })

  useEffect(() => {
    document.title = 'Boards · Planning Poker'
    let cancel = false
    api.fetchBoards()
      .then(data => {
        if (cancel) return
        setBoards(data)
        setStatus(data.length ? 'data' : 'empty')
      })
      .catch(() => {
        if (!cancel) setStatus('error')
      })
    return () => {
      cancel = true
    }
  }, [api])

  const submit = async e => {
    e.preventDefault()
    if (!form.name.trim()) return
    try {
      const created = await api.createBoard(form)
      setBoards(prev => [created, ...prev])
      setStatus('data')
      setShow(false)
      setForm({ name: '', description: '' })
    } catch {
      // ignore errors for now
    }
  }

  return (
    <div className="p-4">
      <header className="flex justify-between items-center mb-4">
        <h1 className="text-xl">Boards</h1>
        <button
          onClick={() => setShow(true)}
          className="bg-blue-600 text-white px-3 py-1 rounded"
        >
          New board
        </button>
      </header>

      {status === 'loading' && <p>Loading...</p>}
      {status === 'error' && <p className="text-red-500">Failed to load boards</p>}
      {status === 'empty' && (
        <div className="text-center space-y-2">
          <p>No boards yet</p>
          <button
            onClick={() => setShow(true)}
            className="bg-blue-600 text-white px-3 py-1 rounded"
          >
            New board
          </button>
        </div>
      )}
      {status === 'data' && (
        <ul className="space-y-2">
          {boards.map(b => (
            <li key={b.id}>
              <a
                href={`/boards/${b.id}`}
                onClick={e => {
                  e.preventDefault()
                  navigate(`/boards/${b.id}`)
                }}
                className="block p-4 rounded bg-gray-800 hover:bg-gray-700"
              >
                <h2 className="text-lg">{b.name}</h2>
                {b.description && (
                  <p className="text-sm opacity-75 overflow-hidden" style={{ display: '-webkit-box', WebkitLineClamp: 2, WebkitBoxOrient: 'vertical' }}>
                    {b.description}
                  </p>
                )}
              </a>
            </li>
          ))}
        </ul>
      )}

      {show && (
        <div className="fixed inset-0 bg-black/50 flex items-center justify-center">
          <form
            onSubmit={submit}
            className="bg-gray-800 p-4 rounded space-y-2 w-80"
          >
            <h2 className="text-lg mb-2">New board</h2>
            <input
              className="w-full p-2 rounded bg-gray-700"
              placeholder="Title"
              value={form.name}
              onChange={e => setForm({ ...form, name: e.target.value })}
              required
            />
            <textarea
              className="w-full p-2 rounded bg-gray-700"
              placeholder="Description"
              value={form.description}
              onChange={e => setForm({ ...form, description: e.target.value })}
            />
            <div className="flex justify-end space-x-2 pt-2">
              <button type="button" onClick={() => setShow(false)} className="px-3 py-1">
                Cancel
              </button>
              <button type="submit" className="bg-blue-600 px-3 py-1 rounded text-white">
                Create
              </button>
            </div>
          </form>
        </div>
      )}
    </div>
  )
}
