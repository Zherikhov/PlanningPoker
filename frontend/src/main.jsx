import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import Login from './Login.jsx'
import './index.css'

// Simple router based on path
const path = window.location.pathname
const RootComponent = path === '/login' ? Login : App

// Render root React component
ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <RootComponent />
  </React.StrictMode>,
)
