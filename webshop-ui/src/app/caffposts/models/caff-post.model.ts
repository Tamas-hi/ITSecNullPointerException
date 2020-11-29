import {User} from '../../authentication/models/user.model';
import {Comment} from './comment.module';

export class CaffPost {
  public id: number;

  public title: string;

  public content: any;

  public posted: Date;

  public user: User;

  public caption: string;

  public tags: string[];

  public comments: Comment[];

  public creatorName: string;
}
