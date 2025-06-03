import type { MatchDTO } from '@/model/dto/matchAddDTO';

export interface TournamentAddDTO {
  name: string;
  sportId: number;
  numTeams: number;
  matches: MatchDTO[];
}