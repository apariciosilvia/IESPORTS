import type { MatchDTO } from '@/model/matchAddDTO';

export interface TournamentAddDTO {
  name: string;
  sportId: number;
  numTeams: number;
  matches: MatchDTO[];
}