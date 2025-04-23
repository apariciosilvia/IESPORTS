<template>
  <ion-app :class="{ 'mobile-wrapper': !isMobile }">
    <router-view></router-view>
  </ion-app>
</template>

<script setup lang="ts">
import { IonApp, isPlatform } from '@ionic/vue'
import { computed } from 'vue'

const isMobile = isPlatform('mobile')
</script>
