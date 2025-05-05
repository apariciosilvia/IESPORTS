<template>
  <!-- Icono para ir al login -->
  <div class="login-icon">
    <span class="material-symbols-outlined" style="font-size: 54px;" @click="goToLogin">
      account_circle
    </span>
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
import { useRouter } from 'vue-router'

const router = useRouter()

// Función de navegación al login
function goToLogin() {
  router.push('/login')
}

// Datos de los slides
const slides = [
  { img: new URL('@/assets/img/1.jpg', import.meta.url).href, title: 'Partidos' },
  { img: new URL('@/assets/img/2.jpg', import.meta.url).href, title: 'Noticias' },
  { img: new URL('@/assets/img/3.jpg', import.meta.url).href, title: 'Deportes' }
]

const currentSlide = ref(0) // Slide activo
let intervalId: any = null   // ID del setInterval

// Inicia el carrusel automático al montar el componente
onMounted(() => {
  intervalId = setInterval(() => {
    currentSlide.value = (currentSlide.value + 1) % slides.length
  }, 4000)
})

// Limpia el intervalo al salir del componente
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
}

</style>
