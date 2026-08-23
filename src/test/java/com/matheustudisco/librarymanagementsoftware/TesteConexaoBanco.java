package com.matheustudisco.librarymanagementsoftware;

import com.matheustudisco.librarymanagementsoftware.enums.Role;
import com.matheustudisco.librarymanagementsoftware.model.User;
import org.junit.Test;

import java.sql.*;
import java.time.LocalDate;

public class TesteConexaoBanco {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USUARIO = "postgres";
    private static final String SENHA = System.getenv("DB_PASSWORD");

    public static Connection conectar(){
        try{
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conexão com o postgresql realizada com sucesso.");
            return conexao;
        } catch (SQLException e){
            System.out.println("Erro ao conectar" + e.getMessage());
            return null;
        }
    }
    public void testartConexao(){
        Connection conexaoTeste = conectar();
    }

@Test
    public void findByCpf(){
    String cpf = "12345678910";
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
                    User user = new User(id, name, lastName, cpfUser, dateBirth, cellphone, email, Role.valueOf(roleBanco), password);
                    System.out.println(user);
                }

            }

        } catch (SQLException e){
            throw new RuntimeException("Erro! Usuário não encontrado, contate o gerente." + e.getMessage());
        }
    }
}
