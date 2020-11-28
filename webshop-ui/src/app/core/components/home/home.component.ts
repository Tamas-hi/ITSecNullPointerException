import {Component, OnInit} from '@angular/core';
import {User} from '../../../authentication/models/user.model';
import {AuthenticationService} from '../../../authentication/services/authentication.service';
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  public user: User;

  constructor(
    private authenticationService: AuthenticationService,
    private router: Router
  ) {
  }

  public ngOnInit(): void {
    this.user = this.authenticationService.getLoggedInUser();
  }

}
