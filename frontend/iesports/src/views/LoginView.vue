<template>
  <ion-page>
   <ion-content>
     <!-- <div id="vanta-login" class="vanta-bg"></div> -->


    <vue-particles
      id="tsparticles"
      @particles-loaded="particlesLoaded"
      :options="{
        background: {
          color: {
            value: '#255766'
          }
        },
        fpsLimit: 80,
        interactivity: {
          events: {
            onClick: {
              enable: true,
              mode: 'push'
            },
            onHover: {
              enable: true,
              mode: 'repulse'
            },
          },
          modes: {
            bubble: {
              distance: 400,
              duration: 2,
              opacity: 0.2,
              size: 20
            },
            push: {
              quantity: 4
            },
            repulse: {
              distance: 200,
              duration: 0.2
            }
          }
        },
        particles: {
          color: {
            value: '#cee6ed'
          },
          links: {
            color: '#cee6ed',
            distance: 150,
            enable: true,
            opacity: 0.8,
            width: 6
          },
          move: {
            direction: 'none',
            enable: true,
            outModes: 'bounce',
            random: false,
            speed: 6,
            straight: false
          },
          number: {
            density: {
              enable: true,
            },
            value: 80
          },
          opacity: {
            value: 0.5
          },
          shape: {
            type: 'image',
            image: [
              {
                src: '/racket.png',
                width: 32,
                height: 32
              },
              {
                src: '/ball.png',
                width: 32,
                height: 32
              },
            ]
          },
          size: {
            value: { min: 1, max: 5 }
          }
        },
        detectRetina: true
      }"
    />
    <div class="card-container" :class="{ 'show-back': showRegister }">
      <div class="card-inner">
        
        <!-- Login -->
        <div class="card-face card-front">
        <span v-if="errores.error" class="error-credenciales">{{ errores.error }}</span>

        <div class="header-row">

          <IonButton @click="goBack" slot="start" fill="clear">
            <span class="material-symbols-outlined back-icon">arrow_back</span>
          </IonButton>


          <h2 class="tittle">Iniciar sesión</h2>
        </div>
        <!-- LOGIN START -->
        <form @submit.prevent="handleLogin">
            <span v-if="errores.email" class="error-msg">{{ errores.email }}</span>
            <ion-input v-model="loginData.email" type="text" placeholder="Correo electrónico" class="custom-input" fill="outline" :class="{ 'error-border': errores.email || errores.error }"/>
            
            <span v-if="errores.password" class="error-msg">{{ errores.password }}</span>
            <ion-input
              v-model="loginData.password"
              :clear-on-edit="false"
              :type="showLoginPassword ? 'text' : 'password'"   
              placeholder="Contraseña"
              class="custom-input"
              fill="outline"
              :class="{ 'error-border': errores.password || errores.error }"
            >
              <ion-button
                slot="end"
                fill="clear"
                @click="showLoginPassword = !showLoginPassword"  
                style="--padding:0; --min-width:auto; cursor:pointer;"
              >
                <span class="material-symbols-outlined">
                  {{ showLoginPassword ? 'visibility_off' : 'visibility' }} 
                </span>
              </ion-button>
            </ion-input>

            <ion-button type="submit" expand="block" class="custom-button">
              <ion-icon name="log-in-outline"></ion-icon>Entrar
            </ion-button>
          </form>

          <div class="login-links">
            <a href="#" @click.prevent="showForgotPassword = true">¿Olvidaste tu contraseña?</a>
            <a href="#" @click.prevent="showRegister = true; handleGetCourses(); cleanInputs()">¿No tienes cuenta? Regístrate aquí</a>
          </div>
        </div>
        <!-- LOGIN END -->

        <!-- REGISTRO START -->
        <div class="card-face card-back">
          <div class="header-row">
            <IonButton @click="goBack" slot="start" fill="clear">
            <span class="material-symbols-outlined back-icon">arrow_back</span>
            </IonButton>
            <h2 class="tittle">Registro</h2>
          </div>

          <form @submit.prevent="handleRegister">
            <div>
              <span v-if="errores.name" class="error-msg">{{ errores.name }}</span>
              <ion-input v-model="registerData.name" type="text" placeholder="Nombre completo" class="custom-input" fill="outline" :class="{ 'error-border': errores.name }" />
            </div>

            <div>
              <span v-if="errores.email" class="error-msg">{{ errores.email }}</span>
              <ion-input v-model="registerData.email" type="text" placeholder="Correo electrónico" class="custom-input" fill="outline" :class="{ 'error-border': errores.email }" />
            </div>

            <div>
              <span v-if="errores.password1" class="error-msg">{{ errores.password1 }}</span>
              <ion-input
                v-model="registerData.password"
                :clear-on-edit="false"
                :type="showPassword ? 'text' : 'password'"
                placeholder="Contraseña"
                class="custom-input"
                fill="outline"
                :class="{ 'error-border': errores.password1 }"
              >
                <ion-button
                  slot="end"
                  fill="clear"
                  @click="showPassword = !showPassword"
                  style="--padding:0; --min-width:auto; cursor:pointer;"
                >
                  <span class="material-symbols-outlined">
                    {{ showPassword ? 'visibility_off' : 'visibility' }}
                  </span>
                </ion-button>
              </ion-input>
            </div>

            <div>
              <span v-if="errores.password2" class="error-msg">{{ errores.password2 }}</span>
              <ion-input
                v-model="registerData.confirmPassword"
                :clear-on-edit="false"
                :type="showConfirmPassword ? 'text' : 'password'"
                placeholder="Confirmar contraseña"
                class="custom-input"
                fill="outline"
                :class="{ 'error-border': errores.password2 }"
              >
                <ion-button
                  slot="end"
                  fill="clear"
                  @click="showConfirmPassword = !showConfirmPassword"
                  style="--padding:0; --min-width:auto; cursor:pointer;"
                >
                  <span class="material-symbols-outlined">
                    {{ showConfirmPassword ? 'visibility_off' : 'visibility' }}
                  </span>
                </ion-button>
              </ion-input>
            </div>

            <div>
              <span v-if="errores.courseId" class="error-msg">{{ errores.courseId }}</span>
              <ion-select
                class="custom-select"
                v-model="selectedCourse"
                aria-label="Courses"
                interface="popover"
                placeholder="Selecciona un curso"
                :interfaceOptions="{ cssClass: 'wide-popover' }"
                :class="{ 'error-border': errores.courseId }"
              >
                <ion-select-option
                  v-for="course in courses"
                  :key="course.id"
                  :value="course.id"
                >
                  {{ course.nombre }}
                </ion-select-option>
              </ion-select>
            </div>

            <ion-button id="present-alert" type="submit" expand="block" class="custom-button">
              <ion-icon name="person-add-outline"></ion-icon>Registrarse
            </ion-button>
          </form>

          <div class="login-links">
            <a href="#" @click.prevent="showRegister = false; cleanInputs()">¿Ya tienes cuenta? Inicia sesión</a>
          </div>
        </div>
        <!-- REGISTRO END -->
      </div>
    </div>

    <!-- POP UP HAS OLVIDADO CONTRASEÑA START -->
    <ion-modal class="forgot-password-modal" :is-open="showForgotPassword" @did-dismiss="showForgotPassword = false" :backdrop-dismiss="false">
      <ion-header class="modal-header">
        <ion-toolbar>
          <ion-title>Recuperar contraseña</ion-title>
          <ion-buttons slot="end">
            <ion-button fill="clear" @click="showForgotPassword = false">
              <span class="material-symbols-outlined">close</span>
            </ion-button>
          </ion-buttons>
        </ion-toolbar>
      </ion-header>

      <ion-content class="ion-padding">
        <span v-if="errores.email" class="error-msg">{{ errores.email }}</span>
        <ion-input
          v-model="forgotEmail"
          type="email"
          placeholder="Introduce tu correo"
          fill="outline"
          class="custom-input"
        />

        <ion-button class="enviar" expand="block" @click="sendEmailRecovery">
          Enviar correo de recuperación
        </ion-button>

        <ion-button class="clean" expand="block" @click="forgotEmail = ''; errores = {}">
          Limpiar
        </ion-button>
      </ion-content>
    </ion-modal>
    <!-- POP UP HAS OLVIDADO CONTRASEÑA END -->

    <!-- POP UP CAMBIO CONTRASEÑA TEMPORAL START -->
    <ion-modal class="reset-password-modal" :is-open="showResetPassword" @did-dismiss="showResetPassword = true" :backdrop-dismiss="false">
      <ion-header class="modal-header">
        <ion-toolbar>
          <ion-title>Recuperar contraseña</ion-title>
          <ion-buttons slot="end">
            <ion-button fill="clear" @click="showResetPassword = false">
              <ion-icon name="close-outline"></ion-icon>
            </ion-button>
          </ion-buttons>
        </ion-toolbar>
      </ion-header>

      <ion-content class="ion-padding">
        <ion-input
          v-model="newPassword"
          type="password"
          placeholder="Nueva contraseña"
          fill="outline"
          class="custom-input"
        />
        <span v-if="errores.password2" class="error-msg">{{ errores.password2 }}</span>
        <ion-input
          v-model="confirmPassword"
          type="password"
          placeholder="Confirmar contraseña"
          fill="outline"
          class="custom-input"
        />

        <ion-button class="enviar" expand="block" @click="changeTemporalPassword">
          Restablecer contraseña
        </ion-button>

        <ion-button class="clean" expand="block" @click="newPassword = ''; confirmPassword = ''; errores = {}">
          Limpiar
        </ion-button>
      </ion-content>
    </ion-modal>
    <!-- POP UP CAMBIO CONTRASEÑA TEMPORAL END -->

  </ion-content>
  </ion-page>
