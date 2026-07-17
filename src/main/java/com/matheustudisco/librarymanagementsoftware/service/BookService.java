package com.matheustudisco.librarymanagementsoftware.service;

import com.matheustudisco.librarymanagementsoftware.exception.*;
import com.matheustudisco.librarymanagementsoftware.model.Book;
import com.matheustudisco.librarymanagementsoftware.repository.BookRepository;

import java.text.AttributedCharacterIterator;

public class BookService {
    private final BookRepository bookRepository;

    //Construtor para passar como argumento o repositoy na instanciação.
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public boolean validarTitulo(String title) {
        if ((title.isEmpty())) {
            throw new TituloInvalidoException("Erro! O campo titulo não pode estar vázio.");
        } else if (title.contains("  ")) {
            throw new TituloInvalidoException("Erro! O campo titulo não pode ter mais de um espaço seguido.");
        } else {
            return true;
        }
    }

    public boolean validarAutor(String author) {
        if (author.isEmpty()) {
            throw new AutorInvalidoException("""
                    -----------------------------------------------------------------------
                    Erro! O campo Autor não pode estar vazio.
                    -----------------------------------------------------------------------""");
        } else if (!author.matches("^[A-Za-z]+(\\s[A-Za-z]+)*$")) {
            throw new AutorInvalidoException("""
                    -----------------------------------------------------------------------
                    Erro! O campo autor somente deve conter letras e espaço.
                    -----------------------------------------------------------------------""");
        } else {
            return true;
        }
    }

    public boolean validarAno(String anoString) {
        if (anoString.isEmpty()) {
            throw new AnoInvalidoException("""
                    -----------------------------------------------------------------------
                    Erro! O campo Ano não pode estar vazio.
                    -----------------------------------------------------------------------""");
        } else {
            try {
                Short.parseShort(anoString);
                return true;
            } catch (NumberFormatException e) {
                throw new AnoInvalidoException("""
                        -----------------------------------------------------------------------
                        Erro! O campo Ano permite somente números.
                        -----------------------------------------------------------------------""");
            }
        }
    }

    public boolean validarVolume(String volumeString) {
        if (volumeString.isEmpty()) {
            throw new VolumeInvalidoException("""
                    -----------------------------------------------------------------------
                    Erro! O campo Volume não pode estar vazio.
                    -----------------------------------------------------------------------""");
        } else if (volumeString.equals("0")) {
            throw new VolumeInvalidoException("""
                    -----------------------------------------------------------------------
                    Erro! O campo Volume não pode ser 0.
                    -----------------------------------------------------------------------""");
        } else {
            try {
                Short.parseShort(volumeString);
                return true;
            } catch (NumberFormatException e) {
                throw new VolumeInvalidoException("""
                        -----------------------------------------------------------------------
                        Erro! O Volume permite somente números.
                        -----------------------------------------------------------------------""");
            }
        }
    }

    public short validarQuantidade(String quantityString) {
        short quantity = 0;
        if (quantityString.isEmpty()) {
            throw new QuantidadeInvalidaException("""
                    -----------------------------------------------------------------------
                    Erro! O campo Quantidade não pode estar vazio.
                    -----------------------------------------------------------------------
                    """);
        } else {
            try {
                quantity = Short.parseShort(quantityString);
                if (quantity < 0) {
                    throw new QuantidadeInvalidaException("""
                            -----------------------------------------------------------------------
                            Erro! O campo Quantidade não pode ser negativo.
                            -----------------------------------------------------------------------
                            """);
                }
                return quantity;
            } catch (NumberFormatException e) {
                throw new QuantidadeInvalidaException("""
                        -----------------------------------------------------------------------
                        Erro! O campo Quantidade permite somente números.
                        -----------------------------------------------------------------------
                        """);
            }
        }
    }

    public void registrationBook(Book newBook) {
        bookRepository.saveBook(newBook);
        System.out.println("Sucesso! O livro " + newBook.getTitle() + " foi cadastrado com sucesso");
    }
}
