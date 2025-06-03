<template>
  <IonFooter class="app-footer">
    <div class="footer-wave"></div>
    <IonToolbar class="footer-toolbar">
      <div class="footer-top">
        <!-- Izquierda: Marca -->
        <div class="footer-left">
          <img src="@/assets/logo.png" alt="Logo" class="footer-logo" />
          <span class="footer-title">IESPORTS</span>
        </div>

        <!-- Centro: Copy -->
        <div class="footer-center">
          <p class="license-text" xmlns:cc="http://creativecommons.org/ns#"
                              xmlns:dct="http://purl.org/dc/terms/">
            <span property="dct:title">Está página</span> está bajo
            <a rel="license"
              href="https://creativecommons.org/licenses/by-nc/4.0/"
              target="_blank"
              class="license-link">
              CC BY-NC 4.0
              <img src="https://mirrors.creativecommons.org/presskit/icons/cc.svg"
                  alt="CC" />
              <img src="https://mirrors.creativecommons.org/presskit/icons/by.svg"
                  alt="BY" />
              <img src="https://mirrors.creativecommons.org/presskit/icons/nc.svg"
                  alt="NC" />
            </a>
          </p>
        </div>

        <!-- Derecha: Redes -->
        <div class="footer-right">
          <a href="https://x.com/i/flow/login?redirect_after_login=%2Fiestetuan"
            target="_blank"
            class="social-icon">
            <IonIcon :icon="logoTwitter" />
          </a>
          <a href="https://www.facebook.com/IesTetuanDeLasVictorias/?locale=es_ES"
            target="_blank"
            class="social-icon">
            <IonIcon :icon="logoFacebook" />
          </a>
          <a href="https://www.instagram.com/tetuinfo/?hl=es"
            target="_blank"
            class="social-icon">
            <IonIcon :icon="logoInstagram" />
          </a>
        </div>
      </div>
    </IonToolbar>
  </IonFooter>
</template>

<script setup lang="ts">
import { IonFooter, IonToolbar, IonIcon } from '@ionic/vue';
import { logoTwitter, logoFacebook, logoInstagram } from 'ionicons/icons';

</script>

<style scoped>
.app-footer {
  position: relative;
  --background: var(--blue-primary-color);
  color: white;
  overflow: visible;
  --min-height: 120px;
}
.footer-wave {
  position: absolute; top: -40px; left: 0; right: 0; height: 40px;
  background: url("data:image/svg+xml,%3Csvg viewBox='0 0 1440 40' xmlns='http://www.w3.org/2000/svg'%3E%3Cpath d='M0,20 C360,60 1080,0 1440,20 L1440,40 L0,40 Z' fill='%23002f3d'/%3E%3C/svg%3E") no-repeat;
  background-size: cover;
}
.footer-toolbar {
  --min-height: 120px; 
  flex-direction: column;
  justify-content: center;
  --background: var(--blue-primary-color);  
  padding: 0 1rem;
}
.footer-top {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

/* Izquierda */
.footer-left {
  display: flex;
  align-items: center;
  gap: 0.5rem;
}
.footer-logo {
  width: 40px; height: 40px;
}
.footer-title {
  font-size: 1.2rem;
  font-weight: bold;
  color: #ffffff;
}

/* Centro */
.footer-center {
  flex: 1;
  text-align: center;
  font-size: 0.85rem;
  color: #e7e7e7;
}

/* Derecha */
.footer-right {
  display: flex;
  gap: 1rem;
}
/* Contenedor de iconos mejorado */
.social-icon {
  display: flex;
  align-items: center;
  justify-content: center;
  background: #ffffff;
  color: #0a2540;           /* Color de fondo blanco */
  width: 40px;              /* +8px de ancho */
  height: 40px;             /* +8px de alto */
  padding: 4px;             /* espacio interno */
  border-radius: 50%;
  box-shadow: 0 2px 4px rgba(0,0,0,0.3);
  transition: background 0.2s, transform 0.2s;
}




/* Hover más suave y efecto "pop" */
.social-icon:hover {
  transform: translateY(-2px);
}


/* Responsive: apilar en móvil */
@media (max-width: 600px) {
  .footer-top {
    flex-direction: column;
    gap: 1rem;
  }
  .footer-center {
    order: 2;
  }
}


/* Estilos para el texto de la licencia */
.license-text {
  margin: 0;
  font-size: 0.85rem;
  color: #bbb;               /* Color más suave */
  line-height: 1.4;          /* Mejor legibilidad */
}

/* Estilos para el enlace de la licencia */
.license-link {
  color: #fff;               /* Texto blanco para destacar */
  text-decoration: none;     /* Sin subrayado */
  display: inline-flex;      /* Para alinear iconos y texto */
  align-items: center;
  gap: 0.25rem;              /* Espacio entre texto e iconos */
}

/* Iconos dentro del enlace */
.license-link img {
  width: 20px;               /* Tamaño uniforme */
  height: auto;
  display: inline-block;
  vertical-align: middle;
}



</style>
