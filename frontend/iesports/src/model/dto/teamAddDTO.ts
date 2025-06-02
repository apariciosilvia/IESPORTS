import type { Person } from '@/model/person';

export interface TeamAddDTO {
  name: string;
  players: Person[];
}