<template>
  <section class="teams">
     <!-- Alerts -->
    <div class="popup-container" v-if="showPopup">
      <!-- SUCCESS -->
      <div class="popup success-popup" v-if="popupType === 'success'">
        <div class="popup-icon success-icon">
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" class="success-svg">
            <path
              fill-rule="evenodd"
              d="m12 1c-6.075 0-11 4.925-11 11s4.925 11 11 11 11-4.925 11-11-4.925-11-11-11zm4.768 9.14c.0878-.1004.1546-.21726.1966-.34383.0419-.12657.0581-.26026.0477-.39319-.0105-.13293-.0475-.26242-.1087-.38085-.0613-.11844-.1456-.22342-.2481-.30879-.1024-.08536-.2209-.14938-.3484-.18828s-.2616-.0519-.3942-.03823c-.1327.01366-.2612.05372-.3782.1178-.1169.06409-.2198.15091-.3027.25537l-4.3 5.159-2.225-2.226c-.1886-.1822-.4412-.283-.7034-.2807s-.51301.1075-.69842.2929-.29058.4362-.29285.6984c-.00228.2622.09851.5148.28067.7034l3 3c.0983.0982.2159.1748.3454.2251.1295.0502.2681.0729.4069.0665.1387-.0063.2747-.0414.3991-.1032.1244-.0617.2347-.1487.3236-.2554z"
              clip-rule="evenodd"
            ></path>
          </svg>
        </div>
        <div class="success-message" v-html="popupMessage"></div>
        <div class="popup-icon close-icon" @click="closePopup">
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" class="close-svg">
            <path
              d="m15.8333 5.34166-1.175-1.175-4.6583 4.65834-4.65833-4.65834-1.175 1.175 4.65833 4.65834-4.65833 4.6583 1.175 1.175 4.65833-4.6583 4.6583 4.6583 1.175-1.175-4.6583-4.6583z"
              class="close-path"
            ></path>
          </svg>
        </div>
      </div>

      <!-- ALERT -->
      <div class="popup alert-popup" v-if="popupType === 'alert'">
        <div class="popup-icon alert-icon">
          <svg class="alert-svg" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" aria-hidden="true">
            <path
              fill-rule="evenodd"
              d="M8.257 3.099c.765-1.36 2.722-1.36 3.486 0l5.58 9.92c.75 1.334-.213 2.98-1.742 2.98H4.42c-1.53 0-2.493-1.646-1.743-2.98l5.58-9.92zM11 13a1 1 0 11-2 0 1 1 0 012 0zm-1-8a1 1 0 00-1 1v3a1 1 0 002 0V6a1 1 0 00-1-1z"
              clip-rule="evenodd"
            ></path>
          </svg>
        </div>
        <div class="alert-message" v-html="popupMessage"></div>
        <div class="popup-icon close-icon" @click="closePopup">
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" class="close-svg">
            <path
              d="m15.8333 5.34166-1.175-1.175-4.6583 4.65834-4.65833-4.65834-1.175 1.175 4.65833 4.65834-4.65833 4.6583 1.175 1.175 4.65833-4.6583 4.6583 4.6583 1.175-1.175-4.6583-4.6583z"
              class="close-path"
            ></path>
          </svg>
        </div>
      </div>

      <!-- ERROR -->
      <div class="popup error-popup" v-if="popupType === 'error'">
        <div class="popup-icon error-icon">
          <svg class="error-svg" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" aria-hidden="true">
            <path
              fill-rule="evenodd"
              d="M10 18a8 8 0 100-16 8 8 0 000 16zM8.707 7.293a1 1 0 00-1.414 1.414L8.586 10l-1.293 1.293a1 1 0 101.414 1.414L10 11.414l1.293 1.293a1 1 0 001.414-1.414L11.414 10l1.293-1.293a1 1 0 00-1.414-1.414L10 8.586 8.707 7.293z"
              clip-rule="evenodd"
            ></path>
          </svg>
        </div>
        <div class="error-message" v-html="popupMessage"></div>
        <div class="popup-icon close-icon" @click="closePopup">
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" class="close-svg">
            <path
              d="m15.8333 5.34166-1.175-1.175-4.6583 4.65834-4.65833-4.65834-1.175 1.175 4.65833 4.65834-4.65833 4.6583 1.175 1.175 4.65833-4.6583 4.6583 4.6583 1.175-1.175-4.6583-4.6583z"
              class="close-path"
            ></path>
          </svg>
        </div>
      </div>

      <!-- INFO -->
      <div class="popup info-popup" v-if="popupType === 'info'">
        <div class="popup-icon info-icon">
          <svg aria-hidden="true" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg" class="info-svg">
            <path
              clip-rule="evenodd"
              d="M18 10a8 8 0 11-16 0 8 8 0 0116 0zm-7-4a1 1 0 11-2 0 1 1 0 012 0zM9 9a1 1 0 000 2v3a1 1 0 001 1h1a1 1 0 100-2v-3a1 1 0 00-1-1H9z"
              fill-rule="evenodd"
            ></path>
          </svg>
        </div>
        <div class="info-message" v-html="popupMessage"></div>
        <div class="popup-icon close-icon" @click="closePopup">
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" class="close-svg">
            <path
              d="m15.8333 5.34166-1.175-1.175-4.6583 4.65834-4.65833-4.65834-1.175 1.175 4.65833 4.65834-4.65833 4.6583 1.175 1.175 4.65833-4.6583 4.6583 4.6583 1.175-1.175-4.6583-4.6583z"
              class="close-path"
            ></path>
          </svg>
        </div>
      </div>
    </div>


    <!-- Confirmación de eliminación -->
    <div v-if="showConfirm" class="confirm-overlay">
      <div class="card">
        <div class="card-content">
          <p class="card-heading">¿Eliminar torneo?</p>
          <p class="card-description">Esta acción no se puede deshacer.</p>
          
        </div>
        <div class="card-button-wrapper">
          <button class="card-button secondary" @click="cancelDelete">Cancelar</button>
          <button class="card-button primary" @click="confirmDelete">Eliminar</button>
        </div>
        <button class="exit-button" @click="cancelDelete">
          <svg height="20px" viewBox="0 0 384 512">
            <path
              d="M342.6 150.6c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L192 210.7 86.6 105.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3L146.7 256 41.4 361.4c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0L192 301.3 297.4 406.6c12.5 12.5 32.8 12.5 45.3 0s12.5-32.8 0-45.3L237.3 256 342.6 150.6z"
            ></path>
          </svg>
        </button>
      </div>
    </div>

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
            <th class="col-name">Nombre</th>
            <th class="col-members">Miembros</th>
            <th class="col-sports">Deportes</th>
            <th class="col-actions">Acciones</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="info in teamsInfo" :key="info.team.id">
            <td class="col-name">{{ info.team.name }}</td>
            <td class="col-members">
              <div v-if="(info.team.players ?? []).length === 0">Sin miembros</div>
              <ion-select
                v-else
                interface="popover"
                :selectedText="info.team.players?.[0]?.name || ''"
                class="list-teams"
              >
                <ion-select-option
                  v-for="p in info.team.players ?? []"
                  :key="p.id"
                  :value="p.id"
                >
                  {{ p.name }}
                </ion-select-option>
              </ion-select>
            </td>
            <td class="col-sports">
              <div v-if="info.sports.length === 0">Sin deportes</div>
              <ion-select
                v-else
                interface="popover"
                :selectedText="info.sports[0]?.name || ''"
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
            <td class="col-actions">
              <div class="actions">
                <button
                  type="button"
                  class="action-btn edit-btn"
                  @click="openEditModal(info.team.id)"
                >
                  <span class="material-symbols-outlined edit-icon">edit_square</span>
                </button>
                <button 
                  type="button" 
                  class="action-btn delete-btn"
                  @click="requestDelete(info.team.id)">
                  <span class="material-symbols-outlined delete-icon">delete</span>
                </button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <!-- Modal principal --> 
    <ion-modal
      :is-open="isModalOpen"
      :key="`${modalMode}-${teamToEdit ?? 'new'}-${isModalOpen}`"
      backdrop-dismiss="false"
      swipe-to-close="false"
    >
      <component
        :is="modalMode === 'add' ? AdminAddTeams : AdminEditTeams"
        @close="closeModal"
        :idTeam="teamToEdit"
      />
    </ion-modal>

  </section>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { IonSelect, IonSelectOption, IonButton } from '@ionic/vue';