</template>
 
<script setup lang="ts">
import { ref, type Ref } from 'vue';
import { IonInput, IonButton, IonIcon, IonContent, IonPage, IonSelect, IonSelectOption } from '@ionic/vue';
import router from '@/router';

//MODELOS 
import type { Person } from '@/model/person';

//SERVICIOS
import { getCourses } from "@/services/courseService";
import { login, register, forgotPassword, changeTempPassword } from '@/services/personServices';
import type { ForgotPasswordRequestDTO } from '@/model/forgotPasswordRequestDTO';
import type { ChangeForgottenPasswordDTO } from '@/model/changeForgottenPasswordDTO';


/* FONDO ANIMADO START */
const particlesLoaded = async (container: any) => {
  console.log("Particles container loaded", container);
};
/* FONDO ANIMADO END */

 





/* FUNCION PARA LOGEARSE START */
const loginData = ref({ email: '', password: '' });

async function handleLogin() {
  try {
    const response: any = await login(loginData.value.email.trim(), loginData.value.password.trim());
    console.log('Login exitoso:', response);
 
    // const user = response.data ?? response;
    const person :Person = response.data;
    console.log('Persona:', person);

     if (person != null) {
        localStorage.setItem('usuario', JSON.stringify(person));

        if(person.tempPassword == 1){
          showResetPassword.value = true; //Activar el popup de restablecer contraseña temporal
        } else {
          if (person?.role.name === 'Administrador') {
            router.push({ name: 'HomeAdmin' });
          } else {
            router.push({ name: 'Home' });
          }
        }

     } else {

      alert('Credenciales incorrectas')
    }
  } catch (error: any) {
    console.error('Error al iniciar sesión:', error.response.data);
  
    if (error.response && error.response.status === 400) {
      errores.value = error.response.data;
    } else {
      errores.value = { general: 'Error inesperado. Intenta de nuevo.' };
    }
  }
}
/* FUNCION PARA LOGEARSE END */



