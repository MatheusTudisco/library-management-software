package com.matheustudisco.librarymanagementsoftware;

import com.matheustudisco.librarymanagementsoftware.exception.CpfInvalidoException;
import com.matheustudisco.librarymanagementsoftware.exception.DataNascInvalidoException;
import com.matheustudisco.librarymanagementsoftware.exception.NomeInvalidoException;
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
        System.out.println("=====================================");
        System.out.println("BEM VINDO AO LBM");

        while (!selecaoWhile) {
            int escolha;
            System.out.println("=====================================");
            System.out.println("Digite 1 para cadastrar usuário");
            System.out.println("Digite 2 para cadastrar livro");
            System.out.println("Digite 3 para mostrar usuários cadastrados");
            System.out.println("Digite 4 para mostrar livros cadastrados");
            System.out.println("Digite 5 para encerrar");
            System.out.println("=====================================");
            System.out.println();
            System.out.print("Sua escolha: ");

            /*
                Utilizei o Integer.parse para transformar o String que vem do nextLine() em inteiro,
                porque quando se usa o nextInt gera um buffer e quero evitar isso.
             */
            try {
                escolha = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException exception) {
                System.out.println("---------------------------------------------------------");
                System.out.println("Erro! caractere inválido, insira apenas o número desejado");
                System.out.println("---------------------------------------------------------");
                continue;
            }

            if (escolha == 1) {
                String name = "", lastName = "", cpf = "", cellphone = "", email = "";
                LocalDate dateOfBirth = null;

                System.out.println("---------------------------------------------------------");
                System.out.println("                   CADASTRO DE USUÁRIO");
                System.out.println("---------------------------------------------------------");

                boolean nameBoolean = false;
                while (!nameBoolean) {
                    try {
                        System.out.print("Digite o nome: ");
                        name = scanner.nextLine().trim();
                        nameBoolean = userService.validarNome(name);
                    } catch (NomeInvalidoException e) {
                        System.out.println(e.getMessage());
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
                    }
                }
                boolean dateBirthBoolean = false;
                while (!dateBirthBoolean) { //OU while (dateBirtBoolean = false)
                    try {
                        System.out.print("Digite a data de nascimento (DD/MM/AAAA): ");
                        String dateOfBirthString = scanner.nextLine();
                        dateBirthBoolean = userService.validarDataNasc(dateOfBirthString);
                        dateOfBirth = LocalDate.parse(dateOfBirthString, formatter);
                    } catch (DataNascInvalidoException e) {
                        System.out.println(e.getMessage());
                        dateBirthBoolean = false;
                    } catch (DateTimeException e) {
                        System.out.println("Erro! Formato inválido ou data inexistente");
                        dateBirthBoolean = false;
                    }
                }
                while (cellphone.isEmpty()) {
                    System.out.print("Digite o numero de celular: ");
                    cellphone = scanner.nextLine();
                    if (cellphone.isEmpty()) {
                        System.out.println("Erro! O campo celular não pode estar vazio.");
                    }
                }
                while (email.isEmpty()) {
                    System.out.print("Digite o email: ");
                    email = scanner.nextLine();
                    if (email.isEmpty()) {
                        System.out.println("Erro! O campo email não pode estar vazio.");
                    }
                }

                User newUser = new User(name, lastName, cpf, dateOfBirth, cellphone, email);
                userService.registrationService(newUser);

            } else if (escolha == 2) {
                String title = "", author = "", genre = "", yearString = "", volumeString = "", quantityString = "";
                int year = 0;
                byte volume = 0;
                short quantity = 0;

                System.out.println("---------------------------------------------------------");
                System.out.println("                   CADASTRO DE LIVRO");
                System.out.println("---------------------------------------------------------");

                while (title.isEmpty()) {
                    System.out.print("Digite o nome do livro: ");
                    title = scanner.nextLine();
                    if (title.isEmpty()) {
                        System.out.println("Erro! O campo titulo não pode estar vazio.");
                    }
                }
                while (author.isEmpty()) {
                    System.out.print("Digite o autor: ");
                    author = scanner.nextLine();
                    if (author.isEmpty()) {
                        System.out.println("Erro! O campo autor não pode estar vazio.");
                    }
                }
                while (genre.isEmpty()) {
                    System.out.print("Digite o genero do livro: ");
                    genre = scanner.nextLine();
                    if (genre.isEmpty()) {
                        System.out.println("Erro! O campo genêro não pode estar vazio.");
                    }
                }
                while (yearString.isEmpty()) {
                    System.out.print("Digite o ano do livro: ");
                    yearString = scanner.nextLine();
                    if (yearString.isEmpty()) {
                        System.out.println("Erro! O campo ano não pode estar vazio.");
                    } else {
                        year = Integer.parseInt(yearString);
                    }
                }
                while (volumeString.isEmpty()) {
                    System.out.print("Digite o volume do livro: ");
                    volumeString = scanner.nextLine();
                    if (volumeString.isEmpty()) {
                        System.out.println("Erro! O campo volume não pode estar vazio.");
                    } else {
                        volume = Byte.parseByte(volumeString);
                    }
                }
                while (quantityString.isEmpty()) {
                    System.out.print("Digite a quantidade disponivel: ");
                    quantityString = scanner.nextLine();
                    if (quantityString.isEmpty()) {
                        System.out.println("Erro! O campo quantidade não pode estar vazio.");
                    } else {
                        ;
                        quantity = Short.parseShort(quantityString);
                    }
                }
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
                } else if (escolhaUser == 2) {
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
                } else if (escolhaBook == 2) {
                    continue;
                } else {
                    System.out.println("Digite uma opção válida");
                }

            } else if (escolha == 5) {
                selecaoWhile = true;

                //For para "limpar a tela", técnica do pulo de linhas
                for (int i = 0; i <= 50; i++) {
                    System.out.println();
                }

                System.out.println("=====================================");
                System.out.println(">>>>>ENCERRANDO O SISTEMA<<<<<");
                System.out.println("=====================================");
            } else {
                System.out.println("--------------------------------------------------------");
                System.out.println("Erro! Digite uma opção válida");
                System.out.println("--------------------------------------------------------");
            }
        }


    }
}
