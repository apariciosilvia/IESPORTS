<template>
 <!-- Cabecera del modal -->
        <ion-header>
          <ion-toolbar class="white-header">
            <ion-title>EDITAR TORNEO</ion-title>
            <ion-buttons slot="end">
              <ion-button @click="$emit('close')">
                Cerrar
              </ion-button>
            </ion-buttons>
          </ion-toolbar>
        </ion-header>
    <ion-content class="modal-body">
      <div class="modal-grid">
        <div class="row">
          <!-- Columna Izquierda (40%) -->
          <div class="column-left">
            <ion-input
              label="Nombre"
              label-placement="floating"
              fill="outline"
              placeholder="Escribe un nombre"
              class="input-name"
            />
            <ion-list class="sports">
              <ion-item class="clean-select" lines="none">
                <ion-select
                  interface="popover"
                  placeholder="Selecciona un deporte"
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

          <!-- Columna Derecha (60%) -->
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
            <h1>Lorem ipsum dolor sit amet consectetur adipisicing elit. Laboriosam cumque iste, error reprehenderit culpa enim soluta dolorem non sint nam ducimus sequi a nobis illum repudiandae libero reiciendis adipisci dolor?</h1>
                      <h1>Lorem ipsum dolor sit amet consectetur adipisicing elit. Laboriosam cumque iste, error reprehenderit culpa enim soluta dolorem non sint nam ducimus sequi a nobis illum repudiandae libero reiciendis adipisci dolor?</h1>

          </div>
        </div>
        
      </div>
    </ion-content>
    <ion-footer class="row">
      <div class="colum-down2">
        <ion-button
          expand="block"
          class="btn-clean"
        ><span class="material-symbols-outlined">mop</span>Limpiar</ion-button>
        <ion-button
          expand="block"
          class="btn-save"
        ><span class="material-symbols-outlined">save</span>Guardar Cambios</ion-button>
      </div>

    </ion-footer>
</template>

<script setup lang="ts">
defineEmits(['close'])

import { IonSelect, IonSelectOption, IonContent, IonSearchbar, IonList, IonItem, IonInput, IonHeader, IonToolbar, IonButton, IonTitle, IonButtons, IonFooter } from '@ionic/vue';

</script>

<style scoped>
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

.modal-body {
  --padding-bottom: 0;
}

.modal-grid .row {
  display: flex;
  flex-wrap: wrap;
  gap: 10%;
  margin-top: 5%;
  margin-left: 6%;
  margin-right: 6%;
}

.column-left, .column-right {
  flex: 0 0 50%;
  box-sizing: border-box;
  padding: 0 0.5rem;
}

.column-left {
  max-width: 40%;
}

.column-right {
  max-width: 60%;
  background-color: #f3f3f3;
  border-radius: 10px;
  padding: 4%;
}

.colum-down {
  flex: 0 0 100%;
  max-width: 100%;
  box-sizing: border-box;
  padding: 1rem 0.5rem;
  background-color: #30f000;
}

.colum-down2 {
  flex: 0 0 100%;
  max-width: 100%;
  box-sizing: border-box;
  padding: 1rem 0.5rem;
  display: flex;
  justify-content: flex-end;
  gap: 1rem;
  margin-left: auto;
}

.input-name {
  --border-color: #022029;
  --border-style: solid;
  --border-width: 1px;
  --color: #022029;
  --placeholder-color: #999999;
  text-align: left ;
}

.sports {
  padding: 0;
  margin-top: 4%;
}

.list-sports {
  --background: #e22f28;
  --border-radius: 5px;
  color: white;
  --placeholder-color: #ffffff;
  font-weight: bolder;
  --padding-start: 1rem;
  --padding-end: 1rem;
}
.list-sports::part(icon) {
  color: #f2acaa;
}
ion-select::part(placeholder) {
  color: white;
  font-weight: bold;
}
.clean-select {
  margin: 0;
  padding: 0;
  --padding-start: 0;
  --padding-end: 0;
  --inner-padding-start: 0;
  --inner-padding-end: 0;
}

.radio-item {
  margin-top: 1rem;
}
.radio-item h6 {
  margin: 0 0 0.5rem;
  font-weight: 600;
  text-align: left;
  color: #0a2540;
}

.glass-radio-group {
  position: relative;         
  display: flex;
  background: #ececec;
  border-radius: 22px;
  overflow: hidden;
  width: max-content;
}
.glass-radio-group input {
  display: none;
}
.glass-radio-group label {
  flex: 1;
  text-align: center;
  padding: 0.75rem 1.5rem;
  cursor: pointer;
  font-weight: 600;
  z-index: 2;
}
.glass-glider {
  position: absolute;
  top: 0; bottom: 0;
  width: 33.333%;
  background: #979797;
  border-radius: 22px;
  z-index: 1;
  transition: transform 0.3s ease;
}

#glass-silver:checked ~ .glass-glider {
  transform: translateX(0%);
  background: linear-gradient(135deg, #ffd70055, #ffcc00);
}
#glass-gold:checked ~ .glass-glider {
  transform: translateX(100%);
  background: linear-gradient(135deg, #e2632865, #e22f28);
}
#glass-platinum:checked ~ .glass-glider {
  transform: translateX(200%);
  background: linear-gradient(135deg, #d0e7ff55, #0a2540);
}

.custom-searchbar {
  --background: #ffffff;
  --border-radius: 8px;
  --border-color: #0a2540;
  --min-height: 40px;
  --padding-start: 0.75rem;
  --padding-end: 0.75rem;
  --icon-color: #0a2540;
  --placeholder-color: #888888;
}

.btn-clean, .btn-save {
  --background: #0a2540;
  --border-radius: 8px;
  --color: white;
  --padding-start: 1rem;
  --padding-end: 1rem;
  font-weight: bold;
}
.btn-clean .material-symbols-outlined,
.btn-save .material-symbols-outlined {
  margin-right: 5px;
}
</style>
