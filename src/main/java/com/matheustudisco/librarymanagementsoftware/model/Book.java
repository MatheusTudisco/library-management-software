package com.matheustudisco.librarymanagementsoftware.model;

public class Book {
    private String title;
    private String author;
    private String genre;
    private int year;
    private byte volume;
    private short quantity;

    public Book(String title, String author, String genre, int year, byte volume, short quantity) {
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public byte getVolume() {
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
                "Genre: " + genre + "\n" +
                "Year: " + year + "\n" +
                "Volume: " + volume + "\n" +
                "Quantity: " + quantity + "\n\n";
    }
}
