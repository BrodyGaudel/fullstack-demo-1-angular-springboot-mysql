import {HardDisk} from "./submodel/harddisk";
import {Network} from "./submodel/network";
import {Processors} from "./submodel/processors";
import {OperatingSystem} from "./submodel/operatingsystem";

export class VirtualmachineModel {
  id!: string;
  name!: string;
  description!: string;
  memory!: number;
  isRunning!: boolean
  operatingSystem!: OperatingSystem;
  processors!: Processors;
  network!: Network;
  hardDisk!: HardDisk;
  creation!: string;
  lastModification!: string;
}
