package com.matheustudisco.librarymanagementsoftware;

import com.matheustudisco.librarymanagementsoftware.model.Book;
import com.matheustudisco.librarymanagementsoftware.model.User;
import com.matheustudisco.librarymanagementsoftware.service.UserService;
import com.matheustudisco.repository.UserRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main( String[] args )
    {
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);

        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<User> user = new ArrayList<>();
        List<Book> book = new ArrayList<>();

        boolean selecaoWhile = false;

        while (selecaoWhile == false){
            System.out.println("=====================================");
            System.out.println("BEM VINDO AO LBM");
            System.out.println("Digite 1 para cadastrar usuario");
            System.out.println("Digite 2 para cadastrar livro");
            System.out.println("Digite 3 para mostrar usuarios cadastrados");
            System.out.println("Digite 4 para mostrar livros cadastrados");
            System.out.println("Digite 5 para encerrar");
            System.out.println("=====================================");

            /*
                Utilizei o Integer.parse para transformar o String que vem do nextLine() em inteiro,
                porque quando se usa o nextInt gera um buffer e quero evitar isso.
             */
            int escolha = Integer.parseInt(scanner.nextLine());

            if (escolha == 1) {
                System.out.print("Digite o primeiro nome: ");
                String name = scanner.nextLine();
                System.out.print("Digite o ultimo nome: ");
                String lastName = scanner.nextLine();
                System.out.print("Digite o cpf: ");
                String cpf = scanner.nextLine();
                System.out.print("Digite a data de nascimento: ");
                LocalDate dateOfBirth = LocalDate.parse(scanner.nextLine(), formatter);
                System.out.print("Digite o numero de celular: ");
                String cellphone = scanner.nextLine();
                System.out.print("Digite o email: ");
                String email = scanner.nextLine();

                User newUser = new User(name, lastName, cpf, dateOfBirth, cellphone, email);
                userService.registrationService(newUser);

            } else if (escolha == 2) {
                System.out.print("Digite o nome do livro: ");
                String title = scanner.nextLine();
                System.out.print("Digite o autor: ");
                String author = scanner.nextLine();
                System.out.print("Digite o genero do livro: ");
                String genre = scanner.nextLine();
                System.out.print("Digite o ano do livro: ");
                int year = Integer.parseInt(scanner.nextLine());
                System.out.print("Digite o volume do livro: ");
                byte volume = Byte.parseByte(scanner.nextLine());
                System.out.print("Digite a quantidade disponivel: ");
                short quantity = Short.parseShort(scanner.nextLine());

                book.add(new Book(title, author, genre, year, volume, quantity));

            } else if (escolha == 3){
                //For para "limpar a tela", técnica do pulo de linhas
                for (int i=0; i<=50; i++) {
                    System.out.println();
                }

                for (User users : user){
                    System.out.println(users);
                }

                System.out.println("Digite 1 para encerrar");
                System.out.println("Digite 2 para voltar ao menu principal");
                byte escolhaUser = Byte.parseByte(scanner.nextLine());

                if (escolhaUser == 1) {
                    selecaoWhile = true;
                    System.out.println("=====================================");
                    System.out.println(">>>>>ENCERRANDO O SISTEMA<<<<<");
                    System.out.println("=====================================");
                }

            } else if (escolha == 4) {
                //For para "limpar a tela", técnica do pulo de linhas
                for (int i=0; i<=50; i++) {
                    System.out.println();
                }

                for (Book books : book) {
                    System.out.println(books);
                }

                System.out.println("Digite 1 para encerrar");
                System.out.println("Digite 2 para voltar ao menu principal");
                byte escolhaBook = Byte.parseByte(scanner.nextLine());

                if (escolhaBook == 1) {
                    selecaoWhile = true;
                    System.out.println("=====================================");
                    System.out.println(">>>>>ENCERRANDO O SISTEMA<<<<<");
                    System.out.println("=====================================");
                }

            } else {
                selecaoWhile = true;

                //For para "limpar a tela", técnica do pulo de linhas
                for (int i=0; i<=50; i++) {
                    System.out.println();
                }

                System.out.println("=====================================");
                System.out.println(">>>>>ENCERRANDO O SISTEMA<<<<<");
                System.out.println("=====================================");
            }
        }


    }
}
