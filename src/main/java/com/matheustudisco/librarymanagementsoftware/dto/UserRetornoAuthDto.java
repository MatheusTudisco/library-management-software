package com.matheustudisco.librarymanagementsoftware.dto;

import com.matheustudisco.librarymanagementsoftware.enums.Role;
import com.matheustudisco.librarymanagementsoftware.model.User;

//Este Dto retornará para o front end os dados após confirmada autenticação pela lógica.
public record UserRetornoAuthDto(String name, String lastName, Role role) {
    public UserRetornoAuthDto (User user){
        this(user.getName(), user.getLastName(), user.getRole());
    }
}
