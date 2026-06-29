package com.matheustudisco.librarymanagementsoftware.model;

import java.time.LocalDate;

public class User {
    private Long id;
    private String name;
    private String lastName;
    private String cpf;
    private LocalDate dateOfBirth;
    private String cellphone;
    private String email;
    private String password;

    public User(Long id, String name, String lastName, String cpf, LocalDate dateOfBirth, String cellphone, String password, String email) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.cpf = cpf;
        this.dateOfBirth = dateOfBirth;
        this.cellphone = cellphone;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
