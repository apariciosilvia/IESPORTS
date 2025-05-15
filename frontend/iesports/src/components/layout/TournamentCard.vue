<template>
  <!-- Lista de torneos -->
  <div class="tournaments-list">
    
    <!-- Un div por cada torneo -->
    <div
      v-for="t in tournaments"
      :key="t.id"
      class="tournament-card"
    >
   
      <!-- Tipo de torneo -->
      <h2 class="tournament-title">
        Torneo de {{sportName}} -- {{ t.name }}
      </h2>
      
      <!-- Tarjeta interna -->
      <div class="tajeta-torneo">

        

        <!-- Fase de grupos -->
        <div class="group-left">
          <h3>Fase de grupos</h3>
          <div class="match" v-for="team in t.phase1" :key="team">
            {{ team }}
          </div>
        </div>

        <!-- Cuartos y Semifinal -->
        <div class="group-center">
          <div class="round">Quarter-Finals</div>
          <div class="match" v-for="m in t.quarter" :key="m">
            {{ m }}
          </div>
          <div class="round">Semi-Finals</div>
          <div class="match" v-for="m in t.semifinal" :key="m">
            {{ m }}
          </div>
        </div>

        <!-- Final y Campeón -->
        <div class="group-right">
          <h1>COPA DEL TORNEO</h1>
          <div class="trophy">
            <img src="@/assets/copa-trofeo.png" alt="Trofeo" />
          </div>
          <div class="round">FINAL</div>
          <div class="match">
            {{ t.final }}
          </div>
          <h2>GANADOR</h2>
          <div class="match">
            {{ t.champion }}
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
import { defineProps, computed } from 'vue';

import type { Tournament } from '@/model/tournament';


const props = defineProps<{
  tournament: Tournament;
  sportName: string | undefined;
}>();

// Computed para convertir el código numérico de estado a texto legible:
const stateText = computed(() => {
  switch (props.tournament.state) {
    case 0: return 'Pendiente';
    case 1: return 'En curso';
    case 2: return 'Finalizado';
    default: return 'Desconocido';
  }
});

