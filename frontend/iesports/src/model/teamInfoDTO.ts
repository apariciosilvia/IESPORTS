import type { Sport } from '@/model/sport';
import type { Team } from '@/model/team';

export interface TeamInfoDTO {
  team: Team;
  sports: Sport[];
}