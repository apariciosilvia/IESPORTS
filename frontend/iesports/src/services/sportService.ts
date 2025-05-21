import axios from 'axios';

function getSports(): Promise<any[]> {

  const url = `${import.meta.env.VITE_URL_API}/sport/getSports`;

  return new Promise((resolve, reject) => {
    axios.get(url)
      .then(response => {
        resolve(response.data);
      })
      .catch(error => {
        console.error('Error cargando deportes:', error);
        reject(error);
      });
  });
}

export { getSports };