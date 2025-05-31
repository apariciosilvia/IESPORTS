<!-- src/components/CreateTournaments.vue --> 
<template>
  <ion-content fullscreen :scroll-events="true" style="--background: #F5F5F5;">
    <section class="tournaments">
      <div class="header">
        <h2 class="tittle">Torneos</h2>
        <ion-button class="new-btn" @click="openAddModal">
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
                <div class="actions">
                  <!-- Botón de editar, que enviará solo el ID del torneo -->
                  <button type="button" class="action-btn edit-btn" @click="openEditModal(t.tournament.id)">
                    <span class="material-symbols-outlined edit-icon">edit_square</span>
                  </button>
                  <!-- Botón de eliminar -->
                  <button type="button" class="action-btn delete-btn">
                    <span class="material-symbols-outlined delete-icon">delete</span>
                  </button>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <!-- Modal principal -->
      <ion-modal :is-open="isModalOpen" backdrop-dismiss="false" swipe-to-close="false">
        <component
          :is="modalMode === 'add' ? AdminAddTournaments : AdminEditTournaments"
          @close="closeModal"
          :tournamentId="tournamentToEdit"
        />
      </ion-modal>
    </section>
  </ion-content>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { IonSelect, IonSelectOption, IonContent, IonModal, IonButton } from '@ionic/vue';
import AdminAddTournaments from '@/components/admin/AdminAddTournaments.vue';
import AdminEditTournaments from '@/components/admin/AdminEditTournaments.vue';

import type { TournamentAdminDTO } from '@/model/tournamentAdminDTO';

import { getTeamsByTournamentId } from '@/services/tournamentService';

const modalMode = ref<'add' | 'edit'>('add');

function openAddModal() {
  modalMode.value = 'add';
  isModalOpen.value = true;
}

const tournamentToEdit = ref<number | null>(null); 

function openEditModal(tournamentId: number) {
  modalMode.value = 'edit';
  tournamentToEdit.value = tournamentId;
  isModalOpen.value = true;
}

const isModalOpen = ref(false); // controla visibilidad del popup

// 1) Array reactivo que usa tu DTO
const tournamentsAdminDTO = ref<TournamentAdminDTO[]>([]);

// 2) Al montar el componente, pedimos los datos
onMounted(async () => {
  try {
    const data = await getTeamsByTournamentId();
    tournamentsAdminDTO.value = data;
    console.log('ADMIN TORNEOS', tournamentsAdminDTO.value);
  } catch (error) {
    console.error('Error cargando torneos:', error);
  }
});

function closeModal() {
  document.activeElement instanceof HTMLElement && document.activeElement.blur();
  isModalOpen.value = false;
}
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
.new-btn .material-symbols-outlined {
  margin-right: 5px;
}
.list-teams {
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
.table {
  overflow-x: auto;
  -webkit-overflow-scrolling: touch;
}
.table .tournaments-table {
  width: 100%;
  min-width: 600px;
}
.tournaments-table {
  border-collapse: collapse;
  width: 100%;
}
.tournaments-table th,
.tournaments-table td {
  padding: 1rem 3rem;
  vertical-align: left;
}
.tournaments-table tbody {
  border: 1px solid #ddd;
}
.tournaments-table tbody tr {
  border-bottom: 1px solid #ddd;
}
.tournaments-table tbody tr:hover {
  background-color: #fafafa;
}
.tournaments-table tbody tr:first-child td {
  border-top: 1px solid #ddd;
}
.tournaments-table thead {
  background-color: #f5f5f5;
}
.tournaments-table thead {
  border: 1px solid #ddd;
}
.actions {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #0a2540;
  border-radius: 5px;
  padding: 10px 2px;
  max-width: 110px;
  gap: 20%;
}
.action-btn {
  background: none;
  border: none;
  padding: 0;
  margin: 0;
  cursor: pointer;
}
.action-btn .material-symbols-outlined {
  font-weight: 700;
}
.actions button:last-child {
  border-left: 1px solid rgba(255, 255, 255, 0.466);
  padding-left: 15%;
}
.edit-icon {
  color: white;
}
.delete-icon {
  color: #e22f28;
}
ion-modal {
  --border-radius: 10px;
  --backdrop-opacity: 0.6;
  --width: 100%;
  --max-width: 1300px;
  --height: 700px;
}
ion-modal::part(backdrop) {
  backdrop-filter: blur(1px);
  background-color: rgba(0, 0, 0, 0.3);
}
</style>
