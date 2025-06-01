<template>
  <!-- Cabecera del modal -->
  <ion-header>
    <ion-toolbar class="white-header">
      <ion-title>EDITAR TORNEO</ion-title>
      <ion-buttons slot="end">
        <ion-button @click="$emit('close')">
          Cerrar
        </ion-button>
      </ion-buttons>
    </ion-toolbar>
  </ion-header>
  <ion-content class="modal-body">
    <div class="modal-grid">
      <div class="row">
        <!-- Columna Izquierda (40%) -->
        <div class="column-left">
          <ion-input
            label="Nombre"
            label-placement="floating"
            fill="outline"
            placeholder="Escribe un nombre"
            class="input-name"
            v-model="tournamentName"
            :readonly="readOnlyTournament"
          />
          <ion-list class="sports">
            <ion-item class="clean-select" lines="none">
              <ion-select
                interface="popover"
                placeholder="Selecciona un deporte"
                class="list-sports"
                v-model="selectedSportId"
                @click="preventSelect"
                :class="{ 'readonly-select': readOnlyTournament }"
              >
                <ion-select-option
                  v-for="s in sports"
                  :key="s.id"
                  :value="s.id"
                >
                  {{ s.name }}
                </ion-select-option>
              </ion-select>
            </ion-item>
          </ion-list>

          <div class="radio-item">
            <h5>Número de equipos</h5>
            <div class="glass-radio-group">
              <input
                type="radio"
                :disabled="true"
                name="teams"
                id="glass-silver"
                value="4"
                v-model="selectedNumberTeams"
              />
              <label for="glass-silver">4</label>
              <input
                type="radio"
                :disabled="true"
                name="teams"
                id="glass-gold"
                value="8"
                v-model="selectedNumberTeams"
              />
              <label for="glass-gold">8</label>
              <input
                type="radio"
                :disabled="true"
                name="teams"
                id="glass-platinum"
                value="16"
                v-model="selectedNumberTeams"
              />
              <label for="glass-platinum">16</label>
              <div class="glass-glider"></div>
            </div>
          </div>
        </div>

        <!-- Columna Derecha (60%) -->
        <div class="column-right">
          <div class="team-selector">
            <div class="header">
              <h2>PARTIDOS JUGADOS</h2>
            </div>
          </div>
        </div>
      </div>

      <!-- Tabla de emparejamientos -->
      <div class="row">
        <div class="colum-down">
          <div class="match-header">
            <span>Equipo 1</span>
            <span>Equipo 2</span>
            <span>Fecha partido (opcional)</span>
            <span>Ronda</span>
          </div>
          <div class="match-rows">
            <div
              class="match-row"
              v-for="n in matchesTournament"
              :key="n.id"
            >
              <!-- Equipo 1 -->
              <div class="team-box">
                <!-- <span>{{ selectedTeams[(n - 1) * 2] || ' sin equipo '  }}</span> -->
              </div>

              <span class="vs-text">VS</span>

              <!-- Equipo 2 -->
              <div class="team-box">
                <!-- <span>{{ selectedTeams[(n - 1) * 2 + 1] || ' sin equipo ' }}</span> -->
              </div>

              <!-- Fecha -->
              <div class="date-btn">
                <!-- <input type="date" v-model="matchDates[n - 1]" name="" id="" class="date-input" /> -->
              </div>

              <!-- Ronda -->
              <span class="round-label">
                {{ selectedNumberTeams == 4
                  ? 'Semis'
                  : selectedNumberTeams == 8
                  ? 'Cuartos'
                  : selectedNumberTeams == 16
                  ? 'Octavos'
                  : '' }}
              </span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </ion-content>
 <ion-footer class="row">
      <div class="colum-down2">
        <ion-button
          expand="block"
          class="btn-clean"
          @click="resetForm"
        ><span class="material-symbols-outlined">mop</span>Limpiar</ion-button>
        <ion-button
          expand="block"
          class="btn-save"
          @click="editTournament"
        ><span class="material-symbols-outlined">save</span>Crear Torneo</ion-button>
      </div>

    </ion-footer>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import {
  IonSelect,
  IonSelectOption,
  IonContent,
  IonList,
  IonItem,
  IonInput,
  IonHeader,
  IonToolbar,
  IonButton,
  IonTitle,
  IonButtons,
  IonFooter,
} from '@ionic/vue';
import { getSports } from '@/services/sportService';
import type { Match } from '@/model/match';
import { getMatchesByTournamentId } from '@/services/matchService';
import { StateTournamentEnum } from '@/model/ENUM/StateTournamentEnum';

