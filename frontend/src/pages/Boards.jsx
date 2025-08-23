import { useEffect } from 'react'
import { navigate } from '../router.js'

const boards = [
  { id: 'room1', name: 'Room 1' },
  { id: 'room2', name: 'Room 2' }
]

export default function Boards() {
  useEffect(() => {
    document.title = 'Boards · Planning Poker'
  }, [])

  return (
    <div className="p-4">
      <h1 className="text-xl mb-4">Boards</h1>
      <ul className="space-y-2">
        {boards.map(b => (
          <li key={b.id}>
            <a
              href={`/boards/${b.id}`}
              onClick={e => {
                e.preventDefault()
                navigate(`/boards/${b.id}`)
              }}
              className="text-blue-400 hover:underline"
            >
              {b.name}
            </a>
          </li>
        ))}
      </ul>
    </div>
  )
}
