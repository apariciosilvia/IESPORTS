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

import { IonGrid, IonRow, IonCol, IonModal, IonContent } from '@ionic/vue';
import { ref } from 'vue';

const selectedImage = ref('');
const isModalOpen = ref(false);

function openImage(src: string) {
  selectedImage.value = src;
  isModalOpen.value = true;
}

function closeModal() {
  isModalOpen.value = false;
  isZoomed.value = false; // reinicia el zoom
}

const isZoomed = ref(false);

function toggleZoom() {
  isZoomed.value = !isZoomed.value;
}

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
];
</script>
<style scoped>
.gallery-title {
  text-align: center;
  margin: 1rem 0;
  color: var(--ion-color-primary);
}

.gallery-img {
  width: 100%;
  height: 450px;
  object-fit: cover;
  border-radius: 10px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
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
