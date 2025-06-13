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
          <div v-if="Object.keys(groupedMatches).length === 0" class="no-results">
            <span class="material-symbols-outlined">search_off</span>
            <p class="no-results-text">No se encontraron torneos con los filtros seleccionados.</p>
            <p class="no-results-subtext">Prueba a cambiar los filtros o restablecer la búsqueda.</p>
          </div>
          
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

import { ref, onMounted, computed } from 'vue';

// Importamos nuestros métodos del servicio
// import { getTournament } from '@/services/tournamentService';
import { getSports } from '@/services/sportService';
import { getYears } from '@/services/tournamentService';
import { getMatches } from '@/services/matchService';

// import { getSports, getYears, getTournament } from '@/services/tournamentService';
import type { Match } from '@/model/match';
// import type { Tournament } from '@/model/tournament';
import type { Sport } from '@/model/sport';


//Extraemos el control de la navbar
const { showNav, handleScroll } = useNavbarVisibility();

//Definimos refs reactivas para filtros y datos
const sports = ref<Sport[]>([]);
const years = ref<String[]>([]);
const matches = ref<Match[]>([]);
const selectedSport = ref<number | null>(null);
const selectedYear = ref<string | null>(null);

// Estados de carga y error
const isLoadingFilters = ref(false);
// const isLoadingTournament = ref(false);
const error = ref<string | null>(null);

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

// Función loadFilters(): carga deportes y años al iniciar
async function loadFilters() {
  isLoadingFilters.value = true;
  error.value = null;
  try {
    sports.value = await getSports();
    console.log('sports:', sports.value);
    
    years.value  = await getYears();
    console.log('years:', years.value);

    matches.value = await getMatches();
  } catch (e: any) {
    error.value = 'No se pudieron cargar los filtros.';
    console.error(e);
  } finally {
    isLoadingFilters.value = false;
  }
}

// onMounted(): al montar el componente, ejecuta loadFilters()
onMounted(() => {
  loadFilters();
});

// watch(): observa cambios en los selects y trae el torneo
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

// Computed para filtrar la lista de torneos
const filteredMatches = computed(() => {
  return matches.value.filter(m =>
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
  margin: 6rem 6rem 0; 
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
  margin: 0 1.5rem 6rem;
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
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  padding: 2rem 1rem;
  color: #666;
  text-align: center;
  box-sizing: border-box;
}

/* Móviles pequeños (hasta 480px) */
@media (max-width: 480px) {
  .no-results {
    padding: 1rem;
    min-height: 30vh;
  }

  .no-results .material-symbols-outlined {
    font-size: 6rem;
  }

  .no-results-text {
    font-size: 1rem;
  }

  .no-results-subtext {
    font-size: 0.9rem;
  }
}

/* Móviles grandes y tablets (481px a 768px) */
@media (min-width: 481px) and (max-width: 768px) {
  .no-results {
    min-height: 50vh;
  }
  .no-results .material-symbols-outlined {
    font-size: 7rem;
  }

  .no-results-text {
    font-size: 1.1rem;
  }

  .no-results-subtext {
    font-size: 1rem;
  }
}

/* Portátiles (769px a 1024px) */
@media (min-width: 769px) and (max-width: 1024px) {
  .no-results {
    min-height: 60vh;
  }
  .no-results .material-symbols-outlined {
    font-size: 8rem;
  }

  .no-results-text {
    font-size: 1.2rem;
  }

  .no-results-subtext {
    font-size: 1.05rem;
  }
}

/* Pantallas grandes (más de 1024px) */
@media (min-width: 1025px) {

  .no-results .material-symbols-outlined {
    font-size: 9rem;
  }

  .no-results-text {
    font-size: 1.3rem;
  }

  .no-results-subtext {
    font-size: 1.1rem;
  }
}


.no-results .material-symbols-outlined {
  font-size: 9rem;
  color: #aaa;
  margin-bottom: 1rem;
}

.no-results-text {
  font-size: 1.2rem;
  font-weight: bold;
  margin-bottom: 0.5rem;
}

.no-results-subtext {
  font-size: 1rem;
  font-style: italic;
  color: #999;
}

@media (max-width: 991px) {
  .filters-container {
    flex-direction: column;
    gap: 2rem;
    margin-top: 6.5rem;
    margin-bottom: 0.75rem;
  }

  .filter-item {
    width: 100% !important;
    display: flex !important;
    flex-direction: column !important;
    align-items: stretch !important;
  }
}

</style>
