import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {DomSanitizer, SafeResourceUrl} from '@angular/platform-browser';
import {CaffPost} from "../../models/caff-post.model";

@Component({
  selector: 'app-caff-file-searcher',
  templateUrl: './caff-file-searcher.component.html',
  styleUrls: ['./caff-file-searcher.component.scss']
})
export class CaffFileSearcherComponent implements OnInit {

  public imagesFromByteArray = [];
  public jsonData = [];
  public byteArrayImages = [];
  public photo: SafeResourceUrl;

  constructor(private http: HttpClient, private sanitizer: DomSanitizer) {

    this.http.get('api/getAll') // TODO backenden getall url-je
      .subscribe((caffPosts: CaffPost[]) => {

        caffPosts.forEach(caffPost => {

        });

        for (const id in caffPosts) {
          if (caffPosts.hasOwnProperty(id)) {
            this.jsonData.push(caffPosts[id]);
            this.byteArrayImages.push(caffPosts[id].content);
          }
        }

        for (const index in this.byteArrayImages) {
          this.convertImagesFromByteArray(this.byteArrayImages[index]);
        }
      });

  }

  public ngOnInit(): void {

  }

  public download(id: number): void {
    console.log(id);
  }

  public convertImageFromByteArray(data: string): void {
    this.photo = this.sanitizer.bypassSecurityTrustResourceUrl(
      'data:image/jpeg;base64,' + data);
  }

  public convertImagesFromByteArray(data: string): void {
    const image = this.sanitizer.bypassSecurityTrustResourceUrl(
      'data:image/jpeg;base64,' + data);
    this.imagesFromByteArray.push(image);
  }
}
