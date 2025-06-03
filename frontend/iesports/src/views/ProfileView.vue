<template>
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

const { goTo, logout } = useUserMenu();


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
      await changeNameAndEmail(changeNameAndEmailDTO);
      errores.value = {};
      // localStorage.setItem('usuario', JSON.stringify(user.value)); // fallback
      alert('Perfil actualizado');
    } catch (error: any) {
      console.log('Error al actualizar perfil');

      if (error.response && error.response.status === 400) {
        errores.value = error.response.data;
      } else {
        errores.value = { general: 'Error inesperado. Intenta de nuevo.' };
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
      await changePassword(changePasswordDTO);
      alert('Contraseña cambiada');
      currentPassword.value = '';
      newPassword.value = '';
      confirmPassword.value = '';
      errores.value = {};
    } catch (error: any) {
      console.log('Error al cambiar contraseña');
      
      if (error.response && error.response.status === 400) {
        errores.value = error.response.data;
      } else {
        errores.value = { general: 'Error inesperado. Intenta de nuevo.' };
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