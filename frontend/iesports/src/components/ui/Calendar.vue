<template>
  <div class="calendar-container">
    <ScheduleXCalendar :calendar-app="calendarApp" />
  </div>
</template>

<script setup lang="ts">
import { defineProps, computed, watch, h } from 'vue'
import { createCalendar, createViewMonthGrid, type CalendarEvent } from '@schedule-x/calendar'
import { ScheduleXCalendar } from '@schedule-x/vue'
import type { Match } from '@/model/match'

// Recibimos la lista de matches desde la prop
const props = defineProps<{ matches: Match[] }>()

// Función para formatear fecha ISO al formato "YYYY-MM-DD HH:mm"
function fmt(dt: string): string {
  return `${dt.slice(0, 10)} ${dt.slice(11, 16)}`
}

// Computed: mapeamos matches a eventos del calendario
const mappedEvents = computed<CalendarEvent[]>(() =>
  props.matches.map(m => ({
    id: String(m.id),
    title: `${m.team1.name} vs ${m.team2.name}`,
    start: fmt(typeof m.date === 'string' ? m.date : m.date.toISOString()),
    end: fmt(typeof m.date === 'string' ? m.date : m.date.toISOString()),
    meta: m,
  }))
)

// Creamos la instancia del calendario, forzando tipo any para eventRenderer
const calendarApp = createCalendar({
  selectedDate: new Date().toISOString().slice(0, 10),
  views: [ createViewMonthGrid() ],
  events: mappedEvents.value,
  // @ts-ignore: eventRenderer no está en la definición oficial
  eventRenderer: ({ event }: { event: CalendarEvent }) => {
    const m = event.meta as Match
    const tooltip =
      `${m.team1.name} ${m.pointsTeam1} - ${m.pointsTeam2} ${m.team2.name}\n` +
      `Ronda: ${m.round}`
    return h(
      'div',
      { title: tooltip, class: 'sx-event--with-tooltip' },
      event.title
    )
  }
} as any)

// Watch para actualizar eventos cuando cambie props.matches
watch(
  mappedEvents,
  (events) => calendarApp.events.set(events),
  { deep: true }
)
</script>

<style>
/* Asegúrate en main.ts de importar primero el theme-default, luego este componente. */

/* Contenedor */
.calendar-container {
  max-width: 1400px;
  margin: 8% auto 0;
}

</style>
