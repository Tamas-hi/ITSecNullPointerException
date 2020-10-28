import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { AuthenticationService } from 'src/app/authentication/services/authentication.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SnackBarHelperUtil } from 'src/app/core/utils/snack-bar-helper.util';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

    public loginForm = new FormGroup({
        userName: new FormControl(''),
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
        if (loginValue.userName && loginValue.password) {
            this.authenticationService.login(loginValue.userName, loginValue.password).subscribe(() => {
                this.router.navigateByUrl('/').then(
                    () => SnackBarHelperUtil.showMessage(this.matSnackBar, 'Sikeres bejelentkezés!', true));
            }, error => {
                console.error(error);
                SnackBarHelperUtil.showMessage(this.matSnackBar, 'Sikertelen bejelentkezés!', true);
            });
        }
    }

}
