import axios from 'axios';

function getPersonas() {

  return new Promise<any[]>((resolve, reject) => {

    const url = `${import.meta.env.VITE_URL_API}/person/getPersons`;

     axios.get(url)
       .then(response => {
        console.log('Respuesta completa:', response.data); 
         resolve(response.data);
       })
       .catch(error => {
        console.error('Error en la petición:', error);
        reject(error);
       });
   });
};

function login(email: string, password: string) {

  return new Promise((resolve, reject) => {

    const url = `${import.meta.env.VITE_URL_API}/person/login?email=${email}&password=${password}`;

    axios.post(url)
      .then(response => {
        resolve(response.data); 
      })
      .catch(error => {
        reject(error);
      });
  });
};

// src/services/personServices.ts

// function register(name: string, email: string, password: string, cursoId: number) {
//   const url = `${import.meta.env.VITE_URL_API}/person/registro`;

//   const params = new URLSearchParams();
//   params.append('name', name);
//   params.append('email', email);
//   params.append('password', password);
//   params.append('password1', password);       
//   params.append('curso', cursoId.toString());

//   return axios.post(url, params, {
//     headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
//   });
// }

function register(name: string, email: string, password1: string, password2: string, courseId: number) {

  const url = `${import.meta.env.VITE_URL_API}/person/register`;

  // 1. Creamos el objeto person
  const person = { name, email, password1, password2, courseId };
  console.log('Objeto person:', person);
  
  // 3. Llamamos al endpoint Spring Boot
  return axios.post(url, person, {
    // 2. Configuramos encabezados para JSON
    headers: { 'Content-Type': 'application/json' }
  });
}

export { getPersonas, login, register};




// import axios from 'axios';

// function getPokemons(offset = 0, limit = 20) {

//   return new Promise<any[]>((resolve, reject) => {

//     const url = `${import.meta.env.VITE_URL_API}/pokemon?offset=${offset}&limit=${limit}`;

//      axios.get(url)
//        .then(response => {
//          resolve(response.data.results);
//        })
//        .catch(error => {
//          reject(error);
//        });
//    });
// }

// export { getPokemons };