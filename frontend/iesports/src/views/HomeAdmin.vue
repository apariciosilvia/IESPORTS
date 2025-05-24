<template>
<ion-content fullscreen @ionScroll="handleScroll" :scroll-events="true">
  <!-- Navbar visible siempre -->
  <Navbar :class="['navbar', { 'navbar-visible': showNav }]" />
  
  <AdminTournaments />
 
</ion-content>
</template>

<script setup lang="ts">
import { IonContent } from '@ionic/vue';

import Navbar from '@/components/layout/Navbar.vue';
import AdminTournaments from '@/components/layout/AdminTournaments.vue';

import { useNavbarVisibility } from '@/composables/useNavbarVisibility';

// 3. Extraemos el control de la navbar
const { showNav, handleScroll } = useNavbarVisibility();
</script>
