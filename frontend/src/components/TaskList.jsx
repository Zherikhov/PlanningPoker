import { useEffect } from 'react'
import { navigate } from '../router.js'

// Sidebar list of tasks
export default function TaskList({ tasks, roomId, activeTaskId }) {
  useEffect(() => {
    if (activeTaskId) {
      const el = document.getElementById(`task-${activeTaskId}`)
      if (el) {
        el.scrollIntoView({ behavior: 'smooth', block: 'center' })
        el.focus()
      }
    }
  }, [activeTaskId])

  return (
    <aside className="bg-gray-800 rounded-lg p-4 space-y-2">
      <h2 className="text-lg mb-2">Темы задания</h2>
      {tasks.map(task => (
        <a
          key={task.id}
          id={`task-${task.id}`}
          href={`/boards/${roomId}/tasks/${task.id}`}
          onClick={e => {
            e.preventDefault()
            navigate(`/boards/${roomId}/tasks/${task.id}`)
          }}
          className={`block p-2 rounded hover:bg-gray-700 transition ${
            task.id === activeTaskId ? 'bg-gray-700' : ''
          }`}
          tabIndex="-1"
        >
          <div className="font-medium">{task.title}</div>
          <div className="text-xs text-gray-400">Автор: {task.author}</div>
        </a>
      ))}
    </aside>
  )
}
