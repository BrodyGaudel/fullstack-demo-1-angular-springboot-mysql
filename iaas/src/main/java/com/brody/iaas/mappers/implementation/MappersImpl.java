package com.brody.iaas.mappers.implementation;

import com.brody.iaas.dtos.*;
import com.brody.iaas.entities.*;
import com.brody.iaas.mappers.Mappers;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MappersImpl implements Mappers {
    @Override
    public VirtualMachine fromVirtualMachineDTO(VirtualMachineDTO vm) {
        try{
            Network network = Network.builder()
                    .id(vm.getNetwork().getId())
                    .ipAddress(vm.getNetwork().getIpAddress())
                    .type(vm.getNetwork().getType())
                    .build();

            Processors processors = Processors.builder()
                    .id(vm.getProcessors().getId())
                    .numberOfProcessors(vm.getProcessors().getNumberOfProcessors())
                    .numberOfCorPerProcessors(vm.getProcessors().getNumberOfCorPerProcessors())
                    .build();

            HardDisk hardDisk = HardDisk.builder()
                    .id(vm.getHardDisk().getId())
                    .type(vm.getHardDisk().getType())
                    .stockage(vm.getHardDisk().getStockage())
                    .build();

            OperatingSystem operatingSystem = OperatingSystem.builder()
                    .id(vm.getOperatingSystem().getId())
                    .type(vm.getOperatingSystem().getType())
                    .name(vm.getOperatingSystem().getName())
                    .size(vm.getOperatingSystem().getSize())
                    .build();

            return VirtualMachine.builder()
                    .id(vm.getId())
                    .name(vm.getName())
                    .creation(vm.getCreation())
                    .lastModification(vm.getLastModification())
                    .description(vm.getDescription())
                    .isRunning(vm.getIsRunning())
                    .memory(vm.getMemory())
                    .hardDisk(hardDisk)
                    .network(network)
                    .operatingSystem(operatingSystem)
                    .processors(processors)
                    .build();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public VirtualMachineDTO fromVirtualMachine(VirtualMachine vm) {
        try {
            NetworkDTO network = NetworkDTO.builder()
                    .id(vm.getNetwork().getId())
                    .ipAddress(vm.getNetwork().getIpAddress())
                    .type(vm.getNetwork().getType())
                    .build();

            ProcessorsDTO processors = ProcessorsDTO.builder()
                    .id(vm.getProcessors().getId())
                    .numberOfProcessors(vm.getProcessors().getNumberOfProcessors())
                    .numberOfCorPerProcessors(vm.getProcessors().getNumberOfCorPerProcessors())
                    .build();

            HardDiskDTO hardDisk = HardDiskDTO.builder()
                    .id(vm.getHardDisk().getId())
                    .type(vm.getHardDisk().getType())
                    .stockage(vm.getHardDisk().getStockage())
                    .build();

            OperatingSystemDTO operatingSystem = OperatingSystemDTO.builder()
                    .id(vm.getOperatingSystem().getId())
                    .type(vm.getOperatingSystem().getType())
                    .name(vm.getOperatingSystem().getName())
                    .size(vm.getOperatingSystem().getSize())
                    .build();

            return VirtualMachineDTO.builder()
                    .id(vm.getId())
                    .name(vm.getName())
                    .creation(vm.getCreation())
                    .lastModification(vm.getLastModification())
                    .description(vm.getDescription())
                    .isRunning(vm.getIsRunning())
                    .memory(vm.getMemory())
                    .hardDisk(hardDisk)
                    .network(network)
                    .operatingSystem(operatingSystem)
                    .processors(processors)
                    .build();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<VirtualMachineDTO> fromVirtualMachines(List<VirtualMachine> vms) {
        try {
            return vms.stream().map(this::fromVirtualMachine).toList();
        }catch (Exception e){
            return Collections.emptyList();
        }
    }

    @Override
    public User fromUserDTO(UserDTO u) {
        try{
           return new User(
                   u.getUserId(),
                   u.getUsername(),
                   u.getPassword(),
                   u.getEnabled(),
                   null
           );
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public UserDTO fromUser(User u) {
        try{
            return UserDTO.builder()
                    .userId(u.getUserId())
                    .username(u.getUsername())
                    .password(null)
                    .enabled(u.getEnabled())
                    .roles(u.getRoles())
                    .build();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<UserDTO> fromUsers(List<User> userList) {
        try{
            return userList.stream().map(this::fromUser).toList();
        }catch (Exception e){
            return Collections.emptyList();
        }
    }

    @Override
    public User fromUserRequestDTO(UserRequestDTO u) {
        try{
            return new User(
                    u.getUserId(),
                    u.getUsername(),
                    u.getPassword(),
                    u.getEnabled(),
                    null
            );
        }catch (Exception e){
            return null;
        }
    }


}
