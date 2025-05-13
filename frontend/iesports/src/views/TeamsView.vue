<template>
  <ion-page>
    <ion-content fullscreen @ionScroll="handleScroll" :scroll-events="true">
      <Navbar :class="['navbar', { 'navbar-visible': showNav }]" />

      <!-- Contenedor de filtros (solo deporte) -->
      <div class="filters-container">
        <ion-item class="filter-item">
          <ion-label>Deporte</ion-label>
          <ion-select v-model="selectedSport" placeholder="Selecciona deporte">
            <ion-select-option
              v-for="sport in sports"
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
      <div class="teams-container" v-if="teams.length && !isLoading">
        <div v-for="team in teams" :key="team.id" class="team-card">
          {{ team.name }}
        </div>
      </div>

    </ion-content>
    <Footer />

    
  </ion-page>
</template>

<script setup lang="ts">
import { IonPage, IonContent } from '@ionic/vue';
import Navbar from '@/components/layout/Navbar.vue';
import Footer from '@/components/ui/Footer.vue';
import { useNavbarVisibility } from '@/composables/useNavbarVisibility';
import { ref, watch, onMounted } from 'vue';
import { getSports } from '@/services/tournamentService';
import { getTeams } from '../services/teamService';

const { showNav, handleScroll } = useNavbarVisibility();

// Estado reactivo
const sports = ref<{ id: number; name: string }[]>([]);
const selectedSport = ref<number | null>(null);
const teams = ref<{ id: number; name: string }[]>([]);
const isLoading = ref(false);
const error = ref<string | null>(null);

// Carga inicial de deportes
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

onMounted(loadSports);

// Cuando cambie el deporte seleccionado, carga los equipos
watch(
  selectedSport,
  async (sportId) => {
    if (sportId !== null) {
      isLoading.value = true;
      error.value = null;
      try {
        teams.value = await getTeams(sportId);
      } catch (e: any) {
        error.value = 'Error al cargar los equipos.';
        console.error(e);
      } finally {
        isLoading.value = false;
      }
    } else {
      teams.value = [];
    }
  }
);
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
  grid-template-columns: repeat(auto-fill, minmax(120px, 1fr));
  gap: 1rem;
  padding: 1rem;
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
