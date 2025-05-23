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

      <!-- Columna izquierda: Logo + botón -->
      <div class="left-column">
        <img src="../assets/logoSinFondo.png" alt="IESPORTS" class="profile-logo" />
        <h3>IESPORTS</h3>
        <IonButton fill="outline" class="logout-button" @click="logout"><span class="material-symbols-outlined">logout</span>Cerrar sesión</IonButton>
      </div>

      <!-- Columna derecha: tarjetas -->
      <div class="right-column">
        <!-- Tarjeta de datos de usuario -->
        <div class="card-section">
          <div class="card-header">
            <h4>Datos de usuario</h4>
            <IonButton fill="outline" size="small" class="buttons">
              <span class="material-symbols-outlined">edit</span> EDITAR
            </IonButton>
          </div>
          <div class="card-body">
            <!-- Nombre -->
            <div class="input-group">
              <span class="material-symbols-outlined">person</span>
              <input type="text" :value="user.name" disabled />
            </div>
            <!-- Email -->
            <div class="input-group">
              <span class="material-symbols-outlined">mail</span>
              <input type="text" :value="user.email" disabled />
            </div>
          </div>
        </div>
        <!-- Tarjeta de cambio de contraseña -->
      <div class="card-section">
        <div class="card-header">
          <h4>Cambiar contraseña</h4>
          <IonButton fill="outline" size="small" class="buttons">
            <span class="material-symbols-outlined">edit</span> EDITAR
          </IonButton>
        </div>
        <div class="card-body">
          <!-- Contraseña actual -->
          <div class="input-group">
            <span class="material-symbols-outlined">key</span>
            <input type="password" placeholder="Contraseña actual" disabled />
          </div>
          <!-- Contraseña nueva -->
          <div class="input-group">
            <span class="material-symbols-outlined">key</span>
            <input type="password" placeholder="Contraseña nueva" disabled />
          </div>
          <!-- Confirmar contraseña -->
          <div class="input-group">
            <span class="material-symbols-outlined">key</span>
            <input type="password" placeholder="Confirmar contraseña" disabled />
          </div>
        </div>
      </div>
    </div>
  </div>   
</IonContent>
</template>

<script setup lang="ts">
import { IonHeader, IonContent, IonToolbar, IonTitle, IonButton } from '@ionic/vue';
import { ref, onMounted } from 'vue';
import { useUserMenu } from '@/composables/useUserMenu';

// Extraemos sólo lo que necesitamos
const { goTo, logout } = useUserMenu();

const user = ref<{ name: string; email: string; course?: { nombre: string } | null; role?: { name: string } | null }>({
  name: '',
  email: '',
  course: null,
  role: null
});



onMounted(() => {
  const stored = localStorage.getItem('usuario');
  if (stored) {
    // Cargamos directamente el objeto guardado
    Object.assign(user.value, JSON.parse(stored));
  }
});


console.log(user.value);
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
  gap: 0.75rem;
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
</style>

