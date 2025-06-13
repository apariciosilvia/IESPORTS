<template>
  <ion-page>

    <Loader v-if="isNowLoading" />
    
    <ion-content fullscreen @ionScroll="handleScroll" :scroll-events="true">
      <Navbar :class="['navbar', { 'navbar-visible': showNav }]" />

      <!-- Contenedor de filtros (deporte + búsqueda de nombre) -->
      <div class="filters-container">
        <!-- Filtro por deporte -->
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

        <!-- Campo de búsqueda por nombre de equipo -->
        <ion-item class="filter-item">
          <ion-searchbar
            v-model="searchTerm"
            placeholder="Escribe el nombre de un equipo..."
            class="custom-search"
          />
        </ion-item>
      </div>

      <!-- Spinner de carga -->
      <ion-spinner v-if="isLoading" name="crescent" />

      <!-- Mensaje de error -->
      <div v-if="error" class="error">{{ error }}</div>

      <!-- Rejilla de equipos filtrados -->
      <div class="teams-container" v-if="!isLoading && !error">
        <TeamInfoCard
          v-for="info in filteredTeams"
          :key="info.team.id"
          :teamInfo="info"
        />
      </div>


      <!-- Mensaje si no hay resultados -->
      <div v-if="Object.keys(filteredTeams).length === 0" class="no-results">
        <span class="material-symbols-outlined">search_off</span>
          <p class="no-results-text">No se encontraron equipos con los filtros seleccionados.</p>
          <p class="no-results-subtext">Prueba a cambiar los filtros o restablecer la búsqueda.</p>
      </div>

      <Footer />
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import {
  IonPage,
  IonContent,
  IonSpinner,
  IonSelect,
  IonSelectOption,
  IonLabel,
  IonItem,
  IonSearchbar,
} from '@ionic/vue';
import Navbar from '@/components/layout/Navbar.vue';
import Footer from '@/components/ui/Footer.vue';
import { useNavbarVisibility } from '@/composables/useNavbarVisibility';
import { ref, watch, onMounted, computed } from 'vue';
import TeamInfoCard from '@/components/layout/TeamInfoCard.vue';

import { getTeamsInfo } from '@/services/teamService';
import { getSports } from '@/services/sportService';

import type { Sport } from '@/model/sport';
import type { TeamInfoDTO } from '@/model/dto/teamInfoDTO';
import Loader from '@/components/ui/Loader.vue';


import { useLoadingEffect } from '@/composables/useLoadingEffect';

const { isNowLoading } = useLoadingEffect();

const { showNav, handleScroll } = useNavbarVisibility();

//Estados reactivos para deportes, equipos, carga, error, filtro deporte y búsqueda
const sports = ref<Sport[]>([]);
const selectedSport = ref<number | null>(null);
const teamInfo = ref<TeamInfoDTO[]>([]);
const isLoading = ref(false);
const error = ref<string | null>(null);

const searchTerm = ref('');

//Cargar deportes y equipos al montar
async function loadSports() {
  isLoading.value = true;
  error.value = null;
  try {
    sports.value = await getSports();
  } catch (e: any) {
    error.value = 'No se pudieron cargar los deportes.';
    console.error(e);
  } finally {
    isLoading.value = false;
  }
}

async function loadTeamsInfo() {
  isLoading.value = true;
  error.value = null;
  try {
    teamInfo.value = await getTeamsInfo();
  } catch (e: any) {
    error.value = 'Error al cargar los equipos.';
    console.error(e);
  } finally {
    isLoading.value = false;
  }
}

onMounted(async () => {
  await loadSports();
  await loadTeamsInfo();
});

//Computed que combina filtro por deporte + búsqueda por nombre
const filteredTeams = computed(() => {
  // Convertimos el término de búsqueda a minúsculas para comparar
  const term = searchTerm.value.trim().toLowerCase();

  return teamInfo.value.filter((info) => {
    // Si hay deporte seleccionado, comprobamos que el equipo participe en él
    if (selectedSport.value !== null) {
      const participa = info.sports.some((s) => s.id === selectedSport.value);
      if (!participa) return false;
    }
    //Si hay término de búsqueda, filtramos por nombre de equipo
    if (term !== '') {
      return info.team.name.toLowerCase().includes(term);
    }
    // Si no hay búsqueda o deporte, devolvemos true (aparecen todos)
    return true;
  });
});

// Cuando cambie el deporte, se recarga la lista original (para no acumular varios filtros sucesivos)
watch(selectedSport, async () => {
  isLoading.value = true;
  error.value = null;
  try {
    teamInfo.value = await getTeamsInfo();
  } catch (e: any) {
    error.value = 'Error al recargar los equipos.';
    console.error(e);
  } finally {
    isLoading.value = false;
  }
});
</script>

<style scoped>
.filters-container {
  display: flex;
  justify-content: center;
  align-items: flex-end;
  padding: 0.75rem 1rem;
  margin: 5rem 4rem 2rem; 
  background: #f2f3f3;
  border-radius: 8px;
  box-shadow: 0 2px 6px rgba(0,0,0,0.1);
  gap: 1rem;
}

@media (max-width: 600px) {
  .filters-container {
    flex-direction: column;
    align-items: stretch;
    margin: 2rem 0.5rem;
  }
}

.filter-item {
  flex: 1;
  display: flex;
  flex-direction: column;
  --padding-end: 0;
  --background: #fff;
  --border-radius: 10px;
}

.filter-item ion-label,
.filter-item ion-select,
.filter-item ion-searchbar {
  width: 100%;
  font-weight: 600;
}



.teams-container {
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  gap: 2rem;
  max-width: 1800px;
  margin: 1rem auto;
  padding: 0 1rem;
  box-sizing: border-box;
  margin-bottom: 3%;
}

@media (max-width: 1100px) {
  .teams-container {
    grid-template-columns: repeat(3, 1fr);
    margin-bottom: 4%;
  }
}

@media (max-width: 800px) {
  .teams-container {
    grid-template-columns: repeat(2, 1fr);
    margin-bottom: 6%;
  }
}

@media (max-width: 500px) {
  .teams-container {
    grid-template-columns: 1fr;
    padding: 0 1rem;
  }
   .filters-container {
    flex-direction: column;
    align-items: stretch;
    margin: 15% 1rem 2rem;
  }
}

@media (max-width: 450px) {
  .teams-container {
    grid-template-columns: 1fr;
    padding: 0 1rem;
    margin-bottom: 9%;
  }
  .filters-container {
    flex-direction: column;
    align-items: stretch;
    margin: 25% 1rem 2rem;
  }
}

.teams-container > * {
  width: 100%;
  max-width: 100%;
  box-sizing: border-box;
  overflow-wrap: break-word;
}

.error {
  font-weight: 500;
  font-size: 1.3rem;
  text-align: center;
  margin-top: 2rem;
}

.custom-search {
  --border-radius: 8px;
  --box-shadow: none;
  text-align: left;
  max-height: 40px;
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
</style>
