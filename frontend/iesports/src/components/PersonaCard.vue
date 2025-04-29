<template>
  <ion-card  @click="$router.push(`/pokemon/${id}`)" button>
    <ion-img :src="image" alt="Imagen de Pokémon" />
    <ion-card-header>
      <ion-card-title class="ion-text-capitalize">{{ name }}</ion-card-title>
    </ion-card-header>
    <ion-card-content>
      <p class="id">ID: {{ id }}</p>
    </ion-card-content>
  </ion-card>
</template>

<script setup lang="ts">
defineProps<{
  name: string
  id: number
  image: string
}>()
</script>
<style>

.ion-text-capitalize{
  font-weight: bolder;
  font-size: 2em;
  text-align: center;

}

.id{
  font-weight: bolder;
  color: #000000;
  text-align: center;
  
}
</style>