<template>
  <div class="tournament-container">
    <!-- IZQUIERDA: Octavos -->
    <div class="group-left" v-if="roundsGroups['OCTAVOS']">
      <h3>{{ formatRoundName('OCTAVOS') }}</h3>
      <div
        v-for="m in roundsGroups['OCTAVOS']"
        :key="m.id"
        class="match"
      >
        <div class="match-teams">
          {{ m.team1.name }} ({{ m.pointsTeam1 }} Puntos) VS
          {{ m.team2.name }} ({{ m.pointsTeam2 }} Puntos)
        </div>
      </div>
    </div>

    <!-- CENTRO: Final -->
    <div class="center" v-if="roundsGroups['FINAL']">
      <h1>{{ tournamentName }}</h1>
      <div class="trophy">
        <img src="c:/Users/apari/Downloads/copa-trofeo.png" width="100px" />
      </div>
      <h2>{{ formatRoundName('FINAL') }}</h2>
      <div
        v-for="m in roundsGroups['FINAL']"
        :key="m.id"
        class="match"
      >
        <div class="match-teams">
          {{ m.team1.name }} ({{ m.pointsTeam1 }} Puntos) VS
          {{ m.team2.name }} ({{ m.pointsTeam2 }} Puntos)
        </div>
        <div class="match-winner">
          Ganador: {{ m.winnerTeam.name }}
        </div>
      </div>
    </div>

    <!-- DERECHA: Cuartos y Semifinal -->
    <div class="group-right">
      <div v-if="roundsGroups['CUARTOS_FINAL']">
        <h3>{{ formatRoundName('CUARTOS_FINAL') }}</h3>
        <div
          v-for="m in roundsGroups['CUARTOS_FINAL']"
          :key="m.id"
          class="match"
        >
          <div class="match-teams">
            {{ m.team1.name }} ({{ m.pointsTeam1 }} Puntos) VS
            {{ m.team2.name }} ({{ m.pointsTeam2 }} Puntos)
          </div>
        </div>
      </div>

      <div v-if="roundsGroups['SEMIFINAL']">
        <h3>{{ formatRoundName('SEMIFINAL') }}</h3>
        <div
          v-for="m in roundsGroups['SEMIFINAL']"
          :key="m.id"
          class="match"
        >
          <div class="match-teams">
            {{ m.team1.name }} ({{ m.pointsTeam1 }} Puntos) VS
            {{ m.team2.name }} ({{ m.pointsTeam2 }} Puntos)
          </div>
        </div>
      </div>
    </div>
  </div>
</template>





<script setup lang="ts">
import { computed, defineProps } from 'vue';
import type { Match } from '@/model/match';

const props = defineProps<{ matches: Match[] }>();

// 1) Orden de las fases que queremos mostrar
const roundOrder = ['OCTAVOS', 'CUARTOS_FINAL', 'SEMIFINAL', 'FINAL'] as const;

// 2) Nombre de la copa (del primer partido)
const tournamentName = computed(() =>
  props.matches.length ? props.matches[0].tournament.name : ''
);

// 3) Función para convertir clave de ronda en texto legible
function formatRoundName(key: typeof roundOrder[number]): string {
  switch (key) {
    case 'OCTAVOS': return 'Octavos de Final';
    case 'CUARTOS_FINAL': return 'Cuartos de Final';
    case 'SEMIFINAL': return 'Semifinal';
    case 'FINAL': return 'Final';
  }
}

// 4) Agrupar partidos por ronda
const roundsGroups = computed<Record<string, Match[]>>(() =>
  props.matches.reduce((acc, m) => {
    (acc[m.round] = acc[m.round] || []).push(m);
    return acc;
  }, {} as Record<string, Match[]>)
);

console.log('MATCHES', props.matches)
</script>



<style scoped>
.tournament-container {
  display: flex;
  justify-content: center;
  gap: 20px;
  padding: 60px;
  flex-wrap: wrap;
  margin-top: 5%;
  background-color: #f2f2f2;
  border-radius: 12px;
}

.group-left,
.group-right,
.center {
  flex: 0 0 30%;
  background-color: rgba(255, 255, 255, 0.829);
  padding: 20px;
  border-radius: 10px;
}

.group-right,
.group-left {
  height: 100%;
}

.center {
  width: 100%;
  text-align: center;
  background-color: white;
  padding: 20px;
  border-radius: 10px;
  align-self: center;
}

.trophy {
  font-size: 60px;
  margin: 20px 0;
}

.match {
  background-color: #dbdada86;
  margin: 10px 0;
  padding: 10px;
  border-left: 4px solid #ffcc00;
  border-radius: 5px;
}

/* Cabecera de cada partido: fecha y torneo */
.match-header {
  font-size: 0.9rem;
  font-weight: bold;
  margin-bottom: 0.5rem;
  color: #333;
}

/* Nombres de equipos y puntuación */
.match-teams {
  font-size: 1rem;
  margin-bottom: 0.5rem;
}

/* Listado de jugadores por equipo */
.match-players {
  margin-bottom: 0.5rem;
}
.match-players strong {
  display: block;
  margin-top: 0.5rem;
}
.match-players ul {
  list-style: disc inside;
  margin: 0.25rem 0;
  padding-left: 1rem;
}

/* Indicador de equipo ganador */
.match-winner {
  font-style: italic;
  color: #006400;
  margin-top: 0.5rem;
}

/* Título de cada sección de ronda */
.round {
  font-weight: bold;
  margin-top: 1rem;
  text-transform: capitalize;
}
</style>
