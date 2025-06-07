import type { Person } from '@/model/person';

export interface TeamUpdateDTO {
  idTeam: number
  nameTeam: string;
  players: Person[];
}