import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { SnackBarHelperUtil } from 'src/app/core/utils/snack-bar-helper.util';
import { AuthenticationService } from 'src/app/authentication/services/authentication.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import {User} from "../../models/user.model";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  public registerForm = new FormGroup({
    email: new FormControl(''),
    password: new FormControl(''),
    passwordAgain: new FormControl(''),
    name: new FormControl(''),
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
    if (registerValue.email && registerValue.password && registerValue.name) {
      const user: User = {
        email: registerValue.email,
        name: registerValue.name,
        password: registerValue.password
      };

      this.authenticationService.register(user).subscribe(() => {
        this.router.navigateByUrl('/').then(
            () => SnackBarHelperUtil.showMessage(this.matSnackBar, 'Sikeres regisztráció!'));
      }, error => {
        console.error(error);
        SnackBarHelperUtil.showMessage(this.matSnackBar, 'Sikertelen regisztráció!', true);
      });
    }
  }

}
