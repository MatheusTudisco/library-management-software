package com.matheustudisco.librarymanagementsoftware.service;

import com.matheustudisco.librarymanagementsoftware.model.Book;
import com.matheustudisco.librarymanagementsoftware.repository.BookRepository;

import java.text.AttributedCharacterIterator;

public class BookService {
    private BookRepository bookRepository;

    //Construtor para passar como argumento o repositoy na instanciação.
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void registrationBook(Book newBook) {
        if (newBook.getTitle() == null) {
            System.out.println("Erro! O título do livro não pode estar vazio.");
            return;
        } else if (newBook.getAuthor() == null) {
            System.out.println("Erro! O autor do livro não pode estar vazio.");
            return;
        } else if (newBook.getGenre() == null) {
            System.out.println("Erro! O gênero do livro não pode estar vazio.");
            return;
        } else if (newBook.getYear() == 0) {
            System.out.println("Erro! O ano do livro não pode estar vazio.");
            return;
        } else if (newBook.getVolume() == 0) {
            System.out.println("Erro! O volume do livro não pode estar vazio.");
            return;
        } else if (newBook.getQuantity() == 0) {
            System.out.println("Erro! O livro selecionado está sem estoque no momento.");
            System.out.println("Não será possível realizar o empréstimo");
            return;
        } else {
            bookRepository.saveBook(newBook);
            System.out.println("Sucesso! O livro " + newBook.getTitle() + " foi cadastrado com sucesso");
        }
    }
}
