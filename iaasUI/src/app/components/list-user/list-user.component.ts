import {Component, OnInit} from '@angular/core';
import {catchError, Observable, throwError} from "rxjs";
import {UserDTO} from "../../models/userdto.model";
import {UserService} from "../../services/user/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-list-user',
  templateUrl: './list-user.component.html',
  styleUrls: ['./list-user.component.css']
})
export class ListUserComponent implements OnInit{

  users!: Observable<Array<UserDTO>>;
  errorMessage!: string;

  constructor(private userService: UserService, private router: Router) {}


  ngOnInit(): void {
    this.listUsers();
  }

  listUsers(){
    this.users = this.userService.getAllUsers().pipe(
      catchError(err => {
        this.errorMessage = err.message;
        return throwError(err);
      })
    );
  }

  gotoUpdateUser(u: UserDTO) {
    this.router.navigate(['/update-user',u.userId]).then();
  }

  getRoleOne(u: UserDTO) {
    let role = u.roles[0];
    return role.name;
  }

  getRoleTwo(u: UserDTO) {
    let role = u.roles[1];
    return role.name;
  }
}
