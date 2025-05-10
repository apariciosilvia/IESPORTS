<template>
  <!-- Navbar solo visible cuando se hace scroll suficiente -->
   <ion-page>
    <!-- Contenido principal de la página -->
    <ion-content fullscreen @ionScroll="handleScroll" :scroll-events="true">
      <Navbar :class="['navbar', { 'navbar-visible': showNav }]" />
      </ion-content>
   </ion-page>
</template>
<script setup lang="ts">
import { IonPage, IonContent } from '@ionic/vue';
import Navbar from '@/components/layout/Navbar.vue';
import { useNavbarVisibility } from '@/composables/useNavbarVisibility';

// Ahora extraemos sólo lo que necesitamos:
const { showNav, handleScroll } = useNavbarVisibility();
</script>