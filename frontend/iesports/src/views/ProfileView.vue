<template>
    <IonHeader>
      <IonToolbar style="--background: #42b983; --color: white;">
        <IonButton @click="goTo('/')" slot="start" fill="clear">
          <span class="material-symbols-outlined back-icon">arrow_back</span>
        </IonButton>

        <IonTitle>Perfil</IonTitle>
      </IonToolbar>
    </IonHeader>
 
    <IonContent class="ion-padding">
      <IonCard class="profile-card">
        

        <IonCardContent>
          <IonList lines="full">
            <IonItem>
              <span class="material-symbols-outlined">person</span>
              <IonLabel class="item-label">
                <span class="item-title">Nombre</span>
                <span class="item-value">{{ user.name }}</span>
              </IonLabel>
            </IonItem>
            <IonItem>
              <span class="material-symbols-outlined">mail</span>
              <IonLabel class="item-label">
                <span class="item-title">Correo</span>
                <span class="item-value">{{ user.email }}</span>
              </IonLabel>
            </IonItem>
            <IonItem>
              <span class="material-symbols-outlined">school</span>
              <IonLabel class="item-label">
                <span class="item-title">Curso</span>
                <span class="item-value">{{ user.course?.nombre || 'No disponible' }}</span>
              </IonLabel>
            </IonItem>
            <IonItem>
              <span class="material-symbols-outlined">assignment_ind</span>
              <IonLabel class="item-label">
                <span class="item-title">Rol</span>
                <span class="item-value">{{ user.role?.name || 'Usuario' }}</span>
              </IonLabel>
            </IonItem>
          </IonList>
        </IonCardContent>

      </IonCard>
    </IonContent>


</template>

<script setup lang="ts">
import { IonHeader, IonContent, IonToolbar, IonTitle, IonButton,   IonItem,IonList, IonCardContent, IonCard, IonLabel } from '@ionic/vue';
import { ref, onMounted} from 'vue';
import { useRouter } from 'vue-router';
const router = useRouter();

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

function goTo(ruta: string) {
  router.push(ruta)
}

console.log(user.value);
</script>

<style scoped>
/* Estilos para la tarjeta de perfil */
IonCard {
  --background: #ffffff;
  border-radius: 12px;
  box-shadow: 0 4px 16px rgba(0,0,0,0.1);
  margin: 1rem 0;
}

/* Lista de ítems */
IonList {
  --padding-top: 0;
  --padding-bottom: 0;
  
}

/* Cada elemento de la lista */
IonItem {
  --inner-padding-end: 16px;
  --inner-padding-start: 16px;
  --border-width: 0 0 1px 0;
  --border-style: solid;
  --border-color: #eee;
  padding: 12px 0;
  display: flex;
  align-items: center;
}

/* Iconos al inicio de cada ítem */
IonIcon[slot="start"] {
  font-size: 1.4rem;
  color: #42b983;
  margin-right: 12px;
}

/* Etiquetas de texto */
IonLabel {
  font-size: 1rem;
  font-weight: 500;
  color: #333;
}

/* Ajuste de título de la tarjeta (si lo quieres) */
IonCardTitle {
  font-size: 1.4rem;
  font-weight: bold;
  margin-bottom: 0.5rem;
}

/* Espaciado extra al primer ítem */
IonItem:first-child {
  padding-top: 16px;
}

/* Espaciado extra al último ítem */
IonItem:last-child {
  padding-bottom: 16px;
  --border-width: 0; /* sin línea inferior */
}

/* Estilo para los spans de icono */
.profile-card span.material-symbols-outlined {
  font-size: 1.5rem;           /* tamaño más grande */
  color: #42b983;              /* verde de la app */
  margin-right: 0.75rem;       /* separa del texto */
  min-width: 1.5rem;           /* fuerza ancho mínimo */
  text-align: center;          /* centra el glifo */
}

.back-icon {
  color: white !important;
  font-size: 1.6rem; /* opcional: aumenta un poco el tamaño */
}


.profile-card {
  --background: #ffffff;
  width: 90vw !important;
  max-width: 920px !important;
  margin: 3rem auto !important;
  border-radius: 12px;
  box-shadow: 0 4px 16px rgba(0,0,0,0.1);
}


/* Etiqueta con título y valor */
.item-label {
  display: flex;
  flex-direction: column;
}

/* El texto del título (e.g. “Nombre”) */
.item-title {
  font-size: 0.85rem;
  font-weight: 600;
  color: #666;
  margin-bottom: 2px;
}

/* El valor en sí (e.g. “Pepito”) */
.item-value {
  font-size: 1rem;
  font-weight: 500;
  color: #333;
}

/* Ajuste para que icono y label queden alineados */
IonItem {
  display: flex !important;
  align-items: center;
}

.profile-card span.material-symbols-outlined {
  font-size: 1.5rem;
  color: #42b983;
  margin-right: 0.75rem;
}


/* Móvil: hasta 480px */
@media (max-width: 480px) {
  .profile-card {
    --background: #ffffff;
    width: 90vw !important;
    max-width: 320px !important;
    margin: 2rem auto !important;
    border-radius: 12px;
    box-shadow: 0 4px 16px rgba(0,0,0,0.1);
  }
}


</style>
