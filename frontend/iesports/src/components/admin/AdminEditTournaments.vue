<template>
  <!-- Popup Container -->
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
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" aria-hidden="true" class="close-svg">
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

  <!-- Cabecera del modal -->
  <ion-header>
    <ion-toolbar class="white-header">
      <ion-title>EDITAR TORNEO</ion-title>
      <ion-buttons slot="end">
        <ion-button @click="$emit('close')">Cerrar</ion-button>
      </ion-buttons>
    </ion-toolbar>
  </ion-header>
  <ion-content class="modal-body">
    <div v-if="error" class="error-message-api">{{ error }}</div>
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
                <ion-select-option v-for="s in sports" :key="s.id" :value="s.id">
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
          <div class="tournament-state">
            <h2>ESTADO DEL TORNEO:  <b>{{ tournamentState }}</b></h2>
          </div>
          <div class="team-selector">
            <div class="header">
              <h2>PARTIDOS JUGADOS</h2>
            </div>
            <div class="round-list">
              <div class="round-item" v-for="r in roundsInfo" :key="r.label">
                <span>{{ r.label }}</span>
                <span class="badge">{{ r.played }}/{{ r.total }}</span>
              </div>
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
            <div class="match-row" v-for="match in matchesTournament" :key="match.id">
              <!-- Equipo 1 -->
              <div
                class="team-box"
                :class="{
                  'winner-border': match.winnerTeam?.id === match.team1.id,
                  'loser-border': match.winnerTeam !== null && match.winnerTeam?.id !== match.team1.id
                }"
              >
                <span>{{ match.team1.name }}</span>
              </div>

              <span class="vs-text">VS</span>

              <!-- Equipo 2 -->
              <div
                class="team-box"
                :class="{
                  'winner-border': match.winnerTeam?.id === match.team2.id,
                  'loser-border': match.winnerTeam !== null && match.winnerTeam?.id !== match.team2.id
                }"
              >
                <span>{{ match.team2.name }}</span>
              </div>

              <!-- Fecha -->
              <div class="date-btn">
                <template v-if="match.winnerTeam !== null">
                  <div class="date-text">
                    {{ formatDate(match.date) }}
                  </div>
                </template>
                <template v-else>
                  <div class="date-btn">
                    <input
                      type="date"
                      :value="formatForInput(match.date)"
                      @input="onDateChange(match, ($event.target as HTMLInputElement).value)"
                      class="date-input"
                      :readonly="match.winnerTeam !== null"
                    />
                  </div>
                </template>
              </div>

              <!-- Ronda -->
              <span class="round-label">{{ getRoundLabel(match.round) }}</span>

              <div
                class="points-wrapper"
                :class="{
                  'winner-border': match.winnerTeam?.id === match.team1.id,
                  'loser-border': match.winnerTeam !== null && match.winnerTeam?.id !== match.team1.id
                }"
              >
                <input
                  class="inputPoints"
                  type="number"
                  v-model.number="match.pointsTeam1"
                  :readonly="match.winnerTeam !== null"
                />
              </div>
              <span class="vs-text">
                {{ match.tournament.sport.name === 'Fútbol' ? 'Goles' : 'Puntos' }}
              </span>
              <div
                class="points-wrapper"
                :class="{
                  'winner-border': match.winnerTeam?.id === match.team2.id,
                  'loser-border': match.winnerTeam !== null && match.winnerTeam?.id !== match.team2.id
                }"
              >
                <input
                  class="inputPoints"
                  type="number"
                  v-model.number="match.pointsTeam2"
                  :readonly="match.winnerTeam !== null"
                />
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </ion-content>
  <ion-footer class="row">
    <div class="colum-down2">
      <ion-button expand="block" class="btn-clean" @click="resetForm">
        <span class="material-symbols-outlined">mop</span>Limpiar
      </ion-button>
      <ion-button expand="block" class="btn-save" @click="editTournament">
        <span class="material-symbols-outlined">save</span>GUARDAR
      </ion-button>
    </div>
  </ion-footer>
</template>

<script setup lang="ts">

// const emit = defineEmits<{
//   (e: 'close'): void;
// }>();

import { ref, onMounted, computed } from 'vue';
import { IonSelect, IonSelectOption, IonContent, IonList, IonItem, IonInput, IonHeader, IonToolbar, IonButton, IonTitle, IonButtons, IonFooter } from '@ionic/vue';

