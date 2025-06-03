<template>
  <!-- Navbar solo visible cuando se hace scroll suficiente -->
   <ion-page>
    <!-- Contenido principal de la página -->
    <ion-content fullscreen @ionScroll="handleScroll" :scroll-events="true">
      <Navbar :class="['navbar', { 'navbar-visible': showNav }]" />

      <Calendar :matches="matches" />
      </ion-content>
   </ion-page>
</template>
<script setup lang="ts">
import { IonPage, IonContent } from '@ionic/vue';
import { onMounted, ref } from 'vue';
import Navbar from '@/components/layout/Navbar.vue';
import Calendar from "@/components/ui/Calendar.vue";
import { useNavbarVisibility } from '@/composables/useNavbarVisibility';

import type { Match } from '@/model/match';


import { getMatches } from '@/services/matchService';

const matches = ref<Match[]>([]);

onMounted(async () => {
  try {
    const data = await getMatches();
    console.log('Data recibida de getMatches():', data);
    matches.value = data;
  } catch (err) {
    console.error('Error al obtener matches:', err);
  }
});

console.log('matches calendar' , matches.value);








// Ahora extraemos sólo lo que necesitamos:
const { showNav, handleScroll } = useNavbarVisibility();
</script>