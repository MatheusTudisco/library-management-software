package com.matheustudisco.librarymanagementsoftware.repository;

import com.matheustudisco.librarymanagementsoftware.enums.Role;
import com.matheustudisco.librarymanagementsoftware.model.User;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Repository
public class UserRepositoryPostgre implements UserRepository {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USUARIO = "postgres";
    private static final String SENHA = System.getenv("DB_PASSWORD");

    @Override
    public void saveUser(User user) {
        String sql = "INSERT INTO users (name, last_name, cpf, date_of_birth, cellphone, email, role, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conectar = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement statement = conectar.prepareStatement(sql)) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getCpf());
            //Uso do Object, a partir do java 8 e driver moderno não necessita converter para Date
            statement.setObject(4, user.getDateOfBirth());
            statement.setString(5, user.getCellphone());
            statement.setString(6, user.getEmail());
            statement.setString(7, user.getRole().name());
            statement.setString(8, user.getPassword());
            statement.executeUpdate(); //Executa o INSERT INTO

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ou executar o banco de dados" + e.getMessage());
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
                Long id = resultado.getLong("id");
                String name = resultado.getString("name");
                String lastName = resultado.getString("last_name");
                String cpf = resultado.getString("cpf");
                LocalDate dateBirth = resultado.getObject("date_of_birth", LocalDate.class);
                String cellphone = resultado.getString("cellphone");
                String email = resultado.getString("email");
                String roleBanco = resultado.getString("role");
                String password = resultado.getString("password");
                User newUser = new User(id, name, lastName, cpf, dateBirth, cellphone, email, Role.valueOf(roleBanco), password);
                userList.add(newUser);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar os usuários cadastrados.");
        }

        return userList;
    }
    public User findByCpf(String cpf){
        String sql = "SELECT * FROM users WHERE cpf = ?";

        try (Connection conectar = DriverManager.getConnection(URL, USUARIO, SENHA);
        PreparedStatement statement = conectar.prepareStatement(sql)) {
            statement.setString(1, cpf);

            try (ResultSet resultado = statement.executeQuery()) {
                if (resultado.next()) {
                    Long id = resultado.getLong("id");
                    String name = resultado.getString("name");
                    String lastName = resultado.getString("last_name");
                    String cpfUser = resultado.getString("cpf");
                    LocalDate dateBirth = resultado.getObject("date_of_birth", LocalDate.class);
                    String cellphone = resultado.getString("cellphone");
                    String email = resultado.getString("email");
                    String roleBanco = resultado.getString("role");
                    String password = resultado.getString("password");
                    return new User(id, name, lastName, cpfUser, dateBirth, cellphone, email, Role.valueOf(roleBanco), password);
                }
                return null;
            }

    } catch (SQLException e){
            throw new RuntimeException("Erro! Usuário não encontrado, contate o gerente." + e.getMessage());
        }
    }

}
