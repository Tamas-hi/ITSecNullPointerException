import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-caff-file-upload',
  templateUrl: './caff-file-upload.component.html',
  styleUrls: ['./caff-file-upload.component.scss']
})
export class CaffFileUploadComponent implements OnInit {

  public url = '';
  public selectedFile: File = null;

  constructor(private http: HttpClient) {
  }

  public ngOnInit(): void {
  }

  public upload(): void {
    console.log('upload button pushed');
    const formData = new FormData();
    formData.append('file', this.selectedFile);
    console.log(this.selectedFile);
    console.log(formData);

    this.http.post('/api/upload', formData).toPromise().then(data => { // TODO kiprobálni feltöltést
      console.log(data);
    });
  }

  public onFileSelected(event): void {
    this.selectedFile = (event.target.files[0] as File);
  }
}