const readOnlyTournament = ref(true);
const error = ref<string | null>(null);
const props = defineProps<{ tournamentId: number }>();

const sports = ref<any[]>([]);
const matchesTournament = ref<Match[]>([]);
const tournamentName = ref('');
const selectedSportId = ref<number | null>(null);
const selectedNumberTeams = ref<number>(0);
const selectedTeams = ref<string[]>([]);
const matchDates = ref<(Date | null)[]>([]);

function preventSelect(e: Event) {
  if (readOnlyTournament.value) {
    e.stopImmediatePropagation();
  }
}

async function loadData() {
  error.value = null;
  try {
    sports.value = await getSports();
    matchesTournament.value = await getMatchesByTournamentId(props.tournamentId);
    if (matchesTournament.value[0]?.tournament.state.toString() === StateTournamentEnum.PENDIENTE) {
      readOnlyTournament.value = false;
    }
    tournamentName.value = matchesTournament.value[0]?.tournament?.name || 'Sin nombre del torneo';
    selectedSportId.value = matchesTournament.value[0]?.tournament?.sport.id || null;

    selectedNumberTeams.value = countTeams();

  } catch (e: any) {
    error.value = 'No se pudieron cargar los datos';
    console.error(e);
  }
}

function countTeams() {
  const teamIds = new Set<number>();
  for (const match of matchesTournament.value) {
    teamIds.add(match.team1.id);
    teamIds.add(match.team2.id);
  }
  return teamIds.size;
}

function resetForm() {
  tournamentName.value = '';
  selectedSportId.value = null;
  selectedTeams.value = [];
  selectedNumberTeams.value = 8;
}

function editTournament() {
  
}

onMounted(() => {
  loadData();
});
</script>

<style scoped>
.white-header {
  --color: #ffffff;
  --background: #e22f28;
  display: flex;
  justify-content: center;
  align-items: center;
}

.white-header ion-title {
  left: 100%;
  transform: translateX(3%);
  font-weight: bolder;
  font-size: 1.5rem;
}

.modal-body {
  --padding-bottom: 0;
}

.modal-grid .row {
  display: flex;
  flex-wrap: wrap;
  gap: 10%;
  margin-top: 5%;
  margin-left: 6%;
  margin-right: 6%;
}

.column-left,
.column-right {
  flex: 0 0 50%;
  box-sizing: border-box;
  padding: 0 0.5rem;
}

.column-left {
  padding-top: 2rem;
  display: flex;
  flex-direction: column;
  gap: 1rem;
  max-width: 40%;
}

.column-right {
  max-width: 60%;
  background-color: #ededed;
  border-radius: 10px;
  padding: 2%;
}

.colum-down {
  flex: 0 0 100%;
  max-width: 100%;
  box-sizing: border-box;
  padding: 1rem 0.5rem;
  border-radius: 10px;
  margin-bottom: 1rem;
}

.colum-down2 {
  flex: 0 0 100%;
  max-width: 100%;
  box-sizing: border-box;
  padding: 1rem 0.5rem;
  display: flex;
  justify-content: flex-end;
  gap: 1rem;
  margin-left: auto;
}

.input-name {
  --border-color: #022029;
  --border-style: solid;
  --border-width: 1px;
  --color: #022029;
  --placeholder-color: #999999;
  text-align: left;
}

