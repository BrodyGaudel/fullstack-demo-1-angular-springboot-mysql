import {Role} from "./submodel/role.model";

export class UserDTO{
  userId!: number;
  username!: string;
  password!: string;
  enabled!: boolean;
  roles!: Role[];
}
