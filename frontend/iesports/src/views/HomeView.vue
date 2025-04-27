<template>
  <ion-page>
  
    <ion-content fullscreen >
      <br><br> <br><br><br>
      <ion-button expand="block" @click="goToLogin">
        Ir a Login
      </ion-button>
      <ion-grid>
        <ion-row>
          <ion-col
            v-for="pokemon in pokemons"
            :key="pokemon.name"
            size="6" size-md="3"
          >
            <PokemonCard
              :name="pokemon.name"
              :id="extractId(pokemon.url)"
              :image="getImageUrl(pokemon.url)"
            />
          </ion-col>
        </ion-row>
      </ion-grid>

      <ion-infinite-scroll
        @ionInfinite="loadMore"
        threshold="100px"
        ref="infiniteScrollRef"
      >
        <ion-infinite-scroll-content
          loading-spinner="dots"
          loading-text="Cargando más..."
        />
      </ion-infinite-scroll>
    </ion-content>
    <ion-header>
      <HeaderNavegation />
    </ion-header>

  </ion-page>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router';
import { IonButton, IonContent, IonHeader, IonPage, IonGrid, IonRow, IonCol, IonInfiniteScrollContent, IonInfiniteScroll } from '@ionic/vue';
import { getPokemons } from '../services/PokemonServices';
import { onMounted, ref, type Ref } from 'vue';

import HeaderNavegation from '../components/HeaderNavegation.vue';
import PokemonCard from '../components/PokemonCard.vue';

const pokemons:Ref<any[]> = ref([]);

const router = useRouter();

function goToLogin() {
  router.push('/login')
}




onMounted(async () => {
  await getPokemons().then((response:any[]) => {
    pokemons.value = response;
  }).catch(() => {
    alert('ERROR OBTENIENDO POKEMONS');
  
  });
});

function extractId(url: string): number {
  const partsURL = url.split('/');
  return parseInt(partsURL[partsURL.length - 2]);
}

function getImageUrl(url: string): string {
  const id = extractId(url);
  return `https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png`;
}


let offset = ref(0);
const limit = 20;

onMounted(async () => {
  await cargarPokemons();
});

async function cargarPokemons() {
  try {
    const response = await getPokemons(offset.value, limit);
    pokemons.value.push(...response);
  } catch {
    alert('ERROR OBTENIENDO POKEMONS');
  }
}

async function loadMore(event: CustomEvent) {
  offset.value += limit;
  await cargarPokemons();
  (event.target as HTMLIonInfiniteScrollElement).complete();
}


</script>
