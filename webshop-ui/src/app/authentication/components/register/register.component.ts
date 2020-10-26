import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { SnackBarHelperUtil } from 'src/app/core/utils/snack-bar-helper.util';
import { AuthenticationService } from 'src/app/authentication/services/authentication.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  public registerForm = new FormGroup({
    userName: new FormControl(''),
    password: new FormControl(''),
    passwordAgain: new FormControl(''),
  });

  public get isLoginButtonDisabled(): boolean {
    return !this.registerForm.valid || (this.registerForm.value.password !== this.registerForm.value.passwordAgain);
  }

  constructor(
      private authenticationService: AuthenticationService,
      private router: Router,
      private matSnackBar: MatSnackBar
  ) { }

  public ngOnInit(): void {
  }

  public register(): void {
    const registerValue = this.registerForm.value;
    if (registerValue.userName && registerValue.password) {
      this.authenticationService.register(registerValue.userName, registerValue.password).subscribe(() => {
        this.router.navigateByUrl('/').then(
            () => SnackBarHelperUtil.showMessage(this.matSnackBar, 'Sikeres regisztráció!', true));
      }, error => {
        console.error(error);
        SnackBarHelperUtil.showMessage(this.matSnackBar, 'Sikertelen regisztráció!', true);
      });
    }
  }

}