/* ERRORES START */
const errores = ref<Record<string, string>>({});
// const showErrorAlert = ref(false);
// const errorMessage = ref('');
/* ERRORES END */



/* FUNCION PARA REGISTRARSE START */
const showRegister = ref(false);
const registerData = ref({ name: '', email: '', password: '', confirmPassword: '' })

async function handleRegister() {
  try {
    const response = await register(
      registerData.value.name.trim(),
      registerData.value.email.trim(),
      registerData.value.password.trim(),
      registerData.value.confirmPassword.trim(),
      Number(selectedCourse.value)
    );

    if (response) {
      localStorage.setItem('usuario', JSON.stringify(response.data));
      alert('Registro exitoso. Has iniciado sesión automáticamente.');
      router.push('/');
    }
  } catch (error: any) {
    console.error('Error al registrar:', error.response.data);
  
    if (error.response && error.response.status === 400) {
      errores.value = error.response.data;
    } else {
      errores.value = { general: 'Error inesperado. Intenta de nuevo.' };
    }
  }
}
/* FUNCION PARA REGISTRARSE END */



/* OLVIDATE TU CONTRASEÑA START*/
const showForgotPassword = ref(false); // controla si se muestra el modal
const forgotEmail = ref(''); // almacena el email introducido

async function sendEmailRecovery() {
  

  const email: ForgotPasswordRequestDTO = { email: forgotEmail.value.trim() };

  try {
    await forgotPassword(email); 
    alert('Correo enviado. Revisa tu bandeja de entrada.');
    showForgotPassword.value = false;
    forgotEmail.value = '';
  } catch (error: any) {
    console.error('Error al recuperar contraseña:', error);

    if (error.response && error.response.status === 400) {
      errores.value = error.response.data;
    } else {
      errores.value = { general: 'Error inesperado. Intenta de nuevo.' };
    }
  }
}
/* OLVIDATE TU CONTRASEÑA END*/



