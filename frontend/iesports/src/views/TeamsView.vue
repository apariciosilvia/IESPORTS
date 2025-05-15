<template>
  <ion-page>
    <ion-content fullscreen @ionScroll="handleScroll" :scroll-events="true">
      <Navbar :class="['navbar', { 'navbar-visible': showNav }]" />

      <!-- Contenedor de filtros (solo deporte) -->
      <div class="filters-container">
        <ion-item class="filter-item">
          <ion-label>Deporte</ion-label>
          <ion-select v-model="selectedSport" placeholder="Selecciona deporte">
            <ion-select-option :value="null">Todos</ion-select-option>

            <ion-select-option
              v-for="sport in sportsList"
              :key="sport.id"
              :value="sport.id"
            >
              {{ sport.name }} 
            </ion-select-option>
          </ion-select>
        </ion-item>
      </div>

      <!-- Spinner de carga -->
      <ion-spinner v-if="isLoading" name="crescent" />

      <!-- Mensaje de error -->
      <div v-if="error" class="error">{{ error }}</div>

      <!-- Lista de equipos -->
      <IonList class="teams-container">
        <TeamInfoCard
          v-for="info in teamInfo"
          :key="info.team.id"
          :teamInfo="info"
        />
      </IonList>

    </ion-content>
    <Footer />

    
  </ion-page>
</template>

<script setup lang="ts">
import { IonPage, IonContent, IonList, IonSelect, IonSpinner, IonSelectOption, IonLabel, IonItem } from '@ionic/vue';
import Navbar from '@/components/layout/Navbar.vue';
import Footer from '@/components/ui/Footer.vue';
import { useNavbarVisibility } from '@/composables/useNavbarVisibility';
import { ref, watch, onMounted } from 'vue';
// import { getSports } from '@/services/tournamentService';
// import { getTeams } from '../services/teamService';
import TeamInfoCard from '@/components/layout/TeamInfoCard.vue';

// import TeamCard from '@/components/layout/TeamCard.vue';
import type { Sport } from '@/model/sport';
import type { TeamInfoDTO } from '@/model/teamInfoDTO';


const { showNav, handleScroll } = useNavbarVisibility();

// Estado reactivo
const sports = ref< Sport[] >([]);
const selectedSport = ref<number | null>(null);
const teamInfo = ref< TeamInfoDTO[] >([]);
const isLoading = ref(false);
const error = ref<string | null>(null);


const sportsList: Sport[] = [
  { id: 1, name: 'Fútbol'},
  { id: 2, name: 'Baloncesto'},
  { id: 3, name: 'Tenis'},
  { id: 4, name: 'Voleibol' },
  { id: 5, name: 'Ping-pong'}
];