const tournaments = [
  {
    id: 1,
    name: 'Campeonato Dragones y Tesoros',
    phase1: [
      'Dragones del Valle', 'Caballeros del Norte',
      'Brujas de la Noche', 'Elfos del Bosque',
      'Trols de la Montaña', 'Enanos del Subterráneo',
      'Fénix de Fuego', 'Sirenas del Lago'
    ],
    quarter: [
      'Dragones vs Caballeros',
      'Brujas vs Elfos',
      'Trols vs Enanos',
      'Fénix vs Sirenas'
    ],
    semifinal: [
      'Dragones/Caballeros vs Brujas/Elfos',
      'Trols/Enanos vs Fénix/Sirenas'
    ],
    final: 'Ganador Mágico vs Ganador Legendario',
    champion: 'Héroes de la Luz'
  },
  {
    id: 2,
    name: 'Liga Interestelar',
    phase1: [
      'Andrómeda', 'Orión',
      'Centauro', 'Orionis',
      'Pegaso', 'Aquila',
      'Sirius', 'Vega'
    ],
    quarter: [
      'Andrómeda vs Orión',
      'Centauro vs Orionis',
      'Pegaso vs Aquila',
      'Sirius vs Vega'
    ],
    semifinal: [
      'Andró/Orión vs Centauro/Orionis',
      'Pegaso/Aquila vs Sirius/Vega'
    ],
    final: 'Campeón Galáctico A vs B',
    champion: 'Nova Suprema'
  },
  {
    id: 3,
    name: 'Copa Sabores del Mundo',
    phase1: [
      'Tacos Mexicanos', 'Sushi Japonés',
      'Pizza Italiana', 'Curry Indio',
      'Tapas Españolas', 'Pho Vietnamita',
      'Burgers Americanas', 'Poutine Canadiense'
    ],
    quarter: [
      'Tacos vs Sushi',
      'Pizza vs Curry',
      'Tapas vs Pho',
      'Burgers vs Poutine'
    ],
    semifinal: [
      'Tacos/Sushi vs Pizza/Curry',
      'Tapas/Pho vs Burgers/Poutine'
    ],
    final: 'Delicia Oriental vs Gusto Occidental',
    champion: 'Fusion Suprema'
  },
  {
    id: 4,
    name: 'Torneo de Héroes',
    phase1: [
      'Iron Valor', 'Capitán Relámpago',
      'Mujer Viento', 'Doctor Misterio',
      'Pantera Sombría', 'Arqueógrafa Áurea',
      'Señor Sombra', 'Guerrero Llama'
    ],
    quarter: [
      'Iron vs Relámpago',
      'Viento vs Misterio',
      'Sombría vs Áurea',
      'Sombra vs Llama'
    ],
    semifinal: [
      'Iron/Relámpago vs Viento/Misterio',
      'Sombría/Áurea vs Sombra/Llama'
    ],
    final: 'Alianza Justiciera vs Hermandad de la Noche',
    champion: 'Guardianes Eternos'
  },
  {
    id: 5,
    name: 'Desafío Retro Gamer',
    phase1: [
      'Mario Bros', 'Sonic',
      'Pac-Man', 'Link',
      'Donkey Kong', 'Mega Man',
      'Kirby', 'Samus'
    ],
    quarter: [
      'Mario vs Sonic',
      'Pac-Man vs Link',
      'Donkey Kong vs Mega Man',
      'Kirby vs Samus'
    ],
    semifinal: [
      'Mario/Sonic vs Pac-Man/Link',
      'Donkey Kong/Mega Man vs Kirby/Samus'
    ],
    final: 'Clásicos vs Leyendas',
    champion: 'Joystick Legend'
  },
  {
    id: 6,
    name: 'Copa Ecotierra',
    phase1: [
      'Bosque Vivo', 'Océano Azul',
      'Montaña Sagrada', 'Desierto Dorado',
      'Selva Tropical', 'Polar Blanco',
      'Pradera Dorada', 'Pantano Místico'
    ],
    quarter: [
      'Bosque vs Océano',
      'Montaña vs Desierto',
      'Selva vs Polar',
      'Pradera vs Pantano'
    ],
    semifinal: [
      'Bosque/Océano vs Montaña/Desierto',
      'Selva/Polar vs Pradera/Pantano'
    ],
    final: 'Verde vs Dorado',
    champion: 'Guardianes del Planeta'
  },
  {
    id: 7,
    name: 'Maratón Musical',
    phase1: [
      'Rock Clásico', 'Pop Moderno',
      'Jazz Suave', 'Electrónica',
      'Hip-Hop', 'Reggae',
      'Country', 'Blues'
    ],
    quarter: [
      'Rock vs Pop',
      'Jazz vs Electrónica',
      'Hip-Hop vs Reggae',
      'Country vs Blues'
    ],
    semifinal: [
      'Rock/Pop vs Jazz/Electrónica',
      'Hip-Hop/Reggae vs Country/Blues'
    ],
    final: 'Ritmo vs Melodía',
    champion: 'Sinfonía Suprema'
  },
  {
    id: 8,
    name: 'Circuito de Arte Urbano',
    phase1: [
      'Graffiti Rojo', 'Stencil Azul',
      'Mural Verde', 'Paste-Up Amarillo',
      'Técnica Mixta', 'Performance',
      'Instalación', 'Proyección'
    ],
    quarter: [
      'Graffiti vs Stencil',
      'Mural vs Paste-Up',
      'Mixta vs Performance',
      'Instalación vs Proyección'
    ],
    semifinal: [
      'Graffiti/Stencil vs Mural/Paste-Up',
      'Mixta/Performance vs Instalación/Proyección'
    ],
    final: 'Calle vs Galería',
    champion: 'Arte Vivo'
  }
];


</script>

<style scoped>
/* Lista vertical de torneos */
.tournaments-list {
  display: flex;
  flex-direction: column;
  gap: 1.5rem;
  background-color: #ffffff;
}

/* Cada torneo ocupa todo el ancho */
.tournament-card {
  width: 100%;
}

/* Tarjeta interna con los tres bloques */
.tajeta-torneo {
  display: flex;
  gap: 1rem;
  padding: 1rem;
  border-radius: 8px;
  box-shadow: 0 2px 5px #0a254065;
}

/* Cada bloque (grupos, cuartos+semis, final) se reparte */
.group-left,
.group-center,
.group-right {
  flex: 1;
  min-width: 0;
  background: #ffffff;
  padding: 1rem;
  border-radius: 6px;
}

/* Títulos */
.group-left h3,
.group-center .round,
.group-right h1,
.group-right h2 {
  margin-bottom: 0.75rem;
  color: var(--primary-color);
}

/* Partidos */
.match {
  background: rgba(0,0,0,0.05);
  margin: 0.5rem 0;
  padding: 0.5rem;
  border-left: 4px solid #ffcc00;
  border-radius: 4px;
}

/* Imagen del trofeo */
.group-right .trophy {
  text-align: center;
  margin-bottom: 0.75rem;
}
.group-right .trophy img {
  width: 80px;
}

.tournament-title{
  font-size: 1.5rem;
  margin-top: 3%;
  margin-left: 1%;
}
</style>