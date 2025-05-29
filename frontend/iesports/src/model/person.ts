import type { Role } from '@/model/role';
import type { Course } from '@/model/course';

export interface Person {
  id: number;  
  course: Course;
  name: string;    
  email: string;
  password: string;
  active: number;
  role: Role;
  tempPassword: number;
}