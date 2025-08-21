// Sidebar list of tasks
const tasks = [
  { id: 1, title: 'Тема задания 1', author: 'Иванов И.', active: false },
  { id: 2, title: 'Тема', author: 'Иванов И.', active: true },
]

export default function TaskList() {
  return (
    <aside className="bg-gray-800 rounded-lg p-4 space-y-2">
      <h2 className="text-lg mb-2">Темы задания</h2>
      {tasks.map(task => (
        <a
          key={task.id}
          href="#"
          className={`block p-2 rounded hover:bg-gray-700 transition ${
            task.active ? 'bg-gray-700' : ''
          }`}
        >
          <div className="font-medium">{task.title}</div>
          <div className="text-xs text-gray-400">Автор: {task.author}</div>
        </a>
      ))}
    </aside>
  )
}
