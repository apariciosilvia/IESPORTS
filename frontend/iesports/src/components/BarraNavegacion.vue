<template>
  <div :class="['navbar', { 'navbar-visible': showNavbar }]">
    <IonToolbar class="custom-toolbar">
      <div class="nav-links">
        <a href="#inicio">Inicio</a>
        <a href="#torneos">Torneos</a>
        <a href="#equipos">Equipos</a>
        <a href="#calendario">Calendario</a>
        <a href="#contacto">Contacto</a>
        <a @click="goToLogin">Iniciar Sesión</a>
      </div>
    </IonToolbar>
  </div>
</template>

<script setup lang="ts">
import { IonToolbar } from '@ionic/vue';
import { ref, onMounted, onUnmounted } from 'vue';
import { useRouter } from 'vue-router';


const showNavbar = ref(false);

const router = useRouter();

const handleScroll = (event: CustomEvent) => {
  const scrollElement = (event.target as any).getScrollElement 
    ? event.target 
    : (event.target as any).scrollElement;

  scrollElement.getScrollElement().then((scrollEl: HTMLElement) => {
    const scrollTop = scrollEl.scrollTop;
    const windowWidth = window.innerWidth;

    if (windowWidth >= 1440) {
      showNavbar.value = scrollTop > 900;
    } else if (windowWidth >= 1024) {
      showNavbar.value = scrollTop > 700;
    } else if (windowWidth >= 768) {
      showNavbar.value = scrollTop > 1000;
    } else {
      showNavbar.value = scrollTop > 600;
    }
  });
};

onMounted(() => {
  document.addEventListener('ionScroll', handleScroll as EventListener);
});

onUnmounted(() => {
  document.removeEventListener('ionScroll', handleScroll as EventListener);
});



function goToLogin() {
  router.push('/login')
}
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
  color: rgb(255, 255, 255);
  text-decoration: none;
  font-weight: bold;
  font-size: 1rem;
  transition: color 0.3s;
}

.nav-links a:hover {
  color: #ffd700; /* Amarillo dorado al pasar el ratón */
}

.navbar {
  position: fixed;
  top: 0;
  width: 100%;
  transform: translateY(-100%);
  transition: transform 0.3s ease;
  z-index: 999;
  background-color: rgb(0, 0, 0); /* Fondo oscuro semitransparente */
}

.navbar-visible {
  transform: translateY(0);
}

.custom-toolbar {
  --background: #0a2540; /* Color de fondo negro */
  --color: white; /* Color de texto */
  --min-height: 60px; /* Opcional: altura del toolbar */
}

</style>
