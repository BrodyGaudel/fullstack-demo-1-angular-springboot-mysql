package com.brody.iaas;

import com.brody.iaas.entities.Role;
import com.brody.iaas.entities.User;
import com.brody.iaas.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootApplication
public class IaasApplication {

    public static void main(String[] args) {
        SpringApplication.run(IaasApplication.class, args);
    }

    @Bean
    BCryptPasswordEncoder getBCE() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner start(UserService service){
        return args -> {
            List<Role> roleList = service.findAllRoles();
            if(roleList.isEmpty()){
                service.addRole(new Role((long)1, "USER"));
                service.addRole(new Role((long)2, "ADMIN"));
            }
            List<User> userList = service.findAllUsers();
            if (userList.isEmpty()){
                User u1 = service.saveUser(
                        new User((long)1, "brody", "admin", true, null ));
                User u2 = service.saveUser(
                        new User((long)2, "gaudel", "user", true, null ));
                service.addRoleToUser(u1.getUsername(), "USER");
                service.addRoleToUser(u1.getUsername(), "ADMIN");
                service.addRoleToUser(u2.getUsername(), "USER");
            }
        };
    }

}
