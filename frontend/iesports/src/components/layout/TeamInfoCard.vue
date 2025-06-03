<template>
  <div class="card">
    <div class="card-details">
      
      <p class="text-title">{{ teamInfo.team.name }}</p>

      <p class="text-body">
        Deportes:
        <span v-for="(sport, idx) in teamInfo.sports" :key="sport.id">
          {{ sport.name }}<span v-if="idx < teamInfo.sports.length - 1">, </span>
        </span>
      </p>
    </div>

    <button class="card-button" @click="abrirModal">Ver integrantes</button>

    <IonModal :is-open="showModal" @didDismiss="cerrarModal" class="player-modal">
        <IonHeader class="modal-header">
          <IonToolbar>
            <IonTitle class="tittle">Integrantes de <b class="nameTeam">{{ teamInfo.team.name }}</b></IonTitle>
          </IonToolbar>
        </IonHeader>

        <IonContent class="modal-content ion-padding">
          <IonList class="player-item">
            <IonItem v-for="player in teamInfo.team.players" :key="player.id" class="player-item">
              <IonLabel>{{ player.name }} - {{ player.course.initials }}</IonLabel>
            </IonItem>
            <IonItem v-if="teamInfo.team.players?.length === 0" class="player-item">
              <IonLabel>No hay jugadores registrados</IonLabel>
            </IonItem>
          </IonList>
        </IonContent>
    </IonModal>
  </div>
</template>


<script setup lang="ts">
import { ref } from 'vue';
import { IonModal, IonHeader, IonToolbar, IonTitle, IonContent, IonList, IonItem, IonLabel } from '@ionic/vue';


import type { TeamInfoDTO } from '@/model/dto/teamInfoDTO';

// Desestructuramos la prop para obtener directamente `teamInfo`
const { teamInfo } = defineProps<{
  teamInfo: TeamInfoDTO;
}>();

// Estado reactivo para controlar la visibilidad del modal
const showModal = ref(false);

// Función que abre el modal
function abrirModal() {
  showModal.value = true;
}

// Función que cierra el modal
function cerrarModal() {
  showModal.value = false;
}
</script>

<style scoped>
.card {
  width: 290px;
  height: 254px; 
  border-radius: 20px;
  background: #f5f5f5;
  position: relative;
  padding: 1.8rem;
  border: 2px solid #c3c6ce;
  transition: 0.5s ease-out;
  overflow: visible;
}

.card-details {
  color: black;
  height: 100%;
  display: grid;
  place-content: center;
  gap: 0.5em;
}

.text-title {
  font-size: 1.5em;
  font-weight: bold;
  margin: 0;
  color: var(--blue-primary-color);
}

.text-body {
  color: rgb(134, 134, 134);
  margin: 0;
  font-size: 1rem;
}

.card-button {
  position: absolute;
  left: 50%;
  bottom: 0;
  transform: translate(-50%, 125%);
  width: 60%;
  border-radius: 1rem;
  border: none;
  background-color: var(--orange-secundary-color);
  color: #fff;
  font-size: 1rem;
  padding: 0.5rem 1rem;
  opacity: 0;
  transition: 0.3s ease-out;
}

.card:hover {
  border-color:var(--orange-secundary-color);
  box-shadow: 0 4px 18px rgba(0, 0, 0, 0.25);
}

.card:hover .card-button {
  transform: translate(-50%, 50%);
  opacity: 1;
}

.card-button:hover {
  background-color: rgb(223, 97, 66);
  transform: translate(-50%, 45%) scale(1.02);
}

:deep(.modal-header) {
  height: auto !important;
  padding-top: 1rem;
  padding-bottom: 0.75rem;
}

.tittle {
  white-space: normal;
  word-break: break-word;
  display: block;
  padding-left: 1rem;
  padding-right: 1rem;
  font-size: 1.1rem;
  line-height: 1.3;
  color: var(--text-color-primary);
  font-weight: 600;
}

.nameTeam {
  color: var(--orange-secundary-color);
  margin-left: 0;
  display: inline;
}

:deep(.modal-header ion-title) {
  width: 100%;
}

.player-modal{
  --border-radius: 10px;
   --width: auto;
  --max-width: 100vw;
}

:deep(.player-modal .modal-wrapper) {
  display: inline-block;
  width: auto !important;
  max-width: 100vw;
}

:deep(.player-modal .modal-page) {
  width: auto !important;
}

.player-item {
  justify-content: center;
  text-align: center;
}
</style>
