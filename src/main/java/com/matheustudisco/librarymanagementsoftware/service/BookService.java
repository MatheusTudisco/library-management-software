package com.matheustudisco.librarymanagementsoftware.service;

import com.matheustudisco.librarymanagementsoftware.exception.*;
import com.matheustudisco.librarymanagementsoftware.model.Book;
import com.matheustudisco.librarymanagementsoftware.repository.BookRepository;

import java.text.AttributedCharacterIterator;
import java.util.List;

public class BookService {
    private final BookRepository bookRepository;

    //Construtor para passar como argumento o repositoy na instanciação.
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public boolean validarTitulo(String title) {
        if ((title.isEmpty())) {
            throw new TituloInvalidoException("Erro! O campo titulo não pode estar vázio.");
        } else if (title.contains("  ")) {
            throw new TituloInvalidoException("Erro! O campo titulo não pode ter mais de um espaço seguido.");
        } else {
            return true;
        }
    }

    public boolean validarAutor(String author) {
        if (author.isEmpty()) {
            throw new AutorInvalidoException("""
                    -----------------------------------------------------------------------
                    Erro! O campo Autor não pode estar vazio.
                    -----------------------------------------------------------------------""");
        } else if (!author.matches("^[A-Za-z]+(\\s[A-Za-z]+)*$")) {
            throw new AutorInvalidoException("""
                    -----------------------------------------------------------------------
                    Erro! O campo autor somente deve conter letras e espaço.
                    -----------------------------------------------------------------------""");
        } else {
            return true;
        }
    }

    public byte validarGenero(String generoString, int tamanhoGenre) {
        if (generoString.isEmpty()) {
            throw new GeneroInvalidoException("""
                    -----------------------------------------------------------------------
                    Erro! O campo Gênero não pode estar vazio.
                    -----------------------------------------------------------------------""");
        }
        try {
            //Esta variável é para uso da regra de escolha igual 0 ou maior que as escolhas.
            byte generoByte = Byte.parseByte(generoString);
            if (generoByte > tamanhoGenre || generoByte <= 0) {
                throw new GeneroInvalidoException("""
                        -----------------------------------------------------------------------
                        Erro! Escolha inexistente.
                        -----------------------------------------------------------------------""");
            } else {
                return generoByte;
            }
        } catch (NumberFormatException e) {
            throw new GeneroInvalidoException("""
                    -----------------------------------------------------------------------
                    Erro! O campo Gênero somente aceita as escolhas citadas.
                    -----------------------------------------------------------------------""");
        }
    }

    public short validarAno(String anoString) {
        if (anoString.isEmpty()) {
            throw new AnoInvalidoException("""
                    -----------------------------------------------------------------------
                    Erro! O campo Ano não pode estar vazio.
                    -----------------------------------------------------------------------""");
        } else {
            try {
                return Short.parseShort(anoString);
            } catch (NumberFormatException e) {
                throw new AnoInvalidoException("""
                        -----------------------------------------------------------------------
                        Erro! O campo Ano permite somente números.
                        -----------------------------------------------------------------------""");
            }
        }
    }

    public short validarVolume(String volumeString) {
        if (volumeString.isEmpty()) {
            throw new VolumeInvalidoException("""
                    -----------------------------------------------------------------------
                    Erro! O campo Volume não pode estar vazio.
                    -----------------------------------------------------------------------""");
        } else if (volumeString.equals("0")) {
            throw new VolumeInvalidoException("""
                    -----------------------------------------------------------------------
                    Erro! O campo Volume não pode ser 0.
                    -----------------------------------------------------------------------""");
        } else {
            try {
                return Short.parseShort(volumeString);
            } catch (NumberFormatException e) {
                throw new VolumeInvalidoException("""
                        -----------------------------------------------------------------------
                        Erro! O Volume permite somente números.
                        -----------------------------------------------------------------------""");
            }
        }
    }

    public short validarQuantidade(String quantityString) {
        short quantity = 0;
        if (quantityString.isEmpty()) {
            throw new QuantidadeInvalidaException("""
                    -----------------------------------------------------------------------
                    Erro! O campo Quantidade não pode estar vazio.
                    -----------------------------------------------------------------------
                    """);
        } else {
            try {
                quantity = Short.parseShort(quantityString);
                if (quantity < 0) {
                    throw new QuantidadeInvalidaException("""
                            -----------------------------------------------------------------------
                            Erro! O campo Quantidade não pode ser negativo.
                            -----------------------------------------------------------------------
                            """);
                }
                return quantity;
            } catch (NumberFormatException e) {
                throw new QuantidadeInvalidaException("""
                        -----------------------------------------------------------------------
                        Erro! O campo Quantidade permite somente números.
                        -----------------------------------------------------------------------
                        """);
            }
        }
    }

    public void registrationBook(Book newBook) {
        bookRepository.saveBook(newBook);
        System.out.println("Sucesso! O livro " + newBook.getTitle() + " foi cadastrado com sucesso");
    }

    public List<Book> showBook (){
        return bookRepository.showBook();
    }
}
