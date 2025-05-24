<!-- src/components/CreateTournaments.vue -->
<template>
  <section class="tournaments">
    <div class="header">
      <h2>Torneos</h2>
      <ion-button class="new-btn" @click="onNew">Nuevo Torneo</ion-button>
    </div>
    <table class="tournaments-table">
      <thead>
        <tr>
          <th>Nombre</th>
          <th>Año</th>
          <th>Estado</th>
          <th>Equipos</th>
          <th>Deporte</th>
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="t in tournaments" :key="t.id">
          <td>{{ t.name }}</td>
          <td>{{ t.year }}</td>
          <td>{{ t.status }}</td>
          <td>
            <ion-button fill="solid" size="small">
              {{ t.teamLabel }} <ion-icon name="chevron-down-outline"/>
            </ion-button>
          </td>
          <td>{{ t.sport }}</td>
          <td>
            <ion-button fill="clear" size="small" @click="edit(t.id)">
              <ion-icon name="create-outline"/>
            </ion-button>
            <ion-button fill="clear" size="small" @click="remove(t.id)">
              <ion-icon name="trash-outline"/>
            </ion-button>
          </td>
        </tr>
      </tbody>
    </table>
  </section>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { IonButton, IonIcon } from '@ionic/vue'

interface Tournament {
  id: number
  name: string
  year: string
  status: string
  teamLabel: string
  sport: string
}

const tournaments = ref<Tournament[]>([
  { id: 1, name: 'Futbol Frontier', year: '2024/25', status: 'EN PROCESO', teamLabel: 'Equipo 1', sport: 'Fútbol' },
  
])

function onNew() {
  // Aquí abrirías el formulario de creación
}

function edit(id: number) {
  // Lógica para editar el torneo con este id
}

function remove(id: number) {
  // Lógica para eliminar el torneo con este id
}
</script>

<style scoped>
.tournaments {
  padding: 1rem;
  margin-top: 6%;
}
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1rem;
}
.new-btn {
  --background: #e22f28;
  --color: #fff;
}
.tournaments-table {
  width: 100%;
  border-collapse: collapse;
}
.tournaments-table th,
.tournaments-table td {
  padding: 0.75rem;
  border: 1px solid #ddd;
  text-align: left;
}
</style>
