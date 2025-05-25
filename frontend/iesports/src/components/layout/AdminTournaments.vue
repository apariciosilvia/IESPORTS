<!-- src/components/CreateTournaments.vue -->
<template>
  <ion-content fullscreen :scroll-events="true" style="--background: #F5F5F5;">
  <section class="tournaments">
    <div class="header">
      <h2 class="tittle">Torneos</h2>
      <ion-button class="new-btn" @click="isModalOpen = true">
        <span class="material-symbols-outlined">add_circle</span>
        Nuevo Torneo
      </ion-button>
    </div>
    <div class="table">
    <table class="tournaments-table">
      <thead>
        <tr>
          <th>Nombre</th>
          <th>Año</th>
          <th>Estado</th>
          <th>Equipos</th>
          <th>Deporte</th>
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="t in tournamentsAdminDTO" :key="t.tournament.id">
          <td>{{ t.tournament.name }}</td>
          <td>{{ t.tournament.date }}</td>
          <td>{{ t.tournament.state }}</td>
          <td>
            <p v-if="t.teams.length === 0">Sin equipos</p>
            <ion-select
              v-else
              interface="popover"
              :interface-options="{ cssClass: 'no-border-popover' }"
              ok-text="Cerrar"
              :selectedText="t.teams[0].name"
              class="list-teams"
            >
              <ion-select-option
                v-for="team in t.teams"
                :key="team.id"
                :value="team.id"
              >
                {{ team.name }}
              </ion-select-option>
            </ion-select>
          </td>
          <td>{{ t.tournament.sport.name }}</td>
          <td>
            <div class="actions">
              <!-- Botón de editar, que abrirá el popup -->
              <button type="button" class="action-btn edit-btn">
                <span class="material-symbols-outlined edit-icon">edit_square</span>
              </button>
              <!-- Botón de eliminar -->
              <button type="button" class="action-btn delete-btn">
                <span class="material-symbols-outlined delete-icon">delete</span>
              </button>
            </div>
          </td>
        </tr>
      </tbody>
    </table>
    </div>

    <!-- 2) Definimos el modal, ligado a isModalOpen -->
      <!-- Modal principal -->
      <ion-modal :is-open="isModalOpen" backdrop-dismiss="false" swipe-to-close="false">
        
        <!-- Cabecera del modal -->
        <ion-header>
          <ion-toolbar class="white-header">
            <ion-title>NUEVO TORNEO</ion-title>
            <ion-buttons slot="end">
              <ion-button @click="isModalOpen = false">
                Cerrar
              </ion-button>
            </ion-buttons>
          </ion-toolbar>
        </ion-header>

        <!-- Cuerpo del modal: aquí irá el formulario -->
        <!-- Cuerpo del modal -->
    <ion-content class="modal-body">
      <!-- Reemplazo de ion-grid -->
      <div class="modal-grid">
        <!-- Fila principal -->
        <div class="row">
          <!-- Columna Izquierda (50%) -->
          <div class="column-left">
            <ion-input
              label="Nombre"
              
              label-placement="floating"
              fill="outline"
              placeholder="Escribe un nombre"
              class="input-name"
            />
            <ion-list class="sports">
              <ion-item class="clean-select" lines="none" detail="false">
                <ion-select
                  aria-label="Deporte"
                  interface="popover"
                  placeholder="Deporte"
                  class="list-sports"
                >
                  <ion-select-option value="apples">Apples</ion-select-option>
                  <ion-select-option value="oranges">Oranges</ion-select-option>
                  <ion-select-option value="bananas">Bananas</ion-select-option>
                </ion-select>
              </ion-item>
            </ion-list>

            <div class="radio-item">
              <h6>Número de equipos</h6>
              <div class="glass-radio-group">
                <input type="radio" name="teams" id="glass-silver" checked />
                <label for="glass-silver">4</label>
                <input type="radio" name="teams" id="glass-gold" />
                <label for="glass-gold">8</label>
                <input type="radio" name="teams" id="glass-platinum" />
                <label for="glass-platinum">16</label>
                <div class="glass-glider"></div>
              </div>
            </div>
          </div>

          <!-- Columna Derecha (50%) -->
          <div class="column-right">
            <div class="teams-panel">
              <div class="panel-header">
                <span>Equipos</span>
                <span class="count"></span>
              </div>
              <ion-searchbar
                class="custom-searchbar"
                placeholder="Buscar equipo…"
                show-cancel-button="focus"
              />
              <ion-list>
                <!-- <ion-item
                  v-for="team in filteredTeams"
                  :key="team.id"
                >
                  <ion-label>{{ team.name }}</ion-label>
                  <ion-button slot="end" fill="clear" @click="addTeam(team)">
                    <ion-icon slot="icon-only" name="add-circle-outline" />
                  </ion-button>
                </ion-item> -->
              </ion-list>
            </div>
          </div>
        </div>

        <!-- Tabla de emparejamientos -->
        <div class="row">
          <div class="colum-down">
            <table class="match-table">
              <thead>
                <tr>
                  <th>Equipo 1</th>
                  <th>Equipo 2</th>
                  <th>Fecha partido (opcional)</th>
                  <th>Ronda</th>
                </tr>
              </thead>
              <tbody>
                <!-- <tr
                  v-for="(m, i) in matches"
                  :key="i"
                >
                  <td>{{ m.team1.name }}</td>
                  <td>{{ m.team2.name }}</td>
                  <td>
                    <ion-datetime
                      display-format="DD/MM/YYYY"
                      picker-format="DD/MM/YYYY"
                      v-model="m.date"
                    />
                  </td>
                  <td>{{ m.round }}</td>
                </tr> -->
              </tbody>
            </table>
          </div>
        </div>
        <div class="row">
          <div class="colum-down2">
            <h1>ihasfghijasdhjfoj</h1>
          </div>

        </div>
      </div>
    </ion-content>
    </ion-modal>
    </section>
  </ion-content>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { IonSelect, IonSelectOption, IonContent } from '@ionic/vue';

