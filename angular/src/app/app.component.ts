import { SocialAuthService, SocialUser } from '@abacritt/angularx-social-login';
import { Component, Inject } from '@angular/core';
import { User } from './user';
import { AuthService } from '@auth0/auth0-angular';
import { DOCUMENT } from '@angular/common';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angular-frontend';
  currentUser!: User;
  nullUser!: SocialUser;
  loggedIn!: boolean;
  user$ = this.auth.user$;
  accessToken!:any;

  constructor(private authService: SocialAuthService, public auth: AuthService, @Inject(DOCUMENT) public document: Document) { }

  ngOnInit() {
    // const savedUser = JSON.parse(localStorage.getItem('currentUser') || 'null');
    // if (savedUser) {
    //   this.currentUser = savedUser;
    //   this.loggedIn = true;
    // }
    // this.authService.authState.subscribe((user) => {
    //   this.currentUser = user;
    //   console.log(this.currentUser);
    //   this.loggedIn = (user != null);
    //   localStorage.setItem('currentUser', JSON.stringify(user));
    // });
    if(this.auth.isAuthenticated$){
      console.log(this.auth.isAuthenticated$);
      console.log(this.auth.user$);
    }
  }
  public logout(){
    // this.currentUser = this.nullUser;
    // this.loggedIn = (this.currentUser != null);
    // localStorage.removeItem('currentUser');
    this.auth.logout({ logoutParams: { returnTo: document.location.origin } });
  }
  public login(){
    this.auth.loginWithRedirect();

  }
  public getAccessToken() {
    this.auth.getAccessTokenSilently({
      authorizationParams: {
        audience: 'http://localhost:4200', // Value in Identifier field for the API being called. // Scope that exists for the API being called. You can create these through the Auth0 Management API or through the Auth0 Dashboard in the Permissions view of your API.
      }}).subscribe(
        (response) => {
            this.accessToken = response;
            console.log(response);
            console.log("Access Token:", this.accessToken);
        },
        (error) => {
            console.error("Error while obtaining the access token:", error);
        }
    );
}
}
