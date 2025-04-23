import { createI18n } from 'vue-i18n'

const messages = {
  en: { welcome: 'Welcome' },
  es: { welcome: 'Bienvenido' }
}

export default createI18n({
  locale: 'es',
  fallbackLocale: 'en',
  legacy: false,
  messages
})
