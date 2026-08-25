package com.matheustudisco.librarymanagementsoftware.controller;

import com.matheustudisco.librarymanagementsoftware.dto.UserAuthenticationDto;
import com.matheustudisco.librarymanagementsoftware.enums.Role;
import com.matheustudisco.librarymanagementsoftware.model.User;
import com.matheustudisco.librarymanagementsoftware.service.UserService;

import java.util.Scanner;

public class MenuControllerCli {
    private Scanner scanner;
    private UserControllerCli userControllerCli;
    private BookControllerCli bookControllerCli;
    private LoginControllerCli loginControllerCli;

    public MenuControllerCli(Scanner scanner, UserControllerCli userControllerCli, BookControllerCli bookControllerCli, LoginControllerCli loginControllerCli) {
        this.scanner = scanner;
        this.userControllerCli = userControllerCli;
        this.bookControllerCli = bookControllerCli;
        this.loginControllerCli = loginControllerCli;
    }

    public void menuInicial() {
        /*
         * Utilizei o Integer.parse para transformar o String que vem do nextLine() em inteiro,
         * porque quando se usa o nextInt gera um buffer e quero evitar isso.
         */
        System.out.println("""
                =====================================
                        BEM VINDO AO LBM
                =====================================""");

        UserAuthenticationDto userLogado = loginControllerCli.verificarLogin();
        if (userLogado.getRole() == Role.CLIENTE) {
            menuCliente(userLogado);
        } else if (userLogado.getRole() == Role.GERENTE || userLogado.getRole() == Role.ADMINISTRADOR) {
            menuGerente(userLogado);
        }
    }
        private void menuCliente (UserAuthenticationDto userLogado){
            boolean selecaoWhile = false;
            System.out.printf("""
                        ===========================================
                        BEM VINDO, %s %s
                        """, userLogado.getName(), userLogado.getLastName());
            while (!selecaoWhile) {
                int escolha;
                System.out.println("""
                        ===========================================
                        Digite 1 para buscar livros.
                        Digite 2 para encerrar.
                        ===========================================
                        """);
                System.out.print("Sua escolha: ");
                try {
                    escolha = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException exception) {
                    System.out.println("""
                            ---------------------------------------------------------
                            Erro! caractere inválido, insira apenas o número desejado
                            ---------------------------------------------------------""");
                    continue;
                }
                switch (escolha) {
                    case 1:
                        System.out.println();
                        bookControllerCli.searchBooks();
                        if (escolhaEncerramento(scanner)) {
                            selecaoWhile = true;
                            mensagemEncerramento();
                        }
                        break;
                    case 2:
                        selecaoWhile = true;
                        mensagemEncerramento();
                        break;
                    default:
                        System.out.println("""
                                --------------------------------------------------------
                                Erro! Digite uma opção válida.
                                --------------------------------------------------------
                                """);
                }
            }
        }
        private void menuGerente (UserAuthenticationDto userLogado){
            boolean selecaoWhile = false;
            System.out.printf("""
                        ===========================================
                        BEM VINDO, %s %s
                        """, userLogado.getName(), userLogado.getLastName());
            while (!selecaoWhile) {
                int escolha;
                System.out.println("""
                        ===========================================
                        Digite 1 para cadastrar usuário.
                        Digite 2 para cadastrar livro.
                        Digite 3 para mostrar usuários cadastrados.
                        Digite 4 para mostrar livros cadastrados.
                        Digite 5 para encerrar.
                        ===========================================""");
                System.out.print("Sua escolha: ");
                try {
                    escolha = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException exception) {
                    System.out.println("""
                            ---------------------------------------------------------
                            Erro! caractere inválido, insira apenas o número desejado
                            ---------------------------------------------------------""");
                    continue;
                }
                switch (escolha) {
                    case 1:
                        userControllerCli.cadastrarUser();
                        break;
                    case 2:
                        bookControllerCli.cadastrarLivro();
                        break;
                    case 3:
                        userControllerCli.searchUsers();
                        if (escolhaEncerramento(scanner)) {
                            selecaoWhile = true;
                            mensagemEncerramento();
                        }
                        break;
                    case 4:
                        bookControllerCli.searchBooks();
                        if (escolhaEncerramento(scanner)) {
                            selecaoWhile = true;
                            mensagemEncerramento();
                        }
                        break;
                    case 5:
                        selecaoWhile = true;
                        mensagemEncerramento();
                        break;
                    default:
                        System.out.println("--------------------------------------------------------");
                        System.out.println("Erro! Digite uma opção válida");
                        System.out.println("--------------------------------------------------------");

                }
            }
        }
        private static boolean escolhaEncerramento (Scanner scanner){
            System.out.println("""
                    --------------------------------------------------------
                    Digite 1 para encerrar
                    Digite 2 para voltar ao menu principal
                    --------------------------------------------------------
                    """);
            System.out.print("Sua escolha: ");
            try {
                byte escolha = Byte.parseByte(scanner.nextLine());
                if (escolha == 1) {
                    System.out.println();
                    return true;
                } else if (escolha == 2) {
                    System.out.println();
                    return false;
                } else {
                    System.out.println("""
                            ---------------------------------------------------------
                            Erro! escolha inexistente, retornando ao menu principal.
                            ---------------------------------------------------------""");
                    return false;
                }
            } catch (NumberFormatException exception) {
                System.out.println("""
                        ---------------------------------------------------------
                        Erro! caractere inválido, retornando ao menu principal.
                        ---------------------------------------------------------""");
                return false;
            }
        }

        private static void mensagemEncerramento () {
            System.out.println();
            System.out.println("""
                    =====================================
                    >>>>>ENCERRANDO O SISTEMA<<<<<
                    =====================================
                    """);
        }
    }

