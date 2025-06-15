import type { Role } from "@/model/role";

export interface PersonRegisterByAdminDTO {
  name: string;
  role: Role;
  email: string;
  password1: string;
  password2: string;
  courseId: number;
}
