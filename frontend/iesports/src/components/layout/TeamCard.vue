<template>
  <div class="card" :class="{ flipped: isFlipped }">
    <div class="card-inner">
      <!-- Cara frontal: mostramos el nombre del equipo dentro de un ion-item -->
      <div class="card-front">
        
          {{ team.name }}
        <button @click="flipCard" class="card-button">Más info</button>
      </div>
      <!-- Cara trasera: contenido extra al girar -->
      <div class="card-back">
        <div class="card-details">
          <p class="text-title">Más información</p>
          <p class="text-body">Aquí va el contenido extra tras girar la tarjeta.</p>
        </div>
        <button @click="flipCard" class="card-button">Volver</button>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';              // Para crear estado reactivo
import { defineProps } from 'vue';      // Para recibir props
import { IonItem, IonLabel } from '@ionic/vue';  // Componentes Ionic

// Definimos la interfaz del equipo
interface Team {
  id: number;
  name: string;
}

// Recibimos el prop 'team'
const props = defineProps<{ team: Team }>();

// Estado reactivo: si la tarjeta está volteada o no
const isFlipped = ref(false);

// Función que alterna isFlipped
function flipCard() {
  isFlipped.value = !isFlipped.value;
}
</script>

<style scoped>
.card {
  width: 190px;           /* ancho fijo */
  height: 354px;          /* alto fijo */
  perspective: 1000px;    
  position: relative;
  overflow: hidden;       /* evita que nada sobresalga */
}

.card-inner {
  width: 100%;
  height: 100%;
  position: relative;
  transition: transform 0.6s;
  transform-style: preserve-3d;
}

.card.flipped .card-inner {
  transform: rotateY(180deg);   /* Gira la tarjeta 180° cuando isFlipped = true */
}

.card-front,
.card-back {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;          
  height: 100%;         
  box-sizing: border-box;
  backface-visibility: hidden;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

/* La cara trasera sigue girada 180° */
.card-back {
  transform: rotateY(180deg);
}

.card-button {
  transform: translate(-50%, 125%); /* Posición inicial (invisible) */
  width: 60%;
  border-radius: 1rem;
  border: none;
  background-color: #008bf8;
  color: #fff;
  font-size: 1rem;
  padding: .5rem 1rem;
  position: absolute;
  left: 50%;
  bottom: 0;
  opacity: 0;
  transition: 0.3s ease-out;     /* Aparece con suavizado */
}

.card:hover .card-button {
  transform: translate(-50%, 50%); /* Se desplaza y muestra al pasar el ratón */
  opacity: 1;
}

.text-title {
  font-size: 1.5em;
  font-weight: bold;
}

.text-body {
  color: rgb(134, 134, 134);
}
</style>

