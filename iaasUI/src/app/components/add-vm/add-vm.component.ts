import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {SecurityService} from "../../services/security/security.service";
import {VmService} from "../../services/vm/vm.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {VirtualmachineModel} from "../../models/virtualmachine.model";
import {Processors} from "../../models/submodel/processors";
import {Network} from "../../models/submodel/network";
import {OperatingSystem} from "../../models/submodel/operatingsystem";
import {HardDisk} from "../../models/submodel/harddisk";

@Component({
  selector: 'app-add-vm',
  templateUrl: './add-vm.component.html',
  styleUrls: ['./add-vm.component.css']
})
export class AddVmComponent implements OnInit{
  createFormGroup!: FormGroup;


  constructor(private fb: FormBuilder,
              private router: Router,
              private securityService: SecurityService,
              private vmService: VmService) {}

  ngOnInit(): void {
    this.initFormGroup();

  }

  handleCreateVm() {
    let processor = new Processors();
    processor.numberOfProcessors = this.createFormGroup.value.cpu;
    processor.numberOfCorPerProcessors = this.createFormGroup.value.cpucore;

    let network = new Network();
    network.type = this.createFormGroup.value.nettype;
    network.ipAddress = this.createFormGroup.value.netip;

    let os = new OperatingSystem();
    os.size = 6;
    os.type = this.createFormGroup.value.ostype;
    os.name = this.createFormGroup.value.osname;

    let disk = new HardDisk();
    disk.stockage = this.createFormGroup.value.stockage;
    disk.type = "SSD";

    let model = new VirtualmachineModel();
    model.isRunning = false;
    model.name = this.createFormGroup.value.name;
    model.description = this.createFormGroup.value.description;
    model.memory = this.createFormGroup.value.memory;
    model.processors = processor;
    model.network = network;
    model.operatingSystem = os;
    model.hardDisk = disk;

    if(this.securityService.isAdmin()){
      this.vmService.createVm(model).subscribe({
        next : data => {
          alert("Machine Virtuelle Créée avec succès");
          this.router.navigate(['details-vm', data.id]).then();
        },
        error : err => {
          console.log(err);
          alert("Problème lors de la création de la machine virtuelle");
        }
      });
    }else{
      alert("Vous ne disposez pas des droits nécessaire pour créer une machine virtuelle");
    }

  }

  initFormGroup(){
    this.createFormGroup = this.fb.group( {
      name : this.fb.control(null, [Validators.required, Validators.minLength(4), Validators.maxLength(64)]),
      description : this.fb.control( null, [Validators.maxLength(128), Validators.minLength(4), Validators.required]),
      memory: this.fb.control( null, [Validators.required]),
      cpu: this.fb.control( null, [Validators.required]),
      cpucore: this.fb.control( null, [Validators.required]),
      osname: this.fb.control(null, [Validators.required, Validators.minLength(4), Validators.maxLength(64)]),
      ostype: this.fb.control(null, [Validators.required]),
      stockage: this.fb.control( null, [Validators.required]),
      nettype: this.fb.control( null, [Validators.required]),
      netip: this.fb.control(null, [Validators.required, Validators.minLength(7), Validators.maxLength(15)])
    });
  }
}
