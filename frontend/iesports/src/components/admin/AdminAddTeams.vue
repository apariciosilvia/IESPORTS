<!-- src/components/NewTeam.vue -->
<template>
  <ion-header>
    <ion-toolbar class="header-red">
      <ion-title>NUEVO EQUIPO</ion-title>
      <ion-buttons slot="end">
        <ion-button @click="$emit('close')">Cerrar</ion-button>
      </ion-buttons>
    </ion-toolbar>
  </ion-header>

  <ion-content class="content">
    <div class="form-container">
      <!-- Input Nombre -->
      <ion-input
        label="Nombre"
        label-placement="floating"
        fill="outline"
        placeholder="Escribe un nombre"
        class="input-name"
        v-model="teamName"
      />

      <div class="panels">
        <!-- Panel Izquierdo: Lista de Miembros -->
        <div class="panel-left">
          <div class="panel-header">
            <span>Miembros</span>
            <span class="counter">{{ filteredUsers.length }}/∞</span>
          </div>

          <ion-searchbar
            v-model="searchText"
            placeholder="Buscar usuarios..."
            class="searchbar"
          />

          <div class="members-list">
            <div
              class="member-row"
              v-for="user in filteredUsers"
              :key="user.id"
            >
              <span class="member-name">{{ user.name }}</span>
              <button
                class="btn-add"
                @click="addMember(user)"
                :disabled="isInTeam(user)"
              >
                <span class="material-symbols-outlined">add_circle</span>
              </button>
            </div>
          </div>
        </div>

        <!-- Panel Derecho: Miembros en el Equipo -->
        <div class="panel-right">
          <div class="panel-header">
            <span>Miembros en el Equipo</span>
            <span class="counter">{{ selectedMembers.length }}/{{ maxMembers }}</span>
          </div>

          <div class="selected-list">
            <div
              class="selected-row"
              v-for="(member, idx) in selectedMembers"
              :key="member.id"
            >
              <span class="selected-name">{{ member.name }}</span>
              <button class="btn-remove" @click="removeMember(idx)">
                <span class="material-symbols-outlined">delete</span>
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </ion-content>

  <ion-footer>
    <div class="footer-buttons">
      <ion-button
        expand="block"
        class="btn-clean"
        @click="resetForm"
      >
        <span class="material-symbols-outlined">mop</span>Limpiar
      </ion-button>
      <ion-button
        expand="block"
        class="btn-save"
        @click="createTeam"
      >
        <span class="material-symbols-outlined">save</span>Crear Equipo
      </ion-button>
    </div>
  </ion-footer>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from 'vue';
import {
  IonHeader,
  IonToolbar,
  IonTitle,
  IonButtons,
  IonButton,
  IonContent,
  IonFooter,
  IonInput,
  IonSearchbar,
} from '@ionic/vue';
import { getPersons } from '@/services/personServices';
import { addTeam } from '@/services/teamService';
import type { TeamAddDTO } from '@/model/dto/teamAddDTO';
import type { Person } from '@/model/person';

interface User {
  id: number;
  name: string;
}

const emit = defineEmits<{
  (e: 'close'): void;
}>();

const teamName = ref<string>('');
const allUsers = ref<User[]>([]);
const searchText = ref<string>('');
const selectedMembers = ref<User[]>([]);
const maxMembers = 15;

onMounted(async () => {
  try {
    allUsers.value = await getPersons();
  } catch (e) {
    console.error('Error cargando usuarios:', e);
  }
});

const filteredUsers = computed(() => {
  return allUsers.value.filter((u) => {
    const matchesSearch = u.name
      .toLowerCase()
      .includes(searchText.value.toLowerCase());
    return matchesSearch && !selectedMembers.value.some((m) => m.id === u.id);
  });
});

function addMember(user: User) {
  if (
    selectedMembers.value.length < maxMembers &&
    !selectedMembers.value.some((m) => m.id === user.id)
  ) {
    selectedMembers.value.push(user);
  }
}

function removeMember(index: number) {
  selectedMembers.value.splice(index, 1);
}

function isInTeam(user: User) {
  return selectedMembers.value.some((m) => m.id === user.id);
}

function resetForm() {
  teamName.value = '';
  searchText.value = '';
  selectedMembers.value = [];
}

