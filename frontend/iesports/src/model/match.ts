import type { Tournament } from '@/model/tournament';
import type { Team } from '@/model/team';

export interface Match {
  id: number;  
  date: Date;
  round: string;    
  tournament: Tournament;
  team1: Team;
  team2: Team;
  pointsTeam1: number;
  pointsTeam2: number;
  winnerTeam?: Team;
}