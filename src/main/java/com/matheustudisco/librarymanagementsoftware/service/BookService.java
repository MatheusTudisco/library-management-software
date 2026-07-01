package com.matheustudisco.librarymanagementsoftware.service;

import com.matheustudisco.librarymanagementsoftware.model.Book;
import com.matheustudisco.librarymanagementsoftware.repository.BookRepository;

public class BookService {
    private BookRepository bookRepository;

    //Construtor para passar como argumento o repositoy na instanciação.
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void registrationBook(Book newBook){

    }
}
