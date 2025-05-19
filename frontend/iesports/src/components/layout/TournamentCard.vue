<template>
  <div class="match-card"> 
    <h2 class="tournament-title">
        Torneo: {{ matches[0]?.tournament.name }}          
    </h2>
    <b>Fecha torneo: {{ matches[0]?.tournament.date }}</b>

    <p>Deporte: {{ matches[0]?.tournament.sport.name }}</p> 

    <div
      v-for="m in matches"              
      :key="m.id"  
      class="match-card"                     
    >
      
      <p>{{ }}</p>
      <p>Fase: {{ m.round }}</p>                    
      <p>Fecha partido: {{  new Date(m.date).toLocaleDateString() }}</p> 
     
      <div>
        <b>{{ m.team1.name }}</b>
      </div>
      <div>
        <b>{{ m.team2.name }}</b>
      </div>
      
    </div>
  </div>
</template>

<script setup lang="ts">
import { defineProps } from 'vue';
import type { Match } from '@/model/match';

const props = defineProps<{
  matches: Match[];                        
}>();


console.log('Matches:', props.matches); 
</script>

<style scoped>
.matches-list {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.match-card {
  padding: 1rem;
  border: 1px solid #ddd;
  border-radius: 8px;
  box-shadow: 0 2px 5px rgba(0,0,0,0.1);
}

.tournament-title {
  font-size: 1.25rem;
  margin-bottom: 0.5rem;
}

.match-card p {
  margin: 0.25rem 0;
}
</style>
