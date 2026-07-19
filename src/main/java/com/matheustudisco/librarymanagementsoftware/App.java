package com.matheustudisco.librarymanagementsoftware;

import com.matheustudisco.librarymanagementsoftware.enums.Genre;
import com.matheustudisco.librarymanagementsoftware.exception.*;
import com.matheustudisco.librarymanagementsoftware.model.Book;
import com.matheustudisco.librarymanagementsoftware.model.User;
import com.matheustudisco.librarymanagementsoftware.repository.BookRepository;
import com.matheustudisco.librarymanagementsoftware.repository.BookRepositoryList;
import com.matheustudisco.librarymanagementsoftware.repository.UserRepository;
import com.matheustudisco.librarymanagementsoftware.repository.UserRepositoryList;
import com.matheustudisco.librarymanagementsoftware.service.BookService;
import com.matheustudisco.librarymanagementsoftware.service.UserService;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        //Instanciação das classes services com os repositórios que serão utilizados.
        UserService userService = new UserService(new UserRepositoryList());
        BookService bookService = new BookService(new BookRepositoryList());

        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<Genre> genreList = new ArrayList<>(List.of(Genre.values()));

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

            if (escolha == 1) {
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

                User newUser = new User(name, lastName, cpf, dateOfBirth, cellphone, email);
                userService.registrationService(newUser);

            } else if (escolha == 2) {
                String title = "", author = "", genreString = "", yearString = "", volumeString = "", quantityString = "";
                Genre genre = null;
                short year = 0;
                short volume = 0;
                short quantity = 0;

                System.out.println("---------------------------------------------------------");
                System.out.println("                   CADASTRO DE LIVRO");
                System.out.println("---------------------------------------------------------");

                boolean titleBoolean = false;
                while (!titleBoolean) {
                    try {
                        System.out.print("Digite o título: ");
                        title = scanner.nextLine().trim();
                        titleBoolean = bookService.validarTitulo(title);
                    } catch (TituloInvalidoException e) {
                        System.out.println(e.getMessage());
                    } catch (RuntimeException e) {
                        System.out.println("""
                                -----------------------------------------------------------------------
                                Erro inesperado! Por favor tente novamente.
                                -----------------------------------------------------------------------""");
                    }
                }
                boolean authorBoolean = false;
                while (!authorBoolean) {
                    try {
                        System.out.print("Digite o autor: ");
                        author = scanner.nextLine().trim();
                        authorBoolean = bookService.validarAutor(author);
                    } catch (AutorInvalidoException e) {
                        System.out.println(e.getMessage());
                    } catch (RuntimeException e) {
                        System.out.println("""
                                -----------------------------------------------------------------------
                                Erro inesperado! Por favor tente novamente.
                                -----------------------------------------------------------------------""");
                    }
                }
                for (int i = 0; i <= 50; i++) {
                    System.out.println();
                }
                boolean genreBoolean = false;
                while (!genreBoolean) {
                    try {
                        System.out.println("Escolha um gênero da lista abaixo:");
                        for (int i = 0; i < genreList.size(); i++) {
                            System.out.println(i + 1 + " - " + genreList.get(i).getDescricao());
                        }
                        System.out.print("Escolha de 1 até " + genreList.size() + ": ");
                        genreString = scanner.nextLine().trim();
                        byte escolhaGenre = bookService.validarGenero(genreString, genreList.size());
                        System.out.println("""
                                -------------------------------------------------
                                Gênero escolhido: %s.
                                -------------------------------------------------
                                """.formatted(genreList.get(escolhaGenre - 1).getDescricao()));
                        genre = genreList.get(escolhaGenre - 1);
                        genreBoolean = true;
                    } catch (GeneroInvalidoException e) {
                        System.out.println(e.getMessage());
                    } catch (RuntimeException e) {
                        System.out.println("""
                                -----------------------------------------------------------------------
                                Erro inesperado! Por favor tente novamente.
                                -----------------------------------------------------------------------""");
                    }
                }
                boolean yearBoolean = false;
                while (!yearBoolean) {
                    try {
                        System.out.print("Digite o ano do livro: ");
                        yearString = scanner.nextLine().trim();
                        year = bookService.validarAno(yearString);
                        yearBoolean = true;
                    } catch (AnoInvalidoException e) {
                        System.out.println(e.getMessage());
                    } catch (RuntimeException e) {
                        System.out.println("""
                                -----------------------------------------------------------------------
                                Erro inesperado! Por favor tente novamente.
                                -----------------------------------------------------------------------""");
                    }
                }
                boolean volumeBoolean = false;
                while (!volumeBoolean) {
                    try {
                        System.out.print("Digite o volume do livro: ");
                        volumeString = scanner.nextLine().trim();
                        volume = bookService.validarVolume(volumeString);
                        volumeBoolean = true;
                    } catch (VolumeInvalidoException e) {
                        System.out.println(e.getMessage());
                    } catch (RuntimeException e) {
                        System.out.println("""
                                -----------------------------------------------------------------------
                                Erro inesperado! Por favor tente novamente.
                                -----------------------------------------------------------------------""");
                    }
                }
                boolean quantityBoolean = false;
                while (!quantityBoolean) {
                    try {
                        System.out.print("Digite a quantidade do livro em estoque: ");
                        quantityString = scanner.nextLine().trim();
                        quantity = bookService.validarQuantidade(quantityString);
                        quantityBoolean = true;
                    } catch (QuantidadeInvalidaException e) {
                        System.out.println(e.getMessage());
                    } catch (RuntimeException e) {
                        System.out.println("""
                                -----------------------------------------------------------------------
                                Erro inesperado! Por favor tente novamente.
                                -----------------------------------------------------------------------""");
                    }
                }
                Book newBook = new Book(title, author, genre, year, volume, quantity);
                bookService.registrationBook(newBook);

            } else if (escolha == 3) {
                //For para "limpar a tela", técnica do pulo de linhas
                for (int i = 0; i <= 50; i++) {
                    System.out.println();
                }

                System.out.println(userService.showService());

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
                System.out.println(bookService.showBook());

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

