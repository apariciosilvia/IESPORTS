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

function register(name: string, email: string, password: string, password2: string, cursoId: number) {
  return new Promise((resolve, reject) => {
    const url = `${import.meta.env.VITE_URL_API}/person/registro`;

    const params = new URLSearchParams();
    params.append("name", name);
    params.append("email", email);
    params.append("password", password);
    params.append("password1", password2);
    params.append("curso", cursoId.toString());

    axios.post(url, params)
      .then(response => resolve(response.data))
      .catch(error => reject(error));
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