package com.brody.iaas.mappers;

import com.brody.iaas.dtos.UserDTO;
import com.brody.iaas.dtos.UserRequestDTO;
import com.brody.iaas.dtos.VirtualMachineDTO;
import com.brody.iaas.entities.User;
import com.brody.iaas.entities.VirtualMachine;

import java.util.List;

public interface Mappers {
    VirtualMachine fromVirtualMachineDTO(VirtualMachineDTO vm);
    VirtualMachineDTO fromVirtualMachine(VirtualMachine vm);
    List<VirtualMachineDTO> fromVirtualMachines(List<VirtualMachine> vms);

    User fromUserDTO(UserDTO u);
    UserDTO fromUser(User u);
    List<UserDTO> fromUsers(List<User> userList);
    User fromUserRequestDTO(UserRequestDTO u);
}
