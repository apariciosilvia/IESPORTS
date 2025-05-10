<template>
  <div :class="['navbar', { 'navbar-visible': showNavbar }]">
    <IonToolbar class="custom-toolbar">
      <div class="nav-links">
        <a
          v-for="item in navItems"
          :key="item.name"
          @click.prevent="handleNav(item)"
          href="javascript:;"
        >
          {{ item.name }}
        </a>
      </div>
    </IonToolbar>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted, onUnmounted } from 'vue';
import { useRouter } from 'vue-router';
import { IonToolbar } from '@ionic/vue';

// Lista de opciones de navegación
const navItems = [
  { name: 'Inicio', href: '/' },
  { name: 'Torneos', href: '/tournaments' },
  { name: 'Equipos', href: '/teams' },
  { name: 'Mi Equipo', href: '#mi-equipo' },
  { name: 'Calendario', href: '/calendar' },
  { name: 'Contacto', href: '#contacto' },
  { name: 'Noticias', href: '#noticias' },
  { name: 'Perfil', href: '/profile' }
];

const router = useRouter();
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
  background-color: #0a2540;
}

.navbar-visible {
  transform: translateY(0);
}

.custom-toolbar {
  --background: #0a2540;
  --color: white;
  --min-height: 60px;
}
</style>
