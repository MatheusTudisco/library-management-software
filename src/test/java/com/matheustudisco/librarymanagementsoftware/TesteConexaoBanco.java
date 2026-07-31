package com.matheustudisco.librarymanagementsoftware;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TesteConexaoBanco {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "sua_senha_aqui";

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
@Test
    public void testartConexao(){
        Connection conexaoTeste = conectar();
    }
}
