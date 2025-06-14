import type { Role } from '@/model/role';
import axios from 'axios';

function getRoles(): Promise<Role[]> {

  const url = `${import.meta.env.VITE_URL_API}/role/getRoles`;

  return new Promise((resolve, reject) => {
    axios.get(url)
      .then(response => {
        resolve(response.data);
      })
      .catch(error => {
        console.error('Error cargando roles:', error);
        reject(error);
      });
  });
}

export { getRoles };