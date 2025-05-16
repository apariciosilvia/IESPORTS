<template>
  
   <ion-page>
    <!-- Contenido principal de la página -->
      <ion-content fullscreen @ionScroll="handleScroll" :scroll-events="true">
        <!-- Navbar visible siempre -->
        <Navbar :class="['navbar', { 'navbar-visible': showNav }]" />

        <!-- Contenedor de filtros -->
        <div class="filters-container">
          <ion-item class="filter-item">
            <ion-label>Filtra por deporte</ion-label>
            <ion-select v-model="selectedSport" placeholder="Selecciona deporte">
              <ion-select-option :value="null">Todos</ion-select-option>
              <ion-select-option
                v-for="sport in sports"
                :key="sport.id"
                :value="sport.id"
              >
                {{ sport.name }}
              </ion-select-option>
            </ion-select>
          </ion-item>

          <ion-item class="filter-item">
            <ion-label>Filtra por año</ion-label>
            <ion-select v-model="selectedYear" placeholder="Selecciona la temporada">
              <ion-select-option :value="null">Todos</ion-select-option>

              <ion-select-option
                v-for="year in years"
                :key="year.toString()"
                :value="year"
              >
                {{ year }}
              </ion-select-option>
            </ion-select>
          </ion-item>
        </div>
        <IonList class="tournament-list">
          <!-- Lista dinámica de torneos filtrados -->
            <TournamentCard
              v-for="(matchesList, tournamentId) in groupedMatches"
              :key="tournamentId"
              :matches="matchesList"
            />

          <!-- Mensaje si no hay resultados -->
          <!-- <ion-item
            v-if="filteredTournaments.length === 0"
            lines="none"
            class="no-results"
          >No hay torneos que coincidan con los filtros.</ion-item> -->
        </IonList>

        <Footer />

      </ion-content>
   </ion-page>
</template>
<script setup lang="ts">
import { IonPage, IonContent, IonList,IonItem, IonLabel, IonSelect, IonSelectOption } from '@ionic/vue';
import Navbar from '@/components/layout/Navbar.vue';
import { useNavbarVisibility } from '@/composables/useNavbarVisibility';
import Footer from '@/components/ui/Footer.vue';
import TournamentCard from '@/components/layout/TournamentCard.vue'

// 1. Importamos los helpers de Vue
import { ref, watch, onMounted, computed } from 'vue';

// 2. Importamos nuestros métodos del servicio
import { getTournament } from '@/services/tournamentService';

// import { getSports, getYears, getTournament } from '@/services/tournamentService';
import type { Match } from '@/model/match';
import type { Tournament } from '@/model/tournament';
import type { Sport } from '@/model/sport';


// 3. Extraemos el control de la navbar
const { showNav, handleScroll } = useNavbarVisibility();

// 4. Definimos refs reactivas para filtros y datos
const sports = ref<Sport[]>([]);
const years = ref<String[]>([]);
const selectedSport = ref<number | null>(null);
const selectedYear = ref<string | null>(null);
// const tournament = ref<any>(null);

// Estados de carga y error
const isLoadingFilters = ref(false);
const isLoadingTournament = ref(false);
const error = ref<string | null>(null);

const sportsList: Sport[] = [
  { id: 1, name: 'Fútbol'},
  { id: 2, name: 'Baloncesto'},
  { id: 3, name: 'Tenis'},
  { id: 4, name: 'Voleibol' },
  { id: 5, name: 'Ping-pong'}
];

const ageList: string[] = [
  '2018/19',
  '2019/20',
  '2020/21',
  '2021/22',
  '2022/23',
  '2023/24',
  '2024/25'
];

// Deporte y torneo compartido
const sport: Sport = { id: 1, name: 'Fútbol' };
const tournament: Tournament = {
  id: 1,
  name: 'Copa Ejemplo',
  date: '2023/24',
  state: 0,
  sport: sport
};

