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
            />
            <ion-list class="sports">
              <ion-item class="clean-select" lines="none">
                <ion-select
                  interface="popover"
                  placeholder="Selecciona un deporte"
                  class="list-sports"
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
              <h6>Número de equipos</h6>
              <div class="glass-radio-group">
                <input type="radio" name="teams" id="glass-silver"  />
                <label for="glass-silver">4</label>
                <input type="radio" name="teams" id="glass-gold" checked />
                <label for="glass-gold">8</label> 
                <input type="radio" name="teams" id="glass-platinum" />
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
                <!-- <span class="counter">{{ selectedTeams }}/{{ totalTeams }}</span> -->
                <span class="counter">3/4</span>
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
                  <button class="add-button">
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
            <table class="match-table">
              <thead>
                <tr>
                  <th>Equipo 1</th>
                  <th>Equipo 2</th>
                  <th>Fecha partido (opcional)</th>
                  <th>Ronda</th>
                </tr>
              </thead>
              <tbody>
                <!-- <tr
                  v-for="(m, i) in matches"
                  :key="i"
                >
                  <td>{{ m.team1.name }}</td>
                  <td>{{ m.team2.name }}</td>
                  <td>
                    <ion-datetime
                      display-format="DD/MM/YYYY"
                      picker-format="DD/MM/YYYY"
                      v-model="m.date"
                    />
                  </td>
                  <td>{{ m.round }}</td>
                </tr> -->
              </tbody>
            </table>
            <h1>Lorem ipsum dolor sit amet consectetur adipisicing elit. Laboriosam cumque iste, error reprehenderit culpa enim soluta dolorem non sint nam ducimus sequi a nobis illum repudiandae libero reiciendis adipisci dolor?</h1>
                      <h1>Lorem ipsum dolor sit amet consectetur adipisicing elit. Laboriosam cumque iste, error reprehenderit culpa enim soluta dolorem non sint nam ducimus sequi a nobis illum repudiandae libero reiciendis adipisci dolor?</h1>

          </div>
        </div>
        
      </div>
    </ion-content>
    <ion-footer class="row">
      <div class="colum-down2">
        <ion-button
          expand="block"
          class="btn-clean"
        ><span class="material-symbols-outlined">mop</span>Limpiar</ion-button>
        <ion-button
          expand="block"
          class="btn-save"
        ><span class="material-symbols-outlined">save</span>Crear Torneo</ion-button>
      </div>

    </ion-footer>
</template>

<script setup lang="ts">
defineEmits(['close'])

import { ref, onMounted, computed } from 'vue';

import { IonSelect, IonSelectOption, IonContent, IonSearchbar, IonList, IonIcon, IonItem, IonInput, IonHeader, IonToolbar, IonButton, IonTitle, IonButtons, IonFooter } from '@ionic/vue';

import { getSports } from '@/services/sportService';

import type { Team } from '@/model/team';

const error = ref<string | null>(null);

const sports = ref<any[]>([]);
const teams = ref<Team[]>([]);

