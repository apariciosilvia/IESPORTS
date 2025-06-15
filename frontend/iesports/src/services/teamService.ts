import axios from 'axios';
import type { Team } from '@/model/team';
import type { TeamAddDTO } from '@/model/dto/teamAddDTO';
import type { TeamUpdateDTO } from '@/model/dto/teamUpdateDTO';

function getTeamsInfo(): Promise<any[]> {

  const url = `${import.meta.env.VITE_URL_API}/team/getTeamsInfo`;

  return new Promise((resolve, reject) => {
    axios.get(url)
      .then(response => {
        resolve(response.data);
      })
      .catch(error => {
        console.error('Error cargando información de equipos:', error);
        reject(error);
      });
  });
}

function getTeamById(idTeam: number): Promise<Team> {

  const url = `${import.meta.env.VITE_URL_API}/team/getTeamById`;

  return axios
    .get<Team>(url, { params: { idTeam } })
    .then(response => response.data)
    .catch(error => {
      console.error('Error cargando información del equipo:', error);
      return Promise.reject(error);
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


function updateTeam(teamUpdateDTO: TeamUpdateDTO) {

  const url = `${import.meta.env.VITE_URL_API}/team/updateTeam`;

  return new Promise((resolve, reject) => {
    axios.post(url, teamUpdateDTO)
      .then(response => {
        resolve(response.data);
      })
      .catch(error => {
        console.error('Error añadiendo equipo:', error);
        reject(error);
      });
  });
}

function deleteTeam(idTeam: number) {

  const url = `${import.meta.env.VITE_URL_API}/team/deleteTeam`;
  
  // 1. Creamos el objeto person
  const teamDTO = { id: idTeam };

  // 3. Llamamos al endpoint Spring Boot
  return axios.post(url, teamDTO,{
    // 2. Configuramos encabezados para JSON
    headers: { 'Content-Type': 'application/json' }
  });
};


export { getTeamsInfo, getTeamById, getTeams, addTeam, updateTeam, deleteTeam };