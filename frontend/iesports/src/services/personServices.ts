import axios from 'axios';
import type { ChangePasswordDTO } from '@/model/dto/changePasswordDTO';
import type { ForgotPasswordRequestDTO } from '@/model/dto/forgotPasswordRequestDTO';
import type { ChangeForgottenPasswordDTO } from '@/model/dto/changeForgottenPasswordDTO';
import type { ChangeNameAndEmailDTO } from '@/model/dto/changeNameAndEmailDTO';
import type { ChangeRoleAndCourseDTO } from '@/model/dto/ChangeRoleAndCourseDTO';

import type { Person } from '@/model/person';

function getPersons() {

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

function getPersonsRoleStudent() {

  return new Promise<Person[]>((resolve, reject) => {

    const url = `${import.meta.env.VITE_URL_API}/person/getPersonsRoleStudent`;

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

  const url = `${import.meta.env.VITE_URL_API}/person/login`;
  
  // 1. Creamos el objeto person
  const person = { email, password };

  // 3. Llamamos al endpoint Spring Boot
  return axios.post(url, person,{
    // 2. Configuramos encabezados para JSON
    headers: { 'Content-Type': 'application/json' }
  });
};

function register(name: string, email: string, password1: string, password2: string, courseId: number) {

  const url = `${import.meta.env.VITE_URL_API}/person/register`;

  // 1. Creamos el objeto person
  const person = { name, email, password1, password2, courseId };
  console.log('Objeto person:', person);
  
  // 3. Llamamos al endpoint Spring Boot
  return axios.post(url, person, {
    // 2. Configuramos encabezados para JSON
    headers: { 'Content-Type': 'application/json' }
  });
}

function changeNameAndEmail(changeNameAndEmailDTO: ChangeNameAndEmailDTO) {

  const url = `${import.meta.env.VITE_URL_API}/person/changeNameAndEmail`;

  return axios.post(url, changeNameAndEmailDTO, {
    headers: { 'Content-Type': 'application/json' }
  });
};

function changePassword(ChangePasswordDTO: ChangePasswordDTO) {

  const url = `${import.meta.env.VITE_URL_API}/person/changePassword`;

  return axios.post(url, ChangePasswordDTO, {
    headers: { 'Content-Type': 'application/json' }
  });
};

function forgotPassword(ForgotPasswordRequestDTO: ForgotPasswordRequestDTO) {

  const url = `${import.meta.env.VITE_URL_API}/person/forgotPassword`;

  return axios.post(url, ForgotPasswordRequestDTO, {
    headers: { 'Content-Type': 'application/json' }
  });
};


function changeTempPassword(ChangeForgottenPasswordDTO: ChangeForgottenPasswordDTO) {

  const url = `${import.meta.env.VITE_URL_API}/person/changeTempPassword`;

  return axios.post(url, ChangeForgottenPasswordDTO, {
    headers: { 'Content-Type': 'application/json' }
  });
};



function changeUserRoleAndCourse(ChangeRoleAndCourseDTO: ChangeRoleAndCourseDTO) {

  const url = `${import.meta.env.VITE_URL_API}/person/changeRoleAndCourse`;

  return axios.post(url, ChangeRoleAndCourseDTO, {
    headers: { 'Content-Type': 'application/json' }
  });
};


function getPersonById(id: number): Promise<Person> {

  const url = `${import.meta.env.VITE_URL_API}/person/getPersonById`;

  return axios
    .get<Person>(url, { params: { id } })
    .then(response => response.data)
    .catch(error => {
      console.error('Error cargando información de la persona:', error);
      return Promise.reject(error);
    });
}



export { getPersons, getPersonsRoleStudent, login, register, changeNameAndEmail, changePassword, forgotPassword, changeTempPassword, changeUserRoleAndCourse, getPersonById};