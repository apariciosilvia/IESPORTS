import type { MatchEditDTO } from '@/model/dto/matchEditDTO';
export interface EditTournamentAndMatchDTO {
  tournamentId: number;
  nameTournament: string;
  sportId: number;
  matchesEditDTO: MatchEditDTO[];
}	