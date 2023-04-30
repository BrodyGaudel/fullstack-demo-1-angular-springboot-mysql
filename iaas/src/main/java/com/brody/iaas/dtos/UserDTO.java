package com.brody.iaas.dtos;

import com.brody.iaas.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDTO {
    private Long userId;
    private String username;
    private String password;
    private Boolean enabled;
    private List<Role> roles;
}
