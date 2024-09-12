import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      '/api': {
        target: 'http://localhost:8082', // Porta do User Service
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ''), // Remove '/api' do início da URL
        secure: false,
      },
    },
  },
});
