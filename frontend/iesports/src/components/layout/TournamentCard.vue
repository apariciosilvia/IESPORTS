<template>
  <div :class="['tournament-container', sportClass]">
    <!-- IZQUIERDA: Octavos -->
    <div class="group-left">
      <h3>{{ formatRoundName('OCTAVOS') }}</h3>

      <div
        v-for="m in roundsGroups['OCTAVOS'] || []"
        :key="m.id"
        class="match"
      >
        <div class="match-teams">
          <!-- Equipo 1 -->
          <span class="team-block">
            <span
              class="team-name"
              :class="{ winner: m.winnerTeam && m.winnerTeam.id === m.team1.id }"
            >
              {{ m.team1.name }}
            </span>
          </span>
          VS
          <!-- Equipo 2 -->
          <span class="team-block">
            <span
              class="team-name"
              :class="{ winner: m.winnerTeam && m.winnerTeam.id === m.team2.id }"
            >
              {{ m.team2.name }}
            </span>
            <span class="points" v-if="m.winnerTeam">
              ({{ m.pointsTeam1 }} - {{ m.pointsTeam2 }})
            </span>
          </span>
        </div>
      </div>

      <p class="match-empty" v-if="!(roundsGroups['OCTAVOS'] && roundsGroups['OCTAVOS'].length)">
        Todavía no hay partidos realizados.
      </p>
    </div>

    <!-- FINAL -->
    <div class="center">
      <h1>{{ tournamentName }}</h1>
      <p class="tournament-info">
        Fecha: {{ props.matches[0]?.tournament?.date || 'Sin fecha' }}<br />
        Estado: {{ props.matches[0]?.tournament?.state || 'Sin estado' }}
      </p>
      <div class="trophy">
        <img src="../../../src/assets/copa-trofeo.png" width="100px" />
      </div>
      <h2>{{ formatRoundName('FINAL') }}</h2>

      <div
        v-for="m in roundsGroups['FINAL'] || []"
        :key="m.id"
        class="match"
      >
        <div class="match-teams">
          {{ m.team1.name }}
          VS
          {{ m.team2.name }}
          <span class="points"> ({{ m.pointsTeam1 }} - {{ m.pointsTeam2 }})</span>
        </div>

      </div>
        <div
        v-for="m in roundsGroups['FINAL'] || []"
        :key="m.id"
        class="final-winner"
      > Ganador: {{ m.winnerTeam?.name }}</div>
      
      <p class="match-empty" v-if="!(roundsGroups['FINAL'] && roundsGroups['FINAL'].length)">
        Todavía no hay un ganador.
      </p>
    </div>

    <!-- DERECHA: Cuartos y Semifinal -->
    <div class="group-right">
      <!-- CUARTOS de Final -->
      <div>
        <h3>{{ formatRoundName('CUARTOS_FINAL') }}</h3>

        <div
          v-for="m in roundsGroups['CUARTOS_FINAL'] || []"
          :key="m.id"
          class="match"
        >
          <div class="match-teams">
          <!-- Equipo 1 -->
          <span class="team-block">
            <span
              class="team-name"
              :class="{ winner: m.winnerTeam && m.winnerTeam.id === m.team1.id }"
            >
              {{ m.team1.name }}
            </span>
          </span>
          VS
          <!-- Equipo 2 -->
          <span class="team-block">
            <span
              class="team-name"
              :class="{ winner: m.winnerTeam && m.winnerTeam.id === m.team2.id }"
            >
              {{ m.team2.name }}
            </span>
            <span class="points" v-if="m.winnerTeam">
              ({{ m.pointsTeam1 }} - {{ m.pointsTeam2 }})
            </span>
          </span>
        </div>
        </div>

        <p class="match-empty" v-if="!(roundsGroups['CUARTOS_FINAL'] && roundsGroups['CUARTOS_FINAL'].length)">
          Todavía no hay partidos registrados.
        </p>
      </div>

      <!-- SEMIFINAL -->
      <div>
        <h3>{{ formatRoundName('SEMIFINAL') }}</h3>

        <div
          v-for="m in roundsGroups['SEMIFINAL'] || []"
          :key="m.id"
          class="match"
        >
          <div class="match-teams">
          <!-- Equipo 1 -->
          <span class="team-block">
            <span
              class="team-name"
              :class="{ winner: m.winnerTeam && m.winnerTeam.id === m.team1.id }"
            >
              {{ m.team1.name }}
            </span>
          </span>
          VS
          <!-- Equipo 2 -->
          <span class="team-block">
            <span
              class="team-name"
              :class="{ winner: m.winnerTeam && m.winnerTeam.id === m.team2.id }"
            >
              {{ m.team2.name }}
            </span>
            <span class="points" v-if="m.winnerTeam">
              ({{ m.pointsTeam1 }} - {{ m.pointsTeam2 }})
            </span>
          </span>
        </div>
        </div>

        <p class="match-empty" v-if="!(roundsGroups['SEMIFINAL'] && roundsGroups['SEMIFINAL'].length)">
          Todavía no hay partidos registrados.
        </p>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { computed } from 'vue';
