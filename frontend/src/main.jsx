import React from 'react'
import ReactDOM from 'react-dom/client'
import { usePath, navigate } from './router.js'
import { isAuthenticated } from './auth.js'
import Login from './Login.jsx'
import Register from './Register.jsx'
import Boards from './pages/Boards.jsx'
import Board from './pages/Board.jsx'
import Settings from './pages/Settings.jsx'
import NotFound from './pages/NotFound.jsx'
import './index.css'

function AppRouter() {
  const path = usePath()
  const [auth, setAuth] = React.useState(null)
  React.useEffect(() => {
    let cancelled = false
    isAuthenticated().then(ok => {
      if (!cancelled) setAuth(ok)
    })
    return () => {
      cancelled = true
    }
  }, [path])
  if (auth === null) return null
  if (path === '/login' || path === '/register') {
    if (auth) {
      navigate('/boards', { replace: true })
      return null
    }
    return path === '/login' ? <Login /> : <Register />
  }
  if (!auth) {
    navigate(`/login?redirectTo=${encodeURIComponent(path)}`, { replace: true })
    return null
  }
  if (path === '/boards') return <Boards />
  const taskMatch = path.match(/^\/boards\/([^/]+)\/tasks\/([^/]+)$/)
  if (taskMatch) return <Board roomId={taskMatch[1]} taskId={taskMatch[2]} />
  const boardMatch = path.match(/^\/boards\/([^/]+)$/)
  if (boardMatch) return <Board roomId={boardMatch[1]} />
  if (path === '/settings') return <Settings />
  return <NotFound />
}

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <AppRouter />
  </React.StrictMode>,
)
