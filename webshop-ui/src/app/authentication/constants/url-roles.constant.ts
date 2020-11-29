import {Role} from '../enums/role.enum';

export const URL_ACCESS: Map<string, Role[]> = new Map<string, Role[]>([
  ['/caff-posts', [Role.USER, Role.ADMIN]],
  ['/caff-posts/upload', [Role.USER]],
  ['/caff-posts/search', [Role.USER, Role.ADMIN]]
]);
