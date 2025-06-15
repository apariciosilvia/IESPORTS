<template>

  <Loader v-if="isNowLoading" />
  
  <IonHeader>
    <IonToolbar style="--background: #002F3D; --color: white;">
      <IonButton @click="goTo('/')" slot="start" fill="clear">
        <span class="material-symbols-outlined back-icon">arrow_back</span>
      </IonButton>
      <IonTitle>Perfil de {{ user.name }}</IonTitle>
    </IonToolbar>
  </IonHeader>

  <IonContent class="ion-padding">
    <div class="profile-container">

    <!-- Alerts -->
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
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" class="close-svg">
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

      <!-- Columna izquierda -->
      <div class="left-column">
        <img src="../assets/logoSinFondo.png" alt="IESPORTS" class="profile-logo" />
        <h3>IESPORTS</h3>
        <IonButton fill="outline" class="logout-button" @click="logout">
          <span class="material-symbols-outlined">logout</span>
          Cerrar sesión
        </IonButton>
      </div>

      <!-- Columna derecha -->
      <div class="right-column">
        <!-- Datos de usuario -->
        <div class="card-section">
          <div class="card-header">
            <h4>Datos de usuario</h4>
            <!-- 1) Botón dinámico -->
            <IonButton
              fill="outline"
              size="small"
              class="buttons"
              @click="toggleUserEdit"
            >
              <span class="material-symbols-outlined">edit</span>
              <!-- 2) Texto cambia según estado -->
              {{ isEditingUser ? 'GUARDAR' : 'EDITAR' }}
            </IonButton>
          </div>
          <div class="card-body">
            <!-- 3) Inputs enlazados y deshabilitados según flag -->
             <span v-if="errores.name" class="error-msg">{{ errores.name }}</span>
            <div class="input-group">
              <span class="material-symbols-outlined">person</span>
              <input
                type="text"
                v-model="user.name"
                :disabled="!isEditingUser"
                placeholder="Escribe tu nombre..."
                :class="{ 'error-border': errores.name }"
              />
            </div>
            <span v-if="errores.email" class="error-msg">{{ errores.email }}</span>
            <div class="input-group">
              <span class="material-symbols-outlined">mail</span>
              <input
                type="text"
                v-model="user.email"
                :disabled="!isEditingUser"
                placeholder="Escribe tu correo..."
                :class="{ 'error-border': errores.email }"
              />
            </div>
          </div>
        </div>

        <!-- Cambio de contraseña -->
        <div class="card-section">
          <div class="card-header">
            <h4>Cambiar contraseña</h4>
            <IonButton
              fill="outline"
              size="small"
              class="buttons"
              @click="togglePasswordEdit"
            >
              <span class="material-symbols-outlined">edit</span>
              {{ isEditingPassword ? 'GUARDAR' : 'EDITAR' }}
            </IonButton>
          </div>
          <div class="card-body">
            <!-- 5) Inputs de contraseña según flag -->
            <span v-if="errores.currentPassword" class="error-msg">{{ errores.currentPassword }}</span>
            <div class="input-group">
              <span class="material-symbols-outlined">key</span>
              <input
                type="password"
                placeholder="Contraseña actual"
                v-model="currentPassword"
                :disabled="!isEditingPassword"
                :class="{ 'error-border': errores.currentPassword }"
              />
            </div>

            <span v-if="errores.password1ChangePassword" class="error-msg">{{ errores.password1ChangePassword }}</span>
            <div class="input-group">
              <span class="material-symbols-outlined">key</span>
              <input
                type="password"
                placeholder="Contraseña nueva"
                v-model="newPassword"
                :disabled="!isEditingPassword"
                :class="{ 'error-border': errores.password1ChangePassword }"
              />
            </div>

            <span v-if="errores.password2ChangePassword" class="error-msg">{{ errores.password2ChangePassword }}</span>
            <div class="input-group">
              <span class="material-symbols-outlined">key</span>
              <input
                type="password"
                placeholder="Confirmar contraseña"
                v-model="confirmPassword"
                :disabled="!isEditingPassword"
                :class="{ 'error-border': errores.password2ChangePassword }"
              />
            </div>
          </div>
        </div>
      </div>
    </div>
  </IonContent>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { IonHeader, IonContent, IonToolbar, IonTitle, IonButton } from '@ionic/vue';

// COMPOSTABLES
import { useUserMenu } from '@/composables/useUserMenu';

// CLASES
import type { ChangeNameAndEmailDTO } from '@/model/dto/changeNameAndEmailDTO';
import type { ChangePasswordDTO } from '@/model/dto/changePasswordDTO';

// SERVICIOS
import { changeNameAndEmail, changePassword } from '@/services/personServices';

