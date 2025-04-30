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

//CAMBIO SILVIA


import axios from 'axios';

function getPersonas() {

  return new Promise<any[]>((resolve, reject) => {

    const url = `${import.meta.env.VITE_URL_API}/persona/todos`;

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
}

export { getPersonas };