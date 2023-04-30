import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {SecurityService} from "../security/security.service";
import {UserDTO} from "../../models/userdto.model";
import {Observable} from "rxjs";
import {UserRequestDTO} from "../../models/userrequest.model";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  apiURL: string = 'http://localhost:8081/vm/api/sec';

  constructor(private http : HttpClient,
              public securityService: SecurityService) {}

  private getHeader(){
    let jwt = this.securityService.getToken();
    jwt = "Bearer "+jwt;
    return new HttpHeaders({"Authorization": jwt});
  }

  getAllUsers(): Observable<Array<UserDTO>>{
    let httpHeaders = this.getHeader();
    return this.http.get<Array<UserDTO>>(this.apiURL+"/list", {headers:httpHeaders});
  }

  addUsers(model: UserRequestDTO): Observable<UserDTO>{
    let httpHeaders = this.getHeader();
    return this.http.post<UserDTO>(this.apiURL+"/save", model, {headers:httpHeaders});
  }

  deleteVm(model: UserDTO){
    let httpHeaders = this.getHeader();
    return this.http.delete(this.apiURL+"/delete/" + model.userId, {headers:httpHeaders});
  }

  updateVm(model: UserDTO): Observable<UserDTO>{
    let httpHeaders = this.getHeader();
    return this.http.put<UserDTO>(this.apiURL+"/update", model, {headers:httpHeaders});
  }

  getVmById(id: string): Observable<UserDTO>{
    let httpHeaders = this.getHeader();
    return this.http.get<UserDTO>(this.apiURL+"/get/"+ id, {headers:httpHeaders});
  }
}
