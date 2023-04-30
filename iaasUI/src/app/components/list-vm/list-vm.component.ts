import {Component, OnInit} from '@angular/core';
import {catchError, Observable, throwError} from "rxjs";
import {VirtualmachineModel} from "../../models/virtualmachine.model";
import {VmService} from "../../services/vm/vm.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-list-vm',
  templateUrl: './list-vm.component.html',
  styleUrls: ['./list-vm.component.css']
})
export class ListVmComponent implements OnInit{

  searchFormGroup!: FormGroup;
  vms!: Observable<Array<VirtualmachineModel>>;
  errorMessage!: string;

  constructor(private vmService: VmService,
              private fb: FormBuilder,
              private router:Router) { }

  ngOnInit(): void {
    this.searchFormGroup=this.fb.group({
      keyword : this.fb.control("")
    });
    this.getAllVm();
  }

  getAllVm(){
    this.vms = this.vmService.getAllVm();
  }

  handleSearchVms() {
    let kw=this.searchFormGroup.value.keyword;
    this.vms =this.vmService.searchVm(kw).pipe(
      catchError(err => {
        this.errorMessage = err.message;
        return throwError(err);
      })
    );
  }

  setFlag(v: VirtualmachineModel){
    if(v.isRunning){
      return "/assets/green flag.png";
    }else{
      return "/assets/red flag.png";
    }
  }

  gotoVmDetails(v: VirtualmachineModel) {
    this.router.navigate(['/details-vm',v.id]).then();
  }
}
