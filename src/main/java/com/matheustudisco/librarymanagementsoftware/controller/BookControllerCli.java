package com.matheustudisco.librarymanagementsoftware.controller;

import com.matheustudisco.librarymanagementsoftware.exception.AnoInvalidoException;
import com.matheustudisco.librarymanagementsoftware.exception.AutorInvalidoException;
import com.matheustudisco.librarymanagementsoftware.exception.GeneroInvalidoException;
import com.matheustudisco.librarymanagementsoftware.exception.QuantidadeInvalidaException;
import com.matheustudisco.librarymanagementsoftware.exception.TituloInvalidoException;
import com.matheustudisco.librarymanagementsoftware.exception.VolumeInvalidoException;
import com.matheustudisco.librarymanagementsoftware.model.Genre;
import com.matheustudisco.librarymanagementsoftware.service.BookService;
import com.matheustudisco.librarymanagementsoftware.service.GenreService;

import java.util.List;
import java.util.Scanner;

public class BookControllerCli {
    private Scanner scanner;
    private BookService bookService;
    private GenreService genreService;

    public BookControllerCli(Scanner scanner, BookService bookService, GenreService genreService) {
        this.scanner = scanner;
        this.bookService = bookService;
        this.genreService = genreService;
    }

    public void cadastrarLivro() {

        try {
            List<Genre> genreList = genreService.showGenres();
            String title = "", author = "", genreString = "", yearString = "", volumeString = "", quantityString = "";
            Long genre = 0L;
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

            boolean genreBoolean = false;
            while (!genreBoolean) {
                try {
                    System.out.println();
                    System.out.println("-----Lista de Gêneros-----");
                    for (Genre generos : genreList) {
                        System.out.println(generos);
                    }
                    System.out.print("Escolha um número do gênero da lista acima: ");
                    genreString = scanner.nextLine().trim();
                    genre = genreService.validarGenero(genreString, genreList);
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
            bookService.registrationBook(title, author, genre, year, volume, quantity);
        } catch (GeneroInvalidoException e) {
            System.out.println(e.getMessage());
        }
    }
}
