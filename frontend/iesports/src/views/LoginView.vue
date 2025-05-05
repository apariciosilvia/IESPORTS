<template>
<<<<<<< HEAD
  <ion-page>
   <ion-content>
     <!-- <div id="vanta-login" class="vanta-bg"></div> -->
     <vue-particles
             id="tsparticles"
             @particles-loaded="particlesLoaded"
             :options="{
                     background: {
                         color: {
                             value: '#76d6ac'
                         }
                     },
                     fpsLimit: 120,
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
                                 opacity: 0.8,
                                 size: 40
                             },
                             push: {
                                 quantity: 4
                             },
                             repulse: {
                                 distance: 200,
                                 duration: 0.4
                             }
                         }
                     },
                     particles: {
                         color: {
                             value: '#ffffff'
                         },
                         links: {
                             color: '#ffffff',
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
           <h2>Iniciar sesión</h2>
           <form @submit.prevent="handleLogin">
             <ion-input v-model="loginData.email" type="email" placeholder="Correo electrónico" required class="custom-input" fill="outline" />
             <ion-input v-model="loginData.password" type="password" placeholder="Contraseña" required class="custom-input" fill="outline" />
             <ion-button type="submit" expand="block" class="custom-button">
               <ion-icon name="log-in-outline"></ion-icon>
               Entrar
             </ion-button>
           </form>
           <div class="login-links">
             <a href="#">¿Olvidaste tu contraseña?</a>
             <a href="#"  @click.prevent="showRegister = true; handleGetCourses()">
               ¿No tienes cuenta? Regístrate aquí
             </a>
           </div>
         </div>
 
         <!-- Registro -->
         <div class="card-face card-back">
           <h2>Registro</h2>
           <form @submit.prevent="handleRegister">
             <ion-input v-model="registerData.name" type="text" placeholder="Nombre completo" required class="custom-input" fill="outline" />
             <ion-input v-model="registerData.email" type="email" placeholder="Correo electrónico" required class="custom-input" fill="outline" />
             <ion-input v-model="registerData.password" type="password" placeholder="Contraseña" required class="custom-input" fill="outline" />
             <ion-input v-model="registerData.confirmPassword" type="password" placeholder="Confirmar contraseña" required class="custom-input" fill="outline" />
             <ion-select
                class="custom-select"
                v-model="selectedCourse"
                aria-label="Courses"
                interface="popover"
                placeholder="Selecciona un curso"
                :interfaceOptions="{ cssClass: 'wide-popover' }"
              >
                <ion-select-option
                  v-for="course in courses"
                  :key="course.id"
                  :value="course.id"
                >
                  {{ course.nombre }}
                </ion-select-option>
              </ion-select>
             <ion-button type="submit" expand="block" class="custom-button">
               <ion-icon name="person-add-outline"></ion-icon>
               Registrarse
             </ion-button>
           </form>
           <div class="login-links">
             <a href="#" @click.prevent="showRegister = false">
               ¿Ya tienes cuenta? Inicia sesión
             </a>
           </div>
         </div>
       </div>
     </div>
   </ion-content>
   </ion-page>
 </template>
 
 <script setup lang="ts">
 import { ref, type Ref } from 'vue';
 import { IonInput, IonButton, IonIcon, IonContent, IonPage, IonSelect, IonSelectOption } from '@ionic/vue';
 import { getCourses, login } from '../services/PersonaServices';
 import router from '../router';
 
 const particlesLoaded = async (container: any) => {
     console.log("Particles container loaded", container);
 };
 
 const showRegister = ref(false)
 
 const loginData = ref({ email: '', password: '' })
 const registerData = ref({ name: '', email: '', password: '', confirmPassword: '' })
 
 async function handleLogin() {
   try {
     const response: any = await login(loginData.value.email.trim(), loginData.value.password.trim())
     console.log('Login exitoso:', response)
 
     if (response && response.email) {
       // Guardar al usuario en localStorage
       localStorage.setItem('usuario', JSON.stringify(response))
 
       alert('Inicio de sesión exitoso')
       router.push('/');
     } else {
       alert('Credenciales incorrectas')
     }
   } catch (error) {
     console.error('Error al iniciar sesión:', error)
     alert('Error al iniciar sesión. Verifica tus credenciales.')
   }
 }
 
 
 function handleRegister() {
   if (registerData.value.password !== registerData.value.confirmPassword) {
     alert('Las contraseñas no coinciden')
     return
   }
   alert(`Registro:\nNombre: ${registerData.value.name}`)
 }
 
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
 
 @media (max-width: 480px) {
   .card-container {
     width: 95vw;
     max-width: 360px;
     aspect-ratio: 10 / 13;
     margin-top: 25%;
     
   }
 
   .card-face {
     padding: 1.5rem;
     
   }
 
   .card-face h2 {
     font-size: 1.5rem;
   }
 
   .custom-input,
   .custom-button {
     font-size: 0.9rem;
     padding: 10px;
   }
 
   .material-icons {
     font-size: 20px;
   }
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
 
 .card-face h2 {
   text-align: center;
   margin-bottom: 1.5rem;
   color: #333;
   font-weight: bold;
   font-size: 2.5rem;
 }
 
 .custom-input {
   width: 100%;
   margin-bottom: 1rem;
   
   font-size: 1.5rem;
   --border-radius: 8px;
   --background: #ffffff10;
   --border-color: #ccc;
   --box-shadow: none;
 }
 
 .custom-input:hover,
 .custom-input:focus,
 .custom-input.ion-touched.ion-valid{
   --background:  #42b9831f;
   --border-color: #cccccc38;
   --box-shadow: 0 0 0 6px #42b9838e;
 }
 
 .custom-select {
   width: 100%;
   margin-bottom: 1rem;
   font-size: 1.5rem;
 
   /* Igual que custom-input */
   --border-radius: 8px;
   --background: #ffffff10;
   --border-color: #ccc;
   --box-shadow: none;
 
   --padding-start: 10px;
   --padding-end: 10px;
   --min-height: 48px;
 
   color: #000; /* texto negro como los inputs */
   border: 1px solid var(--border-color);
   border-radius: var(--border-radius);
 }
 
 .custom-select:hover,
 .custom-select:focus,
 .custom-select.ion-touched.ion-valid {
   --background: #42b9831f;
   --border-color: #cccccc38;
   --box-shadow: 0 0 0 6px #42b9838e;
 }
 
 .custom-button {
   width: 100%;
   margin-top: 5%;
   --background: #42b983;
   --color: white;
   --border-radius: 8px;
   font-weight: bold;
   font-size: 1.5rem;
   display: flex;
   align-items: center;
   justify-content: center;
   gap: 8px;
 }
 
 .custom-button:hover {
   --background: #369e6f;
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
   color: #42b983;
   text-decoration: none;
 }
 
 .login-links a:hover {
   text-decoration: underline;
 }
 </style>
 
 
 <style>
 /*Hacer mas grande el popover*/
 ion-popover.wide-popover::part(content) {
   width: 450px !important;
   max-width: 90vw;
 }
 </style>
 
=======
 <ion-page>
  <ion-content>
    <!-- <div id="vanta-login" class="vanta-bg"></div> -->
    <vue-particles
            id="tsparticles"
            @particles-loaded="particlesLoaded"
            :options="{
                    background: {
                        color: {
                            value: '#76d6ac'
                        }
                    },
                    fpsLimit: 120,
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
                                opacity: 0.8,
                                size: 40
                            },
                            push: {
                                quantity: 4
                            },
                            repulse: {
                                distance: 200,
                                duration: 0.4
                            }
                        }
                    },
                    particles: {
                        color: {
                            value: '#ffffff'
                        },
                        links: {
                            color: '#ffffff',
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
          <h2>Iniciar sesión</h2>
          <form @submit.prevent="handleLogin">
            <ion-input v-model="loginData.email" type="email" placeholder="Correo electrónico" required class="custom-input" fill="outline" />
            <ion-input v-model="loginData.password" type="password" placeholder="Contraseña" required class="custom-input" fill="outline" />
            <ion-button type="submit" expand="block" class="custom-button">
              <ion-icon name="log-in-outline"></ion-icon>
              Entrar
            </ion-button>
          </form>
          <div class="login-links">
            <a href="#">¿Olvidaste tu contraseña?</a>
            <a href="#"  @click.prevent="showRegister = true; handleGetCourses()">
              ¿No tienes cuenta? Regístrate aquí
            </a>
          </div>
        </div>

        <!-- Registro -->
        <div class="card-face card-back">
          <h2>Registro</h2>
          <form @submit.prevent="handleRegister">
            <ion-input v-model="registerData.name" type="text" placeholder="Nombre completo" required class="custom-input" fill="outline" />
            <ion-input v-model="registerData.email" type="email" placeholder="Correo electrónico" required class="custom-input" fill="outline" />
            <ion-input v-model="registerData.password" type="password" placeholder="Contraseña" required class="custom-input" fill="outline" />
            <ion-input v-model="registerData.confirmPassword" type="password" placeholder="Confirmar contraseña" required class="custom-input" fill="outline" />
            <ion-list>
              <ion-item>
                <ion-select v-model="selectedCourse" aria-label="Courses" interface="popover" placeholder="Selecciona un curso">
                  <ion-select-option v-for="course in courses" :key="course.id" :value="course.id">
                    {{ course.nombre }}
                  </ion-select-option>
                </ion-select>
              </ion-item>
            </ion-list>
            <ion-button type="submit" expand="block" class="custom-button">
              <ion-icon name="person-add-outline"></ion-icon>
              Registrarse
            </ion-button>
          </form>
          <div class="login-links">
            <a href="#" @click.prevent="showRegister = false">
              ¿Ya tienes cuenta? Inicia sesión
            </a>
          </div>
        </div>
      </div>
    </div>
  </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import { ref, type Ref } from 'vue';
import { IonInput, IonButton, IonIcon, IonContent, IonPage } from '@ionic/vue';
import { getCourses, login } from '../services/PersonaServices';
import router from '../router';

const particlesLoaded = async (container: any) => {
    console.log("Particles container loaded", container);
};

const showRegister = ref(false)

const loginData = ref({ email: '', password: '' })
const registerData = ref({ name: '', email: '', password: '', confirmPassword: '' })

async function handleLogin() {
  try {
    const response: any = await login(loginData.value.email.trim(), loginData.value.password.trim())
    console.log('Login exitoso:', response)

    if (response && response.email) {
      // Guardar al usuario en localStorage
      localStorage.setItem('usuario', JSON.stringify(response))

      alert('Inicio de sesión exitoso')
      router.push('/');
    } else {
      alert('Credenciales incorrectas')
    }
  } catch (error) {
    console.error('Error al iniciar sesión:', error)
    alert('Error al iniciar sesión. Verifica tus credenciales.')
  }
}


function handleRegister() {
  if (registerData.value.password !== registerData.value.confirmPassword) {
    alert('Las contraseñas no coinciden')
    return
  }
  alert(`Registro:\nNombre: ${registerData.value.name}`)
}

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

@media (max-width: 480px) {
  .card-container {
    width: 95vw;
    max-width: 360px;
    aspect-ratio: 10 / 13;
    margin-top: 25%;
    
  }

  .card-face {
    padding: 1.5rem;
    
  }

  .card-face h2 {
    font-size: 1.5rem;
  }

  .custom-input,
  .custom-button {
    font-size: 0.9rem;
    padding: 10px;
  }

  .material-icons {
    font-size: 20px;
  }
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

.card-face h2 {
  text-align: center;
  margin-bottom: 1.5rem;
  color: #333;
  font-weight: bold;
  font-size: 2.5rem;
}

.custom-input {
  width: 100%;
  margin-bottom: 1rem;
  
  font-size: 1.5rem;
  --border-radius: 8px;
  --background: #ffffff10;
  --border-color: #ccc;
  --box-shadow: none;
}

.custom-input:hover,
.custom-input:focus,
.custom-input.ion-touched.ion-valid{
  --background:  #42b9831f;
  --border-color: #cccccc38;
  --box-shadow: 0 0 0 6px #42b9838e;
}


.custom-button {
  width: 100%;
  margin-top: 5%;
  --background: #42b983;
  --color: white;
  --border-radius: 8px;
  font-weight: bold;
  font-size: 1.5rem;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
}

.custom-button:hover {
  --background: #369e6f;
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
  color: #42b983;
  text-decoration: none;
}

.login-links a:hover {
  text-decoration: underline;
}
</style>
>>>>>>> parent of 2866dc1 (corrección select)
