<template>
  <div class="contact-container">
    <!-- Columna de texto -->
    <div class="contact-text">
      <h2>Contáctanos</h2>
      <p>Si tienes alguna pregunta o comentario, estaremos encantados de ayudarte.</p>
      <p>Puedes contactar con nosotros através de este correo: <b>equipo.iesports@gmail.com</b> o con este formulario.</p>

    </div>

    <!-- Columna de formulario -->
    <form @submit.prevent="handleSubmit" class="contact-form">
      <IonItem class="form-item">
        <IonLabel position="floating">Nombre</IonLabel>
        <IonInput v-model="name" required />
      </IonItem>
      <IonItem class="form-item">
        <IonLabel position="floating">Email</IonLabel>
        <IonInput type="email" v-model="email" required />
      </IonItem>
      <IonItem class="form-item">
        <IonLabel position="floating">Mensaje</IonLabel>
        <IonTextarea v-model="message" required />
      </IonItem>
      <IonButton expand="block" class="submit-button" type="submit">
        Enviar mensaje
      </IonButton>
    </form>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import {
  IonItem, IonLabel, IonInput, IonTextarea, IonButton
} from '@ionic/vue';

const name    = ref('');
const email   = ref('');
const message = ref('');

function handleSubmit() {
  console.log('Enviando formulario:', { name: name.value, email: email.value, message: message.value });
  // Limpieza y feedback
  name.value = '';
  email.value = '';
  message.value = '';
  alert('¡Mensaje enviado! Te responderemos pronto.');
}
</script>

<style scoped>
.contact-container {
  display: flex;
  align-items: flex-start;
  gap: 2rem;
  max-width: 900px;
  margin: 2rem auto;
  padding: 1rem;
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 6px 18px rgba(0,0,0,0.1);
}

/* Columna de texto */
.contact-text {
  flex: 1;
  padding-right: 1rem;
}

.contact-text h2 {
  margin-bottom: 0.5rem;
  color: #333;
}

.contact-text p {
  color: #666;
  line-height: 1.5;
}

/* Columna de formulario */
.contact-form {
  flex: 1;
  display: flex;
  flex-direction: column;
}

.form-item {
  margin-bottom: 1rem;
  --border-radius: 8px;
  --background: #f7f7f7;
}

/* Botón */
.submit-button {
  --background: #42b983;
  --color: white;
  font-weight: bold;
  margin-top: 0.5rem;
}

.submit-button:hover {
  --background: #369e6f;
}

/* Mobile: apila en una sola columna */
@media (max-width: 768px) {
  .contact-container {
    flex-direction: column;
    padding: 0.5rem;
  }
  .contact-text {
    padding-right: 0;
    margin-bottom: 1rem;
  }
}
</style>
