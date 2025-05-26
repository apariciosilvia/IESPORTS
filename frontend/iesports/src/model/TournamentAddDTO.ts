import type { Tournament } from '@/model/tournament';
import type { Team } from '@/model/team';
import type { Match } from '@/model/match';

export interface TournamentAddDTO {
  tournament: Tournament;
  teams: Team[];
  matches: Match[];
}