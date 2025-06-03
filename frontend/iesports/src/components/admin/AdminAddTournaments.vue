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
            <ion-title>NUEVO TORNEO</ion-title>
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
            />
            <ion-list class="sports">
              <ion-item class="clean-select" lines="none">
                <ion-select
                  interface="popover"
                  placeholder="Selecciona un deporte"
                  class="list-sports"
                  v-model="selectedSportId"
                >
                  <ion-select-option  v-for="s in sports"
                  :key="s.id"
                  :value="s.id">
                    {{ s.name }}
                  </ion-select-option>
                 
                </ion-select>
              </ion-item>
            </ion-list>

            <div class="radio-item">
              <h5>Número de equipos</h5>
              <div class="glass-radio-group">
                <input type="radio"  name="teams" id="glass-silver" value="4" v-model="selectedNumberTeams" />
                <label for="glass-silver">4</label>
                <input type="radio" name="teams" id="glass-gold" value="8" checked v-model="selectedNumberTeams" />
                <label for="glass-gold">8</label>
                <input type="radio" name="teams" id="glass-platinum" value="16" v-model="selectedNumberTeams" />
                <label for="glass-platinum">16</label>
                <div class="glass-glider"></div>
              </div>
            </div>
          </div>

          <!-- Columna Derecha (60%) -->
          <div class="column-right">
            <div class="team-selector">
              <div class="header">
                <h2>Equipos</h2>
                <span class="counter">{{ selectedTeams.length }}/{{ selectedNumberTeams }}</span>
                
              </div>

              <ion-searchbar
                v-model="searchText"
                placeholder="Buscar equipo ..."
                class="custom-search"
              />

             <div class="team-list">
                <div
                  class="team-row"
                  v-for="team in filteredTeams"
                  :key="team.id"
                >
                  <div class="team-card">
                    <span class="team-name">{{ team.name }}</span>
                  </div>
                  <button class="add-button" @click="addTeam(team.name)">
                    <span class="material-symbols-outlined">add_circle</span>
                  </button>
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
              <div
                class="match-row"
                v-for="n in matchesToRender"
                :key="n"
              >
                <!-- Equipo 1 -->
                <div class="team-box">
                  <span>{{ selectedTeams[(n - 1) * 2] || ' sin equipo '  }}</span>
                  <button class="delete-btn" @click="removeTeam((n - 1) * 2)">
                    <span class="material-symbols-outlined">delete</span>
                  </button>
                </div>

                <span class="vs-text">VS</span>

                <!-- Equipo 2 -->
                <div class="team-box">
                  <span>{{ selectedTeams[(n - 1) * 2 + 1] || ' sin equipo ' }}</span>
                  <button class="delete-btn" @click="removeTeam((n - 1) * 2 + 1)">
                    <span class="material-symbols-outlined">delete</span>
                  </button>
                </div>

                <!-- Fecha -->
                <div class="date-btn">
                  <input type="date" v-model="matchDates[n - 1]" name="" id="" class="date-input" />
                </div>

                <!-- Ronda -->
                <span class="round-label">{{ selectedNumberTeams == 4 ? 'SEMIFINAL' : selectedNumberTeams == 8 ? 'CUARTOS' : selectedNumberTeams == 16 ? 'OCTAVOS' : '' }}</span>
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
          @click="createTournament"
        ><span class="material-symbols-outlined">save</span>Crear Torneo</ion-button>
      </div>
    </ion-footer>
</template>

<script setup lang="ts">
const emit = defineEmits<{
  (e: 'close'): void;
}>();

import { ref, onMounted, computed, watch  } from 'vue';

import { IonSelect, IonSelectOption, IonContent, IonSearchbar, IonList, IonItem, IonInput, IonHeader, IonToolbar, IonButton, IonTitle, IonButtons, IonFooter } from '@ionic/vue';

import { getSports } from '@/services/sportService';

import type { Team } from '@/model/team';

import { addTournament } from '@/services/tournamentService';
import { getTeams } from '@/services/teamService';

import type { TournamentAddDTO } from '@/model/dto/tournamentAddDTO';
import type { MatchDTO } from '@/model/dto/matchAddDTO';

const error = ref<string | null>(null);

const sports = ref<any[]>([]);
const teams = ref<Team[]>([]);

const tournamentName = ref('');
const selectedSportId = ref<number | null>(null);
const selectedNumberTeams = ref<number>(8); // valor por defecto
const selectedTeams = ref<string[]>([]);
const matchDates = ref<(Date | null)[]>([]);

