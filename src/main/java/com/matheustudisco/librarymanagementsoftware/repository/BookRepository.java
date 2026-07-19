package com.matheustudisco.librarymanagementsoftware.repository;

import java.util.ArrayList;
import java.util.List;

import com.matheustudisco.librarymanagementsoftware.model.Book;

public interface BookRepository {
    //Método que salva o livro na lista
    public void saveBook(Book book);

    //Método que mostra os livros cadastrados
    public List<Book> showBook();
}
