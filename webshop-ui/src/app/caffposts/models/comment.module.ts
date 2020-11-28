import {User} from '../../authentication/models/user.model';
import {CaffPost} from './caff-post.model';

export class Comment{

  public id: number;

  public text: string;

  public createdAt: Date;

  public user: User;

  public caffPost: CaffPost;
}
