import { ref, computed, onMounted, onUnmounted } from 'vue';
import { useRoute } from 'vue-router';

/**
 * Composable que une:
 *  - visibilidad por scroll (ionScroll)
 *  - visibilidad automática en rutas distintas de “/”
 */
export function useNavbarVisibility() {
  const route = useRoute();
  const showNavbarOnScroll = ref(false);

  // Lógica original de scroll:
  function handleScroll(event: CustomEvent) {
    const el = (event.target as any).getScrollElement
      ? event.target
      : (event.target as any).scrollElement;
    el.getScrollElement().then((scrollEl: HTMLElement) => {
      const top = scrollEl.scrollTop;
      const w = window.innerWidth;
      if (w >= 1440) showNavbarOnScroll.value = top > 900;
      else if (w >= 1024) showNavbarOnScroll.value = top > 700;
      else if (w >= 768) showNavbarOnScroll.value = top > 1000;
      else showNavbarOnScroll.value = top > 600;
    });
  }

  // Siempre mostrar en rutas ≠ “/”
  const alwaysShowNavbar = computed(() => route.path !== '/');

  // Flag combinado
  const showNav = computed(() => alwaysShowNavbar.value || showNavbarOnScroll.value);

  // Suscribirse al evento ionScroll
  onMounted(() => {
    document.addEventListener('ionScroll', handleScroll as EventListener);
  });
  onUnmounted(() => {
    document.removeEventListener('ionScroll', handleScroll as EventListener);
  });

  return { showNav, handleScroll };
}
