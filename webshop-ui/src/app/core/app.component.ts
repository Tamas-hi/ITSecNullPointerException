import {Component} from '@angular/core';
import {AuthenticationService} from '../authentication/services/authentication.service';
import {SnackBarHelperUtil} from './utils/snack-bar-helper.util';
import {Router} from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  public get isAuthenticated(): boolean {
    return !!this.authenticationService.loggedInUser;
  }

  constructor(
    private authenticationService: AuthenticationService,
    private router: Router,
    private matSnackBar: MatSnackBar
  ) {

  }

  public logout(): void {
    this.authenticationService.logout().subscribe(() => {
      this.router.navigateByUrl('/auth/login').then(
        () => SnackBarHelperUtil.showMessage(this.matSnackBar, 'Sikeres kijelentkezés!'));
    });
  }
}
