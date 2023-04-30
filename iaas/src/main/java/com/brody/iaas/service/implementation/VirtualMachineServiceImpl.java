package com.brody.iaas.service.implementation;

import com.brody.iaas.dtos.VirtualMachineDTO;
import com.brody.iaas.entities.VirtualMachine;
import com.brody.iaas.exceptions.VmNotFoundException;
import com.brody.iaas.mappers.Mappers;
import com.brody.iaas.repositories.VirtualMachineRepository;
import com.brody.iaas.service.VirtualMachineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@Slf4j
public class VirtualMachineServiceImpl implements VirtualMachineService {

    private final VirtualMachineRepository virtualMachineRepository;
    private final Mappers mappers;

    public VirtualMachineServiceImpl(VirtualMachineRepository virtualMachineRepository, Mappers mappers) {
        this.virtualMachineRepository = virtualMachineRepository;
        this.mappers = mappers;
    }

    @Override
    public VirtualMachineDTO createNewVm(VirtualMachineDTO virtualMachineDTO) {
        String message;
        try{
            VirtualMachine vm = mappers.fromVirtualMachineDTO(virtualMachineDTO);
            String id = UUID.randomUUID().toString();
            vm.getNetwork().setId(id);
            vm.getProcessors().setId(id);
            vm.getHardDisk().setId(id);
            vm.getOperatingSystem().setId(id);
            vm.setId(id);
            vm.setCreation(new Date());
            vm.setLastModification(null);
            if(vm.getMemory()<20){
                vm.setMemory(20);
            }
            VirtualMachine savedVm = virtualMachineRepository.save(vm);
            message = "Virtual machine created successfully with id :"+savedVm.getId();
            log.info(message);
            return mappers.fromVirtualMachine(savedVm);
        }catch (Exception e){
            message = "Virtual machine created due to :"+e.getMessage();
            log.error(message);
            return null;
        }

    }

    @Override
    public VirtualMachineDTO updateVm(VirtualMachineDTO virtualMachineDTO) throws VmNotFoundException {
        VirtualMachine vm = findById(virtualMachineDTO.getId(),
                "the virtual machine you want to modify does not exist.");
        vm.setMemory(virtualMachineDTO.getMemory());
        vm.setName(virtualMachineDTO.getName());
        vm.setDescription(virtualMachineDTO.getDescription());
        vm.setIsRunning(virtualMachineDTO.getIsRunning());
        vm.getNetwork().setType(virtualMachineDTO.getNetwork().getType());
        vm.getNetwork().setIpAddress(virtualMachineDTO.getNetwork().getIpAddress());
        vm.getOperatingSystem().setSize(virtualMachineDTO.getOperatingSystem().getSize());
        vm.getOperatingSystem().setName(virtualMachineDTO.getOperatingSystem().getName());
        vm.getOperatingSystem().setType(virtualMachineDTO.getOperatingSystem().getType());
        vm.getProcessors().setNumberOfProcessors(virtualMachineDTO.getProcessors().getNumberOfProcessors());
        vm.getProcessors().setNumberOfCorPerProcessors(virtualMachineDTO.getProcessors().getNumberOfCorPerProcessors());
        vm.getHardDisk().setStockage(virtualMachineDTO.getHardDisk().getStockage());
        vm.getHardDisk().setType(virtualMachineDTO.getHardDisk().getType());
        vm.setLastModification(new Date());
        VirtualMachine updatedVm = virtualMachineRepository.save(vm);
        log.info("vm updated");
        return mappers.fromVirtualMachine(updatedVm);
    }

    @Override
    public List<VirtualMachineDTO> updateAllVms(List<VirtualMachineDTO> virtualMachineDTOS) {
        List<VirtualMachineDTO> vms = new ArrayList<>();
        VirtualMachineDTO vm;
        for(VirtualMachineDTO v: virtualMachineDTOS){
            try{
                vm = updateVm(v);
            }catch (Exception e){
                log.warn(e.getMessage());
                vm = v;
            }
            vms.add(vm);
        }
        return vms;
    }

    @Override
    public VirtualMachineDTO findVmById(String id) throws VmNotFoundException {
        VirtualMachine vm = findById(id, "VM not found");
        log.info("vm found");
        return mappers.fromVirtualMachine(vm);
    }

    @Override
    public List<VirtualMachineDTO> findAllVm() {
        List<VirtualMachine> virtualMachines = virtualMachineRepository.findAll();
        log.info("list of VMs returned");
        return mappers.fromVirtualMachines(virtualMachines);
    }

    @Override
    public List<VirtualMachineDTO> findAllVmRunning() {
        List<VirtualMachine> virtualMachines = virtualMachineRepository.findByIsRunning(true);
        log.info("list of vms running returned");
        return mappers.fromVirtualMachines(virtualMachines);
    }

    @Override
    public List<VirtualMachineDTO> searchVm(String keyword) {
        List<VirtualMachine> virtualMachines = virtualMachineRepository.search(keyword);
        log.info("return vms found");
        return mappers.fromVirtualMachines(virtualMachines);
    }

    @Override
    public List<VirtualMachineDTO> findAllVmByPage(String kw, int page, int size) {
        Page<VirtualMachine> providers = virtualMachineRepository.findByNameContains(kw, PageRequest.of(page,size));
        log.info("list of vms: page = "+page+". and size = "+size);
        return providers.getContent()
                .stream()
                .map(mappers::fromVirtualMachine)
                .toList();
    }

    @Override
    public void deleteVmById(String id) {
        virtualMachineRepository.deleteById(id);
        log.info("vm deleted");
    }

    @Override
    public void deleteAllVm() {
        virtualMachineRepository.deleteAll();
        log.info("all vms deleted");
    }

    private VirtualMachine findById(String id, String exceptionMessage) throws VmNotFoundException {
        return virtualMachineRepository.findById(id)
                .orElseThrow(()-> new VmNotFoundException(exceptionMessage));
    }
}
