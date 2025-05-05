<template>
  <ion-toolbar id="navbar">
    <div class="nav-center">
      <ion-button routerLink="/inicio">Inicio</ion-button>
      <ion-button routerLink="/torneos">Torneos</ion-button>
      <ion-button routerLink="/equipos">Equipos</ion-button>
      <ion-button routerLink="/calendario">Calendario</ion-button>
      <ion-button routerLink="/contacto">Contacto</ion-button>
      <ion-button @click="goToLogin">Iniciar Sesión</ion-button>
    </div>
  </ion-toolbar>
</template>
<script setup lang="ts"> 
import { useRouter } from 'vue-router';
import { IonButton, IonToolbar } from '@ionic/vue';

const router = useRouter();

function goToLogin() {
  router.push('/login');
}
</script>

<style scoped>
#navbar {
  position: fixed;
  top: 0;
  width: 100%;
  background-color: #76d6ac;
  color: white;
  
  --background: #76d6ac;
  --color: white;
  padding: 10px 16px;
  /* display: none;  */
  z-index: 1000;
}

.nav-center {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  gap: 12px;
}

ion-button {
  --color: inherit; 
  --background: none;
  --box-shadow: none; 
  --padding-start: 0; 
  --padding-end: 0; 
  background: none; 
  border: none; 
  color: inherit; 
  font-weight: bolder; 
  text-transform: none; 
  font-size: 1.2rem;
}
</style>
