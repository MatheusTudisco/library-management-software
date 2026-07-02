package com.matheustudisco.librarymanagementsoftware.repository;

import java.util.ArrayList;
import java.util.List;

import com.matheustudisco.librarymanagementsoftware.model.Book;

public class BookRepository {
    //Instanciação da lista para armazenar e mostrar os livros cadastrados.
    private List<Book> bookList = new ArrayList<>();

    //Método que salva o livro na lista
    public void saveBook(Book book) {
        bookList.add(book);
    }

    //Método que mostra os livros cadastrados
    public List<Book> showBook() {
        return bookList;
    }


}
