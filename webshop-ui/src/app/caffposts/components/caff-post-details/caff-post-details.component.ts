import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {CaffPostsService} from '../../services/caff-posts.service';
import {Comment} from '../../models/comment.module';
import {AuthenticationService} from '../../../authentication/services/authentication.service';
import {CaffPost} from '../../models/caff-post.model';
import {FormControl, FormGroup} from "@angular/forms";
import {SnackBarHelperUtil} from "../../../core/utils/snack-bar-helper.util";
import {MESSAGE_SUCCESSFUL_DELETE, MESSAGE_UNSUCCESSFUL_DELETE} from "../../constants";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-caff-post-details',
  templateUrl: './caff-post-details.component.html',
  styleUrls: ['./caff-post-details.component.scss']
})
export class CaffPostDetailsComponent implements OnInit {

  private selectedId: number;
  public comments: Comment[];
  public selectedCaffPost: Partial<CaffPost>;

  constructor(
    private route: ActivatedRoute,
    private caffPostsService: CaffPostsService,
    private authenticationService: AuthenticationService,
    private matSnackBar: MatSnackBar,
    private router: Router
  ) {

  }

  public ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.selectedId = params.id;

      this.caffPostsService.getById(this.selectedId).subscribe(data => {
        this.selectedCaffPost = data;
        this.selectedCaffPost.content = this.caffPostsService.convertImagesFromByteArray(this.selectedCaffPost.content);
      });

      this.caffPostsService.getComments(this.selectedId).subscribe(data => {
        this.comments = data;
      });
    });
  }

  public download(): void {
    this.caffPostsService.getCaffFile(this.selectedCaffPost.id).subscribe(file => {
      this.caffPostsService.download(file, this.selectedCaffPost.title);
    });
  }

  public comment(input: HTMLInputElement): void {
    this.caffPostsService.addComment(this.selectedId, this.authenticationService.loggedInUser.id, input.value)
      .subscribe(() => {
        input.value = '';
        this.caffPostsService.getComments(this.selectedId).subscribe(data => {
          this.comments = [];
          for (const id in data) {
            if (data.hasOwnProperty(id)) {
              this.comments.push(data[id]);
            }
          }
        });
      });
  }

  public get isUser(): boolean {
    return this.authenticationService.isUser;
  }

  public get isAdmin(): boolean {
    return this.authenticationService.isAdmin;
  }

  public delete(): void {
    this.caffPostsService.delete(this.selectedCaffPost.id).subscribe(() => {
      this.router.navigate(['/caff-posts/search']).then(() =>
        SnackBarHelperUtil.showMessage(this.matSnackBar, MESSAGE_SUCCESSFUL_DELETE));
    }, error => {
      console.error(error);
      SnackBarHelperUtil.showMessage(this.matSnackBar, MESSAGE_UNSUCCESSFUL_DELETE, true);
    });
  }
}
