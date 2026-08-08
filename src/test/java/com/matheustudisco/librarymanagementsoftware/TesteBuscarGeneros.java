package com.matheustudisco.librarymanagementsoftware;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TesteBuscarGeneros {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "sua_senha_aqui";

    @Test
    public void buscarGenero() {
        String sql = "SELECT id, name FROM genres";

        /*
        * A interface Connection representa a ponte ativa entre o código e o banco de dados, desta interface saem os
        * controles de transação (setAutoCommit, commit, rollback, close.).
        * O DriverManager é a classe do Java responsável por abrir a conexão usando a Url, Usuário e senha.
        * O PreparedStatement é o objeto que envia os comandos SQL de forma segura
        * (imune a ataques de SQL Injection) e otimizada.
        * O ResultSet é a resposta do banco de quando você faz uma busca. Ele funciona como uma tabela
        * virtual percorrendo linha por linha com um while (resultSet.next()) para ler as colunas.
        * RESUMO:
        * O [DriverManager] ---> abre a ---> [Connection] ---> cria o [PreparedStatement] --->
        * executa [ResultSet] (se for select.)
        * o PreparedStatement é a pergunta enviada ao banco e o ResultSet é a resposta do banco.
        * Quando o banco devolve o ResultSet, o cursor começa na linha 0, antes do primeiro registro.
        * Para mover este cursor linha por linha usamos o método .next().
        * O método resultSet.next() faz duas coisas:
        *      -> Move o cursor para a próxima linha da tabela;
        *      -> Retorna um valor booleano, true se a linha for válida para ser lida e false quando chega ao fim
        *         da tabela e não hà mais registros.
        * Por este motivo o uso do while para mover o cursos pelas linhas da tabela para ler multiplos registros.
        * Utilize o executeQuery exclusivamente para o SELECT, ele devolve o ResultSet.
        * Utilize o executeUpdate para INSERT, UPDATE E DELETE. Ele não retorna um ResultSet, apenas um int contendo
        * o número de linhas afetadas.
        * Se a busca gerar um único resultado, utilize o if ao invés do while para ler aquele resultado.
        * EXEMPLO:
        * if (resultSet.next()){
        * Lê apenas a linha encontrada
        * String name = resultSet.getString("name");
        * }else {System.out.println("Gênero não enocntrado!");}
        * O erro Before start of result set acontece quando se tenta ler um campo antes de mover o cursor da linha 0.
         */
        try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement statement = conexao.prepareStatement(sql);
             ResultSet resultado = statement.executeQuery()) {

            System.out.println("---Lista de Gêneros---");
            while(resultado.next()){
                System.out.println("ID: " + resultado.getLong("id") + " | Nome: " + resultado.getString("name"));
            }

        } catch (
                SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