import type { TournamentAdminDTO } from '@/model/tournamentAdminDTO';

import { getTeamsByTournamentId } from '@/services/tournamentService';

const isModalOpen = ref(false); // controla visibilidad del popup

// 1) Array reactivo que usa tu DTO
const tournamentsAdminDTO = ref<TournamentAdminDTO[]>([]);

// 2) Al montar el componente, pedimos los datos
onMounted(async () => {
  try {
    const data = await getTeamsByTournamentId();
    tournamentsAdminDTO.value = data;
    console.log('ADMIN TORNEOS',tournamentsAdminDTO.value);
  } catch (error) {
    console.error('Error cargando torneos:', error);
  }
});



</script>

<style scoped>
.tournaments {
  margin: 4.5rem;
  padding: 2.5rem;
  margin-top: 8%;
  background-color: #ffffff;
  border-radius: 10px;
  border: solid 1px #D9D8D8;

}
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1rem;
}

.header h2 {
  font-size: 2rem;
  font-weight: bolder;
  color: #0a2540;
}

.new-btn {
  --background: #e22f28;
  color: #fff;
  font-weight: bolder;
}

.new-btn .material-symbols-outlined{
  margin-right: 5px;
}

.list-teams{
  --background: #e22f28;
  --border-radius: 5px;
  color: white;
  font-weight: bolder;
  --padding-start: 1rem;  
  --padding-end: 1rem;
}
.list-teams::part(icon) {
  color: white;
}


/* Habilita scroll horizontal cuando no quepa */
.table {
  overflow-x: auto;
  -webkit-overflow-scrolling: touch;
}

/* Asegura que la tabla use todo el ancho disponible */
.table .tournaments-table {
  width: 100%;
  min-width: 600px;
}


