package com.brody.iaas.service;

import com.brody.iaas.dtos.VirtualMachineDTO;
import com.brody.iaas.exceptions.VmNotFoundException;

import java.util.List;

public interface VirtualMachineService {

    VirtualMachineDTO createNewVm(VirtualMachineDTO virtualMachineDTO);
    VirtualMachineDTO updateVm(VirtualMachineDTO virtualMachineDTO) throws VmNotFoundException;
    List<VirtualMachineDTO> updateAllVms(List<VirtualMachineDTO> virtualMachineDTOS);
    VirtualMachineDTO findVmById(String id) throws VmNotFoundException;
    List<VirtualMachineDTO> findAllVm();
    List<VirtualMachineDTO> findAllVmRunning();
    List<VirtualMachineDTO> searchVm(String keyword);
    List<VirtualMachineDTO> findAllVmByPage(String kw, int page, int size);
    void deleteVmById(String id);
    void deleteAllVm();
}
