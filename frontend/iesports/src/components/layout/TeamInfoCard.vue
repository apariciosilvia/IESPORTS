<template>
  <div class="card">
    <div class="card-details">
      
      <p class="text-title">{{ teamInfo.team.name }}</p>

      <!-- Cuerpo: mostramos el ID y la lista de deportes -->
      <p class="text-body">
        Deportes:
        <span v-for="(sport, idx) in teamInfo.sports" :key="sport.id">
          {{ sport.name }}<span v-if="idx < teamInfo.sports.length - 1">, </span>
        </span>
      </p>
    </div>

    <!-- Botón que aparece al hacer hover -->
    <button class="card-button" @click="abrirModal">Ver integrantes</button>

    <!-- Modal de Ionic que muestra la lista de jugadores -->
    <!-- Modal con clase player-modal -->
    <IonModal :is-open="showModal" @didDismiss="cerrarModal" class="player-modal">
        <IonHeader class="modal-header">
          <IonToolbar>
            <IonTitle class="tittle">Integrantes de <b class="nameTeam">{{ teamInfo.team.name }}</b></IonTitle>
          </IonToolbar>
        </IonHeader>

        <IonContent class="modal-content ion-padding">
          <IonList class="player-item">
            <IonItem v-for="player in teamInfo.team.players" :key="player.id" class="player-item">
              <IonLabel>{{ player.name }} - {{ player.course.initials }}</IonLabel>
            </IonItem>
            <IonItem v-if="teamInfo.team.players?.length === 0" class="player-item">
              <IonLabel>No hay jugadores registrados</IonLabel>
            </IonItem>
          </IonList>
        </IonContent>
    </IonModal>
  </div>
</template>


<script setup lang="ts">
import { ref } from 'vue';
import { IonModal, IonHeader, IonToolbar, IonTitle, IonContent, IonList, IonItem, IonLabel } from '@ionic/vue';


import type { TeamInfoDTO } from '@/model/dto/teamInfoDTO';

// Desestructuramos la prop para obtener directamente `teamInfo`
const { teamInfo } = defineProps<{
  teamInfo: TeamInfoDTO;
}>();

// Estado reactivo para controlar la visibilidad del modal
const showModal = ref(false);

// Función que abre el modal
function abrirModal() {
  showModal.value = true;
}

// Función que cierra el modal
function cerrarModal() {
  showModal.value = false;
}
</script>

<style scoped>
/* Contenedor principal de la tarjeta */
.card {
  width: 290px;              /* Ancho fijo de la tarjeta */
  height: 254px;             /* Alto fijo de la tarjeta */
  border-radius: 20px;       /* Bordes redondeados */
  background: #f5f5f5;       /* Fondo gris claro */
  position: relative;        /* Para posicionar el botón dentro */
  padding: 1.8rem;           /* Espacio interno alrededor del contenido */
  border: 2px solid #c3c6ce; /* Borde gris tenue */
  transition: 0.5s ease-out; /* Transición suave al cambiar estilos (hover) */
  overflow: visible;         /* Permite que el botón sobresalga si fuera necesario */
}

/* Contenedor de texto dentro de la tarjeta */
.card-details {
  color: black;              /* Color de texto negro */
  height: 100%;              /* Ocupa todo el alto disponible */
  display: grid;             /* Usamos grid para centrar fácilmente */
  place-content: center;     /* Centra vertical y horizontalmente */
  gap: 0.5em;                /* Separación entre líneas (título, ID, deportes) */
}

/* Estilo del título (nombre del equipo) */
.text-title {
  font-size: 1.5em;          /* Tamaño de fuente mayor */
  font-weight: bold;         /* Negrita */
  margin: 0;                 /* Sin margen externo */
  color: var(--blue-primary-color);
}

/* Estilo del texto secundario (ID y deportes) */
.text-body {
  color: rgb(134, 134, 134); /* Gris medio para diferenciar del título */
  margin: 0;                 /* Sin margen externo */
  font-size: 1rem;           /* Tamaño de fuente normal */
}

