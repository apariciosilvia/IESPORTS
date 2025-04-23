<script setup lang="ts">
import Particles from '@tsparticles/vue3'
import { loadFull } from 'tsparticles'

// ✅ Vite necesita procesar la imagen así:
const ball = new URL('@/assets/img/ball.png', import.meta.url).href

const particlesInit = async (engine: any) => {
  await loadFull(engine)
}
</script>

<template>
  <Particles
    id="tsparticles"
    :init="particlesInit"
    :options="{
      background: { color: '#000000' },
      fullScreen: { enable: false },
      particles: {
        number: { value: 10 },
        shape: {
          type: 'image',
          image: [
            {
              src: ball,
              width: 32,
              height: 32
            }
          ]
        },
        size: { value: 24 },
        move: {
          enable: true,
          speed: 2,
          direction: 'none',
          outModes: { default: 'bounce' }
        }
      },
      interactivity: {
        events: {
          onHover: { enable: true, mode: 'repulse' }
        },
        modes: {
          repulse: { distance: 100 }
        }
      }
    }"
    style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; z-index: 0; pointer-events: none"
  />
</template>
