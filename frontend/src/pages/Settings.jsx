import { useEffect } from 'react'

export default function Settings() {
  useEffect(() => {
    document.title = 'Settings · Planning Poker'
  }, [])
  return (
    <div className="p-4">
      <h1 className="text-xl mb-4">Settings</h1>
      <p>Profile settings placeholder.</p>
    </div>
  )
}
