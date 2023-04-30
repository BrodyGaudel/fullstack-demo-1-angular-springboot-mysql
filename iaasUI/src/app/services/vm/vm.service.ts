import { Injectable } from '@angular/core';
import {SecurityService} from "../security/security.service";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {VirtualmachineModel} from "../../models/virtualmachine.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class VmService {

  apiURL: string = 'http://localhost:8081/vm/api/v1';

  constructor(private http : HttpClient,
              public securityService: SecurityService) {}

  private getHeader(){
    let jwt = this.securityService.getToken();
    jwt = "Bearer "+jwt;
    return new HttpHeaders({"Authorization": jwt});
  }

  getAllVm(): Observable<Array<VirtualmachineModel>>{
    let httpHeaders = this.getHeader();
    return this.http.get<Array<VirtualmachineModel>>(this.apiURL+"/list", {headers:httpHeaders});
  }

  getVmById(id: string): Observable<VirtualmachineModel>{
    let httpHeaders = this.getHeader();
    return this.http.get<VirtualmachineModel>(this.apiURL+"/get/"+ id, {headers:httpHeaders});
  }

  searchVm(keyword: string): Observable<Array<VirtualmachineModel>>{
    let httpHeaders = this.getHeader();
    return this.http.get<Array<VirtualmachineModel>>(this.apiURL+"/search?keyword=" + keyword, {headers:httpHeaders});
  }

  updateVm(model: VirtualmachineModel): Observable<VirtualmachineModel>{
    let httpHeaders = this.getHeader();
    return this.http.put<VirtualmachineModel>(this.apiURL+"/update", model, {headers:httpHeaders});
  }

  createVm(model: VirtualmachineModel): Observable<VirtualmachineModel>{
    let httpHeaders = this.getHeader();
    return this.http.post<VirtualmachineModel>(this.apiURL+"/save", model, {headers:httpHeaders});
  }

  deleteVm(model: VirtualmachineModel){
    let httpHeaders = this.getHeader();
    return this.http.delete(this.apiURL+"/delete/" + model.id, {headers:httpHeaders});
  }
}
