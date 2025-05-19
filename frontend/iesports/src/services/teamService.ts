import axios from 'axios';

function getTeamsInfo(): Promise<any[]> {

  const url = `${import.meta.env.VITE_URL_API}/team/getTeamsInfo`;

  return new Promise((resolve, reject) => {
    axios.get(url)
      .then(response => {
        resolve(response.data);
      })
      .catch(error => {
        console.error('Error cargando equipos:', error);
        reject(error);
      });
  });
}

export { getTeamsInfo };