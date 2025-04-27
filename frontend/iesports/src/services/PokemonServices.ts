import axios from 'axios';

function getPokemons(offset = 0, limit = 20) {

  return new Promise<any[]>((resolve, reject) => {

    const url = `${import.meta.env.VITE_URL_API}/pokemon?offset=${offset}&limit=${limit}`;

     axios.get(url)
       .then(response => {
         resolve(response.data.results);
       })
       .catch(error => {
         reject(error);
       });
   });
}

export { getPokemons };

//CAMBIO SILVIA


// import axios from 'axios';

// function getPokemons() {

//   return new Promise<any[]>((resolve, reject) => {

//     const url = `${import.meta.env.VITE_URL_API}/pokemon`;

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