/* Botón que aparece al hacer hover */
.card-button {
  position: absolute;        /* Para colocar en la parte inferior centrado */
  left: 50%;                 /* Centra horizontalmente (punto de referencia) */
  bottom: 0;                 /* Pega al borde inferior de la tarjeta */
  transform: translate(-50%, 125%); /* Inicial: fuera y abajo, oculto */
  width: 60%;                /* Ancho del botón relativo a la tarjeta */
  border-radius: 1rem;       /* Botón con bordes redondeados */
  border: none;              /* Sin borde extra */
  background-color: var(--orange-secundary-color);  /* Color azul vivo */
  color: #fff;               /* Texto blanco */
  font-size: 1rem;           /* Tamaño de fuente legible */
  padding: 0.5rem 1rem;      /* Espacio interno en el botón */
  opacity: 0;                /* Invisible inicialmente */
  transition: 0.3s ease-out; /* Transición suave al hacer hover */
}

/* Al pasar el cursor sobre la tarjeta (.card:hover), se aplica: */
.card:hover {
  border-color:var(--orange-secundary-color);     /* Cambia el borde a azul vivo */
  box-shadow: 0 4px 18px rgba(0, 0, 0, 0.25); /* Sombra más intensa */
}

/* Y al hacer hover, transformamos y mostramos el botón */
.card:hover .card-button {
  transform: translate(-50%, 50%); /* Mueve el botón hacia arriba dentro de la tarjeta */
  opacity: 1;                       /* Lo hace visible */
}





.card-button:hover {
  /* Al pasar el cursor, el botón se ilumina ligeramente */
  background-color: rgb(223, 97, 66); /* naranja más vivo */
  transform: translate(-50%, 45%) scale(1.02);
}




/* 1) Permitir que el header crezca si el título ocupa más de una línea */
::v-deep .modal-header {
  height: auto !important;        /* Quitamos altura rígida */
  padding-top: 1rem;              /* Dejamos algo de “respiro” arriba */
  padding-bottom: 0.75rem;        /* Espacio abajo para que no quede cortado */
}

/* 2) El título puede hacer wrap y ocupar varias líneas */
.tittle {
  white-space: normal;             /* Permitir salto de línea */
  word-break: break-word;          /* Romper palabra si es necesario */
  display: block;                  /* Asegura que ocupe todo el ancho disponible */
  padding-left: 1rem;              /* Un poco de padding en lugar de margin */
  padding-right: 1rem;             /* Evita que el texto quede pegado al borde */
  font-size: 1.1rem;               /* Reducimos ligeramente para que quepa mejor */
  line-height: 1.3;                /* Altura de línea un poco más compacta */
  color: var(--text-color-primary);
  font-weight: 600;
}

/* 3) Ajustar el nombre del equipo (en negrita) para que no herede margen */
.nameTeam {
  color: var(--orange-secundary-color);
  margin-left: 0;                  /* Quitamos el margen fijo */
  display: inline;                 /* Para que quede junto al texto previo */
}

/* 4) (Opcional) Si quieres que el IonTitle ocupe todo el espacio y tenga margen interno */
/*    Podemos apuntar directamente al part que Ionic usa internamente para el título */
::v-deep .modal-header ion-title {
  width: 100%;                     /* Que ocupe todo el ancho posible */
}

.player-modal{
  --border-radius: 10px;
   --width: auto;
  /* Opcional: puedes limitar también el ancho máximo si hace falta */
  --max-width: 100vw;
}

::v-deep .player-modal .modal-wrapper {
  display: inline-block;        /* Hace que el contenedor “shrink-to-fit” */
  width: auto !important;       /* Anula cualquier ancho impuesto */
  max-width: 100vw;              /* Evita que se sobredimensione más allá del 90% de la pantalla */
}

/* 3) Aseguramos que la página interna del modal también use ancho automático */
::v-deep .player-modal .modal-page {
  width: auto !important;
}

.player-item {
  /* IonItem es un contenedor flex; esto centra horizontalmente su contenido */
  justify-content: center;
  /* Asegura que el texto dentro del IonLabel quede centrado */
  text-align: center;
}
</style>