/* CAMBIO CONTRASEÑA TEMPORAL START */
const showResetPassword = ref(false);
const newPassword = ref('');
const confirmPassword = ref('');

async function changeTemporalPassword(){

  const person = JSON.parse(localStorage.getItem('usuario') || '{}');
  console.log('PERSONITA' , person);

  try {
    const changePasswordDto: ChangeForgottenPasswordDTO = {
      personId: person.id,
      password1: newPassword.value.trim(),
      password2: confirmPassword.value.trim()
    };

    await changeTempPassword(changePasswordDto);
    alert('Contraseña restablecida con exito');
    // window.location.reload(); // recarga la página

    // 1️⃣ Cerrar el popup
    showResetPassword.value = false;
    

    if (person?.role.name === 'Administrador') {
      router.push({ name: 'HomeAdmin' });
      console.log('ADMIN');
    } else {
      router.push({ name: 'Home' });
      console.log('ALUMNO');
    }

  } catch (error: any) {
    
    console.error('Error al restablecer la contraseña', error.response.data);
  
    if (error.response && error.response.status === 400) {
      errores.value = error.response.data;
    } else {
      errores.value = { general: 'Error inesperado. Intenta de nuevo.' };
    }
  }
}
/* CAMBIO CONTRASEÑA TEMPORAL END */



/* OBTENER TODOS LOS CURSOS START */
const courses:Ref<any[]> = ref([]);
const selectedCourse = ref('');
 
async function handleGetCourses() {
  try {
    const response = await getCourses() as any[]; 
    courses.value = response;
    console.log(courses.value);
  } catch (error){
    console.error('ERROR OBTENIENDO CURSOS: ', error)
    alert('ERROR OBTENIENDO CURSOS');
  }
}
/* OBTENER TODOS LOS CURSOS END */



/* VER CONTRASEÑAS START */
const showPassword = ref(false);  // para mostrar/ocultar la contraseña principal
const showConfirmPassword = ref(false);  // para mostrar/ocultar la confirmación
const showLoginPassword = ref(false);  // para mostrar/ocultar la contraseña de login
/* VER CONTRASEÑAS END */



/* BOTON VOLVER START */
function goBack() {
  window.history.back();
}
/* BOTON VOLVER END */



/* LIMPIAR CAMPOS START */
function cleanInputs() {
  loginData.value.email = '';
  loginData.value.password = '';
  registerData.value.name = '';
  registerData.value.email = '';
  registerData.value.password = '';
  registerData.value.confirmPassword = '';
  selectedCourse.value = '';
  errores.value = {};
}
/* LIMPIAR CAMPOS END */



/* ALERT PARA VER EL ANCHO DE LA PANTALLA */
// alert(`Ancho de pantalla: ${window.innerWidth}px`);

</script>
 
<style scoped>
 
.vanta-bg {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: 0;
  pointer-events: none;
}
 
.card-container {
  width: 100vw;
  max-width: 600px;
  height: auto;
  aspect-ratio: 10 / 11;
  perspective: 1000px;
  margin: auto;
  margin-top: 10%;
  position: relative;
  z-index: 1;
}

.header-row {
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 0;
  text-align: center;
}

