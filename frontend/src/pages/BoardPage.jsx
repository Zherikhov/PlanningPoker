import {useEffect} from 'react'
import App from '../App.jsx'

// Board page reuses existing App component
export default function BoardPage() {
  useEffect(() => {
    document.title = 'Board · Planning Poker'
  }, [])
  return <App />
}

