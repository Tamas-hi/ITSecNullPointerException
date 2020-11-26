import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {DomSanitizer} from '@angular/platform-browser';

@Component({
  selector: 'app-cafffilesearcher',
  templateUrl: './cafffilesearcher.component.html',
  styleUrls: ['./cafffilesearcher.component.scss']
})
export class CafffilesearcherComponent implements OnInit {

  url = './assets/byetarray.txt';
  test1 = './assets/test1.jpg';
  test2 = './assets/test2.jpg';
  test3 = './assets/test3.jpg';
  list: string[] = [];
  byteArray = 'asd';
  items = [];
  //items: Object = [];
  bytes = [];
  //bytes: Array<string> = [];
  image: any;


  constructor(private http: HttpClient, private sanitizer: DomSanitizer) {

    // this.asyncFunction(http,this.bytes);

    // this.http.get('./assets/data.json')
    //   .subscribe(data => console.log(data));


    this.http.get('./assets/data.json')
      .subscribe(data => {
        let image: any;
        console.log(data);

        for (let id in data) {
          if (data.hasOwnProperty(id)) {
            this.items.push(data[id]);
            this.bytes.push(data[id].content);
          }
        }
        const reader = new FileReader();
        reader.onload = (e) => {
          this.image = reader.result;
        }
        reader.readAsDataURL(new Blob([this.bytes[0]]));

        console.log(this.bytes.length);
        this.image = this.sanitizer.bypassSecurityTrustUrl(this.image);
      });


    // console.log('IDEHE');
    //  console.log(this.items);

    // console.log(this.items.length);
    console.log(this.bytes);
    console.log(this.bytes.length);
    //   for (var key in this.bytes) {
    //    console.log(key);
    //  }


    this.list.push(this.test1);
    this.list.push(this.test2);
    this.list.push(this.test3);
    this.list.push(this.test1);
    this.list.push(this.test2);
    this.list.push(this.test3);
    this.list.push(this.test1);
    this.list.push(this.test2);
    this.list.push(this.test3);
    this.list.push(this.test1);
    this.list.push(this.test2);
    this.list.push(this.test3);
    this.list.push(this.test1);
    this.list.push(this.test2);
    this.list.push(this.test3);
    this.list.push(this.test1);
    this.list.push(this.test2);
    this.list.push(this.test3);
    this.list.push(this.test1);
    this.list.push(this.test2);
    this.list.push(this.test3);
    this.list.push(this.test1);
    this.list.push(this.test2);
    this.list.push(this.test3);
    this.list.push(this.test1);
    this.list.push(this.test2);
    this.list.push(this.test3);
    this.list.push(this.test1);
    this.list.push(this.test2);
    this.list.push(this.test3);
    console.log('asd');

    //   fetch('./assets/byetarray.txt')
    //    .then(async response => response.text())
    //   .then(async text => getText(text, this.byteArray));
    // // .then(text => console.log(text));


    //  console.log('kecske');
    // console.log(this.byteArray);
    //  console.log('kecske');

    //  this.request();
    // console.log('ittabyetarray');
    // console.log(this.byteArray);
    // console.log('ittabyetarray2')
    //  let asd = this.request();
    //  console.log(asd);
  }

  ngOnInit(): void {

  }

  download(id: number): void {
    console.log('test id');
    console.log(id);
  }

  /*
    asyncFunction(http: HttpClient, bytes: Array<string>): void {

      setTimeout(function () {
        http.get('./assets/data.json')
          .subscribe(data => {
         //   console.log(data);

            for (let id in data) {
              if (data.hasOwnProperty(id)) {
               //      this.items.push(data[id]);
                      bytes.push(data[id].content);
              }
            }
          });
      }, 2000); // 2 seconds timeout
    }*/


}
