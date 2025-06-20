<template>
  <div :class="['navbar', { 'navbar-visible': showNavbar }]">
    <IonToolbar class="custom-toolbar">
      <div class="nav-links">
        <a
          v-for="item in navItems"
          :key="item.name"
          @click.prevent="handleNav(item)"
          href="javascript:;"
          :class="{ active: route.path === item.href }"
        >
          {{ item.name }}
        </a>
      </div>
    </IonToolbar>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted, onUnmounted } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import { IonToolbar } from '@ionic/vue';

// 1. Recuperamos el usuario
const userJson = localStorage.getItem('usuario')
const user = userJson ? JSON.parse(userJson) : null

// 2. Definimos navItems como computed
const navItems = computed(() => {
  const inicioHref = user?.role.name === 'Administrador'
    ? '/homeAdmin'
    : '/'

  // Lista de opciones de navegación
  return [
    { name: 'Inicio', href: inicioHref },
    { name: 'Torneos', href: '/tournaments' },
    { name: 'Equipos', href: '/teams' },
    { name: 'Calendario', href: '/calendar' },
    { name: 'Perfil', href: '/profile' }
  ]
})

const router = useRouter();
const route = useRoute();
const showNavbar = ref(false);

// Detecta si hay usuario logado
const isLoggedIn = computed(() => !!localStorage.getItem('usuario'));

// Maneja clicks de navegación
function handleNav(item: { name: string; href: string }) {
  if (item.href === '/') {
    // fuerza recarga de la ruta raíz y scroll al top
    window.location.href = '/'; 
    return;
  }

  if (!isLoggedIn.value && item.name !== 'Inicio') {
    router.push('/login');
    return;
  }

  if (item.href.startsWith('#')) {
    window.location.hash = item.href;
  } else {
    router.push(item.href);
  }
}


// Lógica de scroll para mostrar navbar
const handleScroll = (event: CustomEvent) => {
  const el = (event.target as any).getScrollElement
    ? event.target
    : (event.target as any).scrollElement;
  el.getScrollElement().then((scrollEl: HTMLElement) => {
    const top = scrollEl.scrollTop;
    const w = window.innerWidth;
    if (w >= 1440) showNavbar.value = top > 900;
    else if (w >= 1024) showNavbar.value = top > 700;
    else if (w >= 768) showNavbar.value = top > 1000;
    else showNavbar.value = top > 600;
  });
};

onMounted(() => {
  document.addEventListener('ionScroll', handleScroll as EventListener);
});
onUnmounted(() => {
  document.removeEventListener('ionScroll', handleScroll as EventListener);
});
</script>

<style scoped>
.nav-links {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 2rem;
  padding: 0.5rem 1rem;
}

.nav-links a {
  color: white;
  text-decoration: none;
  font-weight: bold;
  font-size: 1rem;
  transition: color 0.3s;
}

.nav-links a:hover {
  color: #ffd700;
}

.navbar {
  position: fixed;
  top: 0;
  width: 100%;
  transform: translateY(-100%);
  transition: transform 0.3s ease;
  z-index: 999;
  background-color: #002f3d;
}

.navbar-visible {
  transform: translateY(0);
}

.custom-toolbar {
  --background: #002f3d;
  --color: white;
  --min-height: 60px;
}

/* En móviles (≤480px): 3 enlaces por fila */
@media (max-width: 480px) {
  .nav-links {
    display: flex;
    flex-wrap: wrap;          
    justify-content: center;  
    gap: 0.5rem;              
    padding: 0.5rem;
  }

  .nav-links a {
    flex: 1 1 30%;
    text-align: center;       
    margin: 0.25rem 0;        
    font-size: 0.9rem;      
    color: rgb(255, 255, 255);  
  }
}

/* Enlace activo */
.nav-links a.active {
  color: #ffd700;
  border-bottom: 2px solid #ffd700;
}

</style>
