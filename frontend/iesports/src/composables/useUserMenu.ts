// src/composables/useUserMenu.ts
import { ref, onMounted, onBeforeUnmount } from 'vue'
import { useRouter } from 'vue-router'

export function useUserMenu() {
  const router = useRouter()

  // — Estado del usuario y menú
  const nombre = ref('')
  const usuarioLogado = ref(false)
  const menuVisible = ref(false)
  const iconRef = ref<HTMLElement | null>(null)
  const menuRef = ref<HTMLElement | null>(null)

  // — Funciones para abrir/cerrar y navegar
  function toggleMenu() {
    if (usuarioLogado.value) {
      menuVisible.value = !menuVisible.value
    } else {
      router.push('/login')
    }
  }

  function goTo(ruta: string) {
    menuVisible.value = false
    router.push(ruta)
  }

  function logout() {
    localStorage.removeItem('usuario')
    usuarioLogado.value = false
    nombre.value = ''
    menuVisible.value = false
    router.push('/')
  }

  // — Cerrar al click fuera
  function handleOutsideClick(e: MouseEvent) {
    if (
      menuVisible.value &&
      menuRef.value &&
      !menuRef.value.contains(e.target as Node) &&
      !iconRef.value?.contains(e.target as Node)
    ) {
      menuVisible.value = false
    }
  }

  // — Al montar, inicializo estado y listener
  onMounted(() => {
    const stored = localStorage.getItem('usuario')
    if (stored) {
      usuarioLogado.value = true
      nombre.value = JSON.parse(stored).name
    }
    document.addEventListener('click', handleOutsideClick)
  })

  // — Al desmontar, limpio listener
  onBeforeUnmount(() => {
    document.removeEventListener('click', handleOutsideClick)
  })

  return {
    nombre,
    usuarioLogado,
    menuVisible,
    iconRef,
    menuRef,
    toggleMenu,
    goTo,
    logout
  }
}
