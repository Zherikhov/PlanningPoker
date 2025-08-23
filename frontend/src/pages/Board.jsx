import { useEffect } from 'react'
import Header from '../components/Header.jsx'
import TaskList from '../components/TaskList.jsx'
import Content from '../components/Content.jsx'
import Participants from '../components/Participants.jsx'
import Footer from '../components/Footer.jsx'

const tasks = [
  { id: '1', title: 'Тема задания 1', author: 'Иванов И.' },
  { id: '2', title: 'Тема', author: 'Иванов И.' }
]

export default function Board({ roomId, taskId }) {
  useEffect(() => {
    if (taskId) {
      const t = tasks.find(tsk => String(tsk.id) === String(taskId))
      document.title = `${t ? t.title : 'Task'} · Planning Poker`
    } else {
      document.title = `${roomId} · Planning Poker`
    }
  }, [roomId, taskId])

  return (
    <div className="min-h-screen bg-gray-900 text-gray-100 flex flex-col">
      <Header />
      <main className="flex-1 container mx-auto p-4 md:p-8">
        <div className="grid gap-4 lg:grid-cols-3">
          <TaskList tasks={tasks} roomId={roomId} activeTaskId={taskId} />
          <Content />
          <Participants />
        </div>
      </main>
      <Footer />
    </div>
  )
}
