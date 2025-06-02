<template>
  <section class="teams">
    <div class="header">
      <h2 class="tittle">Equipos</h2>
      <ion-button class="new-btn" @click="openAddModal">
        <span class="material-symbols-outlined">add_circle</span>
        Nuevo Equipo
      </ion-button>
    </div>
    <div class="table">
      <table class="teams-table">
        <thead>
          <tr>
            <th class="col-name">name</th>
            <th class="col-members">Miembros</th>
            <th class="col-sports">Deportes</th>
            <th class="col-actions">Acciones</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="info in teamsInfo" :key="info.team.id">
            <td class="col-name">{{ info.team.name }}</td>

            <!-- Miembros -->
            <td class="col-members">
              <div v-if="info.team.players?.length === 0">Sin miembros</div>
              <ion-select
                v-else
                interface="popover"
                :selectedText="info.team.players && info.team.players.length > 0 ? info.team.players[0].name : ''"
                class="list-teams"
              >
                <ion-select-option
                  v-for="p in info.team.players"
                  :key="p.id"
                  :value="p.id"
                >
                  {{ p.name }}
                </ion-select-option>
              </ion-select>
            </td>

            <!-- Deportes -->
            <td class="col-sports">
              <div v-if="info.sports.length === 0">Sin deportes</div>
              <ion-select
                v-else
                interface="popover"
                :selectedText="info.sports[0].name"
                class="list-teams"
              >
                <ion-select-option
                  v-for="s in info.sports"
                  :key="s.id"
                  :value="s.id"
                >
                  {{ s.name }}
                </ion-select-option>
              </ion-select>
            </td>

            <!-- Acciones -->
            <td class="col-actions">
              <div class="actions">
                <button
                  type="button"
                  class="action-btn edit-btn"
                  @click="openEditModal(info.team.id)"
                >
                  <span class="material-symbols-outlined edit-icon">edit_square</span>
                </button>
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
        :is="modalMode === 'add' ? AdminAddTeams : AdminEditTeams"
        @close="closeModal"
        :tournamentId="teamToEdit"
      />
    </ion-modal>
  </section>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { IonSelect, IonSelectOption, IonButton } from '@ionic/vue';
import AdminAddTeams from '@/components/admin/AdminAddTeams.vue';
import AdminEditTeams from '@/components/admin/AdminEditTeams.vue';
import { getTeamsInfo } from '@/services/teamService';
import type { TeamInfoDTO } from '@/model/dto/teamInfoDTO';

const modalMode = ref<'add' | 'edit'>('add');

function openAddModal() {
  modalMode.value = 'add';
  isModalOpen.value = true;
}

const teamToEdit = ref<number | null>(null);

function openEditModal(tournamentId: number) {
  modalMode.value = 'edit';
  teamToEdit.value = tournamentId;
  isModalOpen.value = true;
}

const isModalOpen = ref(false);

const teamsInfo = ref<TeamInfoDTO[]>([]);

onMounted(async () => {
  try {
    teamsInfo.value = await getTeamsInfo();
  } catch (e) {
    console.error('Error al cargar equipos:', e);
  }
});

function closeModal() {
  document.activeElement instanceof HTMLElement && document.activeElement.blur();
  isModalOpen.value = false;
}
</script>

<style scoped>
.teams {
  margin: 4rem 2rem;
  padding: 2.5rem;
  margin-top: 8%;
  background-color: #ffffff;
  border-radius: 10px;
  border: solid 1px #D9D8D8;
  display: flex;
  flex-direction: column;
  height: calc(100vh - 7rem);
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
  flex: 1;
  overflow-y: auto;
  overflow-x: auto;
  -webkit-overflow-scrolling: touch;
}
.table .teams-table {
  width: 100%;
  min-width: 600px;
}
.teams-table {
  border-collapse: collapse;
  width: 100%;
}
.teams-table th,
.teams-table td {
  padding: 1rem 4rem;
  vertical-align: left;
}
.teams-table tbody {
  border: 1px solid #ddd;
}
.teams-table tbody tr {
  border-bottom: 1px solid #ddd;
}
.teams-table tbody tr:hover {
  background-color: #fafafa;
}
.teams-table tbody tr:first-child td {
  border-top: 1px solid #ddd;
}
.teams-table thead {
  background-color: #f5f5f5;
}
.teams-table thead {
  border: 1px solid #ddd;
}

.col-name {
  min-width: 360px;
  width: 30%;
}

.col-members {
  width: 150px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.col-sports {
  width: 150px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.col-actions {
  width: 150px;
  text-align: center;
}

.actions {
  display: inline-flex;
  justify-content: center;
  align-items: center;
  background-color: #0a2540;
  border-radius: 5px;
  padding: 10px 15px;
  gap: 20%;
  width: 110px;
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

.action-btn:focus {
  outline: none;
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
  --width: 95vw;
  --max-width: 1200px;
  --height: auto;
  --max-height: 400vh;
}

ion-modal::part(content) {
  height: 95vh !important;
  max-height: 95vh !important;
  width: 95vw;
  max-width: 1200px;
  overflow: hidden;
  display: flex;
  flex-direction: column;
}

ion-modal::part(backdrop) {
  backdrop-filter: blur(1px);
  background-color: rgba(0, 0, 0, 0.3);
}

@media screen and (max-width: 1024px) {
  ion-modal {
    --width: 95vw;
    --height: 90vh;
    --max-width: 95vw;
    --max-height: 90vh;
  }

  .teams {
    margin: 1rem;
    padding: 1rem;
    margin-top: 5rem;
    height: auto;
  }

  .teams-table th,
  .teams-table td {
    padding: 0.5rem 1rem;
  }

  .header {
    flex-direction: column;
    align-items: flex-start;
    gap: 1rem;
  }

  .new-btn {
    align-self: flex-end;
    width: 100%;
    text-align: right;
  }

  .table {
    overflow-x: auto;
  }
}
</style>
