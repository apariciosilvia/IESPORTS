<template>

  <!-- Popup Container -->
  <div class="popup-container" v-if="showPopup">
    <!-- SUCCESS -->
    <div class="popup success-popup" v-if="popupType === 'success'">
      <div class="popup-icon success-icon">
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" class="success-svg">
          <path
            fill-rule="evenodd"
            d="m12 1c-6.075 0-11 4.925-11 11s4.925 11 11 11 11-4.925 11-11-4.925-11-11-11zm4.768 9.14c.0878-.1004.1546-.21726.1966-.34383.0419-.12657.0581-.26026.0477-.39319-.0105-.13293-.0475-.26242-.1087-.38085-.0613-.11844-.1456-.22342-.2481-.30879-.1024-.08536-.2209-.14938-.3484-.18828s-.2616-.0519-.3942-.03823c-.1327.01366-.2612.05372-.3782.1178-.1169.06409-.2198.15091-.3027.25537l-4.3 5.159-2.225-2.226c-.1886-.1822-.4412-.283-.7034-.2807s-.51301.1075-.69842.2929-.29058.4362-.29285.6984c-.00228.2622.09851.5148.28067.7034l3 3c.0983.0982.2159.1748.3454.2251.1295.0502.2681.0729.4069.0665.1387-.0063.2747-.0414.3991-.1032.1244-.0617.2347-.1487.3236-.2554z"
            clip-rule="evenodd"
          ></path>
        </svg>
      </div>
      <div class="success-message" v-html="popupMessage"></div>
      <div class="popup-icon close-icon" @click="closePopup">
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" aria-hidden="true" class="close-svg">
          <path
            d="m15.8333 5.34166-1.175-1.175-4.6583 4.65834-4.65833-4.65834-1.175 1.175 4.65833 4.65834-4.65833 4.6583 1.175 1.175 4.65833-4.6583 4.6583 4.6583 1.175-1.175-4.6583-4.6583z"
            class="close-path"
          ></path>
        </svg>
      </div>
    </div>

    <!-- ALERT -->
    <div class="popup alert-popup" v-if="popupType === 'alert'">
      <div class="popup-icon alert-icon">
        <svg class="alert-svg" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" aria-hidden="true">
          <path
            fill-rule="evenodd"
            d="M8.257 3.099c.765-1.36 2.722-1.36 3.486 0l5.58 9.92c.75 1.334-.213 2.98-1.742 2.98H4.42c-1.53 0-2.493-1.646-1.743-2.98l5.58-9.92zM11 13a1 1 0 11-2 0 1 1 0 012 0zm-1-8a1 1 0 00-1 1v3a1 1 0 002 0V6a1 1 0 00-1-1z"
            clip-rule="evenodd"
          ></path>
        </svg>
      </div>
      <div class="alert-message" v-html="popupMessage"></div>
      <div class="popup-icon close-icon" @click="closePopup">
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" class="close-svg">
          <path
            d="m15.8333 5.34166-1.175-1.175-4.6583 4.65834-4.65833-4.65834-1.175 1.175 4.65833 4.65834-4.65833 4.6583 1.175 1.175 4.65833-4.6583 4.6583 4.6583 1.175-1.175-4.6583-4.6583z"
            class="close-path"
          ></path>
        </svg>
      </div>
    </div>

    <!-- ERROR -->
    <div class="popup error-popup" v-if="popupType === 'error'">
      <div class="popup-icon error-icon">
        <svg class="error-svg" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" aria-hidden="true">
          <path
            fill-rule="evenodd"
            d="M10 18a8 8 0 100-16 8 8 0 000 16zM8.707 7.293a1 1 0 00-1.414 1.414L8.586 10l-1.293 1.293a1 1 0 101.414 1.414L10 11.414l1.293 1.293a1 1 0 001.414-1.414L11.414 10l1.293-1.293a1 1 0 00-1.414-1.414L10 8.586 8.707 7.293z"
            clip-rule="evenodd"
          ></path>
        </svg>
      </div>
      <div class="error-message" v-html="popupMessage"></div>
      <div class="popup-icon close-icon" @click="closePopup">
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" class="close-svg">
          <path
            d="m15.8333 5.34166-1.175-1.175-4.6583 4.65834-4.65833-4.65834-1.175 1.175 4.65833 4.65834-4.65833 4.6583 1.175 1.175 4.65833-4.6583 4.6583 4.6583 1.175-1.175-4.6583-4.6583z"
            class="close-path"
          ></path>
        </svg>
      </div>
    </div>

    <!-- INFO -->
    <div class="popup info-popup" v-if="popupType === 'info'">
      <div class="popup-icon info-icon">
        <svg aria-hidden="true" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg" class="info-svg">
          <path
            clip-rule="evenodd"
            d="M18 10a8 8 0 11-16 0 8 8 0 0116 0zm-7-4a1 1 0 11-2 0 1 1 0 012 0zM9 9a1 1 0 000 2v3a1 1 0 001 1h1a1 1 0 100-2v-3a1 1 0 00-1-1H9z"
            fill-rule="evenodd"
          ></path>
        </svg>
      </div>
      <div class="info-message" v-html="popupMessage"></div>
      <div class="popup-icon close-icon" @click="closePopup">
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" class="close-svg">
          <path
            d="m15.8333 5.34166-1.175-1.175-4.6583 4.65834-4.65833-4.65834-1.175 1.175 4.65833 4.65834-4.65833 4.6583 1.175 1.175 4.65833-4.6583 4.6583 4.6583 1.175-1.175-4.6583-4.6583z"
            class="close-path"
          ></path>
        </svg>
      </div>
    </div>
  </div>

  <div class="page-wrapper">
    <div class="contact-wrapper">
      <div class="contact-info">
        <h2>Contacto</h2>
        <p>¿Tienes alguna pregunta, duda o sugerencia? Nuestro equipo está siempre dispuesto a ayudarte en lo que necesites, estaremos encantados de leerte.</p>
        <p>Puedes contactar con nosotros a través de este formulario. Tu opinión nos ayuda a mejorar cada día.</p>
        <p>¡Gracias por confiar en nosotros!</p>
      </div>
      <div class="form-container">
        <form class="form" @submit.prevent="sendContactMail">
          <div class="form-group">
            <label for="email">Email</label>
            <input v-model="email" id="email" type="email" required />
          </div>
          <div class="form-group">
            <label for="textarea">¿Podemos ayudarte en algo?</label>
            <textarea v-model="message" id="message" cols="50" rows="10" required></textarea>
          </div>
          <button class="button" type="submit">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 24 24"
              width="24"
              height="24"
              fill="currentColor"
            >
              <path d="M2.01 21 23 12 2.01 3 2 10l15 2-15 2z" />
            </svg>
            <span>Enviar</span>
          </button>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import type { ContactFormRequestDTO } from '@/model/dto/contactFormRequestDTO';
