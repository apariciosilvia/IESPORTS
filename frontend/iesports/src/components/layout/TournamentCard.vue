<template>
  <ion-item class="tournament-item">
    <ion-label>
      <!-- Mostramos nombre, fecha, estado y deporte -->
      {{ tournament.name }} ({{ tournament.date }}) — Estado: {{ stateText }} — Deporte: {{ sportName }}    </ion-label>
  </ion-item>
</template>

<script setup lang="ts">
import { defineProps, computed } from 'vue';

import type { Tournament } from '@/model/tournament';


const props = defineProps<{
  tournament: Tournament;
  sportName: string | undefined;
}>();

// Computed para convertir el código numérico de estado a texto legible:
const stateText = computed(() => {
  switch (props.tournament.state) {
    case 0: return 'Pendiente';
    case 1: return 'En curso';
    case 2: return 'Finalizado';
    default: return 'Desconocido';
  }
});
</script>

<style scoped>
.tournament-item {
  margin-bottom: 0.5rem;
  --background: #f9f9f9;
  border-radius: 6px;
  padding: 0.75rem 1rem;
}
</style>
