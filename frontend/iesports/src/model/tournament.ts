import type { Sport } from '@/model/sport';

export interface Tournament {
  id: number;  
  name: string;    
  date: string; 
  state: number;
  sport: Sport; 
}