<template>
  <div class="spinnerContainer">
    <div class="spinner"></div>
    <div class="loader">
      <p>loading</p>
      <div class="words">
        <span class="word">IESPORTS</span>
        <span class="word">IESPORTS</span>
        <span class="word">IESPORTS</span>
        <span class="word">IESPORTS</span>
        <span class="word">IESPORTS</span>
      </div>
    </div>
  </div>
</template>

<style scoped>
.spinnerContainer {
  position: fixed;
  inset: 0;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
   background-color: #fff; 
}

.spinner {
  width: 56px;
  height: 56px;
  display: grid;
  border: 4px solid #0000;
  border-radius: 50%;
  border-right-color: #F03726;
  animation: tri-spinner 1s infinite linear;
}

.spinner::before,
.spinner::after {
  content: "";
  grid-area: 1/1;
  margin: 2px;
  border: inherit;
  border-radius: 50%;
  animation: tri-spinner 2s infinite;
}

.spinner::after {
  margin: 8px;
  animation-duration: 3s;
}

@keyframes tri-spinner {
  100% {
    transform: rotate(1turn);
  }
}

.loader {
  color: #002F3D;
  font-family: "Poppins",sans-serif;
  font-weight: 500;
  font-size: 25px;
  -webkit-box-sizing: content-box;
  box-sizing: content-box;
  height: 40px;
  padding: 10px 10px;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  border-radius: 8px;
  
  
}

.words {
  overflow: hidden;
}

.word {
  display: block;
  height: 100%;
  padding-left: 6px;
  color: #F03726;
  animation: cycle-words 5s infinite;
}


@keyframes cycle-words {
  10% {
    -webkit-transform: translateY(-105%);
    transform: translateY(-105%);
  }

  25% {
    -webkit-transform: translateY(-100%);
    transform: translateY(-100%);
  }

  35% {
    -webkit-transform: translateY(-205%);
    transform: translateY(-205%);
  }

  50% {
    -webkit-transform: translateY(-200%);
    transform: translateY(-200%);
  }

  60% {
    -webkit-transform: translateY(-305%);
    transform: translateY(-305%);
  }

  75% {
    -webkit-transform: translateY(-300%);
    transform: translateY(-300%);
  }

  85% {
    -webkit-transform: translateY(-405%);
    transform: translateY(-405%);
  }

  100% {
    -webkit-transform: translateY(-400%);
    transform: translateY(-400%);
  }
}
</style>