import { contactForm } from '@/services/personServices';

const email = ref('')
const message = ref('')

async function sendContactMail() {
  const dto: ContactFormRequestDTO = {
    email: email.value.trim(),
    message: message.value.trim(),
  }
  try {
    await contactForm(dto)
    email.value = ''
    message.value = ''
    openPopup('success', 'Correo enviado correctamente');
    
  } catch (err: any) {
    console.error(err)
    openPopup('error', 'Error al enviar el correo. Vuelve a intentarlo más tarde.');
  }
}

// Popup state
const showPopup = ref(false)
const popupType = ref<'success' | 'alert' | 'error' | 'info'>('info')
const popupMessage = ref('')

function openPopup(type: typeof popupType.value, message: string) {
  popupType.value = type
  popupMessage.value = message.replace(/\n/g, '<br>')
  showPopup.value = true

  setTimeout(() => {
    showPopup.value = false
    popupMessage.value = ''
  }, 1000) // 1 segundo
}

function closePopup() {
  showPopup.value = false
  popupMessage.value = ''
}
</script>

<style scoped>

.popup-container {
  position: fixed;
  top: 1rem;
  left: 50%;
  transform: translateX(-50%);
  z-index: 9999;
  display: flex;
  flex-direction: column;
  align-items: center;
}

/* COMMON STYLES*/
.popup {
  margin: 0.5rem 0;
  box-shadow: 4px 4px 10px -10px rgba(0, 0, 0, 1);
  width: 340px;
  justify-content: space-around;
  align-items: center;
  display: flex;
  border-radius: 4px;
  padding: 5px 0;
  font-weight: 300;
}
.popup svg {
  width: 1.25rem;
  height: 1.25rem;
}
.popup-icon svg {
  margin: 5px;
  display: flex;
  align-items: center;
}
.close-icon {
  margin-left: auto;
}
.close-svg {
  cursor: pointer;
}
.close-path {
  fill: #ffffff;
}

