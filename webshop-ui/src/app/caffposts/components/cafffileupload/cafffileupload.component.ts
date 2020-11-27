import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-cafffileupload',
  templateUrl: './cafffileupload.component.html',
  styleUrls: ['./cafffileupload.component.scss']
})
export class CafffileuploadComponent implements OnInit {

  url='';
  selectedFile: File = null;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }

  upload(): void {
    console.log('upload button pushed');
    const formData = new FormData();
    formData.append('file', this.selectedFile);
    console.log(this.selectedFile);
    console.log(formData);

    this.http.post('/api/upload', formData).toPromise().then(data => { //TODOO kiprobálni feltöltést
      console.log(data);
    });
  }

  onFileSelected(event){
    this.selectedFile = (event.target.files[0] as File);
  }

}