import AdminAddTeams from '@/components/admin/AdminAddTeams.vue';
import AdminEditTeams from '@/components/admin/AdminEditTeams.vue';
import { deleteTeam, getTeamsInfo } from '@/services/teamService';
import type { TeamInfoDTO } from '@/model/dto/teamInfoDTO';

const modalMode = ref<'add' | 'edit'>('add');
const teamToEdit = ref<number | null>(null);
const isModalOpen = ref(false);
const teamsInfo = ref<TeamInfoDTO[]>([]);

const showConfirm = ref(false);
const pendingDeleteId = ref<number | null>(null);

// Popup state
const showPopup = ref(false);
const popupType = ref<'success' | 'alert' | 'error' | 'info'>('info');
const popupMessage = ref('');

function openPopup(type: 'success' | 'alert' | 'error' | 'info', message: string) {
  popupType.value = type;
  popupMessage.value = message.replace(/\n/g, '<br>');
  showPopup.value = true;
  setTimeout(() => {
    showPopup.value = false;
    popupMessage.value = '';
  }, 3000);
}

function openAddModal() {
  modalMode.value = 'add';
  teamToEdit.value = null;
  isModalOpen.value = true;
}

function openEditModal(teamId: number) {
  modalMode.value = 'edit';
  teamToEdit.value = teamId;
  isModalOpen.value = true;
}

