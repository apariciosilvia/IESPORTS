<template>
  <ion-page class="page-wrapper">

    <!-- Contenido principal de la página -->
    <!-- <ion-content fullscreen @ionScroll="handleScroll" :scroll-events="true"> -->

      <!-- ② Spinner mientras isLoading es true; contenido cuando termine -->
    <LoadingSpinner v-if="isLoading" />
    <ion-content
      v-else
      fullscreen
      @ionScroll="handleScroll"
      :scroll-events="true"
    >

      <!-- Carrusel principal (portada visual) -->
      <HeroCarousel :nombre="nombre" />

      <!-- Navbar solo visible cuando se hace scroll suficiente -->
      <Navbar :class="['navbar', { 'navbar-visible': showNavbar }]" />


      <!-- Botón para ir a la pantalla de login -->
      <!-- <ion-button expand="block" @click="goToLogin">
        Ir a Login
      </ion-button> -->
      
      <!-- Galería de imágenes u otro contenido visual -->
      <Gallery />

      <ContactComponent/>

      <Footer />

    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
/* Importa el router para poder navegar */
// import { useRouter } from 'vue-router'

/* Importa componentes de Ionic usados en la vista */
import { IonContent, IonPage } from '@ionic/vue'

/* Importa componentes visuales personalizados */
import HeroCarousel from '@/components/ui/HeroCarousel.vue';
import Navbar from '@/components/layout/Navbar.vue';
import Gallery from '@/components/ui/Gallery.vue';
import ContactComponent from '@/components/layout/ContactComponent.vue';
import Footer from '@/components/ui/Footer.vue';
import LoadingSpinner from '@/components/ui/LoadingSpinner.vue'

/* Importa lógica separada (composables) */
import { useLoadingEffect } from '@/composables//useLoadingEffect';
import { useNavbarScroll } from '@/composables/useNavbarScroll'     // gestiona scroll dinámico

import { onMounted, ref } from 'vue';

// Llamamos al composable de carga
const { isLoading, loadData } = useLoadingEffect();

const nombre = ref('');

onMounted(() => {
  const usuarioGuardado = localStorage.getItem('usuario');
  if (usuarioGuardado) {
    const usuario = JSON.parse(usuarioGuardado);
    nombre.value = usuario.name || 'Usuario';
  }
});

/* Inicializa router para redirigir */
// const router = useRouter()

/* Obtiene datos y funciones desde composables */
const { showNavbar, handleScroll } = useNavbarScroll()

/* Redirige al login al hacer clic */
// function goToLogin() {
//   router.push('/login')
// }
</script>
<style scoped>
/* Clase para mostrar/ocultar navbar con animación */
.navbar {
  position: fixed;
  top: 0;
  width: 100%;
  transform: translateY(-100%);
  opacity: 0;
  transition: transform 0.3s ease, opacity 0.3s ease;
  z-index: 999;
}

.navbar-visible {
  transform: translateY(0);
  opacity: 1;
}


/* Clase extra para posibles contenidos */
.contenido {
  padding: 20px;
}
</style>
