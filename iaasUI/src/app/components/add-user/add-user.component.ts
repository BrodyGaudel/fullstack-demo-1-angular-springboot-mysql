import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {UserService} from "../../services/user/user.service";
import {UserDTO} from "../../models/userdto.model";
import {Role} from "../../models/submodel/role.model";
import {UserRequestDTO} from "../../models/userrequest.model";

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit{
  addUserFormGroup!: FormGroup;
  roles!: Role[];

  constructor(private fb: FormBuilder,
              private userService: UserService,
              private router: Router) { }

  ngOnInit(): void {
    this.addUserFormGroup = this.fb.group( {
      username : this.fb.control(null, [Validators.required, Validators.minLength(4), Validators.minLength(64)]),
      password : this.fb.control( null, [Validators.required, Validators.minLength(4), Validators.maxLength(64)])
    });
  }

  handleSaveUser() {
    let user = new UserRequestDTO();
    user.password = this.addUserFormGroup.value.password;
    user.username = this.addUserFormGroup.value.username;
    user.enabled = true;
    this.userService.addUsers(user).subscribe({
      next : data => {
        alert("Utilisateurs Créée avec succès");
        this.router.navigate(['list-user']).then();
      },
      error : err => {
        console.log(err);
        alert("Problème lors de la création de l'utilisateur");
      }
    });
  }
}
