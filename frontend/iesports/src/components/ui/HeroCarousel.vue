<template>
  <!-- Icono para login o menú -->
  <div class="login-icon" ref="iconRef">
    <span class="material-symbols-outlined" style="font-size: 54px;" @click="toggleMenu">
      account_circle
    </span>
    <b>{{ nombre }}</b>

    <!-- Menú desplegable solo si está logado -->
    <div v-if="usuarioLogado && menuVisible" class="user-menu" ref="menuRef">
      <ul>
        <li @click="goTo('/profile')">Perfil</li>
        <li @click="logout">Cerrar sesión</li>
      </ul>
    </div>
  </div>

  <!-- Texto central de bienvenida -->
  <div class="intro-text">
    <h1>BIENVENIDO A IESPORTS</h1>
    <p>Desliza hacia abajo para ver más</p>
  </div>

  <!-- Carrusel automático -->
  <div class="carousel-container">
    <div
      v-for="(slide, index) in slides"
      :key="index"
      class="carousel-slide"
      :style="{
        backgroundImage: `url(${slide.img})`,
        opacity: index === currentSlide ? 1 : 0
      }"
    >
      <h2>{{ slide.title }}</h2>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, onBeforeUnmount } from 'vue'
import { useUserMenu } from '@/composables/useUserMenu'

const {
  nombre,
  usuarioLogado,
  menuVisible,
  iconRef,
  menuRef,
  toggleMenu,
  goTo,
  logout
} = useUserMenu()

// Lógica carrusel
const slides = [
  { img: new URL('@/assets/img/1.jpg', import.meta.url).href, title: 'Partidos' },
  { img: new URL('@/assets/img/2.jpg', import.meta.url).href, title: 'Noticias' },
  { img: new URL('@/assets/img/3.jpg', import.meta.url).href, title: 'Deportes' }
]
const currentSlide = ref(0)
let intervalId: any = null

onMounted(() => {
  intervalId = setInterval(() => {
    currentSlide.value = (currentSlide.value + 1) % slides.length
  }, 4000)
})

onBeforeUnmount(() => {
  clearInterval(intervalId)
})
</script>

<style scoped>
/* Contenedor principal del carrusel, ocupa toda la pantalla */
.carousel-container {
  position: relative;
  width: 100%;
  height: 100vh;
  overflow: hidden;
  margin-bottom: 8%;
}

/* Cada slide ocupa toda la pantalla, se apila en el mismo lugar con opacidad */
.carousel-slide {
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: 100%;
  background-size: cover;
  background-position: center;
  transition: opacity 1s ease;
  display: flex;
  justify-content: center;
  align-items: flex-end;
  padding: 2rem;
  color: white;
  text-shadow: 1px 1px 5px black;
  opacity: 0; /* todos ocultos por defecto */
}

/* Texto principal centrado (encima del carrusel) */
.intro-text {
  position: absolute;
  top: 40%;
  width: 100%;
  text-align: center;
  color: white;
  z-index: 20;
  text-shadow: 1px 1px 5px black;
}
.intro-text h1 {
  font-size: 2.5rem;
  margin-bottom: 0.5rem;
}
.intro-text p {
  font-size: 1.2rem;
  margin-bottom: 1rem;
}

/* Icono de login fijo arriba a la derecha */
.login-icon {
  position: absolute;
  top: 20px;
  right: 20px;
  z-index: 30;
  color: white;
  cursor: pointer;
  display: flex;
  flex-direction: column;
  align-items: center;
  font-size: 0.9rem;
}

.user-menu {
  position: absolute;
  top: 80px;
  right: 0;
  background: white;
  color: black;
  border-radius: 8px;
  padding: 10px 0;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
  z-index: 9999;
  width: 180px;
}

.user-menu ul {
  list-style: none;
  margin: 0;
  padding: 0;
}

.user-menu li {
  padding: 10px 16px;
  cursor: pointer;
}

.user-menu li:hover {
  background: #f2f2f2;
}

</style>
