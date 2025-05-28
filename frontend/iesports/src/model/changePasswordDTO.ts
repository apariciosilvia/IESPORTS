export interface ChangePasswordDTO {
  personId: number;
  currentPassword: string;
  password1: string;
  password2: string;
}