async function createTeam() {
  if (!teamName.value.trim()) {
    alert('El nombre del equipo es obligatorio');
    return;
  }
  if (selectedMembers.value.length === 0) {
    alert('Debes agregar al menos un miembro');
    return;
  }

  const payload: TeamAddDTO = {
    name: teamName.value,
    players: selectedMembers.value.map((m) => ({ id: m.id } as Person)),
  };

  try {
    await addTeam(payload);
    alert('Equipo creado correctamente');
    resetForm();
    emit('close');

     // Refrescamos la página
    window.location.reload();
  } catch (error: any) {
    if (error.response?.status === 409) {
      alert('El nombre del equipo ya existe');
    } else if (error.response?.status === 400) {
      const errors = error.response.data;
      let msg = '';
      for (const key in errors) {
        msg += `${errors[key]}\n`;
      }
      alert(msg);
    } else {
      console.error(error);
      alert('Error al crear el equipo');
    }
  }
}
</script>

<style scoped>
.header-red {
  --color: #ffffff;
  --background: #e22f28;
  display: flex;
  justify-content: center;
  align-items: center;
}

.header-red ion-title {
  left: 100%;
  transform: translateX(3%);
  font-weight: bolder;
  font-size: 1.5rem;
}

.content {
  --padding-bottom: 0;
}

.form-container {
  display: flex;
  flex-direction: column;
  margin: 1rem 1.5rem;
}

.input-name {
  --border-color: #022029;
  --border-style: solid;
  --border-width: 1px;
  --color: #022029;
  --placeholder-color: #999999;
  text-align: left;
  margin-bottom: 1.5rem;
  margin-top: 1rem;
}

.sc-ion-input-md-h {
  --placeholder-color: var(--text-color-primary);
  --highlight-color-focused: var(--ion-color-primary, #0054e9);
  --highlight-color-valid: var(--ion-color-success, #5f7465);
  --highlight-color-invalid: var(--ion-color-danger, #c5000f);
  --highlight-color: var(--border-color-blue);
}

.panels {
  display: flex;
  gap: 2%;
}

.panel-left {
  flex: 0 0 40%;
  background-color: #f5f5f5;
  border-radius: 8px;
  padding: 1rem;
  display: flex;
  flex-direction: column;
  height: 400px;
}

.panel-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 0.75rem;
  font-weight: 600;
  color: #042935;
}

.counter {
  color: #e22f28;
  font-weight: bold;
}

.searchbar {
  --border-radius: 8px;
  --box-shadow: none;
  border: 1px solid #0a254098;
  border-radius: 5px;
  margin-bottom: 0.75rem;
  text-align: left;
}

.sc-ion-searchbar-md-h {
  -webkit-padding-start: 0;
  padding-inline-start: 0;
  -webkit-padding-end: 0;
  padding-inline-end: 0;
  padding: 0;
  margin: 0 0 1rem 0;
}

.members-list {
  flex: 1;
  overflow-y: auto;
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
}

.member-row {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: #ffffff;
  padding: 0.5rem 1rem;
  border-radius: 5px;
}

.member-name {
  font-size: 0.9rem;
  color: #0b2c3e;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.btn-add {
  background: none;
  border: none;
  color: #ff3c2f;
  font-size: 1.4rem;
  cursor: pointer;
  padding: 0;
}

.btn-add:disabled {
  color: #c0c0c0;
  cursor: default;
}

.panel-right {
  flex: 0 0 58%;
  background-color: #f5f5f5;
  border-radius: 8px;
  padding: 1rem;
  display: flex;
  flex-direction: column;
  height: 400px;
}

.selected-list {
  flex: 1;
  display: flex;
  flex-wrap: wrap;
  gap: 0.5rem;
  overflow-y: auto;
  padding-top: 0.5rem;
}

.selected-row {
  background: #ffffff;
  border-radius: 5px;
  padding: 0.5rem 1rem;
  display: flex;
  align-items: center;
  gap: 0.5rem;
}

.selected-name {
  font-size: 0.9rem;
  color: #0b2c3e;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.btn-remove {
  background: none;
  border: none;
  color: #ff3c2f;
  font-size: 1.1rem;
  cursor: pointer;
  padding: 0;
}

.footer-buttons {
  display: flex;
  justify-content: flex-end;
  gap: 1rem;
  padding: 0.75rem 1.5rem;
}

.btn-clean,
.btn-save {
  --background: #042935;
  --border-radius: 8px;
  --color: white;
  font-weight: bold;
}

.btn-clean .material-symbols-outlined,
.btn-save .material-symbols-outlined {
  margin-right: 5px;
}
</style>