function closeModal() {
  document.activeElement instanceof HTMLElement && document.activeElement.blur();
  isModalOpen.value = false;
}

function requestDelete(teamId: number){
  pendingDeleteId.value = teamId;
  showConfirm.value = true;
}

async function confirmDelete() {
  if (pendingDeleteId.value !== null) {

    try {
      await deleteTeam(pendingDeleteId.value);
      teamsInfo.value = teamsInfo.value.filter(
        (t) => t.team.id !== pendingDeleteId.value
      );
      openPopup('success', 'Equipo eliminado correctamente');
    } catch (error: any) {
      const status = error.response?.status;
      const data   = error.response?.data;
      let msg = '';

      if (status >= 400 && status <= 409) {
        msg = Object.values(data ?? {})[0] || error.message;
      } else {
        msg = 'Error inesperado';
      }

      openPopup('error', msg);
      
    } finally {
      showConfirm.value = false;
      pendingDeleteId.value = null;
    }
  }
  showConfirm.value = false;
  pendingDeleteId.value = null;
}

function cancelDelete() {
  showConfirm.value = false;
  pendingDeleteId.value = null;
}

function closePopup() {
  showPopup.value = false;
  popupMessage.value = '';
}

onMounted(async () => {
  try {
    teamsInfo.value = await getTeamsInfo();
  } catch (e) {
    console.error('Error al cargar equipos:', e);
  }
});


</script>


<style scoped>
.confirm-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.4);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1000;
}

.card {
  width: 300px;
  background: #fff;
  border-radius: 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 5px;
  padding: 30px;
  position: relative;
  box-shadow: 20px 20px 30px rgba(0, 0, 0, 0.068);
}

.card-content {
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 5px;
}

.card-heading {
  font-size: 20px;
  font-weight: 700;
  color: rgb(27, 27, 27);
}

.card-description {
  font-weight: 100;
  color: rgb(102, 102, 102);
}

.card-button-wrapper {
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
}

.card-button {
  width: 50%;
  height: 35px;
  border-radius: 10px;
  border: none;
  cursor: pointer;
  font-weight: 600;
}

.primary {
  background-color: var(--orange-secundary-color);
  color: white;
}
.primary:hover {
  background-color: rgb(255, 73, 66);
}

.secondary {
  background-color: var(--blue-primary-color);
}
.secondary:hover {
  background-color: #044b61;
}

.exit-button {
  display: flex;
  align-items: center;
  justify-content: center;
  border: none;
  background-color: transparent;
  position: absolute;
  top: 20px;
  right: 20px;
  cursor: pointer;
}
.exit-button svg {
  fill: rgb(175, 175, 175);
}
.exit-button:hover svg {
  fill: black;
}
.popup-container {
  position: fixed;
  top: 1rem;
  left: 50%;
  transform: translateX(-50%);
  z-index: 9999;
  display: flex;
  flex-direction: column;
  align-items: center;
}

/* COMMON STYLES*/
.popup {
  margin: 0.5rem 0;
  box-shadow: 4px 4px 10px -10px rgba(0, 0, 0, 1);
  width: 340px;
  justify-content: space-around;
  align-items: center;
  display: flex;
  border-radius: 4px;
  padding: 5px 0;
  font-weight: 300;
}
.popup svg {
  width: 1.25rem;
  height: 1.25rem;
}
.popup-icon svg {
  margin: 5px;
  display: flex;
  align-items: center;
}
.close-icon {
  margin-left: auto;
}
.close-svg {
  cursor: pointer;
}
.close-path {
  fill: #ffffff;
}

/* SUCCESS */
.success-popup {
  background-color: #01e226;
  border: solid 1px #01e226;
  font-weight: bolder;
  font-size: 1rem;
}
.success-icon path {
  fill: #ffffff;
}
.success-message {
  color: #ffffff;
}

/* ALERT */
.alert-popup {
  background-color: #ebae08;
  border: solid 1px #ebae08;
  font-weight: bolder;
  font-size: 1rem;
}
.alert-icon path {
  fill: #ffffff;
}
.alert-message {
  color: #ffffff;
}

/* ERROR */
.error-popup {
  background-color: #d11313;
  border: solid 1px #d11313;
  font-weight: bolder;
  font-size: 1rem;
}
.error-icon path {
  fill: #ffffff;
}
.error-message {
  color: #ffffff;
}

/* INFO */
.info-popup {
  background-color: #3563e4;
  border: solid 1px #1e3a8a;
}
.info-icon path {
  fill: #ffffff;
}
.info-message {
  color: #ffffff;
}

.teams {
  margin: 4rem 2rem;
  padding: 2.5rem;
  margin-top: 8%;
  background-color: #ffffff;
  border-radius: 10px;
  border: solid 1px #D9D8D8;
  display: flex;
  flex-direction: column;
  height: calc(100vh - 9rem);
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
  --height: 95vw;
  --max-height: 300vh;
}

ion-modal::part(content) {
  height: 95vh !important;
  max-height: 75vh !important;
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
