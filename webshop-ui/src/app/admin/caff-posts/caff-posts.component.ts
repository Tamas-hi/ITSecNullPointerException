import {Component, OnInit} from '@angular/core';
import {DomSanitizer, SafeResourceUrl} from '@angular/platform-browser';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-caff-posts',
  templateUrl: './caff-posts.component.html',
  styleUrls: ['./caff-posts.component.scss']
})
export class CaffPostsComponent implements OnInit {

  public imagesFromByteArray = [];
  public jsonData = [];
  public byteArrayImages = [];
  public photo: SafeResourceUrl;

  constructor(private http: HttpClient, private sanitizer: DomSanitizer) {

    this.http.get('./assets/data.json') // TODO backenden getall url-je
      .subscribe(data => {

        for (const id in data) {
          if (data.hasOwnProperty(id)) {
            this.jsonData.push(data[id]);
            this.byteArrayImages.push(data[id].content);
          }
        }

        for (const index in this.byteArrayImages) {
          this.convertImagesFromByteArray(this.byteArrayImages[index]);
        }
      });

  }

  public ngOnInit(): void {

  }

  public delete(id: number): void {
    console.log(id);
  }

  public convertImagesFromByteArray(data: string): void {
    const image = this.sanitizer.bypassSecurityTrustResourceUrl(
      'data:image/jpeg;base64,' + data);
    this.imagesFromByteArray.push(image);
  }
}
