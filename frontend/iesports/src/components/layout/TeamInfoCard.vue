<script setup lang="ts">

import type { TeamInfoDTO } from '@/model/teamInfoDTO';

// Desestructuramos la prop para obtener directamente `team`
const { teamInfo } = defineProps<{ 
  teamInfo: TeamInfoDTO 
}>();

</script>
<template>
  <div class="card">
    <div class="card-inner">
      <!-- Cara frontal -->
      <div class="card-front">
        <p>{{ teamInfo.team.name }}</p>
        <ul>
          <li v-for="sport in teamInfo.sport" :key="sport.id">
            {{ sport.name }}
          </li>
        </ul>
      </div>
      <!-- Cara trasera -->
      <div class="card-back">
        <b>INTEGRANTES DEL EQUIPO:</b>
        <ul>
          <li v-for="person in teamInfo.person" :key="person.id">
            {{ person.name }}
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>
<style scoped>
.card {
  width: 17rem;
  height: 20rem;
  perspective: 1000px;
  position: relative;
  overflow: hidden;
 padding: 1rem;
}

.card-inner {
  width: 100%;                 
  height: 100%;
  position: relative;
  transform-style: preserve-3d; 
  transition: transform 1s 0.3s;    
}

/* Flip por hover */
.card:hover .card-inner {
  transform: rotateY(180deg);
}

/* caras front/back */
.card-front,
.card-back {
  position: absolute;
  width: 100%;
  height: 100%;
  backface-visibility: hidden;
  box-sizing: border-box;
  border-radius: 10px;
  display: flex;
  padding: 1rem;
  font-size: 1.25rem;
}

.card-front {
  background-color: #17385a;
  color: #fff;
  border: 10px solid #0a2540;
  transform: rotateY(0deg);
}

.card-back {
  background-color: #90a5bb;
  color: #fff;
  border: 10px solid #384450;
  transform: rotateY(180deg);
}


.card-back {
  background-color: #90a5bb;
  color: #fff;
  border: 10px solid #384450;
  transform: rotateY(180deg);
  display: flex;
  flex-direction: column;
  align-items: center;       /* centra el título y la lista */
  padding: 1rem;
  font-size: 1rem;           /* texto un poco más pequeño */
  overflow: hidden;           /* sin scroll externo */
}

.card-back b {
  margin-bottom: 0.5rem;
  font-size: 1.1rem;
  text-align: center;
}

.card-back ul {
  width: 100%;
  padding: 0;
  margin: 0;
  columns: 2;            /* 2 columnas */
  column-gap: 1rem;
  list-style: none;
  text-align: center;    /* centra el texto dentro de cada columna */
}

.card-back li {
  margin-bottom: 0.5rem;
         /* alinea nombres a la izquierda */
}
</style>