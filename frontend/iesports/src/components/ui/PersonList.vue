<template>
  <ion-grid>
    <ion-row>
      <ion-col
        v-for="persona in personas"
        :key="persona.id"
        size="6"
        size-md="3"
      >
        <ion-card>
          <ion-card-header>
            <ion-card-title>{{ persona.nombre }}</ion-card-title>
          </ion-card-header>
          <ion-card-content>
            <p>Email: {{ persona.email }}</p>
            <p>Activo: {{ persona.activo ? 'Sí' : 'No' }}</p>
            <p>Rol ID: {{ persona.rol_id }}</p>
          </ion-card-content>
        </ion-card>
      </ion-col>
    </ion-row>
  </ion-grid>
</template>

<script setup lang="ts">
import {
  IonGrid,
  IonRow,
  IonCol,
  IonCard,
  IonCardHeader,
  IonCardContent,
  IonCardTitle
} from '@ionic/vue'

import { defineProps } from 'vue'

defineProps<{
  personas: any[]
}>()
</script>
