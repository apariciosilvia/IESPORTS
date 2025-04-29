<template>
  <ion-page>
  
    <ion-content fullscreen  @ionScroll="handleScroll" :scroll-events="true">
 <!-- Portada grande -->
 <CarouselPortada />
      <!-- Navbar que aparece al hacer scroll -->
      <BarraNavegacion />
     
      <br><br> <br><br><br>
      <ion-button expand="block" @click="goToLogin">
        Ir a Login
      </ion-button>
      <ion-grid>
        <ion-row>

          <ion-col
            v-for="persona in personas"
            :key="persona.id"
            size="6" size-md="3"
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
          <!-- <ion-col
            v-for="persona in personas"
            :key="persona.name"
            size="6" size-md="3"
          > -->
            <!-- <PokemonCard
              :name="pokemon.name"
              :id="extractId(pokemon.url)"
              :image="getImageUrl(pokemon.url)"
            /> -->
          <!-- </ion-col> -->
        </ion-row>
      </ion-grid>
      <ion-grid>
        <ion-row>

          <ion-col
            v-for="persona in personas"
            :key="persona.id"
            size="6" size-md="3"
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
          <!-- <ion-col
            v-for="persona in personas"
            :key="persona.name"
            size="6" size-md="3"
          > -->
            <!-- <PokemonCard
              :name="pokemon.name"
              :id="extractId(pokemon.url)"
              :image="getImageUrl(pokemon.url)"
            /> -->
          <!-- </ion-col> -->
        </ion-row>
      </ion-grid><ion-grid>
        <ion-row>

          <ion-col
            v-for="persona in personas"
            :key="persona.id"
            size="6" size-md="3"
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
          <!-- <ion-col
            v-for="persona in personas"
            :key="persona.name"
            size="6" size-md="3"
          > -->
            <!-- <PokemonCard
              :name="pokemon.name"
              :id="extractId(pokemon.url)"
              :image="getImageUrl(pokemon.url)"
            /> -->
          <!-- </ion-col> -->
        </ion-row>
      </ion-grid><ion-grid>
        <ion-row>

          <ion-col
            v-for="persona in personas"
            :key="persona.id"
            size="6" size-md="3"
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
          <!-- <ion-col
            v-for="persona in personas"
            :key="persona.name"
            size="6" size-md="3"
          > -->
            <!-- <PokemonCard
              :name="pokemon.name"
              :id="extractId(pokemon.url)"
              :image="getImageUrl(pokemon.url)"
            /> -->
          <!-- </ion-col> -->
        </ion-row>
      </ion-grid>
      <ion-grid>
        <ion-row>

          <ion-col
            v-for="persona in personas"
            :key="persona.id"
            size="6" size-md="3"
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
          <!-- <ion-col
            v-for="persona in personas"
            :key="persona.name"
            size="6" size-md="3"
          > -->
            <!-- <PokemonCard
              :name="pokemon.name"
              :id="extractId(pokemon.url)"
              :image="getImageUrl(pokemon.url)"
            /> -->
          <!-- </ion-col> -->
        </ion-row>
      </ion-grid>
      <!-- <ion-infinite-scroll
        @ionInfinite="loadMore"
        threshold="100px"
        ref="infiniteScrollRef"
      > -->
        <!-- <ion-infinite-scroll-content
          loading-spinner="dots"
          loading-text="Cargando más..."
        />
      </ion-infinite-scroll> -->
    </ion-content>
    <ion-header>
      <HeaderNavegation />
    </ion-header>

  </ion-page>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router';
import { IonButton, IonContent, IonHeader, IonPage, IonGrid, IonRow, IonCol, IonCard, IonCardHeader, IonCardContent, IonCardTitle } from '@ionic/vue';
import { getPersonas } from '../services/PersonaServices';
import { onMounted, ref, type Ref } from 'vue';

// import PersonaCard from '../components/PersonaCard.vue';
import CarouselPortada from '../components/CarouselPortada.vue';
import BarraNavegacion from '../components/BarraNavegacion.vue';



const personas:Ref<any[]> = ref([]);

const router = useRouter();


function goToLogin() {
  router.push('/login')
}




onMounted(async () => {
  await getPersonas().then((response:any[]) => {
    personas.value = response;
  }).catch(() => {
    alert('ERROR OBTENIENDO PERSONAS');
  
  });
});

const showNavbar = ref(false);


const handleScroll = (event: CustomEvent) => {
  const scrollElement = (event.target as any).getScrollElement 
    ? event.target 
    : (event.target as any).scrollElement;

  scrollElement.getScrollElement().then((scrollEl: HTMLElement) => {
    const scrollTop = scrollEl.scrollTop;

    // Detectamos el tamaño de la pantalla
    const windowWidth = window.innerWidth;

    if (windowWidth >= 1440) { 
      // Ordenador grande
      showNavbar.value = scrollTop > 900;
    } else if (windowWidth >= 1024) { 
      // Portátil
      showNavbar.value = scrollTop > 700;
    } else if (windowWidth >= 768) { // Tablet
      showNavbar.value = scrollTop > 1000;
    } else { // Móvil
      showNavbar.value = scrollTop > 600;
    }
  });
};



// function extractId(url: string): number {
//   const partsURL = url.split('/');
//   return parseInt(partsURL[partsURL.length - 2]);
// }

// function getImageUrl(url: string): string {
//   const id = extractId(url);
//   return `https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png`;
// }


// let offset = ref(0);
// const limit = 20;

// onMounted(async () => {
//   await cargarPokemons();
// });

// async function cargarPokemons() {
//   try {
//     const response = await getPokemons(offset.value, limit);
//     pokemons.value.push(...response);
//   } catch {
//     alert('ERROR OBTENIENDO POKEMONS');
//   }
// }

// async function loadMore(event: CustomEvent) {
//   offset.value += limit;
//   await cargarPokemons();
//   (event.target as HTMLIonInfiniteScrollElement).complete();
// }


</script>

<style scoped>



.navbar {
  position: fixed;
  top: 0;
  width: 100%;
  transform: translateY(-100%);
  transition: transform 0.3s ease;
  z-index: 999;
}

.navbar-visible {
  transform: translateY(0);
}

.contenido {
  padding: 20px;
}
</style>