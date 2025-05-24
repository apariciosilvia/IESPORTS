// src/composables/useLoadingEffect.ts
import { ref, onMounted } from 'vue'

// 1. Definimos el composable
export function useLoadingEffect() {
  // 2. Estado reactivo que controla el spinner
  const isLoading = ref(true)

  // 3. Función para activar el spinner y simular la carga
  function loadData() {
    isLoading.value = true       // Activamos el spinner
    setTimeout(() => {
      isLoading.value = false    // Tras 3s, lo desactivamos
    }, 500)
  }

  // 4. Al montar el componente, llamamos a loadData() automáticamente
  onMounted(() => {
    loadData()
  })

  // 5. Devolvemos todo lo que necesitamos usar
  return { isLoading, loadData }
}
