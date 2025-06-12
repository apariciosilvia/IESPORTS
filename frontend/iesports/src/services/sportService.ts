import axios from 'axios';
import type { SportAddDTO } from '@/model/dto/SportAddDTO';
import type { SportUpdateDTO } from '@/model/dto/SportUpdateDTO';
import type { Sport } from '@/model/sport';

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


function addSport(SportAddDTO: SportAddDTO) {

  const url = `${import.meta.env.VITE_URL_API}/sport/addSport`;

  return new Promise((resolve, reject) => {
    axios.post(url, SportAddDTO)
      .then(response => {
        resolve(response.data);
      })
      .catch(error => {
        console.error('Error agregando deporte:', error);
        reject(error);
      });
  });
}


function getSportById(id: number): Promise<Sport> {

  const url = `${import.meta.env.VITE_URL_API}/sport/getSportById`;

  return axios
    .get<Sport>(url, { params: { id } })
    .then(response => response.data)
    .catch(error => {
      console.error('Error cargando información del deporte:', error);
      return Promise.reject(error);
    });
}

function updateSport(SportUpdateDTO: SportUpdateDTO) {

  const url = `${import.meta.env.VITE_URL_API}/sport/updateSport`;

  return new Promise((resolve, reject) => {
    axios.post(url, SportUpdateDTO)
      .then(response => {
        resolve(response.data);
      })
      .catch(error => {
        console.error('Error editando deporte:', error);
        reject(error);
      });
  });
}



function deleteSport(idSport: number) {

  const url = `${import.meta.env.VITE_URL_API}/sport/deleteSport`;
  
  // 1. Creamos el objeto person
  const SportDeleteDTO = { id: idSport };

  // 3. Llamamos al endpoint Spring Boot
  return axios.post(url, SportDeleteDTO,{
    // 2. Configuramos encabezados para JSON
    headers: { 'Content-Type': 'application/json' }
  });
};




export { getSports, addSport, getSportById, updateSport, deleteSport };