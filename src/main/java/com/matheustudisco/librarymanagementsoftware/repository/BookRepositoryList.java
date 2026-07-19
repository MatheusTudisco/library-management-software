package com.matheustudisco.librarymanagementsoftware.repository;

import com.matheustudisco.librarymanagementsoftware.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookRepositoryList implements BookRepository{
    //Instanciação da lista para armazenar e mostrar os livros cadastrados.
    private final List<Book> bookList = new ArrayList<>();

    //Método que salva o livro na lista
    @Override
    public void saveBook(Book book) {
        bookList.add(book);
    }

    //Método que mostra os livros cadastrados
    @Override
    public List<Book> showBook() {
        return bookList;
    }
}
