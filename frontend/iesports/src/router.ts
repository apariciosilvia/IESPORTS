import { createRouter, createWebHistory } from 'vue-router';

const routes = [
  { path: '/', 
    name: 'Home', 
    component: () => import('./views/HomeView.vue') 
  },
  { path: '/login', 
    name: 'Login', 
    component: () => import('./views/LoginView.vue') 
  },
  {
    path: '/profile',
    name: 'Profile',
    component: () => import('./views/ProfileView.vue'),
    meta: { requiresAuth: true }    // Solo usuarios logueados
  },
  {
    path: '/teams',
    name: 'Teams',
    component: () => import('./views/TeamsView.vue'),
    meta: { requiresAuth: true }
  },
  {
    path: '/tournaments',
    name: 'Tournaments',
    component: () => import('./views/TournamentsView.vue'),
    meta: { requiresAuth: true }
  },
  {
    path: '/calendar',
    name: 'Calendar',
    component: () => import('./views/CalendarView.vue'),
    meta: { requiresAuth: true }
  },
  {
    path: '/homeAdmin',
    name: 'HomeAdmin',
    component: () => import('./views/HomeAdmin.vue'),
    meta: { 
      requiresAuth: true,           // Debe estar logueado
      requiresAdmin: true           // Y ser administrador
    }
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// 1. Añade este bloque justo después de crear el router:
router.beforeEach((to, _from, next) => {
  // 2. Recuperamos el usuario del localStorage
  const userJson = localStorage.getItem('usuario')
  const user = userJson ? JSON.parse(userJson) : null

  // 3. Si la ruta exige autenticación y no hay usuario, vamos a Login
  if (to.meta.requiresAuth && !user) {
    return next({ name: 'Login' })
  }

  // 4. Si la ruta exige admin y el usuario no es “Administrador”, vamos al Home normal
  if (to.meta.requiresAdmin && (!user || user.role.name !== 'Administrador')) {
    return next({ name: 'Home' })
  }

  // 5. Si es admin y va a Home, lo mandamos a HomeAdmin ***
  if (user && user.role.name === 'Administrador' && to.name === 'Home') {
    return next({ name: 'HomeAdmin' })
  }

  // 6. Si pasa todas las comprobaciones, continúa
  return next()
})


export default router