import type { Match } from '@/model/match';

const props = defineProps<{ matches: Match[] }>();

// Orden de las fases que queremos mostrar
const roundOrder = ['OCTAVOS', 'CUARTOS_FINAL', 'SEMIFINAL', 'FINAL'] as const;

// Nombre de la copa (del primer partido)
const tournamentName = computed(() =>
  props.matches.length ? props.matches[0].tournament.name : ''
);

// Función para convertir clave de ronda en texto legible
function formatRoundName(key: typeof roundOrder[number]): string {
  switch (key) {
    case 'OCTAVOS': return 'Octavos';
    case 'CUARTOS_FINAL': return 'Cuartos de Final';
    case 'SEMIFINAL': return 'Semifinal';
    case 'FINAL': return 'Final';
  }
}

// Agrupar partidos por ronda
const roundsGroups = computed<Record<string, Match[]>>(() =>
  props.matches.reduce((acc, m) => {
    (acc[m.round] = acc[m.round] || []).push(m);
    return acc;
  }, {} as Record<string, Match[]>)
);

console.log('MATCHES', props.matches)


const sportClass = computed(() => {
  const sport = props.matches[0]?.tournament?.sport?.name?.toLowerCase() || '';
  switch (sport) {
    case 'ajedrez': return 'ajedrez-bg';
    case 'fútbol': return 'futbol-bg';
    case 'baloncesto': return 'baloncesto-bg';
    case 'pingpong': return 'pingpong-bg';
    case 'tenis': return 'tenis-bg';
    case 'volleyball': return 'volleyball-bg';
    case 'béisbol': return 'beisbol-bg';
    case 'balonmano': return 'balonmano-bg';
    case 'natación': return 'natacion-bg';
    case 'rugby': return 'rugby-bg';
    default: return 'default-bg';
  }
})

// const tournamentState = computed(() =>
//   props.matches[0]?.tournament?.state ?? ''
// );

// const tournamentDate = computed(() =>
//   props.matches[0]?.tournament?.date ?? ''
// );

</script>



<style scoped>

.tournament-container {
  display: flex;
  flex-direction: row;
  justify-content: center;
  gap: 20px;
  padding: 60px;
  flex-wrap: nowrap;
  width: 100%;
  box-sizing: border-box;
  margin-bottom: 3%;
    border-radius: 12px ;
}

.pingpong-bg {
  background: linear-gradient(to right, rgba(0, 0, 0, 0.616), rgba(0, 0, 0, 0.479), rgba(0, 0, 0, 0.185), rgba(0, 0, 0, 0.479), rgba(0, 0, 0, 0.555)),
              url('@/assets/fondos/fondo-pingpong.jpeg') center center;
}

.ajedrez-bg {
  background: linear-gradient(to right, rgba(0, 0, 0, 0.616), rgba(0, 0, 0, 0.479), rgba(0, 0, 0, 0.185), rgba(0, 0, 0, 0.479), rgba(0, 0, 0, 0.555)),
              url('@/assets/fondos/fondo-ajedrez.jpeg') center center;
}

.futbol-bg {
  background: linear-gradient(to right, rgba(0, 0, 0, 0.616), rgba(0, 0, 0, 0.479), rgba(0, 0, 0, 0.185), rgba(0, 0, 0, 0.479), rgba(0, 0, 0, 0.555)),
              url('@/assets/fondos/fondo-futbol.jpeg') center center;
}

