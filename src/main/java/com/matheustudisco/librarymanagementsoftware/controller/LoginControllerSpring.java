package com.matheustudisco.librarymanagementsoftware.controller;

import com.matheustudisco.librarymanagementsoftware.dto.UserAuthenticationDto;
import com.matheustudisco.librarymanagementsoftware.dto.UserRetornoAuthDto;
import com.matheustudisco.librarymanagementsoftware.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class LoginControllerSpring {
    private final UserService userService;

    public LoginControllerSpring(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/auth/login")
    public ResponseEntity<Object> autenticarLogin (@RequestBody UserAuthenticationDto userAuthenticationDto){
        UserRetornoAuthDto userRetornoAuthDto = userService.autenticar(userAuthenticationDto.cpf(), userAuthenticationDto.password());
        if (userRetornoAuthDto == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CPF ou senha incorretos, tente novamente.");
        } else{
            return ResponseEntity.status(HttpStatus.OK).body(userRetornoAuthDto);
        }
    }
}
