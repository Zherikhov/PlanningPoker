import { useEffect } from 'react'

export default function NotFound() {
  useEffect(() => {
    document.title = '404 · Planning Poker'
  }, [])
  return (
    <div className="p-4">
      <h1 className="text-xl mb-2">404</h1>
      <p>Page not found.</p>
    </div>
  )
}