import Loader from '@/components/ui/Loader.vue';
import { useLoadingEffect } from '@/composables/useLoadingEffect';
const { isNowLoading } = useLoadingEffect();

const { goTo, logout } = useUserMenu();


// Popups
const showPopup = ref(false);
const popupType = ref<'success'|'alert'|'error'|'info'>('info');
const popupMessage = ref('');

function openPopup(type: typeof popupType.value, msg: string) {
  popupType.value = type;
  popupMessage.value = msg.replace(/\n/g, '<br>');
  showPopup.value = true;
  // Auto-close after 1000 ms
  setTimeout(() => showPopup.value = false, 1000);
}
function closePopup() {
  showPopup.value = false;
  popupMessage.value = '';
}

const emit = defineEmits<{
  (e: 'close'): void
  (e: 'updated', msg: string): void
}>();


/* ERRORES START */
const errores = ref<Record<string, string>>({});
/* ERRORES END */



/* CAMBIO DE NOMBRE Y EMAIL START */
  // Datos de usuario y flags
  const user = ref<{ id: number; name: string; email: string }>({ id: 0, name: '', email: '' });
  const isEditingUser = ref(false);
  const isEditingPassword = ref(false);

async function toggleUserEdit() {
  if (isEditingUser.value) {
    // Ha pulsado GUARDAR
    const changeNameAndEmailDTO: ChangeNameAndEmailDTO = {
        personId: user.value.id,
        name: user.value.name,
        email: user.value.email,
    }
    try {
      errores.value = {};
      // llamamos y esperamos la respuesta (ahora devuelve directamente Person)
      const updatedUser = await changeNameAndEmail(changeNameAndEmailDTO);
      // actualiza tu ref/reactivo
      user.value = updatedUser;
      // guarda en localStorage
      localStorage.setItem('usuario', JSON.stringify(updatedUser));
      emit('updated','Perfil actualizado');
      openPopup('success','Perfil actualizado');
    
    } catch (error: any) {

      if (error.response && error.response.status === 400) {
        errores.value = error.response.data;
      } else {
        openPopup('error','Error inesperado. Intenta de nuevo.');
      }
    }
  }
  isEditingUser.value = !isEditingUser.value;
}
/* CAMBIO DE NOMBRE Y EMAIL END */



/* CAMBIO DE CONTRASEÑA START */
  // Campos para contraseña
  const currentPassword = ref('');
  const newPassword = ref('');
  const confirmPassword = ref('');

async function togglePasswordEdit() {
  if (isEditingPassword.value) {
    // Ha pulsado GUARDAR
    const changePasswordDTO: ChangePasswordDTO = {
      personId: user.value.id,
      currentPassword: currentPassword.value,
      password1ChangePassword: newPassword.value,
      password2ChangePassword: confirmPassword.value
    };
    try {
      errores.value = {};
      await changePassword(changePasswordDTO);
      emit('updated','Contraseña cambiada');
      openPopup('success','Contraseña cambiada');
    
      currentPassword.value = '';
      newPassword.value = '';
      confirmPassword.value = '';

    } catch (error: any) {
      
      if (error.response && error.response.status === 400) {
        errores.value = error.response.data;
      } else {
        openPopup('error','Error inesperado. Intenta de nuevo.');
      }
      currentPassword.value = '';
      newPassword.value = '';
      confirmPassword.value = '';
    }
  }
  isEditingPassword.value = !isEditingPassword.value;
}
/* CAMBIO DE CONTRASEÑA END */




// CARGA INICIAL DESDE LOCALSTORAGE
onMounted(() => {
  const stored = localStorage.getItem('usuario');
  if (stored) Object.assign(user.value, JSON.parse(stored));
});
</script>

<style scoped>




/* COMMON STYLES*/
.popup-container {
  position: fixed;
  top: 70px;
  left: 50%;
  transform: translateX(-50%);
  z-index: 9999;
}

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

.profile-container {
  margin-top: 4%;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 2rem;
  font-size: 1.05rem; 
   align-items: stretch;
}

.left-column,
.right-column {
  display: flex;
  flex-direction: column;
}

/* Columna izquierda: logo y botón */
.left-column {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 25%;
  max-width: 550px;
  align-self: stretch;    
  flex: 0 0 25%;          
  max-width: 300px;      
}

.left-column h3 {
  margin-top: 0 !important;
  margin-bottom: 3%;
  line-height: 1.2; 
  font-size: 2.5rem;  
  color: #002f3d;
  font-weight: bolder;
}

.profile-logo {
  width: 100%;
  max-width: 230px;
  height: auto;
  margin-bottom: 0;
}

/* Columna derecha: tarjetas apiladas */
.right-column {
  display: flex;
  flex-direction: column;
  gap: 1.8rem;
  flex: 1;
  max-width: 550px;
}