.header-row IonButton {
  position: absolute;
  left: 1rem;
  top: 50%;
  transform: translateY(-50%);
}


.back-icon {
  font-size: 2.2rem;
  color: #000000;
}

.card-face h2 {
  text-align: center;
  margin-bottom: 1.5rem;
  color: #333;
  font-weight: bold;
  font-size: 2.5rem;
}

 
.card-inner {
  width: 100%;
  height: 100%;
  position: relative;
  transition: transform 0.8s ease;
  transform-style: preserve-3d;
}
 
.card-container.show-back .card-inner {
  transform: rotateY(180deg);
}
 
.card-face {
  position: absolute;
  width: 100%;
  height: 100%;
  background: rgba(255, 255, 255, 0.788);
  padding: 2.5rem;
  border-radius: 16px;
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.15);
  backface-visibility: hidden;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
 
.card-front {
  z-index: 2;
}
 
.card-back {
  transform: rotateY(180deg);
}

 
.custom-input {
  width: 100%;
  margin-bottom: 1rem;
  text-align: left;
  font-size: 1.5rem;
  --border-radius: 8px;
  --background: #ffffff10;
  --border-color: #ccc;
  --box-shadow: none;
}

.custom-input ion-button span.material-symbols-outlined {
  color: #002F3D;
  font-size: 1.5rem;
}

 
.custom-input.ion-focused,
.custom-select.ion-focused {
  --border-color: #2cff02 !important;
  --box-shadow: 0 0 0 3px rgba(233,30,99,0.2);
 }

  /*FOCO POR DEFECTO*/
  /* Solo aplica el highlight si NO tiene clase error-border */
 .custom-input.sc-ion-input-md-h:not(.error-border),
 .custom-select.sc-ion-input-md-h:not(.error-border) {
  --highlight-color: #002F3D;
 }
 /* 1) Hover solo si NO tiene error-border */
.custom-input.sc-ion-input-md-h:not(.error-border):hover,
.custom-select.sc-ion-input-md-h:not(.error-border):hover {
  --background: rgba(60, 187, 130, 0.055);
  --border-color: #002F3D;
}
 
.custom-select {
  width: 100%;
  margin-bottom: 1rem;
  font-size: 1.5rem;
  --border-radius: 8px;
  --background: #ffffff10;
  --border-color: #ccc;
  --box-shadow: none;
  --padding-start: 10px;
  --padding-end: 10px;
  --min-height: 48px;
  color: #000; 
  border: 1px solid var(--border-color);
  border-radius: var(--border-radius);
}


.custom-select:not(.error-border):hover {
  border: 1px solid var(--border-color-blue) !important;
  --background: rgba(60, 187, 130, 0.055); /* fondo suave al pasar el ratón */
}

.custom-select {
  --highlight-color-focused: var(--blue-primary-color-hover);
}

.custom-select::part(icon) {
  color: #5b635f !important;

}
 
.custom-button {
   width: 100%;
   margin-top: 5%;
   --background: var(--blue-primary-color);
   --color: var(--text-color-secundary);
   --border-radius: 8px;
   font-weight: bold;
   font-size: 1.5rem;
   display: flex;
   align-items: center;
   justify-content: center;
   gap: 8px;
 }
 
 .custom-button:hover {
   --background: var(--blue-primary-color-hover);
 }
 
 .material-icons {
   font-size: 26px;
 }
 
 .login-links {
   margin-top: 1rem;
   text-align: center;
   font-size: 1.3rem;
 }
 
 .login-links a {
   display: block;
   margin-top: 0.5rem;
   color: var(--text-color-primary);
   text-decoration: none;
 }
 
 .login-links a:hover {
   text-decoration: underline;
 }


/* DISEÑO ERRORES START */
.error-credenciales{
  color: var(--text-error-color);
  font-weight: 600;
  font-size: 1.2rem;
  background-color: var(--background-div-error-color);
  border: var(--border-error-color);
  border-radius: 12px;
  padding: 20px;
  text-align: center;
}

.error-msg {
  color: var(--text-error-color);
  font-size: 0.9rem;
  margin-bottom: 4px;
  display: block;
  
}

.error-border {
  --highlight-color-focused: var(--border-error-color);
  --border-color: var(--border-error-color);
  --box-shadow: 0 0 0 1px var(--border-error-color);
}

