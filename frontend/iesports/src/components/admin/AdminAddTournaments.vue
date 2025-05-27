<template>
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
                <span class="round-label">{{ selectedNumberTeams == 4 ? 'Semis' : selectedNumberTeams == 8 ? 'Cuartos' : selectedNumberTeams == 16 ? 'Octavos' : '' }}</span>
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
defineEmits(['close'])

import { ref, onMounted, computed, watch  } from 'vue';

import { IonSelect, IonSelectOption, IonContent, IonSearchbar, IonList, IonItem, IonInput, IonHeader, IonToolbar, IonButton, IonTitle, IonButtons, IonFooter } from '@ionic/vue';

import { getSports } from '@/services/sportService';

import type { Team } from '@/model/team';

import { addTournament } from '@/services/tournamentService';
import type { TournamentAddDTO } from '@/model/TournamentAddDTO';
import type { MatchDTO } from '@/model/matchDTO';

const error = ref<string | null>(null);

const sports = ref<any[]>([]);
const teams = ref<Team[]>([]);

const tournamentName = ref('');
const selectedSportId = ref<number | null>(null);
const selectedNumberTeams = ref<number>(8); // valor por defecto
const selectedTeams = ref<string[]>([]);
const matchDates = ref<(Date | null)[]>([]);


function addTeam(teamName: string) {
  if (
    selectedTeams.value.length < matchesToRender.value * 2 &&
    !selectedTeams.value.includes(teamName)
  ) {
    selectedTeams.value.push(teamName);
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

//LISTA DE EJEMPLO PARA SIMULAR LOS EQUIPOS
// const teamsList = ref<Team[]>([
//   { id: 1, name: 'The winners', players: [ { id: 101, name: 'Juan Pérez', email: 'juan@example.com', password: '1234', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 1, name: '1º Bach', age: '16-17' } }, { id: 102, name: 'Ana López', email: 'ana@example.com', password: '5678', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 1, name: '1º Bach', age: '16-17' } } ] },
//   { id: 2, name: 'Los campeones', players: [ { id: 103, name: 'Carlos Ruiz', email: 'carlos@example.com', password: 'abcd', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 2, name: '2º Bach', age: '17-18' } }, { id: 104, name: 'Lucía Gómez', email: 'lucia@example.com', password: 'efgh', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 2, name: '2º Bach', age: '17-18' } } ] },
//   { id: 3, name: 'The challengers', players: [ { id: 105, name: 'Pedro Martínez', email: 'pedro@example.com', password: 'ijkl', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 3, name: '3º ESO', age: '14-15' } }, { id: 106, name: 'María Torres', email: 'maria@example.com', password: 'mnop', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 3, name: '3º ESO', age: '14-15' } } ] },
//   { id: 4, name: 'Equipo D', players: [ { id: 107, name: 'Andrés Ramos', email: 'andres@example.com', password: 'qrst', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 4, name: '4º ESO', age: '15-16' } }, { id: 108, name: 'Claudia Vega', email: 'claudia@example.com', password: 'uvwx', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 4, name: '4º ESO', age: '15-16' } } ] },
//   { id: 5, name: 'Las estrellas', players: [ { id: 109, name: 'Diego Navarro', email: 'diego@example.com', password: 'yz12', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 5, name: '1º ESO', age: '12-13' } }, { id: 110, name: 'Paula Nieto', email: 'paula@example.com', password: '3456', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 5, name: '1º ESO', age: '12-13' } } ] },
//   { id: 6, name: 'Equipo F', players: [ { id: 111, name: 'Alberto León', email: 'alberto@example.com', password: '7890', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 6, name: '2º ESO', age: '13-14' } }, { id: 112, name: 'Sara Molina', email: 'sara@example.com', password: 'abcd', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 6, name: '2º ESO', age: '13-14' } } ] },
//   { id: 7, name: 'Equipo G', players: [ { id: 113, name: 'Tomás Gil', email: 'tomas@example.com', password: 'efgh', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 7, name: '1º FP', age: '16-17' } }, { id: 114, name: 'Nuria Díaz', email: 'nuria@example.com', password: 'ijkl', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 7, name: '1º FP', age: '16-17' } } ] },
//   { id: 8, name: 'Equipo H', players: [ { id: 115, name: 'Esteban Ramos', email: 'esteban@example.com', password: 'mnop', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 8, name: '2º FP', age: '17-18' } }, { id: 116, name: 'Laura Romero', email: 'laura@example.com', password: 'qrst', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 8, name: '2º FP', age: '17-18' } } ] },
//   { id: 9, name: 'Los Invencibles', players: [ { id: 117, name: 'Iván Herrera', email: 'ivan@example.com', password: 'abcd1234', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 9, name: '1º Bach', age: '16-17' } }, { id: 118, name: 'Sofía Martín', email: 'sofia@example.com', password: 'pass4567', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 9, name: '1º Bach', age: '16-17' } } ] },
//   { id: 10, name: 'Equipo X', players: [ { id: 119, name: 'Mario Díaz', email: 'mario@example.com', password: 'xyz890', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 10, name: '3º ESO', age: '14-15' } }, { id: 120, name: 'Valeria Ruiz', email: 'valeria@example.com', password: 'qwerty', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 10, name: '3º ESO', age: '14-15' } } ] },
//   { id: 11, name: 'Furia Roja', players: [ { id: 121, name: 'Hugo Serrano', email: 'hugo@example.com', password: 'hugo123', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 11, name: '2º Bach', age: '17-18' } }, { id: 122, name: 'Daniela Ortega', email: 'daniela@example.com', password: 'daniela456', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 11, name: '2º Bach', age: '17-18' } } ] },
//   { id: 12, name: 'Tiburones', players: [ { id: 123, name: 'Fernando Gálvez', email: 'fernando@example.com', password: 'f123', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 12, name: '1º ESO', age: '12-13' } }, { id: 124, name: 'Lucía Gil', email: 'lucia.gil@example.com', password: 'l456', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 12, name: '1º ESO', age: '12-13' } } ] },
//   { id: 13, name: 'Los Titanes', players: [ { id: 125, name: 'Álvaro Mena', email: 'alvaro@example.com', password: 'mena789', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 13, name: '4º ESO', age: '15-16' } }, { id: 126, name: 'Elena Blanco', email: 'elena@example.com', password: 'e123', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 13, name: '4º ESO', age: '15-16' } } ] },
//   { id: 14, name: 'Gladiadores', players: [ { id: 127, name: 'Sergio López', email: 'sergio@example.com', password: 'sl456', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 14, name: '2º ESO', age: '13-14' } }, { id: 128, name: 'Marta Ramos', email: 'marta@example.com', password: 'mr789', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 14, name: '2º ESO', age: '13-14' } } ] },
//   { id: 15, name: 'Los Panteras', players: [ { id: 129, name: 'David Ortega', email: 'david@example.com', password: 'do321', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 15, name: '1º Bach', age: '16-17' } }, { id: 130, name: 'Irene Gutiérrez', email: 'irene@example.com', password: 'ig654', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 15, name: '1º Bach', age: '16-17' } } ] },
//   { id: 16, name: 'Los Rápidos', players: [ { id: 131, name: 'Óscar Nieto', email: 'oscar@example.com', password: 'on987', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 16, name: '3º ESO', age: '14-15' } }, { id: 132, name: 'Natalia Pérez', email: 'natalia@example.com', password: 'np111', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 16, name: '3º ESO', age: '14-15' } } ] },
//   { id: 17, name: 'Águilas Doradas', players: [ { id: 133, name: 'Pablo Reina', email: 'pablo@example.com', password: 'pr123', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 17, name: '2º Bach', age: '17-18' } }, { id: 134, name: 'Carla Morales', email: 'carla@example.com', password: 'cm456', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 17, name: '2º Bach', age: '17-18' } } ] },
//   { id: 18, name: 'Los Relámpagos', players: [ { id: 135, name: 'Rubén Cano', email: 'ruben@example.com', password: 'rc789', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 18, name: '2º FP', age: '17-18' } }, { id: 136, name: 'Julia Castillo', email: 'julia@example.com', password: 'jc000', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 18, name: '2º FP', age: '17-18' } } ] },
//   { id: 19, name: 'Los Lobos', players: [ { id: 137, name: 'Bruno Delgado', email: 'bruno@example.com', password: 'bd432', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 19, name: '4º ESO', age: '15-16' } }, { id: 138, name: 'Sandra Ramos', email: 'sandra@example.com', password: 'sr321', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 19, name: '4º ESO', age: '15-16' } } ] },
//   { id: 20, name: 'Leones del Norte', players: [ { id: 139, name: 'Álex Vega', email: 'alex@example.com', password: 'av876', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 20, name: '1º FP', age: '16-17' } }, { id: 140, name: 'Carmen Soto', email: 'carmen@example.com', password: 'cs123', active: 1, role: { id: 1, name: 'Jugador', active: 1 }, course: { id: 20, name: '1º FP', age: '16-17' } } ] }
// ]);

const teamsList = ref<Team[]>([
  { id: 1, name: 'equipo 1', players: null },
  { id: 2, name: 'equipo 2', players: null },
  { id: 3, name: 'equipo 3', players: null },
  { id: 4, name: 'equipo 4', players: null },
  { id: 5, name: 'equipo 5', players: null },
  { id: 6, name: 'equipo 6', players: null },
  { id: 7, name: 'equipo 7', players: null },
  { id: 8, name: 'equipo 8', players: null },
  { id: 9, name: 'equipo 9', players: null },
  { id: 10, name: 'equipo 10', players: null },
  { id: 11, name: 'equipo 11', players: null },
  { id: 12, name: 'equipo 12', players: null },
  { id: 13, name: 'equipo 13', players: null },
  { id: 14, name: 'equipo 14', players: null },
  { id: 15, name: 'equipo 15', players: null },
  { id: 16, name: 'equipo 16', players: null },
]);



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

    // teams.value = await getTeams();
    teams.value = teamsList.value;
    console.log('Lista de equipos :', teams.value);

  } catch (e: any) {
    error.value = 'No se pudieron cargar los datos';
    console.error(e);
  } 
}

async function createTournament() {

  console.log('FECHAS', matchDates.value);
  const matches: MatchDTO[] = [];

  const teamsTournament: Team[] = [];

  for (let i = 0; i < selectedTeams.value.length; i += 2) {
    
    const team1 = teams.value.find(t => t.name === selectedTeams.value[i]);
    teamsTournament.push(team1!); 

    const team2 = teams.value.find(t => t.name === selectedTeams.value[i + 1]);
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
    alert('Torneo creado correctamente');
    resetForm();
  } catch (error) {
    console.error(error);
    alert('Error al crear el torneo');
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
  font-size: 18px; /* más pequeño */
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

.round-label {
  font-weight: bold;
  color: #0b2c3e;
  text-align: center;
  font-size: 0.85rem;
  letter-spacing: 0.5px;
  text-transform: uppercase;
}

</style>
