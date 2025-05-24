// Schedule-X: solo el tema y el componente
import '@schedule-x/theme-default/dist/index.css'
import './style.css';

import { ScheduleXCalendar } from '@schedule-x/vue'


import { createApp } from 'vue';   // Crea la instancia principal de Vue
import App from './App.vue';       // Tu componente raíz donde se monta la aplicación

import { IonicVue } from '@ionic/vue'; // Habilita Ionic para Vue
import { IonApp } from '@ionic/vue';  // Contenedor principal que usas en App.vue para Ionic

import Particles from "@tsparticles/vue3"; // Librería de partículas para efectos visuales (FONDO ANIMADO)
import { loadSlim } from "@tsparticles/slim";  // Carga la versión ligera de tsParticles

import { createPinia } from 'pinia';   // Manejo de estado global
import router from './router.ts';   // Sistema de rutas
import i18n from './i18n.ts';   // Internacionalización

/* Ionic styles */
import '@ionic/vue/css/core.css'
import '@ionic/vue/css/normalize.css'
import '@ionic/vue/css/structure.css'
import '@ionic/vue/css/typography.css'
import '@ionic/vue/css/padding.css'
import '@ionic/vue/css/float-elements.css'
import '@ionic/vue/css/text-alignment.css'
import '@ionic/vue/css/text-transformation.css'
import '@ionic/vue/css/flex-utils.css'
import '@ionic/vue/css/display.css'

const app = createApp(App);  // Crea la instancia de la aplicación Vue

app.component('ScheduleXCalendar', ScheduleXCalendar); // Registra globalmente el componente de calendario

app.component('ion-app', IonApp)  // Registra el componente IonApp de Ionic
app.use(Particles, {  // Configuración de la librería de partículas
  init: async engine => {   // Inicializa la librería de partículas
    await loadSlim(engine);  // Carga la versión ligera de tsParticles
  },
});
app.use(IonicVue)  // Habilita Ionic para Vue
app.use(createPinia())  // Habilita Pinia para el manejo de estado global
app.use(router)   // Habilita el sistema de rutas
app.use(i18n)   // Habilita la internacionalización


//Espera a que el enrutador esté listo antes de montar la app. Esto evita errores de navegación en apps SPA.
router.isReady().then(() => {   // Espera a que el enrutador esté listo antes de montar la aplicación
  app.mount('#app')   // Monta la aplicación en el elemento con id "app"
})
