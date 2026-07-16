package com.matheustudisco.librarymanagementsoftware.service;

import com.matheustudisco.librarymanagementsoftware.exception.AutorInvalidoException;
import com.matheustudisco.librarymanagementsoftware.exception.TituloInvalidoException;
import com.matheustudisco.librarymanagementsoftware.model.Book;
import com.matheustudisco.librarymanagementsoftware.repository.BookRepository;

import java.text.AttributedCharacterIterator;

public class BookService {
    private BookRepository bookRepository;

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


    public void registrationBook(Book newBook) {
        bookRepository.saveBook(newBook);
        System.out.println("Sucesso! O livro " + newBook.getTitle() + " foi cadastrado com sucesso");
    }
}
