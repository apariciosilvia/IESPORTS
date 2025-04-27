<template>
  <ion-page>
        <ion-content fullscreen v-if="pokemon">
      <ion-card>
        <ion-img :src="pokemon.sprites.front_default" />
        <ion-card-header>
          <ion-card-title class="ion-text-capitalize">
            Detalles de: 
            {{ pokemon.name }}
          </ion-card-title>
        </ion-card-header>
        <ion-card-content>
          <p><strong>ID: </strong> {{ pokemon.id }}</p>
          <p><strong>Altura: </strong> {{ pokemon.height }}</p>
          <p><strong>Peso: </strong> {{ pokemon.weight }}</p>
          <p><strong>Experiencia base: </strong> {{ pokemon.base_experience }}</p>
          <p><strong>Orden: </strong> {{ pokemon.order }}</p>

          <p><strong>Tipos: </strong>
            <span v-for="t in pokemon.types" :key="t.slot">{{ t.type.name }} </span>
          </p>

          <p><strong>Habilidades: </strong>
            <span v-for="a in pokemon.abilities" :key="a.ability.name">
              {{ a.ability.name }} 
            </span>
          </p>

          <p><strong>Sonido:</strong></p>
          <audio
            :src="`https://raw.githubusercontent.com/PokeAPI/cries/main/cries/pokemon/latest/${pokemon.id}.ogg`"
            controls
          />
        </ion-card-content>
      </ion-card>
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';
import { IonContent, IonPage, IonCard, IonImg, IonCardHeader, IonCardTitle, IonCardContent } from '@ionic/vue';

const route = useRoute();
const pokemon = ref<any>(null);

onMounted(async () => {
  const id = route.params.id;
  const res = await fetch(`https://pokeapi.co/api/v2/pokemon/${id}`);
  pokemon.value = await res.json();
})
</script>

<style scoped>
ion-card {
  max-width: 400px;
  margin: 2rem auto;
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
  border-radius: 20px;
  overflow: hidden;
  background: #fdfdfd;
}

ion-img {
  width: 100%;
  background: #f2f2f2;
  padding: 1rem;
  object-fit: contain;
}

ion-card-content p {
  font-size: 1rem;
  margin: 0.5rem 0;
}

strong {
  color: #444;
  font-weight: 600;
}

audio {
  width: 100%;
  margin-top: 1rem;
}

span {
  margin-right: 8px;
  text-transform: capitalize;
}
</style>

