import axios from 'axios';
import type { Team } from '@/model/team';
import type { TeamAddDTO } from '@/model/dto/teamAddDTO'; // Make sure TeamAddDTO is exported from this path

function getTeamsInfo(): Promise<any[]> {

  const url = `${import.meta.env.VITE_URL_API}/team/getTeamsInfo`;

  return new Promise((resolve, reject) => {
    axios.get(url)
      .then(response => {
        resolve(response.data);
      })
      .catch(error => {
        console.error('Error cargando información deequipos:', error);
        reject(error);
      });
  });
}

function getTeams(): Promise<Team[]> {

  const url = `${import.meta.env.VITE_URL_API}/team/getTeams`;

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


function addTeam(TeamAddDTO: TeamAddDTO) {

  const url = `${import.meta.env.VITE_URL_API}/team/addTeam`;

  return new Promise((resolve, reject) => {
    axios.post(url, TeamAddDTO)
      .then(response => {
        resolve(response.data);
      })
      .catch(error => {
        console.error('Error añadiendo equipo:', error);
        reject(error);
      });
  });
}



export { getTeamsInfo, getTeams, addTeam };