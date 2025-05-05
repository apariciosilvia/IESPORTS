<template>
  <ion-page>
  
    <ion-content fullscreen  @ionScroll="handleScroll" :scroll-events="true">
      <HeroCarousel />
      <Navbar/>

      <ion-button expand="block" @click="goToLogin">
        Ir a Login
      </ion-button>
      <Gallery/>
      
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
        </ion-row>
      </ion-grid>
    </ion-content>
  
  </ion-page>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router';
import { IonButton, IonContent, IonPage, IonGrid, IonRow, IonCol, IonCard, IonCardHeader, IonCardContent, IonCardTitle } from '@ionic/vue';
import { getPersonas } from '@/services/personServices';
import { onMounted, ref, type Ref } from 'vue';

// import PersonaCard from '../components/PersonaCard.vue';
import HeroCarousel from '@/components/ui/HeroCarousel.vue';
import Navbar from '@/components/layout/Navbar.vue';
import Gallery from '@/components/ui/Gallery.vue';

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
      showNavbar.value = scrollTop > 690;
    } else if (windowWidth >= 768) { 
      // Tablet
      showNavbar.value = scrollTop > 1000;
    } else { 
      // Móvil
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