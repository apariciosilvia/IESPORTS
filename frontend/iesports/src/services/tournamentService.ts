// services/tournamentService.ts
import axios from 'axios';

import type { TournamentAdminDTO } from '@/model/tournamentAdminDTO';

// // 1. getSports(): devuelve una promesa con el array de deportes
// function getSports(): Promise<{ id: number; name: string }[]> {

//   const url = `${import.meta.env.VITE_URL_API}/sports`;

//   return new Promise((resolve, reject) => {
//     axios.get(url)
//       .then(response => {
//         // 2. response.data debe ser el array de deportes
//         resolve(response.data);
//       })
//       .catch(error => {
//         console.error('Error cargando deportes:', error);
//         reject(error);
//       });
//   });
// }

// 3. getYears(): devuelve una promesa con el array de años
function getYears(): Promise<String[]> {

  const url = `${import.meta.env.VITE_URL_API}/tournament/getYears`;
  
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
function getTournaments(): Promise<any> {

  const url = `${import.meta.env.VITE_URL_API}/tournament/getTournaments`;

  return new Promise((resolve, reject) => {
    axios.post(url, { params: {  } })
      .then(response => {
        resolve(response.data);
      })
      .catch(error => {
        console.error('Error cargando listado de torneos:', error);
        reject(error);
      });
  });
}

function getTeamsByTournamentId(): Promise<TournamentAdminDTO[]> {

  const url = `${import.meta.env.VITE_URL_API}/tournament/getTeamsByTournamentId`;
  
  return new Promise((resolve, reject) => {
    axios.get(url)
      .then(response => {
        resolve(response.data);
      })
      .catch(error => {
        console.error('Error listado de torneos con los equipos:', error);
        reject(error);
      });
  });
}


export { getYears, getTournaments, getTeamsByTournamentId};