.baloncesto-bg {
  background: linear-gradient(to right, rgba(0, 0, 0, 0.616), rgba(0, 0, 0, 0.479), rgba(0, 0, 0, 0.185), rgba(0, 0, 0, 0.479), rgba(0, 0, 0, 0.555)),
              url('@/assets/fondos/fondo-baloncesto.jpeg') center center;
}

.volleyball-bg {
  background: linear-gradient(to right, rgba(0, 0, 0, 0.616), rgba(0, 0, 0, 0.479), rgba(0, 0, 0, 0.185), rgba(0, 0, 0, 0.479), rgba(0, 0, 0, 0.555)),
              url('@/assets/fondos/fondo-volleyball.png') center center;
}

.tenis-bg {
  background: linear-gradient(to right, rgba(0, 0, 0, 0.616), rgba(0, 0, 0, 0.479), rgba(0, 0, 0, 0.185), rgba(0, 0, 0, 0.479), rgba(0, 0, 0, 0.555)),
              url('@/assets/fondos/fondo-tenis.png') center center;
}

.beisbol-bg {
  background: linear-gradient(to right, rgba(0, 0, 0, 0.616), rgba(0, 0, 0, 0.479), rgba(0, 0, 0, 0.185), rgba(0, 0, 0, 0.479), rgba(0, 0, 0, 0.555)),
              url('@/assets/fondos/fondo-beisbol.png') center center;
}

.balonmano-bg {
  background: linear-gradient(to right, rgba(0, 0, 0, 0.616), rgba(0, 0, 0, 0.479), rgba(0, 0, 0, 0.185), rgba(0, 0, 0, 0.479), rgba(0, 0, 0, 0.555)),
              url('@/assets/fondos/fondo-balonmano.png') center center;
}

.natacion-bg {
  background: linear-gradient(to right, rgba(0, 0, 0, 0.616), rgba(0, 0, 0, 0.479), rgba(0, 0, 0, 0.185), rgba(0, 0, 0, 0.479), rgba(0, 0, 0, 0.555)),
              url('@/assets/fondos/fondo-natacion.png') center center;
}

.rugby-bg {
  background: linear-gradient(to right, rgba(0, 0, 0, 0.616), rgba(0, 0, 0, 0.479), rgba(0, 0, 0, 0.185), rgba(0, 0, 0, 0.479), rgba(0, 0, 0, 0.555)),
              url('@/assets/fondos/fondo-rugby.png') center center;
}

.default-bg {
  background: linear-gradient(to right, rgba(0, 0, 0, 0.616), rgba(0, 0, 0, 0.479), rgba(0, 0, 0, 0.185), rgba(0, 0, 0, 0.479), rgba(0, 0, 0, 0.555)),
              url('@/assets/fondos/fondo-default.png') center center;
}

.group-left,
.group-right,
.center {
  width: 100%;
  background-color: rgba(255, 255, 255, 0.938);
  padding: 20px;
  border-radius: 10px;
  box-sizing: border-box;
}

.group-right,
.group-left {
  height: 100%;
}

.center {
  width: 100%;
  text-align: center;
  padding: 20px;
  border-radius: 10px;
  align-self: center;
}

.trophy {
  font-size: 60px;
  margin: 20px 0;
}

.match {
  background-color: #dbdada56;
  margin: 10px 0;
  padding: 10px;
  border-left: 4px solid #ffcc00;
  border-radius: 5px;
}

.match-empty{
  background-color: #dbdada86;
  margin: 10px 0;
  padding: 10px;
  border-left: 4px solid #ff0000;
  border-radius: 5px;
}

.final-winner{
  background-color: #dbdada86;
  margin: 10px 0;
  padding: 10px;
  border: 4px solid #ffcc00;
  border-radius: 5px;
  color: #504724;
  text-transform: uppercase;
  font-weight: bold;
} 

/* Cabecera de cada partido: fecha y  */
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

.winner {
  color: #e0b403;
  text-transform: uppercase;
  font-weight: bold;
}

.tournament-info {
  font-size: 0.95rem;
  margin-bottom: 1rem;
  color: #444;
}


@media (max-width: 991px) {
  .tournament-container {
    display: block !important;
    padding: 20px !important;
    margin-bottom: 8% !important;
    border-radius: 12px !important;
  }

  .group-left,
  .center,
  .group-right {
    width: 100% !important;
    display: block !important;
    float: none !important;
    margin-bottom: 20px !important;
    box-sizing: border-box !important;
  }
}
</style>
