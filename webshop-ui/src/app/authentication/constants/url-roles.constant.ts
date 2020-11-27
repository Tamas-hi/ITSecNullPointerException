import {Role} from '../enums/role.enum';

export const URL_ACCESS: Map<string, Role[]> = new Map<string, Role[]>([
  ['/home', [Role.USER, Role.ADMIN]],
]);
