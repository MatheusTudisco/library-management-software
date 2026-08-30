package com.matheustudisco.librarymanagementsoftware.dto;

import com.matheustudisco.librarymanagementsoftware.enums.Role;
import com.matheustudisco.librarymanagementsoftware.model.User;

public record UserAuthenticationDto (String cpf, String password) {

}
