<template>
  <div class="page-wrapper">
    <div class="contact-wrapper">
      <div class="contact-info">
        <h2>Contacto</h2>
        <p>¿Tienes alguna pregunta, duda o sugerencia? Nuestro equipo está siempre dispuesto a ayudarte en lo que necesites, estaremos encantados de leerte.</p>
        <p>Puedes contactar con nosotros a través de este formulario. Tu opinión nos ayuda a mejorar cada día.</p>
        <p>¡Gracias por confiar en nosotros!</p>
      </div>
      <div class="form-container">
        <form class="form">
          <div class="form-group">
            <label for="email">Email</label>
            <input name="email" id="email" type="text" />
          </div>
          <div class="form-group">
            <label for="textarea">¿Podemos ayudarte en algo?</label>
            <textarea cols="50" rows="10" id="textarea" name="textarea"></textarea>
          </div>
          <button class="button">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 24 24"
              width="24"
              height="24"
              fill="currentColor"
            >
              <path d="M2.01 21 23 12 2.01 3 2 10l15 2-15 2z" />
            </svg>
            <span>Enviar</span>
          </button>
        </form>
      </div>
    </div>
  </div>
</template>

<style scoped>
.page-wrapper {
  min-height: 70vh;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 2rem;
  box-sizing: border-box;
}

.contact-wrapper {
  display: flex;
  gap: 130px;
  justify-content: center;
  align-items: flex-start;
  padding: 40px;
  flex-wrap: wrap;
  background-color: white;
  border-radius: 10px;
  max-width: 1600px;
  width: 100%;
  box-shadow: 0 2px 10px rgba(0,0,0,0.1);
}
@media (max-width: 1024px) {
  .contact-wrapper {
    gap: 40px;
  }
}

.contact-info {
  flex: 1;
  max-width: 300px;
  color: var(--orange-secundary-color);
  font-family: inherit;
}

.contact-info h2 {
  font-size: 24px;
  margin-bottom: 12px;
}

.contact-info p {
  margin-bottom: 8px;
  font-size: 14px;
}

.form-container {
  flex: 1;
  min-width: 340px;
  max-width: 580px;
  background: linear-gradient(rgba(255, 255, 255, 0.85), rgba(255, 255, 255, 0.85)) padding-box,
              linear-gradient(145deg, #ff3e1c, #6693d6, #ff3e1c) border-box;
  border: 4px solid transparent;
  padding: 32px 24px;
  font-size: 14px;
  font-family: inherit;
  color: white;
  display: flex;
  flex-direction: column;
  gap: 20px;
  box-sizing: border-box;
  border-radius: 16px;
  background-size: 200% 100%;
  animation: animated-border 5s ease infinite;
}

.form {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.form-group {
  display: flex;
  flex-direction: column;
  gap: 2px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
  color: #ff3e1c;
  font-weight: 600;
  font-size: 12px;
}

.form-group input,
.form-group textarea {
  width: 100%;
  padding: 12px 16px;
  border-radius: 8px;
  color: #000;
  background-color: transparent;
  border: 1px solid #fc5757;
  font-family: inherit;
}

.form-group textarea {
  resize: none;
  height: 96px;
}

.form-group input:focus,
.form-group textarea:focus {
  outline: none;
  border-color: #ff1c1c;
}

.button {
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0.8em 1.1em;
  gap: 0.4rem;
  border: none;
  font-weight: bold;
  border-radius: 30px;
  cursor: pointer;
  text-shadow: 2px 2px 3px rgb(136 0 136 / 50%);
  background: linear-gradient(
      15deg,
      #af6403,
      #aa4720,
      #cc3f47,
      #de6f3d,
      #f09f33,
      #de6f3d,
      #cc3f47,
      #aa4720,
      #af6403
    )
    no-repeat;
  background-size: 300%;
  background-position: left center;
  transition: background 0.3s ease;
  color: #fff;
  text-align: center;
}

.button:hover {
  background-size: 320%;
  background-position: right center;
}

.button:hover svg {
  fill: #fff;
}

.button svg {
  width: 20px;
  fill: #ffffff;
  transition: 0.3s ease;
}

@keyframes animated-border {
  0% {
    background-position: 0% 50%;
  }
  50% {
    background-position: 100% 50%;
  }
  100% {
    background-position: 0% 50%;
  }
}
</style>
