<template>

<Loader v-if="isNowLoading" />

<ion-content fullscreen @ionScroll="handleScroll" :scroll-events="true">
  <!-- Navbar visible siempre -->
  <Navbar :class="['navbar', { 'navbar-visible': showNav }]" />
  
  <AdminTournaments/>

  <AdminTeams/>

  <AdminSports/>

 
</ion-content>
</template>

<script setup lang="ts">
import { IonContent } from '@ionic/vue';

import Navbar from '@/components/layout/Navbar.vue';
import AdminTournaments from '@/components/admin/AdminTournaments.vue';
import AdminTeams from '@/components/admin/AdminTeams.vue';
import AdminSports from '@/components/admin/AdminSports.vue';

import { useNavbarVisibility } from '@/composables/useNavbarVisibility';

import Loader from '@/components/ui/Loader.vue';
import { useLoadingEffect } from '@/composables/useLoadingEffect';
const { isNowLoading } = useLoadingEffect();

// 3. Extraemos el control de la navbar
const { showNav, handleScroll } = useNavbarVisibility();
</script>
<style>

</style>