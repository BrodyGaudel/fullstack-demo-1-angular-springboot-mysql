import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {VmService} from "../../services/vm/vm.service";
import {VirtualmachineModel} from "../../models/virtualmachine.model";

@Component({
  selector: 'app-update-vm',
  templateUrl: './update-vm.component.html',
  styleUrls: ['./update-vm.component.css']
})
export class UpdateVmComponent implements OnInit{
  updateFormGroup!: FormGroup;
  model = new VirtualmachineModel();
  vmId!: string;

  constructor(private fb: FormBuilder,
              private vmService: VmService,
              private router: Router,
              private activatedRoute: ActivatedRoute) {}



  ngOnInit(): void {
    this.initFormGroup();
    this.vmId = this.activatedRoute.snapshot.params['id'];
    this.vmService.getVmById(this.vmId).subscribe( vm => {
      this.model = vm;
    });
  }

  initFormGroup(){
    this.updateFormGroup = this.fb.group( {
      name : this.fb.control(null, [Validators.required, Validators.minLength(4), Validators.maxLength(64)]),
      description : this.fb.control( null, [Validators.maxLength(128), Validators.minLength(4), Validators.required]),
      memory: this.fb.control( null, [Validators.required]),
      osname: this.fb.control(null, [Validators.required, Validators.minLength(4), Validators.maxLength(64)]),
      ostype: this.fb.control(null, [Validators.required]),
      stockage: this.fb.control( null, [Validators.required]),
      nettype: this.fb.control( null, [Validators.required]),
      netip: this.fb.control(null, [Validators.required, Validators.minLength(7), Validators.maxLength(15)]),
      cpu: this.fb.control( null, [Validators.required]),
      cpucore: this.fb.control( null, [Validators.required])
    });
  }


  handleUpdateVm() {
    this.vmService.updateVm(this.model).subscribe(() => {
      this.router.navigate(['details-vm', this.vmId]).then();
    });
  }


}
