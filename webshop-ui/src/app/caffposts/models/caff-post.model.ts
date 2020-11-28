import {User} from '../../authentication/models/user.model';

export class CaffPost {
  public id: number;

  public title: string;

  public content: Blob;

  public posted: Date;

  public user: User;

  public caption: string;

  public tags: string[];
}
