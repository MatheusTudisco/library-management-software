package com.matheustudisco.librarymanagementsoftware.repository;

import com.matheustudisco.librarymanagementsoftware.model.Book;

import javax.xml.catalog.Catalog;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryPostgre implements BookRepository {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USUARIO = "postgres";
    private static final String SENHA = System.getenv("DB_PASSWORD");

    public void saveBook(Book book) {
        String sql = "INSERT INTO books (title, author, genre_id, year, volume, quantity) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setLong(3, book.getGenre());
            statement.setShort(4, book.getYear());
            statement.setShort(5, book.getVolume());
            statement.setShort(6, book.getQuantity());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro! Não possível cadastrar o livro no banco de dados." + e.getMessage());
        }
    }

    public List<Book> showBook() {
        List<Book> bookList = new ArrayList<>();
        String sql = """
                SELECT books.id, books.title, books.author, 
                genres.name AS genre_name, 
                books.year, books.volume, books.quantity 
                FROM books 
                JOIN genres ON books.genre_id = genres.id
                ORDER BY books.id ASC
                """;
        try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement statement = conexao.prepareStatement(sql);
             ResultSet resultado = statement.executeQuery()) {
            while (resultado.next()) {
                Book newBook = new Book(
                        resultado.getLong("id"),
                        resultado.getString("title"),
                        resultado.getString("author"),
                        resultado.getString("genre_name"),
                        resultado.getShort("year"),
                        resultado.getShort("volume"),
                        resultado.getShort("quantity")
                );
                bookList.add(newBook);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro! Não possível buscar os livros no banco de dados." + e.getMessage());
        }
        return bookList;
    }
}
