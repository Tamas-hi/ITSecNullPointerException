import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {map} from 'rxjs/operators';
import {CaffPostsService} from '../../services/caff-posts.service';
import {CaffPost} from '../../models/caff-post.model';
import {Comment} from '../../models/comment.module';
import {AuthenticationService} from '../../../authentication/services/authentication.service';

@Component({
  selector: 'app-caff-post-details',
  templateUrl: './caff-post-details.component.html',
  styleUrls: ['./caff-post-details.component.scss']
})
export class CaffPostDetailsComponent implements OnInit {

//  testComments: string[] = ['wow', 'very comment', 'much wow', 'very big commentasdasdasdasdasdsadasd', 'kecske', 'heoheoheo sziasztok', ':))))))))))'];
  selectedId: any;
  comments: Comment[];

  public get selectedCaffPost(): Partial<CaffPost> {
    return this.caffPostsService.selectedCaffPost;
  }

  constructor(
    private route: ActivatedRoute,
    private caffPostsService: CaffPostsService,
    private authentiacionService: AuthenticationService
  ) {
    route.params.subscribe(params => {
      this.selectedId = params.id;
    });

    caffPostsService.getComments(this.selectedId)
        .subscribe(data => {
          this.comments = [];
          for (const id in data) {
            if (data.hasOwnProperty(id)) {
              this.comments.push(data[id]);
            }
          }
        });
  }

  ngOnInit(): void {
  }

  public download(id: number): void {
    console.log(this.selectedId);
  }

  public comment(comment: string): void {
    this.caffPostsService.comment(this.selectedId, this.authentiacionService.loggedInUser.id, comment)
      .subscribe(() => {
        this.caffPostsService.getComments(this.selectedId)
          .subscribe(data => {
            this.comments = [];
            for (const id in data) {
              if (data.hasOwnProperty(id)) {
                this.comments.push(data[id]);
              }
            }
          });
      });
  }
}
