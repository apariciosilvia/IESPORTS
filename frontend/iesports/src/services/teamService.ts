import axios from 'axios';

/**
 * Obtiene el listado de equipos de un deporte.
 * @param sportId - ID del deporte seleccionado
 * @returns Promise con array de equipos
 */

function getTeams(sportId: number): Promise<any[]> {
  const url = `${import.meta.env.VITE_URL_API}/teams?sportId=${sportId}`;
  return new Promise((resolve, reject) => {
    axios.get(url)
      .then(response => {
        // response.data debería ser el array de equipos
        resolve(response.data);
      })
      .catch(error => {
        console.error('Error cargando equipos:', error);
        reject(error);
      });
  });
}

export { getTeams };