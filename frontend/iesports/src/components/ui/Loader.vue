<style scoped>
  .pl {
    width: 8em;
    height: 8em;
  }

  .pl circle {
    transform-box: fill-box;
    transform-origin: 50% 50%;
  }

  .pl__ring1 {
    animation: ring1_ 3s 0s ease-in-out infinite;
  }

  .pl__ring2 {
    animation: ring2_ 3s 0.04s ease-in-out infinite;
  }

  .pl__ring3 {
    animation: ring3_ 3s 0.08s ease-in-out infinite;
  }

  .pl__ring4 {
    animation: ring4_ 3s 0.12s ease-in-out infinite;
  }

  .pl__ring5 {
    animation: ring5_ 3s 0.16s ease-in-out infinite;
  }

  .pl__ring6 {
    animation: ring6_ 3s 0.2s ease-in-out infinite;
  }

  /* Animations */
  @keyframes ring1_ {
    from {
      stroke-dashoffset: -376.237129776;
      transform: rotate(-0.25turn);
      animation-timing-function: ease-in;
    }

    23% {
      stroke-dashoffset: -94.247778;
      transform: rotate(1turn);
      animation-timing-function: ease-out;
    }

    46%,
    50% {
      stroke-dashoffset: -376.237129776;
      transform: rotate(2.25turn);
      animation-timing-function: ease-in;
    }

    73% {
      stroke-dashoffset: -94.247778;
      transform: rotate(3.5turn);
      animation-timing-function: ease-out;
    }

    96%,
    to {
      stroke-dashoffset: -376.237129776;
      transform: rotate(4.75turn);
    }
  }

  @keyframes ring2_ {
    from {
      stroke-dashoffset: -329.207488554;
      transform: rotate(-0.25turn);
      animation-timing-function: ease-in;
    }

    23% {
      stroke-dashoffset: -82.46680575;
      transform: rotate(1turn);
      animation-timing-function: ease-out;
    }

    46%,
    50% {
      stroke-dashoffset: -329.207488554;
      transform: rotate(2.25turn);
      animation-timing-function: ease-in;
    }

    73% {
      stroke-dashoffset: -82.46680575;
      transform: rotate(3.5turn);
      animation-timing-function: ease-out;
    }

    96%,
    to {
      stroke-dashoffset: -329.207488554;
      transform: rotate(4.75turn);
    }
  }

  @keyframes ring3_ {
    from {
      stroke-dashoffset: -288.4484661616;
      transform: rotate(-0.25turn);
      animation-timing-function: ease-in;
    }

    23% {
      stroke-dashoffset: -72.2566298;
      transform: rotate(1turn);
      animation-timing-function: ease-out;
    }

    46%,
    50% {
      stroke-dashoffset: -288.4484661616;
      transform: rotate(2.25turn);
      animation-timing-function: ease-in;
    }

    73% {
      stroke-dashoffset: -72.2566298;
      transform: rotate(3.5turn);
      animation-timing-function: ease-out;
    }

    96%,
    to {
      stroke-dashoffset: -288.4484661616;
      transform: rotate(4.75turn);
    }
  }

  @keyframes ring4_ {
    from {
      stroke-dashoffset: -253.9600625988;
      transform: rotate(-0.25turn);
      animation-timing-function: ease-in;
    }

    23% {
      stroke-dashoffset: -63.61725015;
      transform: rotate(1turn);
      animation-timing-function: ease-out;
    }

    46%,
    50% {
      stroke-dashoffset: -253.9600625988;
      transform: rotate(2.25turn);
      animation-timing-function: ease-in;
    }

    73% {
      stroke-dashoffset: -63.61725015;
      transform: rotate(3.5turn);
      animation-timing-function: ease-out;
    }

    96%,
    to {
      stroke-dashoffset: -253.9600625988;
      transform: rotate(4.75turn);
    }
  }

  @keyframes ring5_ {
    from {
      stroke-dashoffset: -225.7422778656;
      transform: rotate(-0.25turn);
      animation-timing-function: ease-in;
    }

    23% {
      stroke-dashoffset: -56.5486668;
      transform: rotate(1turn);
      animation-timing-function: ease-out;
    }

    46%,
    50% {
      stroke-dashoffset: -225.7422778656;
      transform: rotate(2.25turn);
      animation-timing-function: ease-in;
    }

    73% {
      stroke-dashoffset: -56.5486668;
      transform: rotate(3.5turn);
      animation-timing-function: ease-out;
    }

    96%,
    to {
      stroke-dashoffset: -225.7422778656;
      transform: rotate(4.75turn);
    }
  }

  @keyframes ring6_ {
    from {
      stroke-dashoffset: -203.795111962;
      transform: rotate(-0.25turn);
      animation-timing-function: ease-in;
    }

    23% {
      stroke-dashoffset: -51.05087975;
      transform: rotate(1turn);
      animation-timing-function: ease-out;
    }

    46%,
    50% {
      stroke-dashoffset: -203.795111962;
      transform: rotate(2.25turn);
      animation-timing-function: ease-in;
    }

    73% {
      stroke-dashoffset: -51.05087975;
      transform: rotate(3.5turn);
      animation-timing-function: ease-out;
    }

    96%,
    to {
      stroke-dashoffset: -203.795111962;
      transform: rotate(4.75turn);
    }
  }

.loader-container {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  background: rgba(255, 255, 255, 0.877); /* Puedes cambiarlo o ponerlo semitransparente si quieres overlay */
  z-index: 9999;
}

.pl {
  width: 8em;
  height: 8em;
}

</style>
<template>
  <div class="loader-container">
    <svg
      class="pl"
      width="128px"
      height="128px"
      viewBox="0 0 128 128"
      xmlns="http://www.w3.org/2000/svg"
    >
      <circle
        class="pl__ring2"
        cx="64"
        cy="64"
        r="52.5"
        fill="none"
        stroke="hsl(13,90%,55%)"
        stroke-width="12"
        transform="rotate(-90,64,64)"
        stroke-linecap="round"
        stroke-dasharray="329.9 329.9"
        stroke-dashoffset="-329.3"
      />
      <circle
        class="pl__ring4"
        cx="64"
        cy="64"
        r="37.5"
        fill="none"
        stroke="hsl(33,90%,55%)"
        stroke-width="9"
        transform="rotate(-90,64,64)"
        stroke-linecap="round"
        stroke-dasharray="254.5 254.5"
        stroke-dashoffset="-254"
      />
      <circle
        class="pl__ring6"
        cx="64"
        cy="64"
        r="22.5"
        fill="none"
        stroke="hsl(53,90%,55%)"
        stroke-width="9"
        transform="rotate(-90,64,64)"
        stroke-linecap="round"
        stroke-dasharray="204.2 204.2"
        stroke-dashoffset="-203.9"
      />
    </svg>
  </div>
</template>
