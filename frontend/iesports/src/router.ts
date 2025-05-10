import { createRouter, createWebHistory } from 'vue-router';  // Importaciones necesarias para crear un enrutador y manejar la historia de navegación

// Definición de las rutas de la aplicación
const routes = [
  {
    path: '/', // Ruta raíz de la aplicación
    name: 'Home',
    component: () => import('./views/HomeView.vue')
  },
  {
    path: '/login', // Ruta para la vista de inicio de sesión
    name: 'Login',
    component: () => import('./views/LoginView.vue')
  },
  {
    path: '/profile', // Ruta para la vista de inicio de sesión
    name: 'Profile',
    component: () => import('./views/ProfileView.vue')
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
