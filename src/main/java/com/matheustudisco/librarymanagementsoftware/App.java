package com.matheustudisco.librarymanagementsoftware;

import com.matheustudisco.librarymanagementsoftware.controller.BookControllerCli;
import com.matheustudisco.librarymanagementsoftware.controller.UserControllerCli;
import com.matheustudisco.librarymanagementsoftware.model.Book;
import com.matheustudisco.librarymanagementsoftware.model.User;
import com.matheustudisco.librarymanagementsoftware.repository.BookRepositoryList;
import com.matheustudisco.librarymanagementsoftware.repository.GenreRepositoryPostgre;
import com.matheustudisco.librarymanagementsoftware.repository.UserRepositoryPostgre;
import com.matheustudisco.librarymanagementsoftware.service.BookService;
import com.matheustudisco.librarymanagementsoftware.service.GenreService;
import com.matheustudisco.librarymanagementsoftware.service.UserService;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        //Instanciação das classes services com os repositórios que serão utilizados.
        UserService userService = new UserService(new UserRepositoryPostgre());
        BookService bookService = new BookService(new BookRepositoryList());
        GenreService genreService = new GenreService(new GenreRepositoryPostgre());
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var bookControllerCli = new BookControllerCli(scanner, bookService, genreService);
        UserControllerCli userControllerCli = new UserControllerCli(scanner, userService, formatter);

        boolean selecaoWhile = false;
        System.out.println("""
                =====================================
                        BEM VINDO AO LBM""");

        while (!selecaoWhile) {
            int escolha;
            System.out.println("""
                    =====================================
                    Digite 1 para cadastrar usuário.
                    Digite 2 para cadastrar livro.
                    Digite 3 para mostrar usuários cadastrados.
                    Digite 4 para mostrar livros cadastrados.
                    Digite 5 para encerrar.
                    =====================================
                    """);
            System.out.print("Sua escolha: ");

            /*
                Utilizei o Integer.parse para transformar o String que vem do nextLine() em inteiro,
                porque quando se usa o nextInt gera um buffer e quero evitar isso.
             */
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
                    if (escolhaEncerramento(scanner)){
                        selecaoWhile = true;
                        mensagemEncerramento();
                    }
                    break;
                case 4:
                    for (Book bookList : bookService.showBook()) {
                        System.out.println(bookList);
                    }
                    if (escolhaEncerramento(scanner)){
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

    private static boolean escolhaEncerramento(Scanner scanner) {
        System.out.println("Digite 1 para encerrar");
        System.out.println("Digite 2 para voltar ao menu principal");
        try {
            byte escolha = Byte.parseByte(scanner.nextLine());
            if (escolha == 1){
                return true;
            } else if(escolha == 2){
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

    private static void mensagemEncerramento() {
        System.out.println("""
                =====================================
                >>>>>ENCERRANDO O SISTEMA<<<<<
                =====================================
                """);
    }
}

