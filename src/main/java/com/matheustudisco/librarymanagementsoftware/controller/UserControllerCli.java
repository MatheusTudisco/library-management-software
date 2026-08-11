package com.matheustudisco.librarymanagementsoftware.controller;

import com.matheustudisco.librarymanagementsoftware.exception.CelularInvalidoException;
import com.matheustudisco.librarymanagementsoftware.exception.CpfInvalidoException;
import com.matheustudisco.librarymanagementsoftware.exception.DataNascInvalidoException;
import com.matheustudisco.librarymanagementsoftware.exception.EmailInvalidoException;
import com.matheustudisco.librarymanagementsoftware.exception.NomeInvalidoException;
import com.matheustudisco.librarymanagementsoftware.service.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class UserControllerCli {
    private Scanner scanner;
    private UserService userService;
    private DateTimeFormatter formatter;

    public UserControllerCli(Scanner scanner, UserService userService, DateTimeFormatter formatter) {
        this.scanner = scanner;
        this.userService = userService;
        this.formatter = formatter;
    }

    public void cadastrarUser() {
        String name = "", lastName = "", cpf = "", cellphone = "", email = "";
        LocalDate dateOfBirth = null;

        System.out.println("""
                ---------------------------------------------------------
                                   CADASTRO DE USUÁRIO
                ---------------------------------------------------------""");

        boolean nameBoolean = false;
        while (!nameBoolean) {
            try {
                System.out.print("Digite o nome: ");
                name = scanner.nextLine().trim();
                nameBoolean = userService.validarNome(name);
            } catch (NomeInvalidoException e) {
                System.out.println(e.getMessage());
            } catch (RuntimeException e) {
                System.out.println("""
                        -----------------------------------------------------------------------
                        Erro inesperado! Por favor tente novamente.
                        -----------------------------------------------------------------------""");
            }
        }
        boolean lastnameBoolean = false;
        while (!lastnameBoolean) {
            try {
                System.out.print("Digite o ultimo nome: ");
                lastName = scanner.nextLine().trim();
                lastnameBoolean = userService.validarSobrenome(lastName);
            } catch (NomeInvalidoException e) {
                System.out.println(e.getMessage());
            } catch (RuntimeException e) {
                System.out.println("""
                        -----------------------------------------------------------------------
                        Erro inesperado! Por favor tente novamente.
                        -----------------------------------------------------------------------""");
            }
        }
        /* Criação de uma variável booleana para capturar o retorno
         * do método validarCPF. Se o método passar por todas as regras sem erros
         * o método retorna true e atribui isso ao cpfBoolean gerando uma interrupção do loop,
         * mas caso a regra lance uma exceção, o fluxo é desviado para o catch, mantendo a
         * variável como false e forçando o loop a continuar até que a digitação esteja correta.
         */
        boolean cpfBoolean = false;
        while (!cpfBoolean) {
            try {
                System.out.print("Digite o cpf: ");
                cpf = scanner.nextLine();
                cpfBoolean = userService.validarCPF(cpf);
            } catch (CpfInvalidoException e) {
                System.out.println(e.getMessage());
            } catch (RuntimeException e) {
                System.out.println("""
                        -----------------------------------------------------------------------
                        Erro inesperado! Por favor tente novamente.
                        -----------------------------------------------------------------------""");
            }
        }
        boolean dateBirthBoolean = false;
        while (!dateBirthBoolean) { //OU while (dateBirtBoolean == false)
            try {
                System.out.print("Digite a data de nascimento (DD/MM/AAAA): ");
                String dateOfBirthString = scanner.nextLine();
                dateBirthBoolean = userService.validarDataNasc(dateOfBirthString, formatter);
                dateOfBirth = LocalDate.parse(dateOfBirthString, formatter);
            } catch (DataNascInvalidoException e) {
                System.out.println(e.getMessage());
            } catch (RuntimeException e) {
                System.out.println("""
                        -----------------------------------------------------------------------
                        Erro inesperado! Por favor tente novamente.
                        -----------------------------------------------------------------------""");
            }
        }
        boolean cellphoneBoolean = false;
        while (!cellphoneBoolean) {
            try {
                System.out.print("Digite o número de celular com prefixo: ");
                cellphone = scanner.nextLine();
                cellphoneBoolean = userService.validarCelular(cellphone);
            } catch (CelularInvalidoException e) {
                System.out.println(e.getMessage());
            } catch (RuntimeException e) {
                System.out.println("""
                        -----------------------------------------------------------------------
                        Erro inesperado! Por favor tente novamente.
                        -----------------------------------------------------------------------""");
            }
        }
        boolean emailBoolean = false;
        while (!emailBoolean) {
            try {
                System.out.print("Digite o email: ");
                email = scanner.nextLine();
                emailBoolean = userService.validarEmail(email);
            } catch (EmailInvalidoException e) {
                System.out.println(e.getMessage());
            } catch (RuntimeException e) {
                System.out.println("""
                        -----------------------------------------------------------------------
                        Erro inesperado! Por favor tente novamente.
                        -----------------------------------------------------------------------""");
            }
        }
        userService.registrationUser(name, lastName, cpf, dateOfBirth, cellphone, email);
    }
}
