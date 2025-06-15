import { ref, onMounted, type Ref } from 'vue'
import { getPersons } from '@/services/personServices';

export function usePersonList() {
  const personas: Ref<any[]> = ref([])

  const fetchPersonas = async () => {
    try {
      const response = await getPersons()
      personas.value = response
    } catch {
      alert('ERROR OBTENIENDO PERSONAS')
    }
  }

  onMounted(fetchPersonas)

  return { personas }
}
