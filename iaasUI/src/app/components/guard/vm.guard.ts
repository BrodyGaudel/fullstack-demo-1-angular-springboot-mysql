import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {SecurityService} from "../../services/security/security.service";

@Injectable({
  providedIn: 'root'
})
export class VmGuard implements CanActivate{

  constructor (private authService: SecurityService,
               private router : Router) {}

  canActivate(route: ActivatedRouteSnapshot,
              state: RouterStateSnapshot): boolean {
    if (this.authService.isAdmin())
      return true;
    else {
      this.router.navigate(['app-forbidden']).then(()=>{});
      return false;
    }
  }

}