const teamInfoList: TeamInfoDTO[] = [
  {
    team: { id: 1, name: 'Real Madrid' },
    sport: [{ id: 1, name: 'Fútbol' }],
    person: [
      { id: 101, course_id: 201, name: 'Juan Pérez',       email: 'juan.perez@example.com',       password: 'pass1234', active: 1, role: { id: 1, name: 'Admin', active: 1 } },
      { id: 102, course_id: 202, name: 'Lucía Fernández',  email: 'lucia.fernandez@example.com',  password: 'pass1234', active: 1, role: { id: 2, name: 'User',  active: 1 } },
      { id: 103, course_id: 203, name: 'Miguel Torres',    email: 'miguel.torres@example.com',    password: 'pass1234', active: 1, role: { id: 2, name: 'User',  active: 1 } },
      { id: 104, course_id: 204, name: 'Sofía Gómez',      email: 'sofia.gomez@example.com',      password: 'pass1234', active: 1, role: { id: 3, name: 'Guest', active: 0 } },
      { id: 105, course_id: 205, name: 'Diego Ramírez',    email: 'diego.ramirez@example.com',    password: 'pass1234', active: 1, role: { id: 3, name: 'Guest', active: 0 } },
    ]
  },
  {
    team: { id: 2, name: 'Barcelona' },
    sport: [{ id: 1, name: 'Fútbol' }],
    person: [
      { id: 106, course_id: 206, name: 'María García',    email: 'maria.garcia@example.com',     password: 'pass1234', active: 1, role: { id: 2, name: 'User',  active: 1 } },
      { id: 107, course_id: 207, name: 'Pedro Hernández', email: 'pedro.hernandez@example.com',  password: 'pass1234', active: 1, role: { id: 2, name: 'User',  active: 1 } },
      { id: 108, course_id: 208, name: 'Elena Ruiz',      email: 'elena.ruiz@example.com',       password: 'pass1234', active: 1, role: { id: 3, name: 'Guest', active: 0 } },
      { id: 109, course_id: 209, name: 'Carlos Martín',   email: 'carlos.martin@example.com',    password: 'pass1234', active: 1, role: { id: 3, name: 'Guest', active: 0 } },
      { id: 110, course_id: 210, name: 'Laura Sánchez',   email: 'laura.sanchez@example.com',    password: 'pass1234', active: 1, role: { id: 1, name: 'Admin', active: 1 } },
    ]
  },
  {
    team: { id: 3, name: 'Manchester United' },
    sport: [{ id: 2, name: 'Baloncesto' }],
    person: [
      { id: 111, course_id: 211, name: 'Ana Martínez',    email: 'ana.martinez@example.com',     password: 'pass1234', active: 1, role: { id: 2, name: 'User',  active: 1 } },
      { id: 112, course_id: 212, name: 'Luis Gómez',      email: 'luis.gomez@example.com',       password: 'pass1234', active: 1, role: { id: 2, name: 'User',  active: 1 } },
      { id: 113, course_id: 213, name: 'Marta Ruiz',      email: 'marta.ruiz@example.com',       password: 'pass1234', active: 1, role: { id: 3, name: 'Guest', active: 0 } },
      { id: 114, course_id: 214, name: 'Jorge Díaz',      email: 'jorge.diaz@example.com',       password: 'pass1234', active: 1, role: { id: 3, name: 'Guest', active: 0 } },
      { id: 115, course_id: 215, name: 'Carla López',     email: 'carla.lopez@example.com',      password: 'pass1234', active: 1, role: { id: 1, name: 'Admin', active: 1 } },
    ]
  },
  {
    team: { id: 4, name: 'Bayern Munich' },
    sport: [{ id: 3, name: 'Tenis' }],
    person: [
      { id: 116, course_id: 216, name: 'Pedro Pérez',     email: 'pedro.perez2@example.com',     password: 'pass1234', active: 1, role: { id: 2, name: 'User',  active: 1 } },
      { id: 117, course_id: 217, name: 'Marina Díaz',     email: 'marina.diaz@example.com',      password: 'pass1234', active: 1, role: { id: 2, name: 'User',  active: 1 } },
      { id: 118, course_id: 218, name: 'Juan López',      email: 'juan.lopez2@example.com',      password: 'pass1234', active: 1, role: { id: 3, name: 'Guest', active: 0 } },
      { id: 119, course_id: 219, name: 'Carmen Ruiz',     email: 'carmen.ruiz@example.com',      password: 'pass1234', active: 1, role: { id: 3, name: 'Guest', active: 0 } },
      { id: 120, course_id: 220, name: 'David García',    email: 'david.garcia@example.com',     password: 'pass1234', active: 1, role: { id: 1, name: 'Admin', active: 1 } },
    ]
  },
  {
    team: { id: 5, name: 'Paris Saint Germain' },
    sport: [{ id: 4, name: 'Voleibol' }],
    person: [
      { id: 121, course_id: 221, name: 'Ana Ruiz',        email: 'ana.ruiz2@example.com',        password: 'pass1234', active: 1, role: { id: 2, name: 'User',  active: 1 } },
      { id: 122, course_id: 222, name: 'Luis Fernández',  email: 'luis.fernandez2@example.com',  password: 'pass1234', active: 1, role: { id: 2, name: 'User',  active: 1 } },
      { id: 123, course_id: 223, name: 'Marta Torres',    email: 'marta.torres@example.com',     password: 'pass1234', active: 1, role: { id: 3, name: 'Guest', active: 0 } },
      { id: 124, course_id: 224, name: 'Javier Gómez',    email: 'javier.gomez@example.com',     password: 'pass1234', active: 1, role: { id: 3, name: 'Guest', active: 0 } },
      { id: 125, course_id: 225, name: 'Carmen López',    email: 'carmen.lopez2@example.com',    password: 'pass1234', active: 1, role: { id: 1, name: 'Admin', active: 1 } },
    ]
  },
  {
    team: { id: 6, name: 'Liverpool' },
    sport: [{ id: 1, name: 'Fútbol' }],
    person: [
      { id: 126, course_id: 226, name: 'Luis Martínez',   email: 'luis.martinez2@example.com',   password: 'pass1234', active: 1, role: { id: 2, name: 'User',  active: 1 } },
      { id: 127, course_id: 227, name: 'Elena Pérez',     email: 'elena.perez@example.com',      password: 'pass1234', active: 1, role: { id: 2, name: 'User',  active: 1 } },
      { id: 128, course_id: 228, name: 'Jorge Fernández', email: 'jorge.fernandez2@example.com', password: 'pass1234', active: 1, role: { id: 3, name: 'Guest', active: 0 } },
      { id: 129, course_id: 229, name: 'Sofía Ramírez',   email: 'sofia.ramirez2@example.com',   password: 'pass1234', active: 1, role: { id: 3, name: 'Guest', active: 0 } },
      { id: 130, course_id: 230, name: 'Diego Torres',    email: 'diego.torres2@example.com',    password: 'pass1234', active: 1, role: { id: 1, name: 'Admin', active: 1 } },
    ]
  },
  {
    team: { id: 7, name: 'Juventus' },
    sport: [{ id: 2, name: 'Baloncesto' }],
    person: [
      { id: 131, course_id: 231, name: 'Ana Rodríguez',   email: 'ana.rodriguez@example.com',    password: 'pass1234', active: 1, role: { id: 2, name: 'User',  active: 1 } },
      { id: 132, course_id: 232, name: 'Pablo Sánchez',   email: 'pablo.sanchez@example.com',    password: 'pass1234', active: 1, role: { id: 2, name: 'User',  active: 1 } },
      { id: 133, course_id: 233, name: 'María Torres',    email: 'maria.torres2@example.com',     password: 'pass1234', active: 1, role: { id: 3, name: 'Guest', active: 0 } },
      { id: 134, course_id: 234, name: 'Javier Martínez', email: 'javier.martinez2@example.com', password: 'pass1234', active: 1, role: { id: 3, name: 'Guest', active: 0 } },
      { id: 135, course_id: 235, name: 'Clara Gómez',     email: 'clara.gomez@example.com',      password: 'pass1234', active: 1, role: { id: 1, name: 'Admin', active: 1 } },
    ]
  },
  {
    team: { id: 8, name: 'AC Milan' },
    sport: [{ id: 3, name: 'Tenis' }],
    person: [
      { id: 136, course_id: 236, name: 'Luis Ruiz',       email: 'luis.ruiz3@example.com',       password: 'pass1234', active: 1, role: { id: 2, name: 'User',  active: 1 } },
      { id: 137, course_id: 237, name: 'Sofía Pérez',     email: 'sofia.perez3@example.com',     password: 'pass1234', active: 1, role: { id: 2, name: 'User',  active: 1 } },
      { id: 138, course_id: 238, name: 'Miguel Gómez',    email: 'miguel.gomez2@example.com',    password: 'pass1234', active: 1, role: { id: 3, name: 'Guest', active: 0 } },
      { id: 139, course_id: 239, name: 'Marina Ramírez',  email: 'marina.ramirez@example.com',   password: 'pass1234', active: 1, role: { id: 3, name: 'Guest', active: 0 } },
      { id: 140, course_id: 240, name: 'Jorge López',     email: 'jorge.lopez3@example.com',     password: 'pass1234', active: 1, role: { id: 1, name: 'Admin', active: 1 } },
    ]
  },
  {
    team: { id: 9, name: 'Chelsea' },
    sport: [{ id: 4, name: 'Voleibol' }],
    person: [
      { id: 141, course_id: 241, name: 'Ana Gómez',       email: 'ana.gomez2@example.com',       password: 'pass1234', active: 1, role: { id: 2, name: 'User',  active: 1 } },
      { id: 142, course_id: 242, name: 'Pablo Ruiz',      email: 'pablo.ruiz2@example.com',      password: 'pass1234', active: 1, role: { id: 2, name: 'User',  active: 1 } },
      { id: 143, course_id: 243, name: 'Marta Torres',    email: 'marta.torres3@example.com',     password: 'pass1234', active: 1, role: { id: 3, name: 'Guest', active: 0 } },
      { id: 144, course_id: 244, name: 'Javier Ramírez',  email: 'javier.ramirez2@example.com',  password: 'pass1234', active: 1, role: { id: 3, name: 'Guest', active: 0 } },
      { id: 145, course_id: 245, name: 'Clara Sánchez',   email: 'clara.sanchez2@example.com',   password: 'pass1234', active: 1, role: { id: 1, name: 'Admin', active: 1 } },
    ]
  },
  {
    team: { id: 10, name: 'Arsenal' },
    sport: [{ id: 5, name: 'Ping-pong' }],
    person: [
      { id: 146, course_id: 246, name: 'Luis Martínez',   email: 'luis.martinez3@example.com',   password: 'pass1234', active: 1, role: { id: 2, name: 'User',  active: 1 } },
      { id: 147, course_id: 247, name: 'Elena Fernández', email: 'elena.fernandez@example.com', password: 'pass1234', active: 1, role: { id: 2, name: 'User',  active: 1 } },
      { id: 148, course_id: 248, name: 'Miguel Ramírez',  email: 'miguel.ramirez2@example.com',  password: 'pass1234', active: 1, role: { id: 3, name: 'Guest', active: 0 } },
      { id: 149, course_id: 249, name: 'María López',     email: 'maria.lopez3@example.com',     password: 'pass1234', active: 1, role: { id: 3, name: 'Guest', active: 0 } },
      { id: 150, course_id: 250, name: 'Diego Torres',    email: 'diego.torres3@example.com',    password: 'pass1234', active: 1, role: { id: 1, name: 'Admin', active: 1 } },
    ]
  },
];
// const teamInfoList[];


