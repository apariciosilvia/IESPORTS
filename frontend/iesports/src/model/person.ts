import type { Role } from '@/model/role';

export interface Person {
  id: number;  
  course_id: number;
  name: string;    
  email: string;
  password: string;
  active: number;
  role: Role;
}