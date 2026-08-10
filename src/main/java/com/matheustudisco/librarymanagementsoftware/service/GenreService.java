package com.matheustudisco.librarymanagementsoftware.service;

import com.matheustudisco.librarymanagementsoftware.exception.GeneroInvalidoException;
import com.matheustudisco.librarymanagementsoftware.model.Genre;
import com.matheustudisco.librarymanagementsoftware.repository.GenreRepository;

import java.util.ArrayList;
import java.util.List;

public class GenreService {
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Long validarGenero(String generoString, List<Genre> genreList) {
        if (genreList == null || genreList.isEmpty()) {
            throw new GeneroInvalidoException("Nenhum gênero disponível para seleção");
        }

        if (generoString.isEmpty()) {
            throw new GeneroInvalidoException("""
                    -----------------------------------------------------------------------
                    Erro! O campo Gênero não pode estar vazio.
                    -----------------------------------------------------------------------""");
        }
        try {
            Long idDigitado = Long.parseLong(generoString);
            //Leia-se: para cada genero do tipo Genre dentro de genreList
            for (Genre genero : genreList) {
                if (genero.getId().equals(idDigitado)) {
                    return idDigitado;
                }
            }
            throw new GeneroInvalidoException("""
                    -----------------------------------------------------------------------
                    Erro! Escolha inexistente.
                    -----------------------------------------------------------------------""");

        } catch (NumberFormatException e) {
            throw new GeneroInvalidoException("""
                    -----------------------------------------------------------------------
                    Erro! O campo Gênero somente aceita as escolhas citadas.
                    -----------------------------------------------------------------------""");
        }
    }

    public List<Genre> showGenres() {
        try {
            List<Genre> generos = genreRepository.buscarGenero();
            if (generos.isEmpty()) {
                throw new GeneroInvalidoException("""
                        -----------------------------------------------------------------------
                        Erro! Nenhum gênero cadastrado no banco.
                        -----------------------------------------------------------------------""");
            }
            return generos;
        } catch (RuntimeException e) {
            throw new GeneroInvalidoException("""
                    -----------------------------------------------------------------------
                    Erro! Não foi possível carregar os gêneros cadastrados no banco.
                    -----------------------------------------------------------------------""");
        }
    }
}
