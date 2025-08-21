import Header from './components/Header.jsx'
import TaskList from './components/TaskList.jsx'
import Content from './components/Content.jsx'
import Participants from './components/Participants.jsx'
import Footer from './components/Footer.jsx'

// Root application component assembling page layout
export default function App() {
  return (
    <div className="min-h-screen bg-gray-900 text-gray-100 flex flex-col">
      {/* Header with navigation */}
      <Header />

      {/* Main responsive layout */}
      <main className="flex-1 container mx-auto p-4 md:p-8">
        <div className="grid gap-4 lg:grid-cols-3">
          {/* Left column with tasks */}
          <TaskList />
          {/* Middle column with active task and cards */}
          <Content />
          {/* Right column with participants */}
          <Participants />
        </div>
      </main>

      {/* Footer */}
      <Footer />
    </div>
  )
}
