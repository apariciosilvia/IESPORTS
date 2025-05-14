import type { Sport } from '@/model/sport';
import type { Team } from '@/model/team';
import type { Person } from '@/model/person';

export interface TeamInfoDTO {
  team: Team;
  sport: Sport[];
  person: Person[];
}