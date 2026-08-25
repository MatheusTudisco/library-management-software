package com.matheustudisco.librarymanagementsoftware.dto;

import com.matheustudisco.librarymanagementsoftware.enums.Role;
import com.matheustudisco.librarymanagementsoftware.model.User;

import java.time.LocalDate;

public class UserSearchDto {
    private Long id;
    private String name;
    private String lastName;
    private String cpf;
    private LocalDate dateOfBirth;
    private String cellphone;
    private String email;
    private Role role;

    public UserSearchDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.cpf = user.getCpf();
        this.dateOfBirth = user.getDateOfBirth();
        this.cellphone = user.getCellphone();
        this.email = user.getEmail();
        this.role = user.getRole();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getCellphone() {
        return cellphone;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }
}