.sc-ion-input-md-h {
  --placeholder-color: var(--text-color-primary);
  --highlight-color-focused: var(--ion-color-primary, #0054e9);
  --highlight-color-valid: var(--ion-color-success, #2dd55b);
  --highlight-color-invalid: var(--ion-color-danger, #c5000f);
  --highlight-color: var(--border-color-blue);
}

.sports {
  padding: 0;
  margin-top: 4%;
}

.list-sports {
  --background: #e22f28;
  --border-radius: 5px;
  color: white;
  --placeholder-color: #ffffff;
  font-weight: bolder;
  --padding-start: 1rem;
  --padding-end: 1rem;
}

.list-sports::part(icon) {
  color: #f2acaa;
}

ion-select::part(placeholder) {
  color: white;
  font-weight: bold;
}

.clean-select {
  margin: 0;
  padding: 0;
  --padding-start: 0;
  --padding-end: 0;
  --inner-padding-start: 0;
  --inner-padding-end: 0;
}

.radio-item {
  margin-top: 1rem;
}

.radio-item h5 {
  margin: 0 0 0.5rem;
  font-weight: 600;
  text-align: left;
  color: #0a2540;
}

.glass-radio-group {
  position: relative;
  display: flex;
  background: #ececec;
  border-radius: 22px;
  overflow: hidden;
  width: max-content;
}

.glass-radio-group input {
  display: none;
}

.glass-radio-group label {
  flex: 1;
  text-align: center;
  padding: 0.75rem 1.5rem;
  cursor: pointer;
  font-weight: 600;
  z-index: 2;
}

.glass-glider {
  position: absolute;
  top: 0;
  bottom: 0;
  width: 33.333%;
  background: #979797;
  border-radius: 22px;
  z-index: 1;
  transition: transform 0.3s ease;
}

#glass-silver:checked ~ .glass-glider {
  transform: translateX(0%);
  background: linear-gradient(135deg, #ffd70055, #ffcc00);
}

#glass-gold:checked ~ .glass-glider {
  transform: translateX(100%);
  background: linear-gradient(135deg, #e2632865, #e22f28);
}

#glass-platinum:checked ~ .glass-glider {
  transform: translateX(200%);
  background: linear-gradient(135deg, #d0e7ff55, #0a2540);
}

.team-selector {
  padding: 1rem;
  border-radius: 12px;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 0.5rem;
}

.header h2 {
  font-size: 1.2rem;
  margin: 0;
}

.counter {
  font-weight: bold;
  color: #ff3c2f;
}

.custom-search {
  --border-radius: 8px;
  --box-shadow: none;
  border: 1px solid #0a254098;
  border-radius: 5px;
  text-align: left;
}

.sc-ion-searchbar-md-h {
  -webkit-padding-start: 0;
  padding-inline-start: 0;
  -webkit-padding-end: 0;
  padding-inline-end: 0;
  padding: 0;
  margin: 0 0 1rem 0;
}

.match-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0.5rem;
  background-color: #ededed;
  color: #042935;
  font-weight: bold;
  border-radius: 8px;
  margin-bottom: 1rem;
}

.match-header span {
  flex: 1;
  text-align: center;
}

.match-rows {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
}

.match-row {
  display: grid;
  grid-template-columns: 0.9fr 0.1fr 0.9fr 1fr 1fr;
  align-items: center;
  gap: 0.5rem;
  background-color: #f1f1f1;
  padding: 0.5rem 1rem;
  border-radius: 8px;
}

.team-box {
  position: relative;
  background: white;
  border-radius: 8px;
  padding: 0.4rem;
  display: flex;
  font-size: 0.85rem;
  color: #0b2c3e;
  align-items: center;
}

.team-box span {
  flex: 1;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  align-items: left;
}

.delete-btn {
  position: absolute;
  right: 6px;
  top: 50%;
  transform: translateY(-50%);
  background: transparent;
  border: none;
  color: #ff3c2f;
  cursor: pointer;
  padding: 0;
  font-size: 18px;
}

.vs-text {
  font-weight: bold;
  color: #0b2c3e;
  text-align: center;
  align-items: center;
  justify-content: center;
}

.date-btn {
  margin: 0;
  padding: 0;
  color: white;
  border: none;
  border-radius: 8px;
  padding: 0.4rem;
  font-size: 0.8rem;
  display: flex;
  align-items: center;
  justify-content: center;
}

.date-input {
  background-color: #ff3c2f;
  color: white;
  border: none;
  border-radius: 8px;
  padding: 0.5rem;
  font-size: 0.8rem;
}

.round-label {
  font-weight: bold;
  color: #0b2c3e;
  text-align: center;
  font-size: 0.85rem;
  letter-spacing: 0.5px;
  text-transform: uppercase;
}

.readonly-select {
  pointer-events: none;
  background: var(--ion-background-color);
  color: var(--text-color-secundary);
}




.modal-grid .row {
  display: flex;
  flex-wrap: wrap;
  gap: 10%;
  margin-top: 5%;
  margin-left: 6%;
  margin-right: 6%;
}
.colum-down2 {
  flex: 0 0 100%;
  max-width: 100%;
  box-sizing: border-box;
  padding: 1rem 0.5rem;
  display: flex;
  justify-content: flex-end;
  gap: 1rem;
  margin-left: auto;
}

.btn-clean, .btn-save {
  --background: #0a2540;
  --border-radius: 8px;
  --color: white;
  --padding-start: 1rem;
  --padding-end: 1rem;
  font-weight: bold;
}

.btn-clean .material-symbols-outlined,
.btn-save .material-symbols-outlined {
  margin-right: 5px;
}

</style>
