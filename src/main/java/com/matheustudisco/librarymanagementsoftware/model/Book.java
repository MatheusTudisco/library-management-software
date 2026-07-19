package com.matheustudisco.librarymanagementsoftware.model;

import com.matheustudisco.librarymanagementsoftware.enums.Genre;

public class Book {
    private String title;
    private String author;
    private Genre genre;
    private short year;
    private short volume;
    private short quantity;

    public Book(String title, String author, Genre genre, short year, short volume, short quantity) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.volume = volume;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public short getVolume() {
        return volume;
    }

    public void setVolume(byte volume) {
        this.volume = volume;
    }

    public short getQuantity() {
        return quantity;
    }

    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Livros cadastrados: \n" +
                "Title: " + title + "\n" +
                "Author " + author + "\n" +
                "Genre: " + genre.getDescricao() + "\n" +
                "Year: " + year + "\n" +
                "Volume: " + volume + "\n" +
                "Quantity: " + quantity + "\n\n";
    }
}
