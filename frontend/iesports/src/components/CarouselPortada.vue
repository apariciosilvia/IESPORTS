<template>
  <div class="login-icon">
    <span  class="material-symbols-outlined" style="font-size: 54px;" @click="goToLogin">account_circle</span>  
  </div>
  <div class="intro-text">
    <h1>BIENVENIDO A IESPORTS</h1>
    <p>Desliza hacia abajo para ver más</p>
  </div>

  <div class="carousel-container">
    <div
      v-for="(slide, index) in slides"
      :key="index"
      class="carousel-slide"
      :style="{
        backgroundImage: `url(${slide.img})`,
        opacity: index === currentSlide ? 1 : 0
      }"
    >
      <h2>{{ slide.title }}</h2>
    </div>

    <button class="nav-button prev" @click="prevSlide">❮</button>
    <button class="nav-button next" @click="nextSlide">❯</button>
  </div>
</template>



<script setup lang="ts">
import { ref, onMounted, onBeforeUnmount } from 'vue';
import { useRouter } from 'vue-router';


const router = useRouter();

function goToLogin() {
  router.push('/login')
}

const slides = [
  { img: new URL('../assets/img/1.jpg', import.meta.url).href, title: 'Partidos' },
  { img: new URL('../assets/img/2.jpg', import.meta.url).href, title: 'Noticias' },
  { img: new URL('../assets/img/3.jpg', import.meta.url).href, title: 'Deportes' }
];

const currentSlide = ref(0);

let intervalId: any = null;

const nextSlide = () => {
  currentSlide.value = (currentSlide.value + 1) % slides.length;
};

const prevSlide = () => {
  currentSlide.value = (currentSlide.value - 1 + slides.length) % slides.length;
};

onMounted(() => {
  intervalId = setInterval(() => {
    nextSlide();
  }, 4000); 
});

onBeforeUnmount(() => {
  clearInterval(intervalId);
});

</script>

<style scoped>
.carousel-container {
  position: relative;
  width: 100%;
  height: 100vh;
  overflow: hidden;
}

.carousel-slide {
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: 100%;
  background-size: cover;
  background-position: center;
  transition: opacity 1s ease;
  display: flex;
  justify-content: center;
  align-items: flex-end;
  padding: 2rem;
  color: white;
  text-shadow: 1px 1px 5px black;
  opacity: 0;
}

.nav-button {
  position: absolute;
  top: 45%;
  transform: translateY(-50%);
  background: rgba(0, 0, 0, 0);
  color: white;
  border: none;
  font-size: 2rem;
  padding: 0.5rem 1rem;
  cursor: pointer;
  z-index: 10;
  margin: 2%;
}

.prev {
  left: 10px;
}

.next {
  right: 10px;
}

.intro-text {
  position: absolute;
  top: 40%;
  width: 100%;
  text-align: center;
  color: white;
  z-index: 20;
  text-shadow: 1px 1px 5px black;
}

.intro-text h1 {
  font-size: 2.5rem;
  margin-bottom: 0.5rem;
}

.intro-text p {
  font-size: 1.2rem;
  margin-bottom: 1rem;
}

.login-icon {
  position: absolute;
  top: 20px;
  right: 20px;
  z-index: 30;
  color: white;
  cursor: pointer;
}


</style>
