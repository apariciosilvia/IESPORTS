<template>
  <ion-page>
    <ion-content fullscreen @ionScroll="handleScroll" :scroll-events="true">
      <Navbar :class="['navbar', { 'navbar-visible': showNav }]" />

      <!-- Contenedor de filtros (deporte + búsqueda de nombre) -->
      <div class="filters-container">
        <!-- Filtro por deporte -->
        <ion-item class="filter-item">
          <ion-label>Deporte</ion-label>
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
import type { TeamInfoDTO } from '@/model/teamInfoDTO';

const { showNav, handleScroll } = useNavbarVisibility();

// 1) Estados reactivos para deportes, equipos, carga, error, filtro deporte y búsqueda
const sports = ref<Sport[]>([]);
const selectedSport = ref<number | null>(null);
const teamInfo = ref<TeamInfoDTO[]>([]);
const isLoading = ref(false);
const error = ref<string | null>(null);

// Nuevo: término de búsqueda para filtrar por nombre
const searchTerm = ref('');

// 2) Cargar deportes y equipos al montar
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

// 3) Computed que combina filtro por deporte + búsqueda por nombre
const filteredTeams = computed(() => {
  // Convertimos el término de búsqueda a minúsculas para comparar
  const term = searchTerm.value.trim().toLowerCase();

  return teamInfo.value.filter((info) => {
    // 3.1) Si hay deporte seleccionado, comprobamos que el equipo participe en él
    if (selectedSport.value !== null) {
      const participa = info.sports.some((s) => s.id === selectedSport.value);
      if (!participa) return false;
    }
    // 3.2) Si hay término de búsqueda, filtramos por nombre de equipo
    if (term !== '') {
      return info.team.name.toLowerCase().includes(term);
    }
    // Si no hay búsqueda o deporte, devolvemos true (aparecen todos)
    return true;
  });
});

// 4) Cuando cambie el deporte, se recarga la lista original
//    (para no acumular varios filtros sucesivos)
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
  gap: 1rem; /* separa cada filtro */
}

/* Adaptación para pantallas pequeñas */
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
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 2rem;
  padding: 1rem 1.5rem;
  justify-content: center;
  margin-bottom: 2rem;
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
</style>
