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
    <div className="p-6 space-y-6">
      <header className="flex items-center justify-between">
        <h1 className="text-2xl font-semibold">Boards</h1>
        <button
          onClick={() => setShow(true)}
          className="bg-blue-600 hover:bg-blue-500 text-white px-4 py-2 rounded-md"
        >
          Create board
        </button>
      </header>

      {status === 'loading' && <p className="text-gray-400">Loading...</p>}
      {status === 'error' && <p className="text-red-500">Failed to load boards</p>}
      {status === 'empty' && (
        <div className="text-center py-10 space-y-4">
          <p className="text-gray-400">You don't have any boards yet.</p>
          <button
            onClick={() => setShow(true)}
            className="bg-blue-600 hover:bg-blue-500 text-white px-4 py-2 rounded-md"
          >
            Create your first board
          </button>
        </div>
      )}
      {status === 'data' && (
        <ul className="grid gap-4 sm:grid-cols-2 lg:grid-cols-3">
          {boards.map(b => (
            <li key={b.id}>
              <a
                href={`/boards/${b.id}`}
                onClick={e => {
                  e.preventDefault()
                  navigate(`/boards/${b.id}`)
                }}
                className="block h-full p-5 rounded-lg bg-gray-800 hover:bg-gray-700 transition-colors"
              >
                <h2 className="text-lg font-medium mb-1">{b.name}</h2>
                {b.description && (
                  <p
                    className="text-sm text-gray-300 overflow-hidden"
                    style={{ display: '-webkit-box', WebkitLineClamp: 2, WebkitBoxOrient: 'vertical' }}
                  >
                    {b.description}
                  </p>
                )}
              </a>
            </li>
          ))}
        </ul>
      )}

      {show && (
        <div className="fixed inset-0 flex items-center justify-center bg-black/50">
          <form
            onSubmit={submit}
            className="bg-gray-800 rounded-lg p-6 w-full max-w-sm space-y-4"
          >
            <h2 className="text-lg font-medium">Create board</h2>
            <label className="block space-y-1">
              <span className="text-sm">Title</span>
              <input
                className="w-full p-2 rounded bg-gray-700"
                placeholder="Title"
                value={form.name}
                onChange={e => setForm({ ...form, name: e.target.value })}
                required
              />
            </label>
            <label className="block space-y-1">
              <span className="text-sm">Description</span>
              <textarea
                className="w-full p-2 rounded bg-gray-700"
                placeholder="Description"
                value={form.description}
                onChange={e => setForm({ ...form, description: e.target.value })}
              />
            </label>
            <div className="flex justify-end space-x-2 pt-2">
              <button type="button" onClick={() => setShow(false)} className="px-4 py-2">
                Cancel
              </button>
              <button type="submit" className="bg-blue-600 hover:bg-blue-500 text-white px-4 py-2 rounded-md">
                Create
              </button>
            </div>
          </form>
        </div>
      )}
    </div>
  )
}
