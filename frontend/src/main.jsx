import React from 'react'
import ReactDOM from 'react-dom/client'
import {BrowserRouter, Routes, Route, Navigate} from 'react-router-dom'
import BoardsPage from './pages/BoardsPage.jsx'
import BoardPage from './pages/BoardPage.jsx'
import './index.css'

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Navigate to="/boards" replace />} />
        <Route path="/boards" element={<BoardsPage/>}/>
        <Route path="/boards/:roomId" element={<BoardPage/>}/>
        <Route path="*" element={<div>404</div>} />
      </Routes>
    </BrowserRouter>
  </React.StrictMode>,
)
