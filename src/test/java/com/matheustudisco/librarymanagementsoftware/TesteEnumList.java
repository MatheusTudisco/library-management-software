package com.matheustudisco.librarymanagementsoftware;

import com.matheustudisco.librarymanagementsoftware.enums.Genre;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TesteEnumList {
    @Test //Essa anotação avisa a IDE que esse é um teste executável.
    public void testarListaEnum() {
        System.out.println("Escolha um gênero da lista abaixo:");
        List<Genre> genre = new ArrayList<>(List.of(Genre.values()));
        for (int i=0; i<genre.size(); i++){
            System.out.println(i+1 + " - " + genre.get(i).getDescricao());
        }
        System.out.println("Escolha de 1 até " + genre.size() + ": ");
        int escolhaGenre = 13;

        System.out.println("""
                -------------------------------------------------
                gênero escolhido: %s.
                -------------------------------------------------
                """.formatted(genre.get(escolhaGenre - 1).getDescricao()));
    }
}