/* Cada sección (tarjeta) */
.card-section {
  background: #ffffff;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0,0,0,0.1);
  padding: 1rem;


  width: 90%;
  max-width: 550px;
  margin: 0 auto;
  box-sizing: border-box;

}

/* Encabezado con título y botón editar */
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 0.75rem;
}
.card-header h4 {
  margin: 0;
  font-size: 1.3rem;
  color: #333;
}
.back-icon {
  font-size: 1.7rem;
  vertical-align: middle;
  color: #fff;
}

.logout-button{
  /* Fondo sólido naranja */
  background-color: #B92E21;
  --background-activated: #d83523;
  --background-focused: #d83523;

  /* Elimina cualquier borde o sombra interna */
  --border-width: 0;
  --box-shadow: none;

  color: #ffffff;
  border-radius: 6px;
  font-weight: 600;
  text-transform: none;
  padding: 4px 22px;
}

.buttons {
  /* Fondo sólido naranja */
  background-color: #F03726;
  --background-activated: #d83523;
  --background-focused: #d83523;

  /* Elimina cualquier borde o sombra interna */
  --border-width: 0;
  --box-shadow: none;

  color: #ffffff;
  border-radius: 6px;
  font-weight: 600;
  text-transform: none;
}

.buttons .material-symbols-outlined {
  font-size: 1rem;
  margin-right: 4px;
  color: #ffffff;
}

/* Cuerpo con inputs */
.card-body {
  display: flex;
  flex-direction: column;
  gap: 0.20rem;
}

/* Cada fila: icono + input */
.input-group {
  display: flex;
  align-items: center;
  gap: 0.75rem;
}
.input-group .material-symbols-outlined {
  font-size: 1.6rem;
  color: #F03726;
}
.input-group input {
  flex: 1;
  padding: 0.6rem;
  border: 1px solid #ddd;
  border-radius: 6px;
  background: #f9f9f9;
  font-size: 1.1rem;
  margin-bottom: 2px;
}

/* Ajustes responsive */
@media (max-width: 600px) {
  .profile-container {
    flex-direction: column;
    align-items: center;
  }
  .right-column, .left-column {
    max-width: 90%;
  }
}

/* Al final de tu <style scoped> */
ion-toolbar {
  position: relative;
}

/* Centrar el título */
ion-title {
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  margin: 0;
}

/* AJUSTE EN ESTILO ERRORES START*/
.error-msg {
  margin-top: 1px;
  margin-left: 10% !important;
}
/* AJUSTE EN ESTILO ERRORES END*/


@media (max-width: 768px) {
  .profile-container {
    flex-direction: column;
    align-items: center;
    gap: 2rem;
    margin: 1rem 0;
  }

  .left-column,
  .right-column {
    width: 100%;
    max-width: 100%;
    padding: 0 1rem;
  }

  .card-section {
    width: 100%;
    max-width: 100%;
    margin: 0 auto;
  }

  .input-group input {
    font-size: 1rem;
    padding: 0.5rem;
  }

  .card-header h4 {
    font-size: 1.1rem;
  }

  .logout-button{
    width: 60%;
    justify-content: center;
    font-size: 1rem;
    padding: 0.6rem;
  }

  .buttons {
    width: 20%;
  }

  .buttons .material-symbols-outlined {
    font-size: 1.1rem;
  }

  .error-msg {
    margin-left: 0 !important;
  }
  .profile-logo {
    width: 100%;
    max-width: 190px;
    height: auto;
    margin-bottom: 0;
  }

}


@media (max-width: 480px) {
 .profile-container {
    flex-direction: column;
    align-items: center;
    gap: 2rem;
    margin: 1rem 0;
  }

  .left-column,
  .right-column {
    width: 100%;
    max-width: 100%;
    padding: 0 1rem;
  }

  .card-section {
    width: 100%;
    max-width: 100%;
    margin: 0 auto;
  }

  .input-group input {
    font-size: 1rem;
    padding: 0.5rem;
  }

  .card-header h4 {
    font-size: 1.1rem;
  }

  .logout-button{
    width: 60%;
    justify-content: center;
    font-size: 1rem;
    padding: 0.6rem;
  }

  .buttons {
    width: 30%;
  }

  .buttons .material-symbols-outlined {
    font-size: 1.1rem;
  }

  .error-msg {
    margin-left: 0 !important;
  }

  .profile-logo {
    width: 100%;
    max-width: 160px;
    height: auto;
    margin-bottom: 0;
  }

  .logout-button{
    width: 100%;
    justify-content: center;
    font-size: 1rem;
    padding: 0.2rem;
  }
}


</style>