// Carga inicial de deportes
async function loadSports() {
  isLoading.value = true;
  error.value = null;
  try {
    sports.value = sportsList;
    // sports.value = await getSports();
  } catch (e: any) {
    error.value = 'No se pudieron cargar los deportes.';
    console.error(e);
  } finally {
    isLoading.value = false;
  }
}

onMounted(() => {
  loadSports()
  teamInfo.value = teamInfoList  
  //loadTeamsInfo()     
})

// Cuando cambie el deporte seleccionado, carga los equipos
watch(selectedSport, (sportId) => {
  isLoading.value = true;
  error.value = null;

  console.log('Deporte seleccionado:', sportId);

  if (sportId !== null) {
    // Solo los TeamInfoDTO cuyo array `sport` contenga el id seleccionado
    teamInfo.value = teamInfoList.filter(info =>
      info.sport.some(s => s.id === sportId)
    );
    console.log(teamInfo.value);
  } else {
    // null = Todos → recargamos la lista completa
      teamInfo.value = teamInfoList;
  }

  isLoading.value = false;
});


// async function loadTeamsInfo() {
//   try {
//     teamInfo.value = await getTeamsInfo(sportId);
//     teamInfoList = teamInfo.value;
//   } catch (e: any) {
//     error.value = 'Error al cargar los equipos.';
//     console.error(e);
//   } finally {
//     isLoading.value = false;
//   }
// }
</script>

<style scoped>
.filters-container {
  display: flex;
  justify-content: center;
  padding: 0.75rem 1rem;
  margin: 5rem 2rem 1rem;
  background: #f2f3f3;
  border-radius: 8px;
  box-shadow: 0 2px 6px rgba(0,0,0,0.1);
}
.filter-item {
  flex: 1;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  --padding-end: 0;
  --background: #fff;
  --border-radius: 10px;
}
.filter-item ion-label,
.filter-item ion-select {
  text-align: center;
  width: 100%;
  font-weight: 600;
}

@media (max-width: 600px) {
  .filters-container {
    margin: 5rem 1rem 1rem;
    flex-direction: column;
  }
}

.teams-container {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, auto)); 
  gap: 3rem;       
  padding: 1rem;
  justify-content: center; 
  justify-items: center;  
    margin: 2rem 1rem;
 
}
.team-card {
  background: #fff;
  padding: 0.75rem;
  border-radius: 8px;
  text-align: center;
  box-shadow: 0 1px 4px rgba(0,0,0,0.1);
}
.error {
  font-weight: 500;  
  font-size: 1.3rem;
  text-align: center;
  margin-top: 4%;
}
</style>
