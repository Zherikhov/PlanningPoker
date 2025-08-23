import {useEffect, useState} from 'react'
import {Link} from 'react-router-dom'

export default function BoardsPage() {
  const [boards, setBoards] = useState([])
  const [loading, setLoading] = useState(true)
  const [show, setShow] = useState(false)
  const [form, setForm] = useState({
    name: '',
    description: '',
    deckType: 'FIBONACCI',
    allowGuests: false,
  })

  useEffect(() => {
    fetch('/api/rooms?mine=true')
      .then(res => res.json())
      .then(data => {
        setBoards(data.content || [])
        setLoading(false)
      })
      .catch(() => setLoading(false))
  }, [])

  const submit = e => {
    e.preventDefault()
    if (form.name.length < 1 || form.name.length > 80) return
    fetch('/api/rooms', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(form)
    })
      .then(() => fetch('/api/rooms?mine=true').then(r => r.json()).then(data => setBoards(data.content || [])))
      .then(() => { setShow(false); setForm({ ...form, name: '', description: '' }) })
  }

  if (loading) return <div className="p-4">Loading...</div>

  return (
    <div className="p-4 text-gray-100">
      <div className="flex justify-between mb-4">
        <h1 className="text-xl">Boards</h1>
        <button className="bg-blue-600 px-3 py-1" onClick={() => setShow(true)}>New board</button>
      </div>
      {boards.length === 0 && <p>No boards yet</p>}
      <div className="grid gap-4 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4">
        {boards.map(b => (
          <Link key={b.id} to={`/boards/${b.id}`} className="block p-4 bg-gray-800 rounded">
            <h2 className="font-semibold">{b.name}</h2>
            <p className="text-sm text-gray-400">{b.description}</p>
          </Link>
        ))}
      </div>
      {show && (
        <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50">
          <form className="bg-gray-800 p-4 space-y-2" onSubmit={submit}>
            <h2 className="text-lg">New board</h2>
            <input className="w-full p-1 text-black" placeholder="Title" value={form.name}
                   onChange={e => setForm({ ...form, name: e.target.value })} />
            <textarea className="w-full p-1 text-black" placeholder="Description" value={form.description}
                      onChange={e => setForm({ ...form, description: e.target.value })} />
            <label className="flex items-center space-x-2">
              <input type="checkbox" checked={form.allowGuests}
                     onChange={e => setForm({ ...form, allowGuests: e.target.checked })} />
              <span>Allow guests</span>
            </label>
            <div className="flex justify-end space-x-2">
              <button type="button" onClick={() => setShow(false)}>Cancel</button>
              <button type="submit" className="bg-blue-600 px-2 py-1">Create</button>
            </div>
          </form>
        </div>
      )}
    </div>
  )
}

