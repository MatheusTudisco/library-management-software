package com.matheustudisco.librarymanagementsoftware.model;

import com.matheustudisco.librarymanagementsoftware.enums.Role;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class User {
    private Long id;
    private String name;
    private String lastName;
    private String cpf;
    private LocalDate dateOfBirth;
    private String cellphone;
    private String email;
    private Role role;
    private String password;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /*
    * Este construtor é utilizado para montar o usuário que será enviado ao banco para registro, O ID
    * é gerado pelo banco
     */
    public User(String name, String lastName, String cpf, LocalDate dateOfBirth, String cellphone, String email, Role role, String password) {
        this.name = name;
        this.lastName = lastName;
        this.cpf = cpf;
        this.dateOfBirth = dateOfBirth;
        this.cellphone = cellphone;
        this.email = email;
        this.role = role;
        this.password = password;
    }
/*
* Este Construtor é utilizado para montar o usuário que vem como resultado do SELECT do banco, o ID é gerado
* automaticamente pelo banco de dados.
 */
    public User(Long id, String name, String lastName, String cpf, LocalDate dateOfBirth, String cellphone, String email, Role role, String password) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.cpf = cpf;
        this.dateOfBirth = dateOfBirth;
        this.cellphone = cellphone;
        this.email = email;
        this.role = role;
        this.password = password;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return  id +
                " \n" +
                "Name: " + name + "\n" +
                "Last Name: " + lastName + "\n" +
                "CPF: " + cpf + "\n" +
                "Date of Birth: " + dateOfBirth.format(formatter) + "\n" +
                "Cellphone: " + cellphone + "\n" +
                "Email: " + email + "\n\n";
    }
}
