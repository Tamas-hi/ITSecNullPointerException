import {Component, OnInit} from '@angular/core';
import {CaffPostsService} from '../../services/caff-posts.service';
import {Router} from '@angular/router';
import {CaffPost} from '../../models/caff-post.model';
import {AuthenticationService} from '../../../authentication/services/authentication.service';
import {SnackBarHelperUtil} from '../../../core/utils/snack-bar-helper.util';
import {MESSAGE_SUCCESSFUL_DELETE, MESSAGE_UNSUCCESSFUL_DELETE} from '../../constants';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-caff-file-searcher',
  templateUrl: './caff-file-searcher.component.html',
  styleUrls: ['./caff-file-searcher.component.scss']
})
export class CaffFileSearcherComponent implements OnInit {

  public caffPosts: Partial<CaffPost>[] = [];

  constructor(
    private caffPostsService: CaffPostsService,
    private router: Router,
    private authenticationService: AuthenticationService,
    private matSnackBar: MatSnackBar
  ) {

  }

  public ngOnInit(): void {
    this.getAllCaffPosts();
  }

  private getAllCaffPosts(): void {
    this.caffPostsService.getAll().subscribe(data => {
      this.caffPosts = data;

      for (const index in this.caffPosts) {
        this.caffPosts[index].content = this.caffPostsService.convertImagesFromByteArray(this.caffPosts[index].content);
      }
    });
  }

  public searchByString(searchBy: string): void {
    this.caffPostsService.search(searchBy)
      .subscribe(data => {
        this.caffPosts = data;

        for (const index in this.caffPosts) {
          this.caffPosts[index].content = this.caffPostsService.convertImagesFromByteArray(this.caffPosts[index].content);
        }
      });
  }

  public download(caffPostId: number, title: string): void {
    this.caffPostsService.getCaffFile(caffPostId).subscribe(caffFile => {
      this.caffPostsService.download(caffFile, title);
    });
  }

  public showComments(id: number): void {
    this.caffPostsService.selectedCaffPost = this.caffPosts.find(post => post.id === id);
    this.router.navigate(['/caff-posts/' + id]);
  }

  public get isUser(): boolean {
    return this.authenticationService.isUser;
  }

  public get isAdmin(): boolean {
    return this.authenticationService.isAdmin;
  }

  public delete(id: number): void {
    this.caffPostsService.delete(id).subscribe(() => {
      SnackBarHelperUtil.showMessage(this.matSnackBar, MESSAGE_SUCCESSFUL_DELETE);
      this.getAllCaffPosts();
    }, error => {
      console.error(error);
      SnackBarHelperUtil.showMessage(this.matSnackBar, MESSAGE_UNSUCCESSFUL_DELETE, true);
    });
  }
}

