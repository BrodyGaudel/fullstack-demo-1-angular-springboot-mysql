import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {SecurityService} from "./services/security/security.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'iaasUI';

  constructor (public securityService: SecurityService, private router: Router) {}


  ngOnInit(): void {
    this.securityService.loadToken();
    if (this.securityService.getToken()==null || this.securityService.isTokenExpired()){
      this.router.navigate(['/login']);
    }
  }


  onLogout(){
    this.securityService.logout();
  }
}