.tournaments-table {
  border-collapse: collapse; 
  width: 100%;
}

/* 1) Eliminamos cualquier borde en los lados de celdas */
.tournaments-table th,
.tournaments-table td {
  padding: 1rem 3rem;
  vertical-align: left;
}

.tournaments-table tbody  {
  border: 1px solid #ddd;
}

.tournaments-table tbody tr {
 
  border-bottom: 1px solid #ddd;
}

/* 5) Hover suave en filas */
.tournaments-table tbody tr:hover {
  background-color: #fafafa;
}

/* 3) Para la primera fila del body, igualar estilo con el thead si quieres */
.tournaments-table tbody tr:first-child td {
  border-top: 1px solid #ddd;
}

/* 1) Estilo del encabezado */
.tournaments-table thead {
  background-color: #f5f5f5;
}

.tournaments-table thead  {
  border: 1px solid #ddd;
}

.actions {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #0a2540;
  border-radius: 5px;
  padding: 10px 2px;
  max-width: 110px;
  gap: 20%;
}

.action-btn {
  background: none;
  border: none;
  padding: 0;
  margin: 0;
  cursor: pointer;
}

.action-btn .material-symbols-outlined {
  font-weight: 700;
}

.actions button:last-child {
  border-left: 1px solid rgba(255, 255, 255, 0.466);
  padding-left: 15%;
}

.edit-icon{
  color: white;
}

.delete-icon{
  color: #e22f28;
}

ion-modal {
  --border-radius: 10px;
  --backdrop-opacity: 0.6;/* opacidad del fondo para que no quede tan oscuro */
  --width: 100%;
  --max-width: 1300px;
  --height: 700px;
}
ion-modal::part(backdrop) {
  backdrop-filter: blur(1px); /* desenfoque */
  background-color: rgba(0, 0, 0, 0.3); /* semitransparente */
}



.white-header {
  --color: #ffffff;
  --background: #e22f28;
  display: flex;           
  justify-content: center;
  align-items: center;
}

.white-header ion-title {
  left: 100%;
  transform: translateX(3%);
  font-weight: bolder;
  font-size: 1.5rem;

}

