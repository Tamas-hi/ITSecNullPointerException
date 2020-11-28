import {Component, OnInit} from '@angular/core';
import {DomSanitizer, SafeResourceUrl, SafeUrl} from '@angular/platform-browser';
import {CaffPostsService} from '../../services/caff-posts.service';
import {Router} from '@angular/router';
import {CaffPost} from '../../models/caff-post.model';

@Component({
  selector: 'app-caff-file-searcher',
  templateUrl: './caff-file-searcher.component.html',
  styleUrls: ['./caff-file-searcher.component.scss']
})
export class CaffFileSearcherComponent implements OnInit {

  public imagesFromByteArray = [];
  public jsonData = [];
  public caffPosts: Partial<CaffPost>[] = [];
  public photo: SafeResourceUrl;

  constructor(private caffpostservice: CaffPostsService, private sanitizer: DomSanitizer, private router: Router) {

    caffpostservice.getAll().subscribe(data => {
      console.log(data);
      for (const id in data) {
        if (data.hasOwnProperty(id)) {
          this.caffPosts.push(data[id]);
        }
      }
      console.log(this.caffPosts);
      for (const index in this.caffPosts) {
        this.caffPosts[index].content = this.convertImagesFromByteArray(this.caffPosts[index].content);
      }
    });
  }

  public ngOnInit(): void {

  }

  public searchByString(searchBy: string): void {
    this.caffpostservice.search(searchBy)
      .subscribe(data => {
        console.log(data);
        this.caffPosts = [];
        this.imagesFromByteArray = [];
        for (const id in data) {
          if (data.hasOwnProperty(id)) {
            this.caffPosts.push(data[id]);
          }
        }

        for (const index in this.caffPosts) {
          this.caffPosts[index].content = this.convertImagesFromByteArray(this.caffPosts[index].content);
        }
      });
  }

  public download(id: number): void {
    console.log(id);
  }

  public convertImageFromByteArray(data: string): void {
    this.photo = this.sanitizer.bypassSecurityTrustResourceUrl(
      'data:image/jpeg;base64,' + data);
  }

  public convertImagesFromByteArray(data: string): SafeResourceUrl {
    return this.sanitizer.bypassSecurityTrustResourceUrl(
      'data:image/jpeg;base64,' + data);
  }

  public comments(id: number): void {
    this.caffpostservice.selectedCaffPost = this.caffPosts.find(post => post.id === id);
    this.router.navigate(['/caff-posts/' + id]);
  }
}

