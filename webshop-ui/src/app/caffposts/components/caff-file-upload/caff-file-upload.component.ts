import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AuthenticationService} from '../../../authentication/services/authentication.service';
import {FormControl, FormGroup} from '@angular/forms';
import {SnackBarHelperUtil} from '../../../core/utils/snack-bar-helper.util';
import {MatSnackBar} from '@angular/material/snack-bar';
import {MESSAGE_SUCCESSFUL_UPLOAD, MESSAGE_UNSUCCESSFUL_UPLOAD} from '../../constants';
import {CaffPostsService} from '../../services/caff-posts.service';

@Component({
  selector: 'app-caff-file-upload',
  templateUrl: './caff-file-upload.component.html',
  styleUrls: ['./caff-file-upload.component.scss']
})
export class CaffFileUploadComponent implements OnInit {

  public caffPostForm = new FormGroup({
    title: new FormControl('')
  });

  public url = '';
  public selectedFile: File = null;
  private target: HTMLInputElement;

  constructor(
    private http: HttpClient,
    private authenticationService: AuthenticationService,
    private matSnackBar: MatSnackBar,
    private caffPostsService: CaffPostsService
  ) {
  }

  public ngOnInit(): void {
  }

  public get isUploadButtonDisabled(): boolean {
    return !(this.target !== undefined && this.caffPostForm.get('title').value !== undefined && this.caffPostForm.get('title').value !== '');
  }

  private convertFile(file): Promise<any> {
    return new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.readAsArrayBuffer(file);
      reader.onload = () => {
        resolve(reader.result);
      };
      reader.onerror = error => reject(error);
    });
  }

  public uploadFile(): void {
    if (this.target) {
      const file = (this.target as HTMLInputElement).files[0];
      this.convertFile(file).then((base64: any): any => {
        this.caffPostsService.uploadCaff(base64).subscribe(id => {
          if (this.caffPostForm.value.title) {
            this.caffPostsService.uploadCaffDetails(id, this.caffPostForm.value.title).subscribe(() => {
              SnackBarHelperUtil.showMessage(this.matSnackBar, MESSAGE_SUCCESSFUL_UPLOAD);
              this.emptyForm();
            }, error => {
              console.error(error);
              SnackBarHelperUtil.showMessage(this.matSnackBar, MESSAGE_UNSUCCESSFUL_UPLOAD, true);
            });
          }
        });
      });
    }
  }

  public uploadFileInputChanged(event: Event): void {
    if ((event.target as HTMLInputElement).value) {
      this.target = event.target as HTMLInputElement;
    }
  }

  private emptyForm(): void {
    this.caffPostForm.get('title').setValue('');
    this.target.value = '';
    this.target = undefined;
  }
}