//LISTA DE EJEMPLO PARA SIMULAR LOS EQUIPOS
const teamsList = ref<Team[]>([
  {
    id: 1,
    name: 'The winners',
    players: [
      {
        id: 101,
        name: 'Juan Pérez',
        email: 'juan@example.com',
        password: '1234',
        active: 1,
        role: { id: 1, name: 'Jugador', active: 1 },
        course: { id: 1, name: '1º Bach', age: '16-17' }
      },
      {
        id: 102,
        name: 'Ana López',
        email: 'ana@example.com',
        password: '5678',
        active: 1,
        role: { id: 1, name: 'Jugador', active: 1 },
        course: { id: 1, name: '1º Bach', age: '16-17' }
      }
    ]
  },
  {
    id: 2,
    name: 'Los campeones',
    players: [
      {
        id: 103,
        name: 'Carlos Ruiz',
        email: 'carlos@example.com',
        password: 'abcd',
        active: 1,
        role: { id: 1, name: 'Jugador', active: 1 },
        course: { id: 2, name: '2º Bach', age: '17-18' }
      },
      {
        id: 104,
        name: 'Lucía Gómez',
        email: 'lucia@example.com',
        password: 'efgh',
        active: 1,
        role: { id: 1, name: 'Jugador', active: 1 },
        course: { id: 2, name: '2º Bach', age: '17-18' }
      }
    ]
  },
  {
    id: 3,
    name: 'The challengers',
    players: [
      {
        id: 105,
        name: 'Pedro Martínez',
        email: 'pedro@example.com',
        password: 'ijkl',
        active: 1,
        role: { id: 1, name: 'Jugador', active: 1 },
        course: { id: 3, name: '3º ESO', age: '14-15' }
      },
      {
        id: 106,
        name: 'María Torres',
        email: 'maria@example.com',
        password: 'mnop',
        active: 1,
        role: { id: 1, name: 'Jugador', active: 1 },
        course: { id: 3, name: '3º ESO', age: '14-15' }
      }
    ]
  },
  {
    id: 4,
    name: 'Equipo D',
    players: [
      {
        id: 107,
        name: 'Andrés Ramos',
        email: 'andres@example.com',
        password: 'qrst',
        active: 1,
        role: { id: 1, name: 'Jugador', active: 1 },
        course: { id: 4, name: '4º ESO', age: '15-16' }
      },
      {
        id: 108,
        name: 'Claudia Vega',
        email: 'claudia@example.com',
        password: 'uvwx',
        active: 1,
        role: { id: 1, name: 'Jugador', active: 1 },
        course: { id: 4, name: '4º ESO', age: '15-16' }
      }
    ]
  },
  {
    id: 5,
    name: 'Las estrellas',
    players: [
      {
        id: 109,
        name: 'Diego Navarro',
        email: 'diego@example.com',
        password: 'yz12',
        active: 1,
        role: { id: 1, name: 'Jugador', active: 1 },
        course: { id: 5, name: '1º ESO', age: '12-13' }
      },
      {
        id: 110,
        name: 'Paula Nieto',
        email: 'paula@example.com',
        password: '3456',
        active: 1,
        role: { id: 1, name: 'Jugador', active: 1 },
        course: { id: 5, name: '1º ESO', age: '12-13' }
      }
    ]
  },
  {
    id: 6,
    name: 'Equipo F',
    players: [
      {
        id: 111,
        name: 'Alberto León',
        email: 'alberto@example.com',
        password: '7890',
        active: 1,
        role: { id: 1, name: 'Jugador', active: 1 },
        course: { id: 6, name: '2º ESO', age: '13-14' }
      },
      {
        id: 112,
        name: 'Sara Molina',
        email: 'sara@example.com',
        password: 'abcd',
        active: 1,
        role: { id: 1, name: 'Jugador', active: 1 },
        course: { id: 6, name: '2º ESO', age: '13-14' }
      }
    ]
  },
  {
    id: 7,
    name: 'Equipo G',
    players: [
      {
        id: 113,
        name: 'Tomás Gil',
        email: 'tomas@example.com',
        password: 'efgh',
        active: 1,
        role: { id: 1, name: 'Jugador', active: 1 },
        course: { id: 7, name: '1º FP', age: '16-17' }
      },
      {
        id: 114,
        name: 'Nuria Díaz',
        email: 'nuria@example.com',
        password: 'ijkl',
        active: 1,
        role: { id: 1, name: 'Jugador', active: 1 },
        course: { id: 7, name: '1º FP', age: '16-17' }
      }
    ]
  },
  {
    id: 8,
    name: 'Equipo H',
    players: [
      {
        id: 115,
        name: 'Esteban Ramos',
        email: 'esteban@example.com',
        password: 'mnop',
        active: 1,
        role: { id: 1, name: 'Jugador', active: 1 },
        course: { id: 8, name: '2º FP', age: '17-18' }
      },
      {
        id: 116,
        name: 'Laura Romero',
        email: 'laura@example.com',
        password: 'qrst',
        active: 1,
        role: { id: 1, name: 'Jugador', active: 1 },
        course: { id: 8, name: '2º FP', age: '17-18' }
      }
    ]
  }
]);

const searchText = ref('');
const filteredTeams = computed(() =>
  teams.value.filter(team =>
    team.name.toLowerCase().includes(searchText.value.toLowerCase())
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
  background-color: #f3f3f3;
  border-radius: 10px;
  padding: 2%;
}

.colum-down {
  flex: 0 0 100%;
  max-width: 100%;
  box-sizing: border-box;
  padding: 1rem 0.5rem;
  background-color: #30f000;
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
.radio-item h6 {
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
  background: #f1f1f1;
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
  border: 1px solid #0a2540;
  border-radius: 5px;
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
  background: #f1f1f1;
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
  margin-right: 0.5rem; /* ⬅️ aquí separas el botón del borde */
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
