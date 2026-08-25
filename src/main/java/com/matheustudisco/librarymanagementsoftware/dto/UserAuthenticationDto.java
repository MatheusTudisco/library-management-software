package com.matheustudisco.librarymanagementsoftware.dto;

import com.matheustudisco.librarymanagementsoftware.enums.Role;
import com.matheustudisco.librarymanagementsoftware.model.User;

public class UserAuthenticationDto {
    private String name;
    private String lastName;
    private String cpf;
    private String password;
    private Role role;

    public UserAuthenticationDto(User user){
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.cpf = user.getCpf();
        this.role = user.getRole();
        this.password = user.getPassword();
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Role getRole() {
        return role;
    }

    public String getCpf() {
        return cpf;
    }

    public String getPassword() {
        return password;
    }
}
