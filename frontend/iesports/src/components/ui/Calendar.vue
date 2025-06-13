<template>
  <div class="calendar-container">
    <FullCalendar :options="calendarOptions" />
    <ion-modal :is-open="showModal" @ionModalDidDismiss="closeModal">
      <ion-header>
        <ion-toolbar>
          <ion-title>{{ selectedMatch?.tournament.name }}</ion-title>
          <ion-buttons slot="end">
            <ion-button class="button-close" @click="closeModal"><span class="material-symbols-outlined">close</span></ion-button>
          </ion-buttons>
        </ion-toolbar>
      </ion-header>
      <ion-content class="ion-padding">
        <p class="content"><strong>Partido: </strong>{{ selectedMatch?.team1.name }} vs {{ selectedMatch?.team2.name }}</p>
        <p class="content"><strong>Fecha:</strong> {{ formattedDate }}</p>
        <p class="content"><strong>Ronda:</strong> {{ selectedMatch?.round }}</p>
        <p class="content"><strong>Resultado:</strong> {{ selectedMatch?.pointsTeam1 }} - {{ selectedMatch?.pointsTeam2 }}</p>
        <div class="team-block team1">
          <p><strong>Equipo 1:</strong></p>
          <div class="player-list">
            <div v-for="p in selectedMatch?.team1.players || []" :key="p.id" class="player-item">
              <span class="player-badge team1"></span>
              <span class="player-name">{{ p.name }}</span>
            </div>
          </div>
        </div>
        <div class="team-block team2">
          <p><strong>Equipo 2:</strong></p>
          <div class="player-list">
            <div v-for="p in selectedMatch?.team2.players || []" :key="p.id" class="player-item">
              <span class="player-badge team2"></span>
              <span class="player-name">{{ p.name }}</span>
            </div>
          </div>
        </div>
      </ion-content>
    </ion-modal>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, watch } from 'vue'
import FullCalendar from '@fullcalendar/vue3'
import dayGridPlugin from '@fullcalendar/daygrid'
import esLocale from '@fullcalendar/core/locales/es'
import type { Match } from '@/model/match'
import { IonModal, IonHeader, IonToolbar, IonTitle, IonButtons, IonButton, IonContent } from '@ionic/vue'

const props = defineProps<{ matches: Match[] }>()

const showModal = ref(false)
const selectedMatch = ref<Match | null>(null)

const formattedDate = computed(() => {
  if (!selectedMatch.value?.date) return ''
  return new Date(selectedMatch.value.date as any).toLocaleDateString('es-ES')
})

const events = computed(() =>
  props.matches
    .filter(m => m.date)
    .map(m => {
      // formatea la fecha en YYYY-MM-DD en zona local para evitar desfase UTC
      const isoDate = new Date(m.date as any).toLocaleDateString('en-CA')
      return {
        title: `${m.team1.name} vs ${m.team2.name}`,
        start: isoDate,
        allDay: true,
        color: '#FF6D43',
        extendedProps: m
      }
    })
)

const calendarOptions = ref({
  plugins: [dayGridPlugin],
  initialView: 'dayGridMonth',
  locale: esLocale,
  headerToolbar: {
    left: 'prev,next today',
    center: 'title',
    right: 'dayGridMonth,dayGridWeek,dayGridDay'
  },
  weekends: true,
  height: 'auto',
  dayMaxEvents: true,
  showNonCurrentDates: false,
  events: events.value,
  eventClick: (info: { event: { extendedProps: any } }) => {
    selectedMatch.value = info.event.extendedProps as any
    showModal.value = true
  },
  dayCellDidMount: (arg: any) => {
    arg.el.style.backgroundColor = '#ffffff'
    if ([0,6].includes(arg.date.getDay())) {
      arg.el.style.backgroundColor = '#f0f0f0'
    }
  }
})

watch(events, val => {
  calendarOptions.value.events = val
})

function closeModal() {
  showModal.value = false
}
</script>



<style scoped>
.calendar-container {
  max-width: 900px;
  margin: 10rem auto;
  padding: 1rem;
  background: #ffffff;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  border-radius: 12px;
}

/* FullCalendar core overrides */
.fc {
  border: none;
  border-radius: 8px;
}

.fc-header-toolbar {
  margin-bottom: 0.5rem;
}

/* Botones personalizados */
::v-deep .fc-button {
  border: none;
  border-radius: 6px;
  padding: 0.5rem 1rem;
  font-weight: 500;
  background-color: #f03726 !important;
  color: #fff !important;
  transition: background-color 0.2s;
}

::v-deep .fc-button:hover,
::v-deep .fc-button:focus {
  background-color: #f03726 !important;
  outline: none;
}

/* Texto de los días de la semana */
::v-deep .fc-col-header-cell-cushion {
  color: #000 !important;
  font-weight: 600;
}

/* Número de días en negro para todos los días */
::v-deep .fc-daygrid-day-number {
  font-size: 0.9rem;
  color: #000 !important;
}

/* Resaltar día actual */
::v-deep .fc-day-today .fc-daygrid-day-number {
  border: 3px solid #f03726 !important;
  border-radius: 50%;
  padding: 0.2rem;
}

.fc-daygrid-day:hover {
  background-color: rgba(255, 109, 67, 0.1);
  cursor: pointer;
}

/* Estilo de eventos */
::v-deep .fc-event {
  border: none;
  border-radius: 4px;
  padding: 4px 6px;
  font-size: 0.8rem;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.356);
  background-color: #f03726 !important;
  border-color: #f03726 !important;
  color: #fff !important;
  transition: transform 0.1s;
}

::v-deep .fc-event:hover {
  transform: translateY(-1px);
}

/* Fondo de scrollgrid */
.fc-scrollgrid .fc-scrollgrid-section {
  background-color: #fdfdfd;
}







/* Estilos para el modal */
::v-deep ion-modal {
  --width: 70%;
  --max-width: 500px;
  --border-radius: 12px;
  --box-shadow: 0 8px 24px rgba(0, 0, 0, 0.2);
}

::v-deep ion-header {
  background: #002f3d;
  color: #fff;
}

::v-deep ion-toolbar {
  --background: transparent;
}

::v-deep ion-title {
  font-size: 1.25rem;
  font-weight: bold;
  text-align: center;
  color: #fff;
  margin-left: 3rem;
}

::v-deep .button-close .material-symbols-outlined {
  color: #fff;
}

::v-deep ion-content {
  background: #fafafa;
}

::v-deep ion-content p {
  margin: 0.75rem 0;
  line-height: 1.4;
}

.content{
  text-align: left;
  padding-left: 1rem;
}

.team-block {
  padding: 0.5rem;
  margin-bottom: 1rem;
  border-radius: 6px;
}

.team1 {
  background: #e0f0ff;
  text-align: left;
  padding-left: 1rem;
}

.team2 {
  background: #ffe0e0;
  text-align: left;
  padding-left: 1rem;
}

.player-list {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
  margin-top: 0.5rem;
  margin-left: 1.5rem;
}

.player-item {
  display: flex;
  align-items: center;
}

.player-badge {
  width: 12px;
  height: 12px;
  border-radius: 2px;
  margin-right: 0.5rem;
}

.player-badge.team1 {
  background: #007aff;
}

.player-badge.team2 {
  background: #ff3b30;
}

.player-name {
  font-size: 0.9rem;
  color: #333;
}

::v-deep ion-button {
  --background: transparent;
  --color: #fff;
  --padding-start: 0.5rem;
  --padding-end: 0.5rem;
}

::v-deep ion-button:hover {
  opacity: 0.8;
}
</style>
