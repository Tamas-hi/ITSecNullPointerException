import {Component, OnInit} from '@angular/core';
import {DomSanitizer, SafeResourceUrl, SafeUrl} from '@angular/platform-browser';
import {CaffPostsService} from '../../services/caff-posts.service';
import {Router} from '@angular/router';

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

  constructor(private caffpostservice: CaffPostsService, private sanitizer: DomSanitizer, private router: Router) {

    caffpostservice.getAll() // TODO backenden getall url-je
      .subscribe(data => {
        console.log(data);
        for (const id in data) {
          if (data.hasOwnProperty(id)) {
            this.byteArrayImages.push(data[id]);
          }
        }
        console.log(this.byteArrayImages);
        for (const index in this.byteArrayImages) {
          this.byteArrayImages[index].content = this.convertImagesFromByteArray(this.byteArrayImages[index].content);
        }
      });
  }

  public ngOnInit(): void {

  }

  public searchByString(serachBy: string): void {
    this.caffpostservice.search(serachBy) // TODO backenden getall url-je
      .subscribe(data => {
        console.log(data);
        this.byteArrayImages = [];
        this.imagesFromByteArray = [];
        for (const id in data) {
          if (data.hasOwnProperty(id)) {
            this.byteArrayImages.push(data[id]);
          }
        }

        for (const index in this.byteArrayImages) {
          this.byteArrayImages[index].content = this.convertImagesFromByteArray(this.byteArrayImages[index]);
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

  comments(id: number): void {
    this.router.navigate(['/caff-posts/' + id]);
  }
}

