import { createApp } from 'vue'
import App from './App.vue'

import { IonicVue } from '@ionic/vue'
import { IonApp } from '@ionic/vue'


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


app.use(IonicVue)
app.use(createPinia())
app.use(router)
app.use(i18n)

router.isReady().then(() => {
  app.mount('#app')
})
