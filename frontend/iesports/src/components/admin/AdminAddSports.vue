<!-- src/components/NewSport.vue -->
<template>
  <!-- START ALERTS POPUPS -->
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
  <!-- END ALERTS POPUPS -->

  <!-- START VISTA CREAR DEPORTE -->
  <ion-header>
    <ion-toolbar class="header-red">
      <ion-title>NUEVO DEPORTE</ion-title>
      <ion-buttons slot="end">
        <ion-button @click=" $emit('close')">Cerrar</ion-button>
      </ion-buttons>
    </ion-toolbar>
  </ion-header>

  <ion-content class="content">
    <div class="form-container">
      <!-- Input Nombre -->
      <ion-input
        label="Nombre de deporte"
        label-placement="floating"
        fill="outline"
        placeholder="Escribe un deporte"
        class="input-name"
        v-model="sportNameAdd"
      />

    </div>
  </ion-content>

  <ion-footer>
    <div class="footer-buttons">
      <ion-button
        expand="block"
        class="btn-clean"
        @click="resetForm"
      >
        <span class="material-symbols-outlined">mop</span>Limpiar
      </ion-button>
      <ion-button
        expand="block"
        class="btn-save"
        @click="createSport"
      >
        <span class="material-symbols-outlined">save</span>Crear Deporte
      </ion-button>
    </div>
  </ion-footer>
  <!-- END VISTA CREAR DEPORTE -->
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { IonHeader, IonToolbar, IonTitle, IonButtons, IonButton, IonContent, IonFooter, IonInput } from '@ionic/vue';
import { addSport } from '@/services/sportService';
import type { SportAddDTO } from '@/model/dto/SportAddDTO';

const emit = defineEmits<{
  (e: 'close'): void;
  (e: 'created', message: string): void;
}>();

const sportNameAdd = ref<string>('');

// Popup state
const showPopup = ref<boolean>(false);
const popupType = ref<'success' | 'alert' | 'error' | 'info'>('info');
const popupMessage = ref<string>('');

function openPopup(type: 'success' | 'alert' | 'error' | 'info', message: string) {
  popupType.value = type;
  popupMessage.value = message.replace(/\n/g, '<br>');
  showPopup.value = true;

  // Cerrar automáticamente los tipos distintos a 'success'
  if (type !== 'success') {
    setTimeout(() => {
      showPopup.value = false;
      popupMessage.value = '';
    }, 3000);
  }
}
function closePopup() {
  showPopup.value = false;
  popupMessage.value = '';
}

function resetForm() {
  sportNameAdd.value = '';
}

async function createSport() {
  if (!sportNameAdd.value.trim()) {
    openPopup('alert', 'El nombre del deporte es obligatorio');
    return;
  }

  const sportAddDTO: SportAddDTO = {
    name: sportNameAdd.value,
  };

  try {
    await addSport(sportAddDTO);
    emit('created', 'Deporte creado correctamente');
    openPopup('success','Deporte creado')
    resetForm();

    // espera 2 segundos antes de recargar
    await new Promise(resolve => setTimeout(resolve, 1000))
    window.location.reload();

  } catch (error: any) {
    const status = error.response?.status;
    const data   = error.response?.data;
    let msg = '';

    if (status >= 400 && status <= 409) {
      msg = Object.values(data ?? {})[0] || error.message;
    } else {
      msg = 'Error inesperado';
    }
    openPopup('error', msg); 
  }
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

/* Styles for main component */
.header-red {
  --color: #ffffff;
  --background: #e22f28;
  display: flex;
  justify-content: center;
  align-items: center;
}

.header-red ion-title {
  left: 100%;
  transform: translateX(3%);
  font-weight: bolder;
  font-size: 1.5rem;
}

.content {
  --padding-bottom: 0;
}

.form-container {
  display: flex;
  flex-direction: column;
  margin: 1rem 1.5rem;
}

.input-name {
  --border-color: #022029;
  --border-style: solid;
  --border-width: 1px;
  --color: #022029;
  --placeholder-color: #999999;
  text-align: left;
  margin-bottom: 1.5rem;
  margin-top: 1rem;
}

.sc-ion-input-md-h {
  --placeholder-color: var(--text-color-primary);
  --highlight-color-focused: var(--ion-color-primary, #0054e9);
  --highlight-color-valid: var(--ion-color-success, #5f7465);
  --highlight-color-invalid: var(--ion-color-danger, #c5000f);
  --highlight-color: var(--border-color-blue);
}

.panels {
  display: flex;
  gap: 2%;
}

.panel-left {
  flex: 0 0 40%;
  background-color: #f5f5f5;
  border-radius: 8px;
  padding: 1rem;
  display: flex;
  flex-direction: column;
  height: 400px;
}

.panel-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 0.75rem;
  font-weight: 600;
  color: #042935;
}

.counter {
  color: #e22f28;
  font-weight: bold;
}

.searchbar {
  --border-radius: 8px;
  --box-shadow: none;
  border: 1px solid #0a254098;
  border-radius: 5px;
  margin-bottom: 0.75rem;
  text-align: left;
}

.sc-ion-searchbar-md-h {
  -webkit-padding-start: 0;
  padding-inline-start: 0;
  -webkit-padding-end: 0;
  padding-inline-end: 0;
  padding: 0;
  margin: 0 0 1rem 0;
}

.members-list {
  flex: 1;
  overflow-y: auto;
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
}

.member-row {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: #ffffff;
  padding: 0.5rem 1rem;
  border-radius: 5px;
}

.member-name {
  font-size: 0.9rem;
  color: #0b2c3e;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.btn-add {
  background: none;
  border: none;
  color: #ff3c2f;
  font-size: 1.4rem;
  cursor: pointer;
  padding: 0;
}

.btn-add:disabled {
  color: #c0c0c0;
  cursor: default;
}

.panel-right {
  flex: 0 0 58%;
  background-color: #f5f5f5;
  border-radius: 8px;
  padding: 1rem;
  display: flex;
  flex-direction: column;
  height: 400px;
}

.selected-list {
  flex: 1;
  display: flex;
  flex-wrap: wrap;
  gap: 0.5rem;
  overflow-y: auto;
  padding-top: 0.5rem;
}

.selected-row {
  background: #ffffff;
  border-radius: 5px;
  padding: 0.5rem 1rem;
  display: flex;
  align-items: center;
  gap: 0.5rem;
}

.selected-name {
  font-size: 0.9rem;
  color: #0b2c3e;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.btn-remove {
  background: none;
  border: none;
  color: #ff3c2f;
  font-size: 1.1rem;
  cursor: pointer;
  padding: 0;
}

.footer-buttons {
  display: flex;
  justify-content: flex-end;
  gap: 1rem;
  padding: 0.75rem 1.5rem;
}

.btn-clean,
.btn-save {
  --background: #042935;
  --border-radius: 8px;
  --color: white;
  font-weight: bold;
}

.btn-clean .material-symbols-outlined,
.btn-save .material-symbols-outlined {
  margin-right: 5px;
}
</style>
