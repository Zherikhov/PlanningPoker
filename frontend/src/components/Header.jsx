import { useState } from 'react'
import { isAuthenticated, logout } from '../auth.js'
import { navigate } from '../router.js'

// Top navigation bar with brand and responsive burger menu
export default function Header() {
  const [open, setOpen] = useState(false)
  const [loading, setLoading] = useState(false)
  const authed = isAuthenticated()

  const handleLogout = async () => {
    if (loading) return
    setLoading(true)
    try {
      await logout()
    } catch (e) {
      window.alert('Не удалось выйти, попробуйте ещё раз')
    } finally {
      setLoading(false)
      navigate('/login')
    }
  }

  return (
    <header className="bg-gray-800 px-4 py-3 flex items-center justify-between relative">
      <a href="#" className="font-semibold text-lg">Planning Poker</a>

      {/* Desktop navigation */}
      <nav className="hidden md:flex gap-4 items-center">
        <a href="#" className="hover:text-blue-400">Room-1234</a>
        {authed && (
          <button
            onClick={handleLogout}
            disabled={loading}
            className="flex items-center gap-1 hover:text-blue-400"
          >
            <span aria-hidden="true">⇦</span>
            Выйти
          </button>
        )}
      </nav>

      {/* Burger menu for mobile */}
      <button
        className="md:hidden text-2xl"
        onClick={() => setOpen(!open)}
        aria-label="Toggle menu"
      >
        {open ? '✖' : '☰'}
      </button>

      {/* Dropdown menu shown on small screens */}
      {open && (
        <div className="md:hidden absolute top-full left-0 right-0 bg-gray-800 p-4 shadow-lg">
          <a
            href="#"
            className="block py-1 hover:text-blue-400"
            onClick={() => setOpen(false)}
          >
            Room-1234
          </a>
          {authed && (
            <button
              onClick={handleLogout}
              disabled={loading}
              className="block py-1 w-full text-left hover:text-blue-400"
            >
              Выйти
            </button>
          )}
        </div>
      )}
    </header>
  )
}
