package com.matheustudisco.librarymanagementsoftware.dto;

import com.matheustudisco.librarymanagementsoftware.enums.Role;

import java.time.LocalDate;

public class UserRegisterDto {
    private String name;
    private String lastName;
    private String cpf;
    private LocalDate dateOfBirth;
    private String cellphone;
    private String email;
    private Role role;
    private String password;

    public UserRegisterDto(String name, String lastName, String cpf, LocalDate dateOfBirth, String cellphone, String email, Role role, String password) {
        this.name = name;
        this.lastName = lastName;
        this.cpf = cpf;
        this.dateOfBirth = dateOfBirth;
        this.cellphone = cellphone;
        this.email = email;
        this.role = role;
        this.password = password;
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

    public String getPassword() {
        return password;
    }
}
