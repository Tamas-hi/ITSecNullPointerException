import {Role} from '../enums/role.enum';
import {Comment} from '../../caffposts/models/comment.module';

export class User {
  id?: number;
  email: string;
  password: string;
  name?: string;
  comments?: Comment[];

  roles?: { id: number, role: Role }[];
}
