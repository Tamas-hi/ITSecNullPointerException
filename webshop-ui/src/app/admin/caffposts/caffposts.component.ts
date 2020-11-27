import { Component, OnInit } from '@angular/core';
import {DomSanitizer, SafeResourceUrl} from "@angular/platform-browser";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-caffposts',
  templateUrl: './caffposts.component.html',
  styleUrls: ['./caffposts.component.scss']
})
export class CaffpostsComponent implements OnInit {

  imagesFromByteArray = [];
  jsonData = [];
  byteArrayImages = [];
  photo: SafeResourceUrl;

  constructor(private http: HttpClient, private sanitizer: DomSanitizer) {

    this.http.get('./assets/data.json') //TODOO backenden getall url-je
      .subscribe(data => {

        for (let id in data) {
          if (data.hasOwnProperty(id)) {
            this.jsonData.push(data[id]);
            this.byteArrayImages.push(data[id].content);
          }
        }

        for(let index in this.byteArrayImages){
          this.convertImagesFromByteArray(this.byteArrayImages[index]);
        }
      });

  }

  ngOnInit(): void {

  }

  delete(id: number): void {
    console.log(id);
  }

  convertImagesFromByteArray(data: string){
    let image = this.sanitizer.bypassSecurityTrustResourceUrl(
      'data:image/jpeg;base64,' + data);
    this.imagesFromByteArray.push(image);
  }
}
