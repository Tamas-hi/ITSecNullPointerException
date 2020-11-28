import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {AuthenticationService} from 'src/app/authentication/services/authentication.service';
import {Router} from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';
import {SnackBarHelperUtil} from 'src/app/core/utils/snack-bar-helper.util';
import {User} from "../../models/user.model";
import {MESSAGE_SUCCESSFUL_LOGIN, MESSAGE_UNSUCCESSFUL_LOGIN} from "../../constants";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  public loginForm = new FormGroup({
    email: new FormControl(''),
    password: new FormControl(''),
  });

  public get isLoginButtonDisabled(): boolean {
    return !this.loginForm.valid;
  }

  constructor(
    private authenticationService: AuthenticationService,
    private router: Router,
    private matSnackBar: MatSnackBar
  ) {
  }

  public ngOnInit(): void {
  }

  public login(): void {
    const loginValue = this.loginForm.value;
    if (loginValue.email && loginValue.password) {
      const user: User = {
        email: loginValue.email,
        password: loginValue.password
      };

      this.authenticationService.login(user).subscribe(() => {
        this.router.navigateByUrl('/home').then(
          () => SnackBarHelperUtil.showMessage(this.matSnackBar, MESSAGE_SUCCESSFUL_LOGIN));
      }, error => {
        console.error(error);
        SnackBarHelperUtil.showMessage(this.matSnackBar, MESSAGE_UNSUCCESSFUL_LOGIN, true);
      });
    }
  }
}
