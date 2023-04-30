package com.brody.iaas.restcontroller;

import com.brody.iaas.dtos.Form;
import com.brody.iaas.dtos.UserDTO;
import com.brody.iaas.dtos.UserRequestDTO;
import com.brody.iaas.entities.User;
import com.brody.iaas.mappers.Mappers;
import com.brody.iaas.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sec")
public class UserRestController {
    private final UserService userService;
    private final Mappers mappers;

    public UserRestController(UserService userService, Mappers mappers) {
        this.userService = userService;
        this.mappers = mappers;
    }

    @GetMapping("/list")
    public List<UserDTO> getAllUsers() {
        return mappers.fromUsers(
                userService.findAllUsers()
        );
    }

    @GetMapping("/get/{id}")
    public UserDTO findUserById(@PathVariable(name = "id") Long id){
        return mappers.fromUser(
                userService.findUserById(id)
        );
    }

    @PostMapping("/save")
    public UserDTO addUser(@RequestBody UserRequestDTO user){
        User u = userService.createUser(
                mappers.fromUserRequestDTO(user)
        );
        return mappers.fromUser(u);
    }

    @PutMapping("/update")
    public UserDTO updateUser(@RequestBody UserRequestDTO user){
        User u = userService.updateUser(
                mappers.fromUserRequestDTO(user)
        );
        return mappers.fromUser(u);
    }

    @PutMapping("/addrole")
    public UserDTO addRoleToUser(@RequestBody Form form){
        return mappers.fromUser(
                userService.addRoleToUser(form.getUsername(), form.getRolename())
        );
    }

    @PutMapping("/removerole")
    public UserDTO removeRoleFromUser(@RequestBody Form form){
        return mappers.fromUser(
                userService.addRoleToUser(form.getUsername(), form.getRolename())
        );
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable(name = "id") Long id){
        userService.deleteUser(id);
    }
}
