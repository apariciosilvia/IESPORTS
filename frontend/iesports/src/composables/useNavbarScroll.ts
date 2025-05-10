import { ref } from 'vue'

export function useNavbarScroll() {
  const showNavbar = ref(false)

  const handleScroll = (event: CustomEvent) => {
    const scrollElement = (event.target as any).getScrollElement
      ? event.target
      : (event.target as any).scrollElement

    scrollElement.getScrollElement().then((scrollEl: HTMLElement) => {
      const scrollTop = scrollEl.scrollTop
      const width = window.innerWidth

      showNavbar.value =
        (width >= 1440 && scrollTop > 900) ||
        (width >= 1024 && scrollTop > 690) ||
        (width >= 768 && scrollTop > 1000) ||
        (width < 768 && scrollTop > 600)
    })
  }

  return { showNavbar, handleScroll }
}
