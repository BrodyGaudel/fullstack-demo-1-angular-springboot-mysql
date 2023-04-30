import {Component, OnInit} from '@angular/core';
import {VmService} from "../../services/vm/vm.service";
import {ActivatedRoute, Router} from "@angular/router";
import {VirtualmachineModel} from "../../models/virtualmachine.model";
import {catchError, Observable, throwError} from "rxjs";
import {SecurityService} from "../../services/security/security.service";

@Component({
  selector: 'app-details-vm',
  templateUrl: './details-vm.component.html',
  styleUrls: ['./details-vm.component.css']
})
export class DetailsVmComponent implements OnInit{

  vm!: Observable<VirtualmachineModel>;
  vmId!: string;
  errorMessage: any;
  messageFlag!: string;
  buttonMessageFlag!: string;
  buttonClassAuthorizationDelete!: string;
  buttonClassAuthorizationUpdate!: string;

  constructor(private vmService: VmService,
              private securityService: SecurityService,
              private router: Router,
              private activateRoute: ActivatedRoute) {}
  ngOnInit(): void {
    this.vmId = this.activateRoute.snapshot.params['id'];
    this.testAuthorization();
    this.getVm();
  }

  getVm() {
    this.vm = this.vmService.getVmById(this.vmId).pipe(
      catchError(err => {
        this.errorMessage = err.message;
        return throwError(err);
      })
    );
  }

  setFlag(v: VirtualmachineModel){
    if(v.isRunning){
      this.messageFlag = "En cours d'exécution !";
      this.buttonMessageFlag = "arrêter"
      return "/assets/green flag.png";
    }else{
      this.messageFlag = "En arret !";
      this.buttonMessageFlag = "démarrer"
      return "/assets/red flag.png";
    }
  }


  handleDeleteVm(v: VirtualmachineModel) {
    if(this.securityService.isAdmin()){
      const conf = confirm('Etes-vous sûr de vouloir supprimer?');
      if (conf){
        this.vmService.deleteVm(v).subscribe(() => {
          console.log('VM supprimé!');
          this.gotoListVm();
        });
      }
    }else{
      alert("Vous ne disposez pas des droits nécessaire pour supprimer cette machine virtuelle");
    }
  }

  handleStatus(v: VirtualmachineModel) {
    let model: VirtualmachineModel = v;
    model.isRunning = !v.isRunning;
    this.vm = this.vmService.updateVm(model).pipe(
      catchError(err => {
        this.errorMessage = err.message;
        return throwError(err);
      })
    );
  }

  handleUpdateVm(v: VirtualmachineModel) {
    if(this.securityService.isAdmin()){
      this.router.navigate(['/update-vm',v.id]).then();
    }else{
      alert("Vous ne disposez pas des droits nécessaire pour modifier cette machine virtuelle");
    }

  }

  testAuthorization(){
    if(!this.securityService.isAdmin()){
      this.buttonClassAuthorizationDelete = "btn btn-outline-light";
      this.buttonClassAuthorizationUpdate = "btn btn-outline-light";
    }else {
      this.buttonClassAuthorizationDelete = "btn btn-danger";
      this.buttonClassAuthorizationUpdate = "btn btn-success";
    }
  }

  gotoListVm(){
    this.router.navigateByUrl("list-vm").then();
  }
}
