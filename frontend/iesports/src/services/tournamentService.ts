// services/tournamentService.ts
import axios from 'axios';

// 1. getSports(): devuelve una promesa con el array de deportes
function getSports(): Promise<{ id: number; name: string }[]> {

  const url = `${import.meta.env.VITE_URL_API}/sports`;

  return new Promise((resolve, reject) => {
    axios.get(url)
      .then(response => {
        // 2. response.data debe ser el array de deportes
        resolve(response.data);
      })
      .catch(error => {
        console.error('Error cargando deportes:', error);
        reject(error);
      });
  });
}

// 3. getYears(): devuelve una promesa con el array de años
function getYears(): Promise<number[]> {

  const url = `${import.meta.env.VITE_URL_API}/tournaments/years`;
  
  return new Promise((resolve, reject) => {
    axios.get(url)
      .then(response => {
        resolve(response.data);
      })
      .catch(error => {
        console.error('Error cargando años:', error);
        reject(error);
      });
  });
}

// 4. getTournament(): devuelve una promesa con la estructura del torneo
function getTournament(sportId: number, year: string): Promise<any> {

  const url = `${import.meta.env.VITE_URL_API}/tournaments`;

  return new Promise((resolve, reject) => {
    axios.get(url, { params: { sportId, year } })
      .then(response => {
        resolve(response.data);
      })
      .catch(error => {
        console.error('Error cargando torneo:', error);
        reject(error);
      });
  });
}


export { getSports, getYears, getTournament};
