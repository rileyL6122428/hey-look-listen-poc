import { Component } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {

  constructor(
    private authService: AuthService,
    private http: HttpClient
  ) { }


  get isLoggedIn(): boolean {
    return this.authService.isAuthenticated;
  }

  goToLogin(): void {
    this.authService.login();
  }

  logout(): void {
    this.authService.logout();
  }

  pingPublic(): void {
    this.http.get('http://localhost:8080/api/public')
      .subscribe(
        response => console.log('ping public response', response)
      );
  }

  pingPrivate(): void {
    this.http.get('http://localhost:8080/api/private', {
      headers: new HttpHeaders({
        Authorization: `Bearer ${this.authService.idToken}`
      })
    })
      .subscribe(
        response => console.log('ping private repsonse', response)
      );
  }

  pingPrivateScoped(): void {
    this.http.get('http://localhost:8080/api/private-scoped', {
      headers: new HttpHeaders({
        Authorization: `Bearer ${this.authService.idToken}`
      })
    })
      .subscribe(
        response => console.log('ping private repsonse', response)
      );
  }

}