//Classes
import type { Match } from '@/model/match';
import { StateTournamentEnum } from '@/model/enum/StateTournamentEnum';
import { RoundMatchEnum } from '@/model/enum/RoundMatchEnum';
import type { TournamentModifyDTO } from '@/model/dto/tournamentModifyDTO';
import type { MatchModifyTournamentDTO } from '@/model/dto/matchModifyTournamentDTO';
//Services
import { modifyTournament } from '@/services/tournamentService';
import { getSports } from '@/services/sportService';
import { getMatchesByTournamentId } from '@/services/matchService';

const readOnlyTournament = ref(true);
const error = ref<string | null>(null);
const props = defineProps<{ tournamentId: number }>();

const sports = ref<any[]>([]);
const matchesTournament = ref<Match[]>([]);
const tournamentName = ref('');
const selectedSportId = ref<number | null>(null);
const selectedNumberTeams = ref<number>(0);
const tournamentState = ref<StateTournamentEnum | null>(null);

// Popup state
const showPopup = ref(false);
const popupType = ref<'success' | 'alert' | 'error' | 'info'>('info');
const popupMessage = ref('');

function openPopup(type: 'success' | 'alert' | 'error' | 'info', message: string) {
  popupType.value = type;
  popupMessage.value = message.replace(/\n/g, '<br>');
  showPopup.value = true;
  if (type !== 'success') {
    setTimeout(() => {
      showPopup.value = false;
      popupMessage.value = '';
    }, 3000);
  }
}

function closePopup() {
  showPopup.value = false;
  popupMessage.value = '';
}

function preventSelect(e: Event) {
  if (readOnlyTournament.value) {
    e.stopImmediatePropagation();
  }
}

function getRoundLabel(round: string): string {
  switch (round) {
    case RoundMatchEnum.OCTAVOS:
      return 'OCTAVOS';
    case RoundMatchEnum.CUARTOS_FINAL:
      return 'CUARTOS';
    case RoundMatchEnum.SEMIFINAL:
      return 'SEMIFINALES';
    case RoundMatchEnum.FINAL:
      return 'FINAL';
    default:
      return '';
  }
}

function formatDate(d: Date | null): string {
  if (!d) return '';
  const dt = new Date(d);
  const yy = String(dt.getFullYear() % 100).padStart(2, '0');
  const mm = String(dt.getMonth() + 1).padStart(2, '0');
  const dd = String(dt.getDate()).padStart(2, '0');
  return `${dd}/${mm}/${yy}`;
}

function formatForInput(d: Date | null): string {
  if (!d) return '';
  const dt = new Date(d);
  const yyyy = dt.getFullYear();
  const mm = String(dt.getMonth() + 1).padStart(2, '0');
  const dd = String(dt.getDate()).padStart(2, '0');
  return `${yyyy}-${mm}-${dd}`;
}

function onDateChange(match: Match, value: string) {
  match.date = value ? new Date(value) : null;
}