// Popup state
const showPopup = ref<boolean>(false);
const popupType = ref<'success' | 'alert' | 'error' | 'info'>('info');
const popupMessage = ref<string>('');

function openPopup(type: 'success' | 'alert' | 'error' | 'info', message: string) {
  popupType.value = type;
  popupMessage.value = message.replace(/\n/g, '<br>');
  showPopup.value = true;

  // Cerrar automáticamente los tipos distintos a 'success'
  if (type !== 'success') {
    setTimeout(() => {
      showPopup.value = false;
      popupMessage.value = '';
    }, 1000);
  }
}
function closePopup() {
  showPopup.value = false;
  popupMessage.value = '';
}


// function addTeam(teamName: string) {
//   if (
//     selectedTeams.value.length < matchesToRender.value * 2 &&
//     !selectedTeams.value.includes(teamName)
//   ) {
//     selectedTeams.value.push(teamName);
//   }
// }

function addTeam(teamName: string) {
  console.log("Intentando agregar equipo:", teamName);
  if (
    selectedTeams.value.length < matchesToRender.value * 2 &&
    !selectedTeams.value.includes(teamName)
  ) {
    selectedTeams.value.push(teamName);
    console.log("Equipo añadido:", teamName, selectedTeams.value);
  } else {
    console.log("No se pudo añadir equipo:", teamName);
  }
}

function removeTeam(index: number) {
  selectedTeams.value.splice(index, 1);
}

const matchesToRender = computed(() => selectedNumberTeams.value / 2);


// Cada vez que cambia el número de equipos seleccionados, actualizamos el array de fechas para que tenga un campo por cada partido
watch(selectedNumberTeams, (newVal) => {
  matchDates.value = Array(newVal / 2).fill(null);
});


const searchText = ref('');
const filteredTeams = computed(() =>
  teams.value.filter(team =>
    team.name.toLowerCase().includes(searchText.value.toLowerCase()) &&
    !selectedTeams.value.includes(team.name)
  )
);

async function loadData () {
  error.value = null;
  try {
    sports.value = await getSports();
    console.log('Lista de deportes :', sports.value);

    teams.value = await getTeams();
    // teams.value = teamsList.value;
    console.log('Lista de equipos :', teams.value);

  } catch (e: any) {
    error.value = 'No se pudieron cargar los datos';
    console.error(e);
  } 
}

async function createTournament() {

  console.log('FECHAS', matchDates.value);

  if (!tournamentName.value.trim()) {
    openPopup('alert', 'El nombre del torneo es obligatorio');
    return;
  }

  if (!selectedSportId.value) {
    openPopup('alert', 'Debes seleccionar un deporte');
    return;
  }

  if (selectedTeams.value.length !== Number(selectedNumberTeams.value)) {
    openPopup('alert', `Debes seleccionar ${selectedNumberTeams.value} equipos`);
    return;
  }

  const matches: MatchDTO[] = [];

  const teamsTournament: Team[] = [];

  for (let i = 0; i < selectedTeams.value.length; i += 2) {
    
    const team1 = teams.value.find(t => t.name === selectedTeams.value[i]);
    const team2 = teams.value.find(t => t.name === selectedTeams.value[i + 1]);
    
    if (!team1 || !team2) {
      openPopup('error', 'Error interno: uno o más equipos no se encuentran en la lista');
      return;
    }

    teamsTournament.push(team1!); 
    teamsTournament.push(team2!);

    
  }

  console.log('Equipos seleccionados para el torneo:', teamsTournament);

  const numberMatches = selectedNumberTeams.value / 2;

  for (let i = 0; i < numberMatches; i++) {
    const idx1 = 2 * i;
    const idx2 = 2 * i + 1;
    matches.push({
      team1Id: teamsTournament[idx1]?.id ?? null,
      team2Id: teamsTournament[idx2]?.id ?? null,
      matchDate: matchDates.value?.[i] ?? null
    });
  }

  const tournamentData: TournamentAddDTO = {
    name: tournamentName.value,
    sportId: selectedSportId.value!,
    numTeams: selectedNumberTeams.value,
    matches : matches
  };

  try {
    console.log('Datos del torneo a crear:', tournamentData);
    await addTournament(tournamentData);
    resetForm();
    emit('close');
    setTimeout(() => window.location.reload(), 1000);
    
  } catch (error) {
    // Narrow error to any to access properties safely
    const err = error as any;
    if (err.response?.status === 409) {
      openPopup('info', 'Ya existe un torneo con este nombre');
    } else if (err.response?.status === 404) {
      openPopup('error', err.response.data.error || 'Datos inválidos enviados al servidor');
    } else if (err.response?.status === 400) {
      const errors = err.response.data;
      let msg = '';
      for (const key in errors) {
        msg += `${errors[key]}\n`;
      }
      openPopup('error', msg);
    } else {
      console.error(error);
      openPopup('error', 'Error al crear el torneo');
    }
  }
}

