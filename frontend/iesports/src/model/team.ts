import type { Person } from '@/model/person';

export interface Team {
  id: number;  
  name: string;    
  players: Person[];
}