/* SUCCESS */
.success-popup {
  background-color: #01e226;
  border: solid 1px #01e226;
  font-weight: bolder;
  font-size: 1rem;
}
.success-icon path {
  fill: #ffffff;
}
.success-message {
  color: #ffffff;
}

/* ALERT */
.alert-popup {
  background-color: #ebae08;
  border: solid 1px #ebae08;
  font-weight: bolder;
  font-size: 1rem;
}
.alert-icon path {
  fill: #ffffff;
  
}
.alert-message {
  color: #ffffff;
}

/* ERROR */
.error-popup {
  background-color: #d11313;
  border: solid 1px #d11313;
  font-weight: bolder;
  font-size: 1rem;
}
.error-icon path {
  fill: #ffffff;
}
.error-message {
  color: #ffffff;
}

/* INFO */
.info-popup {
  background-color: #3563e4;
  border: solid 1px #1e3a8a;
}
.info-icon path {
  fill: #ffffff;
}
.info-message {
  color: #ffffff;
}


.page-wrapper {
  min-height: 70vh;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 2rem;
  box-sizing: border-box;
}

.contact-wrapper {
  display: flex;
  gap: 130px;
  justify-content: center;
  align-items: flex-start;
  padding: 40px;
  flex-wrap: wrap;
  background-color: white;
  border-radius: 10px;
  max-width: 1600px;
  width: 100%;
  box-shadow: 0 2px 10px rgba(0,0,0,0.1);
}
@media (max-width: 1024px) {
  .contact-wrapper {
    gap: 40px;
  }
}

.contact-info {
  flex: 1;
  max-width: 300px;
  color: var(--orange-secundary-color);
  font-family: inherit;
}

.contact-info h2 {
  font-size: 24px;
  margin-bottom: 12px;
}

.contact-info p {
  margin-bottom: 8px;
  font-size: 14px;
}

.form-container {
  flex: 1;
  min-width: 340px;
  max-width: 580px;
  background: linear-gradient(rgba(255, 255, 255, 0.85), rgba(255, 255, 255, 0.85)) padding-box,
              linear-gradient(145deg, #ff3e1c, #6693d6, #ff3e1c) border-box;
  border: 4px solid transparent;
  padding: 32px 24px;
  font-size: 14px;
  font-family: inherit;
  color: white;
  display: flex;
  flex-direction: column;
  gap: 20px;
  box-sizing: border-box;
  border-radius: 16px;
  background-size: 200% 100%;
  animation: animated-border 5s ease infinite;
}

.form {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.form-group {
  display: flex;
  flex-direction: column;
  gap: 2px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
  color: #ff3e1c;
  font-weight: 600;
  font-size: 12px;
}

.form-group input,
.form-group textarea {
  width: 100%;
  padding: 12px 16px;
  border-radius: 8px;
  color: #000;
  background-color: transparent;
  border: 1px solid #fc5757;
  font-family: inherit;
}

.form-group textarea {
  resize: none;
  height: 96px;
}

.form-group input:focus,
.form-group textarea:focus {
  outline: none;
  border-color: #ff1c1c;
}

.button {
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0.8em 1.1em;
  gap: 0.4rem;
  border: none;
  font-weight: bold;
  border-radius: 30px;
  cursor: pointer;
  text-shadow: 2px 2px 3px rgb(136 0 136 / 50%);
  background: linear-gradient(
      15deg,
      #af6403,
      #aa4720,
      #cc3f47,
      #de6f3d,
      #f09f33,
      #de6f3d,
      #cc3f47,
      #aa4720,
      #af6403
    )
    no-repeat;
  background-size: 300%;
  background-position: left center;
  transition: background 0.3s ease;
  color: #fff;
  text-align: center;
}

.button:hover {
  background-size: 320%;
  background-position: right center;
}

.button:hover svg {
  fill: #fff;
}

.button svg {
  width: 20px;
  fill: #ffffff;
  transition: 0.3s ease;
}

@keyframes animated-border {
  0% {
    background-position: 0% 50%;
  }
  50% {
    background-position: 100% 50%;
  }
  100% {
    background-position: 0% 50%;
  }
}
</style>
