import type { Match } from '@/model/match';
import axios from 'axios';

function getMatches(): Promise<any[]> {

  const url = `${import.meta.env.VITE_URL_API}/match/getMatches`;

  return new Promise((resolve, reject) => {
    axios.get(url)
      .then(response => {
        resolve(response.data);
      })
      .catch(error => {
        console.error('Error cargando partidos:', error);
        reject(error);
      });
  });
}

function getMatchesByTournamentId(tournamentId: number): Promise<Match[]> {

  const url = `${import.meta.env.VITE_URL_API}/match/getMatchesByTournamentId`;

  return new Promise((resolve, reject) => {
    axios.get(url, { params: { tournamentId } })
      .then(response => {
        resolve(response.data);
      })
      .catch(error => {
        console.error('Error cargando partidos del torneo:', error);
        reject(error);
      });
  });
}

export { getMatches, getMatchesByTournamentId };