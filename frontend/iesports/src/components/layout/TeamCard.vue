<template>
  <ion-item class="team-card">
    <ion-label>
      <!-- Mostramos el nombre del equipo -->
      {{ team.name }}
    </ion-label>
  </ion-item>
</template>

<script setup lang="ts">
import { defineProps } from 'vue';
import { IonItem, IonLabel } from '@ionic/vue';
import type { Team } from '@/model/team';

const props = defineProps<{ 
  team: Team 
}>();

console.log('TeamCard props:', props);


</script>

<style scoped>
.team-card {
  background: #fff;
  padding: 0.75rem;
  border-radius: 8px;
  text-align: center;
  box-shadow: 0 1px 4px rgba(0,0,0,0.1);
}
</style>
