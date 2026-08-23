package com.matheustudisco.librarymanagementsoftware.controller;

import com.matheustudisco.librarymanagementsoftware.exception.CpfInvalidoException;
import com.matheustudisco.librarymanagementsoftware.exception.SenhaInvalidaException;
import com.matheustudisco.librarymanagementsoftware.model.User;
import com.matheustudisco.librarymanagementsoftware.service.UserService;

import java.util.Scanner;

public class LoginControllerCli {
     private Scanner scanner;
     private UserService userService;

    public LoginControllerCli(Scanner scanner, UserService userService) {
        this.scanner = scanner;
        this.userService = userService;
    }

    public User verificarLogin(){
        String cpf = "", senha = "";
        boolean cpfValido = false;

        while(true) {
            System.out.println();
            System.out.print("Digite seu CPF para login: ");
            cpf = scanner.nextLine().trim();
            System.out.println();
            System.out.print("Digite sua senha para login: ");
            senha = scanner.nextLine().trim();

            try {
               User usuarioLogado = userService.autenticar(cpf, senha);
                System.out.println();
                System.out.println("""
                        ==================================
                        Login realizado com sucesso!
                        ==================================
                        """);
               return usuarioLogado;
            } catch (CpfInvalidoException | SenhaInvalidaException e) {
                System.out.println(e.getMessage());
            } catch (RuntimeException erro) {
                System.out.println("""
                        -----------------------------------------------------------------------
                        Erro inesperado! Por favor tente novamente.
                        -----------------------------------------------------------------------""");
                System.out.println(erro.getMessage());
            }
        }
    }
}
