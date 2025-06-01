import type { MatchEditDTO } from '@/model/matchEditDTO';
export interface EditTournamentAndMatchDTO {
  tournamentId: number;
  nameTournament: string;
  sportId: number;
  matchesEditDTO: MatchEditDTO[];
}	