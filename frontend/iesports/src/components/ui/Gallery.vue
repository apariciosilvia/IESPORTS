<template>
  <ion-grid>
    <!-- Título -->
    <ion-row>
      <ion-col size="12">
        <h2 class="gallery-title">Galería del Instituto</h2>
      </ion-col>
    </ion-row>

    <!-- FILA 1: 3 imágenes -->
    <ion-row>
      <ion-col
        v-for="(img, i) in rawRows[0]"
        :key="'row1-' + i"
        size="12"
        size-sm="6"
        size-md="4"
      >
        <img
          :src="img.src"
          :alt="img.alt"
          class="gallery-img"
          @click="openImage(img.src)"
        />
      </ion-col>
    </ion-row>

    <!-- FILA 2: 2 imágenes -->
    <ion-row>
      <ion-col
        v-for="(img, i) in rawRows[1]"
        :key="'row2-' + i"
        size="12"
        size-sm="6"
        size-md="6"
      >
        <img
          :src="img.src"
          :alt="img.alt"
          class="gallery-img"
          @click="openImage(img.src)"
        />
      </ion-col>
    </ion-row>

    <!-- FILA 3: 3 imágenes -->
    <ion-row>
      <ion-col
        v-for="(img, i) in rawRows[2]"
        :key="'row3-' + i"
        size="12"
        size-sm="6"
        size-md="4"
      >
        <img
          :src="img.src"
          :alt="img.alt"
          class="gallery-img"
          @click="openImage(img.src)"
        />
      </ion-col>
    </ion-row>
  </ion-grid>

  <!-- Modal para zoom -->
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

const selectedImage = ref('')
const isModalOpen = ref(false)
const isZoomed = ref(false)

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

const rawRows = [
  [
    { src: new URL('@/assets/img/1.jpg', import.meta.url).href, alt: 'Foto torneo 1' },
    { src: new URL('@/assets/img/2.jpg', import.meta.url).href, alt: 'Foto torneo 2' },
    { src: new URL('@/assets/img/3.jpg', import.meta.url).href, alt: 'Foto torneo 3' }
  ],
  [
    { src: new URL('@/assets/img/4.jpg', import.meta.url).href, alt: 'Instalaciones' },
    { src: new URL('@/assets/img/5.jpg', import.meta.url).href, alt: 'Entrega de premios' }
  ],
  [
    { src: new URL('@/assets/img/6.jpg', import.meta.url).href, alt: 'Celebración 1' },
    { src: new URL('@/assets/img/7.jpg', import.meta.url).href, alt: 'Celebración 2' },
    { src: new URL('@/assets/img/8.jpg', import.meta.url).href, alt: 'Celebración 3' }
  ]
]
</script>

<style scoped>
.gallery-title {
  text-align: center;
  margin: 1rem 0;
  color: var(--ion-color-primary);
}

/* Imagenes responsivas: ajuste de altura según pantalla */
.gallery-img {
  width: 100%;
  height: auto;
  max-height: 250px;
  object-fit: cover;
  border-radius: 10px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
}

@media (min-width: 576px) {
  /* En pantallas ≥ 576px (sm), queremos al menos dos columnas por fila */
  .gallery-img {
    max-height: 300px;
  }
}

@media (min-width: 768px) {
  /* En pantallas ≥ 768px (md), ajustamos altura para un diseño de 3-2-3 */
  .gallery-img {
    max-height: 350px;
  }
}

.zoom-container {
  display: flex;
  justify-content: center;
  align-items: center;
  overflow: auto;
  height: 100%;
}

.zoom-img {
  width: 100%;
  max-width: 800px;
  height: auto;
  transition: transform 0.3s ease;
  cursor: zoom-in;
}

.zoom-img.zoomed {
  transform: scale(2);
  cursor: zoom-out;
}
</style>
