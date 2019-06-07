import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  public userData: any;

  constructor(private http: HttpClient) { }

  getToken = () => {
    this.http.get(environment.authServiceUrl).subscribe((data: any) => {
      console.log(data);
    });
  }

  isAuthenticated = () => {
    return true;
    if (this.userData) {
      
      // TODO: go to API and check token
      return true;
    }

    let token = localStorage.getItem('pinboardtoken');

    if (token) {
      // TODO: parse user data from token
      this.userData = JSON.parse(token);
      
      // TODO: go to API and check token
      return true;
    }

    return false;
  };

  authenticate = (username: string, password: string, shouldCache: boolean) => {
    this.userData = { fullName: username };

    if (shouldCache) {
      localStorage.setItem('pinboardtoken', JSON.stringify(this.userData));
    }
  }

  logOut = () => {
    localStorage.removeItem("pinboardtoken");
    this.userData = null;
  }
}
