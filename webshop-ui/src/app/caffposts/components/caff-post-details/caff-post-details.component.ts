import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {map} from 'rxjs/operators';

@Component({
  selector: 'app-caff-post-details',
  templateUrl: './caff-post-details.component.html',
  styleUrls: ['./caff-post-details.component.scss']
})
export class CaffPostDetailsComponent implements OnInit {

  testComments: string[] = ['wow', 'very comment', 'much wow', 'very big commentasdasdasdasdasdsadasd', 'kecske', 'heoheoheo sziasztok', ':))))))))))'];
  selectedId: any;

  constructor(route: ActivatedRoute) {
    route.params.subscribe(params => {
      this.selectedId = params['id'];
    });
   // console.log(route.params.value.id);
    console.log(this.selectedId);
  }

  ngOnInit(): void {
  }

  public download(id: number): void {
    console.log(this.selectedId);
  }

  comment(comment: string) {
    console.log(comment);
  }
}
