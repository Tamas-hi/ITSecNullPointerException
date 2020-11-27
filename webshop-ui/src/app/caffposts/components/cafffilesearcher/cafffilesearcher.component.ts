import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {DomSanitizer, SafeResourceUrl} from '@angular/platform-browser';

@Component({
  selector: 'app-cafffilesearcher',
  templateUrl: './cafffilesearcher.component.html',
  styleUrls: ['./cafffilesearcher.component.scss']
})
export class CafffilesearcherComponent implements OnInit {

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

  download(id: number): void {
    console.log(id);
  }

  convertImageFromByteArray(data: string){
    this.photo = this.sanitizer.bypassSecurityTrustResourceUrl(
      'data:image/jpeg;base64,' + data);
  }

  convertImagesFromByteArray(data: string){
    let image = this.sanitizer.bypassSecurityTrustResourceUrl(
      'data:image/jpeg;base64,' + data);
    this.imagesFromByteArray.push(image);
  }
}