// Deporte y torneo compartido
const sport3: Sport = { id: 3, name: 'Tenis' };
const tournament2: Tournament = {
  id: 2,
  name: 'Torneaso de Tenis',
  date: '2024/25',
  state: 0,
  sport: sport3
};

const matches: Match[] = [
  // Octavos de final (Octavos)
  { id: 1,  date: new Date('2025-06-01T18:00:00'), round: 'Octavos', tournament,
    team1: { id: 1, name: 'Equipo A' }, team2: { id: 2, name: 'Equipo B' } },
  { id: 2,  date: new Date('2025-06-01T20:00:00'), round: 'Octavos', tournament,
    team1: { id: 3, name: 'Equipo C' }, team2: { id: 4, name: 'Equipo D' } },
  { id: 3,  date: new Date('2025-06-02T18:00:00'), round: 'Octavos', tournament,
    team1: { id: 5, name: 'Equipo E' }, team2: { id: 6, name: 'Equipo F' } },
  { id: 4,  date: new Date('2025-06-02T20:00:00'), round: 'Octavos', tournament,
    team1: { id: 7, name: 'Equipo G' }, team2: { id: 8, name: 'Equipo H' } },
  { id: 5,  date: new Date('2025-06-03T18:00:00'), round: 'Octavos', tournament,
    team1: { id: 9,  name: 'Equipo I' }, team2: { id: 10, name: 'Equipo J' } },
  { id: 6,  date: new Date('2025-06-03T20:00:00'), round: 'Octavos', tournament,
    team1: { id: 11, name: 'Equipo K' }, team2: { id: 12, name: 'Equipo L' } },
  { id: 7,  date: new Date('2025-06-04T18:00:00'), round: 'Octavos', tournament,
    team1: { id: 13, name: 'Equipo M' }, team2: { id: 14, name: 'Equipo N' } },
  { id: 8,  date: new Date('2025-06-04T20:00:00'), round: 'Octavos', tournament,
    team1: { id: 15, name: 'Equipo O' }, team2: { id: 16, name: 'Equipo P' } },

  // Cuartos de final
  { id: 9,  date: new Date('2025-06-05T18:00:00'), round: 'Cuartos', tournament,
    team1: { id: 1, name: 'Equipo A' }, team2: { id: 3, name: 'Equipo C' } },
  { id: 10, date: new Date('2025-06-05T20:00:00'), round: 'Cuartos', tournament,
    team1: { id: 5, name: 'Equipo E' }, team2: { id: 7, name: 'Equipo G' } },
  { id: 11, date: new Date('2025-06-06T18:00:00'), round: 'Cuartos', tournament,
    team1: { id: 9, name: 'Equipo I' }, team2: { id: 11, name: 'Equipo K' } },
  { id: 12, date: new Date('2025-06-06T20:00:00'), round: 'Cuartos', tournament,
    team1: { id: 13, name: 'Equipo M' }, team2: { id: 15, name: 'Equipo O' } },

  // Semifinales
  { id: 13, date: new Date('2025-06-07T19:00:00'), round: 'Semi-finales', tournament,
    team1: { id: 1, name: 'Equipo A' }, team2: { id: 5, name: 'Equipo E' } },
  { id: 14, date: new Date('2025-06-07T21:00:00'), round: 'Semi-finales', tournament,
    team1: { id: 9, name: 'Equipo I' }, team2: { id: 13, name: 'Equipo M' } },

  // Final
  { id: 15, date: new Date('2025-06-08T20:00:00'), round: 'Final', tournament,
    team1: { id: 1, name: 'Equipo A' }, team2: { id: 9, name: 'Equipo I' } },

  { id: 1,  date: new Date('2025-06-01T18:00:00'), round: 'Octavos', tournament: tournament2,
    team1: { id: 1, name: 'Equipo A' }, team2: { id: 2, name: 'Equipo B' } },
  { id: 1,  date: new Date('2025-06-01T18:00:00'), round: 'Octavos', tournament: tournament2,
    team1: { id: 1, name: 'Equipo A' }, team2: { id: 2, name: 'Equipo B' } },
  { id: 1,  date: new Date('2025-06-01T18:00:00'), round: 'Octavos', tournament: tournament2,
    team1: { id: 1, name: 'Equipo A' }, team2: { id: 2, name: 'Equipo B' } },
];