.error-border:hover{
  --background: var(--background-error-color-hover);
  --border-color: var(--border-error-color);
  --box-shadow: 0 0 0 6px var(--border-error-color);
}
/* DISEÑO ERRORES END */



/* Grandes pantallas: >1200px */
@media (min-width: 1920px) {
  :deep(.card-face)  {
    padding: 2rem !important;
  }

  .card-container {
    position: relative !important;
    transform: none !important;
    margin: auto !important;
    width: 65vw !important;       
    max-width: 800px !important;
    height: auto !important;
    margin-top: 5% !important;
  }

  :deep(.custom-input) ,
  :deep(.custom-select) {
    --padding-start: 12px !important;
    --padding-end:   12px !important;
    margin-bottom:   1.2rem !important;
  }

  :deep(.card-container) {
    width: 35vw !important;
    max-width: 650px !important;
  }
}


/* MEDIA QUERY PARA PANTALLAS GRANDES (>768px) START */
@media (min-width: 885px) and (max-width: 1919px) {
  ion-content {
    display: flex !important;
    align-items: center !important;
    justify-content: center !important;
    padding: 0 !important;
    
  }

  .card-container {
    position: relative !important;
    transform: none !important;
    margin: auto !important;
    width: 45vw !important;       
    max-width: 600px !important;
    height: auto !important;
    margin-top: 3vw !important;
  }
}
/* MEDIA QUERY PARA PANTALLAS GRANDES (>768px) END */



/* MEDIA QUERY PARA TABLET PEQUEÑA ENTRE 768px y 834px START */
@media (min-width: 768px) and (max-width: 884px) {
  /* Centrado del contenido */
  :deep(ion-content.login-page) {
    display: flex !important;
    align-items: center !important;
    justify-content: center !important;
    padding: 1rem !important;
  }

  /* Tamaño y centrado de la tarjeta */
  :deep(.card-container) {
    width: 85vw !important;
    max-width: 600px !important;
    margin: 19vh auto !important;
    height: auto !important;
  }

  /* Espacio interno */
  :deep( .card-face) {
    padding: 2rem !important;
  }

  /* Título ajustado */
  :deep( .card-face h2) {
    font-size: 2rem !important;
    margin-bottom: 1.2rem !important;
  }

  /* Inputs y selects */
  :deep(.custom-input),
  :deep(.custom-select) {
    font-size: 1.05rem !important;
    --padding-start: 8px !important;
    --padding-end:   8px !important;
    margin-bottom:   0.8rem !important;
  }

  /* Botones */
  :deep(.custom-button) {
    font-size: 1.1rem !important;
    padding: 8px 0 !important;
    gap: 6px !important;
  }

  /* Mensajes de error */
  :deep(.error-msg) {
    font-size: 0.8rem !important;
    margin-bottom: 0.5rem !important;
  }

  /* Enlaces de login */
  :deep(.login-links) {
    font-size: 1rem !important;
    margin-top: 0.8rem !important;
  }
}
/* MEDIA QUERY PARA TABLET PEQUEÑA ENTRE 768px y 834px END */



