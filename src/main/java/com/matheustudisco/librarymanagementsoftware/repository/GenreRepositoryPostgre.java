package com.matheustudisco.librarymanagementsoftware.repository;

import com.matheustudisco.librarymanagementsoftware.model.Genre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreRepositoryPostgre implements GenreRepository {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "sua_senha_aqui";

    public List<Genre> buscarGenero() {
        //Lista criada como variável local para não ter duplicidade de dados.
        List<Genre> genreList = new ArrayList<>();
        String sql = "SELECT id, name FROM genres";
        try (Connection conectar = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement statement = conectar.prepareStatement(sql);
             ResultSet resultado = statement.executeQuery()) {

            while(resultado.next()) {
                Long id = resultado.getLong("id");
                String name = resultado.getString("name");
                Genre genero = new Genre(id, name);
                genreList.add(genero);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar os gêneros" + e.getMessage());
        }
        return genreList;
    }
}