const groupedMatches = computed<Record<number, Match[]>>(() =>
//Creamos este mapa con todos los partidos que cumplen con todos los filtros
  filteredMatches.value.reduce((acc, m) => {
    const id = m.tournament.id;
    if (!acc[id]){ 
      acc[id] = [];
    }
    acc[id].push(m);
    return acc;
    //acc es un map clave-valor, donde la clave es el id del torneo y el valor es un array de partidos
  }, {} as Record<number, Match[]>)
);

// 5. Función loadFilters(): carga deportes y años al iniciar
async function loadFilters() {
  isLoadingFilters.value = true;
  error.value = null;
  try {
    sports.value = sportsList;
    // sports.value = await getSports();
    // years.value = tournamentsList;
    years.value = ageList;
    // years.value  = await getYears();
  } catch (e: any) {
    error.value = 'No se pudieron cargar los filtros.';
    console.error(e);
  } finally {
    isLoadingFilters.value = false;
  }
}

// 6. onMounted(): al montar el componente, ejecuta loadFilters()
onMounted(() => {
  loadFilters();
});

// 7. watch(): observa cambios en los selects y trae el torneo
// watch(
//   [selectedSport, selectedYear],
//   async ([sport, year]) => {
//     if (sport && year) {
//       isLoadingTournament.value = true;
//       error.value = null;
//       try {
//         tournament.value = await getTournament(sport, year);
//       } catch (e: any) {
//         error.value = 'Error al cargar el torneo.';
//         console.error(e);
//       } finally {
//         isLoadingTournament.value = false;
//       }
//     }
//   }
// );

// 8. Computed para filtrar la lista de torneos
const filteredMatches = computed(() => {
  return matches.filter(m =>
    (!selectedSport.value || m.tournament.sport.id === selectedSport.value) &&
    (!selectedYear.value  || m.tournament.date === selectedYear.value)
  );
});
</script>

<style scoped>
.filters-container {
  display: flex;
  align-items: center;
  gap: 3rem;
  padding: 0.75rem 1rem;
  margin: 6rem 8rem 0.5rem; 
  background: #ffffff;
  border-radius: 8px;
  box-shadow: 0 2px 6px rgba(0,0,0,0.1);
  background-color: #f2f3f3;
}

.filter-item {
  flex: 1;           
  flex-direction: column;
  align-items: center;
  justify-content: center;
  --padding-end: 0;
  --background: rgb(255, 255, 255);
  --border-radius: 10px;
}

.filter-item ion-label,
.filter-item ion-select {
  color: #0a2540;
  font-weight: 600;
  text-align: center;
  width: 100%;  
}

@media (max-width: 600px) {
  .filters-container {
    flex-direction: column;
    gap: 5rem;       
    margin: 5rem 1rem; /* ajusta márgenes laterales para móvil */
    
  }
}

.tournament-container {
  display: grid;
  grid-template-columns: 1fr auto 1fr;
  gap: 1rem;
  align-items: start;
}

.error {
  font-weight: 500;  
  font-size: 1.2rem;
  text-align: center;
}

.tournament-list {
  margin: 1.5rem;
  margin-bottom: 6rem;
  padding: 1rem;
  background: #ffffff;
}

.tournament-item {
  margin-bottom: 0.5rem;
  --background: #f9f9f9;
  border-radius: 6px;
  padding: 0.75rem 1rem;
}
.no-results {
  text-align: center;
  font-style: italic;
  margin: 1rem;
}

</style>
