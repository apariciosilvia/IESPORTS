import type { MatchDTO } from '@/model/matchDTO';

export interface TournamentAddDTO {
  name: string;
  sportId: number;
  numTeams: number;
  matches: MatchDTO[];
}