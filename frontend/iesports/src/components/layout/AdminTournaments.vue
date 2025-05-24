<!-- src/components/CreateTournaments.vue -->
<template>
  <ion-content fullscreen :scroll-events="true" style="--background: #F5F5F5;">
  <section class="tournaments">
    <div class="header">
      <h2 class="tittle">Torneos</h2>
      <ion-button class="new-btn">
        <span class="material-symbols-outlined">add_circle</span>
        Nuevo Torneo
      </ion-button>
    </div>
    <div class="table">
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
        <tr v-for="t in tournamentsAdminDTO" :key="t.tournament.id">
          <td>{{ t.tournament.name }}</td>
          <td>{{ t.tournament.date }}</td>
          <td>{{ t.tournament.state }}</td>
          <td>
            <p v-if="t.teams.length === 0">Sin equipos</p>
            <ion-select
              v-else
              interface="popover"
              :interface-options="{ cssClass: 'no-border-popover' }"
              ok-text="Cerrar"
              :selectedText="t.teams[0].name"
              class="list-teams"
            >
              <ion-select-option
                v-for="team in t.teams"
                :key="team.id"
                :value="team.id"
              >
                {{ team.name }}
              </ion-select-option>
            </ion-select>
          </td>
          <td>{{ t.tournament.sport.name }}</td>
          <td>

          </td>
        </tr>
      </tbody>
    </table>
    </div>
  </section>
  </ion-content>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { IonSelect, IonSelectOption, IonContent } from '@ionic/vue';

import type { TournamentAdminDTO } from '@/model/tournamentAdminDTO';

import { getTeamsByTournamentId } from '@/services/tournamentService';

// 1) Array reactivo que usa tu DTO
const tournamentsAdminDTO = ref<TournamentAdminDTO[]>([]);

// 2) Al montar el componente, pedimos los datos
onMounted(async () => {
  try {
    const data = await getTeamsByTournamentId();
    tournamentsAdminDTO.value = data;
    console.log('ADMIN TORNEOS',tournamentsAdminDTO.value);
  } catch (error) {
    console.error('Error cargando torneos:', error);
  }
});



</script>

<style scoped>
.tournaments {
  margin: 4.5rem;
  padding: 2.5rem;
  margin-top: 8%;
  background-color: #ffffff;
  border-radius: 10px;
  border: solid 1px #D9D8D8;

}
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1rem;
}

.header h2 {
  font-size: 2rem;
  font-weight: bolder;
  color: #0a2540;
}

.new-btn {
  --background: #e22f28;
  color: #fff;
  font-weight: bolder;
}

.new-btn .material-symbols-outlined{
  margin-right: 5px;
}

.list-teams{
  --background: #e22f28;
  --border-radius: 5px;
  color: white;
  font-weight: bolder;
  --padding-start: 1rem;  
  --padding-end: 1rem;
}
.list-teams::part(icon) {
  color: white;
}


/* Habilita scroll horizontal cuando no quepa */
.table {
  overflow-x: auto;
  -webkit-overflow-scrolling: touch; /* suave en iOS */
}

/* Asegura que la tabla use todo el ancho disponible */
.table .tournaments-table {
  width: 100%;
  min-width: 600px; /* ajusta según tu número de columnas */
}


.tournaments-table {
  border-collapse: collapse; /* ya lo tienes */
  width: 100%;
}

/* 1) Eliminamos cualquier borde en los lados de celdas */
.tournaments-table th,
.tournaments-table td {
  padding: 1rem 3rem;
  vertical-align: left;
}

.tournaments-table tbody  {
  border: 1px solid #ddd;
}

.tournaments-table tbody tr {
 
  border-bottom: 1px solid #ddd;
}

/* 5) Hover suave en filas */
.tournaments-table tbody tr:hover {
  background-color: #fafafa;
}

/* 3) Para la primera fila del body, igualar estilo con el thead si quieres */
.tournaments-table tbody tr:first-child td {
  border-top: 1px solid #ddd;
}

/* 1) Estilo del encabezado */
.tournaments-table thead {
  background-color: #f5f5f5; /* fondo gris */
}

.tournaments-table thead  {
  border: 1px solid #ddd;    /* borde alrededor de cada celda */
}

</style>
