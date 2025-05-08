<template>
    <ion-grid>

      <ion-row>
        <ion-col size="12">
          <h2 class="gallery-title">Galería del Instituto</h2>
        </ion-col>
      </ion-row>

      <ion-row>
        <ion-col v-for="(img, i) in rows[0]" :key="'row1-' + i" size="4">
          <img :src="img.src" :alt="img.alt" class="gallery-img" @click="openImage(img.src)"/>
        </ion-col>
      </ion-row>

      <ion-row>
        <ion-col v-for="(img, i) in rows[1]" :key="'row2-' + i" size="6">
          <img :src="img.src" :alt="img.alt" class="gallery-img" @click="openImage(img.src)"/>
        </ion-col>
      </ion-row>

      <ion-row>
        <ion-col v-for="(img, i) in rows[2]" :key="'row3-' + i" size="4">
          <img :src="img.src" :alt="img.alt" class="gallery-img" @click="openImage(img.src)"/>
        </ion-col>
      </ion-row>

    </ion-grid>

    <ion-modal :is-open="isModalOpen" @didDismiss="closeModal">
      <ion-content class="ion-padding">
        <div class="zoom-container">
          <img
            :src="selectedImage"
            alt="Vista ampliada"
            class="zoom-img"
            :class="{ 'zoomed': isZoomed }"
            @click="toggleZoom"
          />
        </div>
      </ion-content>
    </ion-modal>

</template>

<script setup lang="ts">
import { ref } from 'vue'
import {
  IonGrid,
  IonRow,
  IonCol,
  IonModal,
  IonContent
} from '@ionic/vue'

// ▶️ Estado reactivo
const selectedImage = ref('')
const isModalOpen = ref(false)
const isZoomed = ref(false)

// ▶️ Funciones de interacción
function openImage(src: string) {
  selectedImage.value = src
  isModalOpen.value = true
}

function closeModal() {
  isModalOpen.value = false
  isZoomed.value = false
}

function toggleZoom() {
  isZoomed.value = !isZoomed.value
}

// ▶️ Datos: galería en 3 filas
const rows = [
  [
    { src: new URL('@/assets/img/1.jpg', import.meta.url).href, alt: 'Foto torneo 1' },
    { src: new URL('@/assets/img/2.jpg', import.meta.url).href, alt: 'Foto torneo 2' },
    { src: new URL('@/assets/img/3.jpg', import.meta.url).href, alt: 'Foto torneo 3' }
  ],
  [
    { src: new URL('@/assets/img/2.jpg', import.meta.url).href, alt: 'Instalaciones' },
    { src: new URL('@/assets/img/2.jpg', import.meta.url).href, alt: 'Entrega de premios' }
  ],
  [
    { src: new URL('@/assets/img/1.jpg', import.meta.url).href, alt: 'Celebración 1' },
    { src: new URL('@/assets/img/2.jpg', import.meta.url).href, alt: 'Celebración 2' },
    { src: new URL('@/assets/img/3.jpg', import.meta.url).href, alt: 'Celebración 3' }
  ]
]
</script>

<style scoped>
/* 🎨 Título centrado con color principal de Ionic */
.gallery-title {
  text-align: center;
  margin: 1rem 0;
  color: var(--ion-color-primary); /* Usa el color primario del tema de Ionic */
}

/* 🖼️ Imagen de la galería: ocupa todo el ancho del ion-col, altura fija y estilo visual */
.gallery-img {
  width: 100%;                /* Ocupa todo el ancho de su columna */
  height: 450px;              /* Altura fija para uniformidad */
  object-fit: cover;          /* Recorta imagen sin deformar */
  border-radius: 10px;        /* Bordes redondeados */
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15); /* Sombra ligera para efecto de profundidad */
}

/* 🔍 Contenedor del modal con imagen ampliada */
.zoom-container {
  display: flex;              /* Centra contenido con flexbox */
  justify-content: center;    /* Centrado horizontal */
  align-items: center;        /* Centrado vertical */
  overflow: auto;             /* Permite scroll si la imagen ampliada es grande */
  height: 100%;               /* Ocupa toda la altura del modal */
}

/* 🔎 Imagen dentro del modal (vista ampliada) */
.zoom-img {
  width: 100%;                /* Se adapta al contenedor */
  max-width: 800px;           /* Máximo tamaño permitido */
  height: auto;               /* Mantiene la proporción */
  transition: transform 0.3s ease; /* Animación suave al hacer zoom */
  cursor: zoom-in;            /* Muestra el icono de lupa al pasar el cursor */
}

/* 🖱️ Estilo cuando la imagen está ampliada (modo zoom) */
.zoom-img.zoomed {
  transform: scale(2);        /* Escala al doble de tamaño */
  cursor: zoom-out;           /* Cambia el cursor para indicar que puedes hacer zoom-out */
}
</style>