/* MEDIA QUERY PARA TABLET ENTRE 481px y 1024px START */
@media (min-width: 481px) and (max-width: 768px) {
  /* 1) Selector profundo para ion-content */
  :deep(ion-content) {
    position: relative;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  /* 2) Y para la tarjeta */
  :deep(.card-container) {
    position: absolute;
    top: 31%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 80vw;
    max-width: 600px;
    height: 65vh;
    margin: 0;
  }
}
/* MEDIA QUERY PARA TABLET ENTRE 481px y 1024px END */



/* MEDIA QUERY PARA MOVILES START 480px */
@media (max-width: 480px) {
  /* 1) Centrar dentro de ion-content */
  :deep(ion-content) {
    position: relative;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  /* 2) Saca la tarjeta del flujo y céntrala */
:deep(.card-container) {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 90vw;        /* ocupa casi todo el ancho */
    max-width: 360px;   /* no pase de cierto tamaño */
    height: 90vh;
    margin: 0;
  }

  /* Inputs y selects más compactos */
  :deep(.custom-input),
  :deep(.custom-select) {
    font-size: 0.9rem !important;
    padding: 1px !important;
    margin-bottom: 0.5rem !important;
  }

  /* Botones más compactos */
  :deep(.custom-button) {
    font-size: 1rem !important;
    padding: 6px 0 !important;
    gap: 4px !important;
  }

  /* Título un poco más pequeño */
  :deep(.card-face h2) {
    font-size: 1.2rem !important;
    margin-bottom:  0.85rem !important;
    margin-top: 0;
  }

  /* Links pequeños */
  :deep(.login-links) {
    font-size: 0.9rem !important;
    margin-top: 0.5rem !important;
  }
   /* Mensajes de error más pequeños */
  :deep(.error-msg) {
    font-size: 0.65rem !important;
    margin-bottom: 0.3rem !important;
  }
} 
/* MEDIA QUERY PARA MOVILES END 480px */



/* MEDIA QUERY PARA MOVILES START (≤320px) */
@media (max-width: 320px) {
  /* 1) Evita scroll horizontal */
  :deep(ion-content.login-page) {
    padding: 0 !important;
    overflow-x: hidden !important;
  }

  /* 2) Tarjeta full-width con un pequeño “gap” (10px por lado) */
  :deep(.card-container) {
    width: calc(100% - 20px) !important;
    max-width: none !important;
    border-radius: 4px !important;
    box-shadow: none !important;
    height:90vh !important;
  }

  /* 3) Padding interno reducido */
  :deep(.card-face) {
    padding: 0.25rem !important;
  }

  /* 4) Título compacto */
  :deep(.card-face h2) {
    font-size: 1.2rem !important;
    margin-bottom: 0.4rem !important;
  }

  /* 5) Inputs y selects muy ajustados */
  :deep(.custom-input),
  :deep(.custom-select) {
    --padding-start: 4px !important;
    --padding-end:   4px !important;
    margin-right: 1% !important;
    margin-left: 1% !important;

    margin-bottom:   0.1rem !important;
  }

  /* 6) Botón mínimo viable */
  :deep(.custom-button) {
    font-size: 0.7rem !important;
    padding:  0 !important;
    gap: 1px !important;
  }

  /* 7) Mensajes de error y enlaces compactos */
  :deep(.error-msg) {
    font-size: 0.55rem !important;
    margin-bottom: 0.2rem !important;
  }
  :deep(.login-links) {
    font-size: 0.45rem !important;
    margin-top: 0.2rem !important;
  }
}
/* MEDIA QUERY PARA MOVILES END */






/* OLVIDASTE TU CONTRASEÑA START */

ion-modal::part(content) {
  border-radius: 16px;
  max-width: 500px;
  margin: auto;
  background-color: #ffffff;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
  justify-content: center;
  align-items: center;
}

ion-modal.forgot-password-modal::part(content) {
  /* 👇 Ajusta la altura al contenido */
  height: 280px;
  max-height: fit-content;
  display: inline-block;
}

ion-modal.reset-password-modal::part(content) {
  /* 👇 Ajusta la altura al contenido */
  height: 360px;
  max-height: fit-content;
  display: inline-block;
}

.modal-header {
  margin: 0;
  padding: 0;
  border-radius: 16px 16px 0 0;
}

ion-toolbar {
  --min-height: 56px;
  --background:var(--blue-primary-color);
  --color: var(--text-color-secundary);
  
  font-weight: bold;
}

ion-title {
  font-size: 1.5rem;
  text-align: center;
  margin-left: 1%;
}

.modal-body {
  display: flex;
  flex-direction: column;
  justify-content: center;
  gap: 16px;
}

.custom-input {
  
  font-size: 1.2rem;
}

.enviar {
  --background: var(--blue-primary-color);
  --color: var(--text-color-secundary);
  font-weight: bold;
  margin-bottom: 4%;
}

.clean {
  --color: var(--text-color-secundary);
  --background: var(--orange-secundary-color);
  font-weight: bold;
  margin-top: -4px;
}
/* OLVIDASTE TU CONTRASEÑA END */

</style>
 
 
<style>
 /*Hacer mas grande el popover*/
ion-popover.wide-popover::part(content) {
  width: 450px !important;
  max-width: 90vw;
}
</style>
 