.sc-ion-input-md-h {
    --highlight-color-focused: #022029;
    --highlight-color-valid: var(--ion-color-success, #2dd55b);
    --highlight-color-invalid: var(--ion-color-danger, #e22f28);
    --highlight-color: var(--highlight-color-focused);
}

.input-name{
  --border-color: #022029;   /* color del borde */
  --border-style: solid;     /* estilo de línea */
  --border-width: 1px;       /* grosor */
  /* color del texto */
  --color: #022029;
  /* color del placeholder, si quisieras otro distinto */
  --placeholder-color: #999999;
  text-align: left ;
}

.sports{
  padding: 0;
    margin-top: 4%;
   
}

.list-sports{
  --background: #e22f28;
  --border-radius: 5px;
  color: white;
  font-weight: bolder;
  font-weight: bolder;
  --padding-start: 1rem;  
  --padding-end: 1rem;
}

.list-sports::part(icon) {
  color: white;
}


.list-sports[data-v-bac0bc6b] {
    --background: #e22f28;
    --border-radius: 5px;
    --color: white;
    --placeholder-color: #ffffff;
    font-weight: bolder;
    --padding-start: 1rem;
    --padding-end: 1rem;

}
.clean-select {
  margin: 0;               /* quita márgenes exteriores */
  padding: 0;              /* quita relleno interno */
  
  /* anula todo el padding interno de ion-item */
  --padding-start: 0;
  --padding-end: 0;
  --inner-padding-start: 0;
  --inner-padding-end: 0;
}

/* Haz cada fila un flex container */
.modal-grid .row {
  display: flex;
  flex-wrap: wrap;
  gap: 6%;
  margin-top: 5%;
  margin-left: 6%;
  margin-right: 6%;

}

/* Columna izquierda y derecha comparten fila: 50% cada una */
.column-left,
.column-right {
  flex: 0 0 50%;      /* base y máximo 50% del ancho */
  box-sizing: border-box;
  padding: 0 0.5rem;  /* opcional: espacio interno */
}

.column-left{
  max-width: 40%;
}

.column-right{
  max-width: 60%;
  background-color: #f3f3f3;
  border-radius: 10px;
  padding: 4%;
}

/* La columna de abajo ocupa el 100% y va en otra fila */
.colum-down {
  flex: 0 0 100%;
  max-width: 100%;
  box-sizing: border-box;
  padding: 1rem 0.5rem; /* separa de las anteriores */
  background-color: #022029;
}

.colum-down2{
  flex: 0 0 100%;
  max-width: 100%;
  box-sizing: border-box;
  padding: 1rem 0.5rem; /* separa de las anteriores */
  background-color: #2dd55b;
}

.radio-item {
  margin-top: 1rem;           /* separa del resto */
}
.radio-item h6 {
  margin: 0 0 0.5rem;         /* espacio debajo del título */
  font-weight: 600;
  text-align: left;
  color: #0a2540;
}

/* contenedor “pill” */
.glass-radio-group {
  position: relative;         
  display: flex;              /* mantiene todo en fila */
  background: #ececec;        /* gris claro */
  border-radius: 22px;        /* bordes redondeados */
  overflow: hidden;           /* recorta el “glider” */
  width: max-content;         /* ajusta al contenido */
}

/* ocultamos los radios nativos */
.glass-radio-group input {
  display: none;
}

/* cada etiqueta ocupa 1/3 dentro de la “pill” */
.glass-radio-group label {
  flex: 1;
  text-align: center;
  padding: 0.75rem 1.5rem;
  cursor: pointer;
  font-weight: 600;
  z-index: 2;                 /* por encima del glider */
}

/* barra deslizante coloreada */
.glass-glider {
  position: absolute;
  top: 0; bottom: 0;
  width: 33.333%;             /* un tercio del ancho total */
  background: #979797;        /* rojo central */
  border-radius: 22px;
  z-index: 1;
  transition: transform 0.3s ease;
}

/* mueve el glider según el radio seleccionado */
#glass-silver:checked ~ .glass-glider   { transform: translateX(0%);   }
#glass-gold:checked   ~ .glass-glider   { transform: translateX(100%); }
#glass-platinum:checked ~ .glass-glider { transform: translateX(200%); }


  /* Silver */
  #glass-silver:checked ~ .glass-glider {
    transform: translateX(0%);
    background: linear-gradient(135deg, #ffd70055, #ffcc00);
    box-shadow:
     0 0 18px rgba(255, 215, 0, 0.5),
     0 0 10px rgba(255, 235, 150, 0.4) inset;
  }

  /* Gold */
  #glass-gold:checked ~ .glass-glider {
    transform: translateX(100%);
    background: linear-gradient(135deg, #e2632865, #e22f28);
    box-shadow:
      0 0 18px rgba(255, 72, 0, 0.336),
      0 0 10px rgba(255, 174, 150, 0.089) inset;
  }

  /* Platinum */
  #glass-platinum:checked ~ .glass-glider {
    transform: translateX(200%);
    background: linear-gradient(135deg, #d0e7ff55, #0a2540);
    box-shadow:
      0 0 18px rgba(160, 216, 255, 0.5),
      0 0 10px rgba(200, 240, 255, 0.4) inset;
  }

.custom-searchbar {
  /* fondo y bordes tipo input */
  --background: #ffffff;
  --border-radius: 8px;
  --border-color: #0a2540;

  /* altura y padding interior */
  --min-height: 40px;
  --padding-start: 0.75rem;
  --padding-end: 0.75rem;
  

  /* icono y placeholder en gris suave */
  --icon-color: #0a2540;
  
  --placeholder-color: #888888;
}

</style>
