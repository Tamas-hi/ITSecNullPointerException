import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {map} from 'rxjs/operators';
import {CaffPostsService} from "../../services/caff-posts.service";
import {CaffPost} from "../../models/caff-post.model";

@Component({
  selector: 'app-caff-post-details',
  templateUrl: './caff-post-details.component.html',
  styleUrls: ['./caff-post-details.component.scss']
})
export class CaffPostDetailsComponent implements OnInit {

  testComments: string[] = ['wow', 'very comment', 'much wow', 'very big commentasdasdasdasdasdsadasd', 'kecske', 'heoheoheo sziasztok', ':))))))))))'];
  selectedId: any;

  public get selectedCaffPost(): Partial<CaffPost> {
    return this.caffPostsService.selectedCaffPost;
  }

  constructor(
    private route: ActivatedRoute,
    private caffPostsService: CaffPostsService
  ) {
    route.params.subscribe(params => {
      this.selectedId = params['id'];
    });
  }

  ngOnInit(): void {
  }

  public download(id: number): void {
    console.log(this.selectedId);
  }

  public comment(comment: string): void {
    console.log(comment);
  }
}
