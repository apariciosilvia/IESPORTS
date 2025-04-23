import { createApp } from 'vue'
import App from './App.vue'

import { IonicVue } from '@ionic/vue'
import { IonApp } from '@ionic/vue'

import Particles from "@tsparticles/vue3";
import { loadSlim } from "@tsparticles/slim"; 

import { createPinia } from 'pinia'
import router from './router.ts'
import i18n from './i18n.ts'

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

const app = createApp(App)
app.component('ion-app', IonApp)
app.use(Particles, {
  init: async engine => {
    // await loadFull(engine); // you can load the full tsParticles library from "tsparticles" if you need it
    await loadSlim(engine); // or you can load the slim version from "@tsparticles/slim" if don't need Shapes or Animations
  },
});
app.use(IonicVue)
app.use(createPinia())
app.use(router)
app.use(i18n)

router.isReady().then(() => {
  app.mount('#app')
})
