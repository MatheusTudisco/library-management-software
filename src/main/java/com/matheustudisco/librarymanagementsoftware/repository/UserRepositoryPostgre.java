package com.matheustudisco.librarymanagementsoftware.repository;

import com.matheustudisco.librarymanagementsoftware.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryPostgre implements UserRepository {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USUARIO = "postgres";
    private static final String SENHA = System.getenv("DB_PASSWORD");

    @Override
    public void saveUser(User user) {
        String sql = "INSERT INTO users (name, last_name, cpf, date_of_birth, cellphone, email) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conectar = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement statement = conectar.prepareStatement(sql)) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getCpf());
            //Uso do Object, a partir do java 8 e driver moderno não necessita converter para Date
            statement.setObject(4, user.getDateOfBirth());
            statement.setString(5, user.getCellphone());
            statement.setString(6, user.getEmail());
            statement.executeUpdate(); //Executa o INSERT INTO

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ou executar o banco de dados");
        }
    }

    @Override
    public List<User> showUser() {
        String sql = "SELECT * FROM users";
        List<User> userList = new ArrayList<>();

        try (Connection conectar = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement statement = conectar.prepareStatement(sql);
             ResultSet resultado = statement.executeQuery()) {

            while (resultado.next()) {

                User newUser = new User(
                        resultado.getLong("id"),
                        resultado.getString("name"),
                        resultado.getString("last_name"),
                        resultado.getString("cpf"),
                        resultado.getObject("date_of_birth", LocalDate.class),
                        resultado.getString("cellphone"),
                        resultado.getString("email"));
                userList.add(newUser);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar os usuários cadastrados.");
        }

        return userList;
    }
}
