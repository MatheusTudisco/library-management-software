package com.matheustudisco.librarymanagementsoftware.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CpfInvalidoException.class)
    public ResponseEntity<String> tratarCpfAuthInvalido (CpfInvalidoException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    @ExceptionHandler(SenhaInvalidaException.class)
    public ResponseEntity<String> tratarSenhaAuthInvalida (SenhaInvalidaException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
    @ExceptionHandler(CpfInexistenteException.class)
    public ResponseEntity<String> tratarCpfAuthInexistente (CpfInexistenteException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
