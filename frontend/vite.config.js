import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

export default defineConfig({
  plugins: [react()],
  server: {
    port: 5173,
    proxy: {
      '/api': {
        target: 'http://localhost:3344',
        changeOrigin: true,
        secure: false,
        // если на бэке нет префикса /api, раскомментируй:
        // rewrite: path => path.replace(/^\/api/, ''),
      },
    },
  },
})