async function loadData() {
  error.value = null;
  try {
    sports.value = await getSports();
    matchesTournament.value = await getMatchesByTournamentId(props.tournamentId);

    if (matchesTournament.value[0]?.tournament.state.toString() === StateTournamentEnum.PENDIENTE) {
      readOnlyTournament.value = false;
    }
    tournamentName.value =
      matchesTournament.value[0]?.tournament?.name || 'Sin nombre del torneo';
    selectedSportId.value =
      matchesTournament.value[0]?.tournament?.sport.id || null;
    tournamentState.value =
      (matchesTournament.value[0]?.tournament?.state as unknown) as StateTournamentEnum ||
      null;

    selectedNumberTeams.value = countTeams();
  } catch (e: any) {
    error.value = 'No se pudieron cargar los datos';
    openPopup('error', 'No se pudieron cargar los datos');
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

async function resetForm() {
  await loadData(); // Vuelve a cargar los valores iniciales 

}

async function editTournament() {
  const payload: TournamentModifyDTO = {
    tournamentId: props.tournamentId,
    tournamentNameModified: tournamentName.value,
    sportModifiedId: selectedSportId.value!,
    matches: matchesTournament.value.map((m) => {
      const dto: MatchModifyTournamentDTO = {
        matchId: m.id,
        matchDate: m.date,
        scoreTeam1: m.pointsTeam1,
        scoreTeam2: m.pointsTeam2,
      };
      return dto;
    }),
  };

  try {
    await modifyTournament(payload);
    openPopup('success','Torneo actualizado')
    resetForm();

    // espera 2 segundos antes de recargar
    await new Promise(resolve => setTimeout(resolve, 1000))
    window.location.reload();

    

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
  }
}

const roundsInfo = computed(() => {
  const totalTeams = selectedNumberTeams.value;
  if (!totalTeams) return [];
  const levels = Math.log2(totalTeams);
  const info: { label: string; played: number; total: number }[] = [];
  for (let i = 1; i <= levels; i++) {
    const offset = levels - i;
    let roundKey = '';
    if (offset === 3) roundKey = RoundMatchEnum.OCTAVOS;
    else if (offset === 2) roundKey = RoundMatchEnum.CUARTOS_FINAL;
    else if (offset === 1) roundKey = RoundMatchEnum.SEMIFINAL;
    else if (offset === 0) roundKey = RoundMatchEnum.FINAL;
    else continue;

    const totalMatches = totalTeams / Math.pow(2, i);
    const playedMatches = matchesTournament.value.filter(
      (m) => m.round === roundKey && m.winnerTeam !== null
    ).length;
    const label = getRoundLabel(roundKey);
    info.push({ label, played: playedMatches, total: totalMatches });
  }
  return info;
});

onMounted(() => {
  loadData();
  console.log("Editar torneo ", props.tournamentId)
});
</script>

<style scoped>
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
  gap: rem;
  max-width: 40%;
}

.column-right {
  max-width: 50%;
  background-color: #ffffff;
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 1rem;
  gap: 5%;
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

.glass-radio-group input:checked + label {
  color: #ffffff;
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
  border-radius: 12px;
}

.header {
  display: flex;
  justify-content: center;
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

.points-wrapper {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  background: white;
  border-radius: 8px;
  padding: 0.4rem;
  display: flex;
  font-size: 2rem;
  color: #0b2c3e;
  align-items: center;
}

.inputPoints:focus {
  outline: none;
}

.inputPoints {
  background-color: #ffffff;
  outline: none;
  border: none;
  text-align: center;
  padding: 0;
  font-size: 0.85rem;
  width: 50px;
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

.date-input:focus {
  outline: none;
}

.date-text{
  background-color: #ff3c2f;
  color: white;
  border: none;
  
  border-radius: 8px;
  padding:  0.5rem 1.3rem;
  font-size: 1rem;
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

.vs-text {
  font-weight: bold;
  color: #0b2c3e;
  text-align: center;
  align-items: center;
  justify-content: center;
  font-size: 0.95rem;
}

.winner-border {
  border: 2px solid #26F033;
  border-radius: 8px;
}

.loser-border {
  border: 2px solid #F03726;
  border-radius: 8px;
}


.team-selector {
  background-color: #ededed;
  border-radius: 10px;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}

.header {
  width: 100%;
  background-color: #e22f28;
  text-align: center;
}

.header h2 {
  color: #ffffff;
  margin: 0;
  padding: 0.75rem 0;
  font-size: 1.2rem;
  font-weight: bold;
}

.round-list {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 1rem;
  width: 100%;
}

.round-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 40%;
  margin-bottom: 0.5rem;
}

.round-item:last-child {
  margin-bottom: 0;
}

.round-item span:first-child {
  color: #e22f28;
  font-weight: 600;
}

.badge {
  background-color: #e22f28;
  color: #ffffff;
  border-radius: 12px;
  padding: 0.25rem 0.5rem;
  font-size: 0.8rem;
  font-weight: bold;
}


.tournament-state {
  width: 100%;
  text-align: center;
  margin-bottom: 1rem;
  background-color: #ededed;
  padding: 0.75rem;
  border-radius: 10px;
}

.tournament-state h2 {
  margin: 0;
  font-size: 1.2rem;
  font-weight: bold;
  color: var(--text-color-primary);
}
.tournament-state b {
  margin: 0;
  font-size: 1.2rem;
  font-weight: bold;
  color: var(--orange-secundary-color);
}

@media screen and (max-width: 768px) {
  .modal-grid .row {
    flex-direction: column;
    gap: 1.5rem;
    margin: 0 1rem;
  }

  .column-left,
  .column-right {
    flex: 0 0 100%;
    max-width: 100%;
    padding: 0;
  }

  .colum-down {
    overflow-x: auto;
  }

  .match-header,
  .match-row {
    min-width: 700px;
  }

  .modal-grid .row {
    margin-top: 6%;
   
  }
  .vs-text {
    font-weight: bold;
    color: #0b2c3e;
    text-align: center;
    align-items: center;
    justify-content: center;
    font-size: 0.75rem;
  }
  .round-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 55%;
  margin-bottom: 0.5rem;
}
}

</style>