function resetForm() {
  tournamentName.value = ''; //resetea el nombre del torneo
  selectedSportId.value = null; //resetea el deporte seleccionado 
  selectedTeams.value = []; //resetea los equipos seleccionados
  searchText.value = '';  //resetea el texto de búsqueda
  selectedNumberTeams.value = 8; // resetea el numero de equipos a 8
  matchDates.value = Array(matchesToRender.value).fill(null); //resetea la fecha
}

onMounted(() => {
  loadData();
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

.column-left, .column-right {
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
  background-color: #EDEDED;
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
  text-align: left ;
}
/* Estilos para ion-input forzados */
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
  top: 0; bottom: 0;
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
  padding-inline-end: 0 ;
  padding: 0;
  margin: 0 0 1rem 0;
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

.counter {
  font-weight: bold;
  color: #ff3c2f;
}

.team-list {
  max-height: 140px;
  overflow-y: auto;
  display: flex;
  flex-direction: column;
  align-items: left;
  gap: 0.5rem;
}

.team-row {
  display: flex;
  justify-content: space-between;
  gap: 1rem; /* aumenta este valor para más separación */
  background: #f1f1f1;
}

.team-card {
  background: white;
  border-radius: 8px;
  padding: 0.5rem 1rem;
  flex: 1;
}

.team-name {
  font-weight: 500;
  color: #0b2c3e;
}

.add-button {
  background-color: #ff3c2f;
  color: white;
  border: none;
  border-radius: 10px;
  width: 36px;
  height: 36px;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 0;
  flex-shrink: 0;
  margin-right: 0.5rem;
}

.add-button:focus {
  outline: none;
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

.match-header{
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0.5rem;
  background-color: #EDEDED;
  color: #042935;
  font-weight: bold;
  border-radius: 8px;
  margin-bottom: 1rem;
}

.match-header span {
  flex: 1;
  text-align: center;
} 




/* .match-rows {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
} */

/* .match-row {
  display: flex;
  justify-content: space-between;
  background-color: #EDEDED;
  padding: 0.5rem 1rem;
  border-radius: 8px;
  font-size: 0.85rem;
  color: #0b2c3e;
  margin-top: 0.65rem;
} */

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
  padding: 0.4rem; /* más espacio a la derecha */
  display: flex;
  font-size: 0.85rem;
  color: #0b2c3e;
  align-items: center; /* centra verticalmente */
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
  font-size: 10%; /* más pequeño */
}

.delete-btn:focus {
  outline: none;
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
  align-items: center;
  justify-content: center;
}

.date-input{
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

.round-label {
  font-weight: bold;
  color: #0b2c3e;
  text-align: center;
  font-size: 0.85rem;
  letter-spacing: 0.5px;
  text-transform: uppercase;
}

@media screen and (max-width: 600px) {
  .colum-down {
    overflow-x: auto;
    width: 100%;
  }

  .match-header{
    min-width: 600px;
    display: grid;
    grid-template-columns: 1.4fr 1fr 1.3fr 1fr ;
    align-items: center;
    gap: 0.5rem;
    padding: 0.5rem 1rem;
    background-color: #ededed;
    border-radius: 8px;
    font-weight: bold;
    color: #042935;
  }
  .match-row {
    min-width: 600px;
    display: grid;
    grid-template-columns: 0.9fr 0.1fr 0.9fr 1fr 1fr;
    align-items: center;
    gap: 0.5rem;
    padding: 0.5rem 1rem;
    background-color: #ededed;
    border-radius: 8px;
    font-weight: bold;
    color: #042935;
  }

  .column-left,
  .column-right {
    flex: 0 0 100%;
    max-width: 100%;
    padding: 0 0.5rem;
  }

  .column-left {
    order: -1;
    padding-top: 1rem;
  }

  .column-right {
    margin-top: 1.5rem;
  }

  .delete-btn {
    position: absolute;
    right: 2px;
    top: 48%;
    transform: translateY(-50%);
    background: transparent;
    border: none;
    color: #ff3c2f;
    cursor: pointer;
    padding: 0;
    font-size: 1%; /* más pequeño */
  }
}



</style>
