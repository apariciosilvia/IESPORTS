import type { Tournament } from '@/model/tournament';
import type { Team } from '@/model/team';

export interface TournamentAdminDTO {
  teams: Team[];
  tournament: Tournament;
}