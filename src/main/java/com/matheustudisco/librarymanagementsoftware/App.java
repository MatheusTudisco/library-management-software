package com.matheustudisco.librarymanagementsoftware;

import com.matheustudisco.librarymanagementsoftware.model.Book;
import com.matheustudisco.librarymanagementsoftware.model.User;
import com.matheustudisco.librarymanagementsoftware.repository.BookRepository;
import com.matheustudisco.librarymanagementsoftware.repository.UserRepository;
import com.matheustudisco.librarymanagementsoftware.service.BookService;
import com.matheustudisco.librarymanagementsoftware.service.UserService;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);

        BookRepository bookRepository = new BookRepository();
        BookService bookService = new BookService(bookRepository);

        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<User> user = new ArrayList<>();
        List<Book> book = new ArrayList<>();

        boolean selecaoWhile = false;

        while (!selecaoWhile) {
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
                String name = "", lastName = "", cpf = "", cellphone = "", email = "";
                LocalDate dateOfBirth = null;

                //Loop para não permitir o preenchimento vazio.
                while (name.isEmpty()) {
                    System.out.print("Digite o primeiro nome: ");
                    name = scanner.nextLine();
                    if (name.isEmpty()) {
                        System.out.println("Erro! O campo nome não pode estar vazio.");
                    }
                }
                while (lastName.isEmpty()) {
                    System.out.print("Digite o ultimo nome: ");
                    lastName = scanner.nextLine();
                    if (lastName.isEmpty()){
                        System.out.println("Erro! O campo último nome não pode estar vazio.");
                    }
                }
                while (cpf.isEmpty()) {
                    System.out.print("Digite o cpf: ");
                    cpf = scanner.nextLine();
                    if (cpf.isEmpty()) {
                        System.out.println("Erro! O campo CPF não pode estar vazio.");
                    }
                }
                /*
                * Para validar a verificação de um campo vazio na digitação, primeiro
                * inicio a variável dateOfBirth como "null", após entrar no while ele pede
                * ao usuário que digite a data, salva em uma variável String para poder
                * validar se o usuário deixe vazia, caso se confirme ele exibe a mensagem de erro
                * na tela e o "continue" força a retornar para o teste de condição do loop.
                * Caso o usuário faça a digitação, ele passa para a variável LocalDate
                * a variável String com o parse e faz a verificação com o DateTimeParseException se
                * o formato escrito está correto, ou se o usuário digitou algum caractere inválido.
                * Caso tudo estiver correto ele pula para o próximo item do cadastro.
                 */
                while (dateOfBirth == null){
                    System.out.print("Digite a data de nascimento (DD/MM/AAAA): ");
                    String dateOfBirthString = scanner.nextLine();
                    if (dateOfBirthString.isEmpty()){
                        System.out.println("Erro! O campo Data de nascimento não pode estar vazio.");
                        continue;
                    }
                    try{
                        dateOfBirth = LocalDate.parse(dateOfBirthString, formatter);
                    }catch (DateTimeException e){
                        System.out.println("Erro! Formato inválido ou data inexistente");
                    }
                }
                while (cellphone.isEmpty()) {
                    System.out.print("Digite o numero de celular: ");
                    cellphone = scanner.nextLine();
                    if (cellphone.isEmpty()) {
                        System.out.println("Erro! O campo celular não pode estar vazio.");
                    }
                }
                while (email.isEmpty()){
                    System.out.print("Digite o email: ");
                    email = scanner.nextLine();
                    if (email.isEmpty()){
                        System.out.println("Erro! O campo email não pode estar vazio.");
                    }
                }

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

            Book newBook = new Book(title, author, genre, year, volume, quantity);
            bookService.registrationBook(newBook);

        } else if (escolha == 3) {
            //For para "limpar a tela", técnica do pulo de linhas
            for (int i = 0; i <= 50; i++) {
                System.out.println();
            }

            System.out.println(userRepository.showUser());

            System.out.println("Digite 1 para encerrar");
            System.out.println("Digite 2 para voltar ao menu principal");
            byte escolhaUser = Byte.parseByte(scanner.nextLine());

            if (escolhaUser == 1) {
                selecaoWhile = true;
                System.out.println("=====================================");
                System.out.println(">>>>>ENCERRANDO O SISTEMA<<<<<");
                System.out.println("=====================================");
            } else if (escolhaUser == 2){
                continue;
            } else {
                System.out.println("Digite uma opção válida");
            }

        } else if (escolha == 4) {
            //For para "limpar a tela", técnica do pulo de linhas
            for (int i = 0; i <= 50; i++) {
                System.out.println();
            }
            System.out.println(bookRepository.showBook());

            System.out.println("Digite 1 para encerrar");
            System.out.println("Digite 2 para voltar ao menu principal");
            byte escolhaBook = Byte.parseByte(scanner.nextLine());

            if (escolhaBook == 1) {
                selecaoWhile = true;
                System.out.println("=====================================");
                System.out.println(">>>>>ENCERRANDO O SISTEMA<<<<<");
                System.out.println("=====================================");
            } else if (escolhaBook == 2){
                continue;
            } else {
                System.out.println("Digite uma opção válida");
            }

        } else {
            selecaoWhile = true;

            //For para "limpar a tela", técnica do pulo de linhas
            for (int i = 0; i <= 50; i++) {
                System.out.println();
            }

            System.out.println("=====================================");
            System.out.println(">>>>>ENCERRANDO O SISTEMA<<<<<");
            System.out.println("=====================================");
        }
    }


}
}
