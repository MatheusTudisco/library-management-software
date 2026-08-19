package com.matheustudisco.librarymanagementsoftware.model;


public class Book {
    private Long id;
    private String title;
    private String author;
    private Long genre;
    private String genre_name;
    private short year;
    private short volume;
    private short quantity;

    public Book(String title, String author, Long genre, short year, short volume, short quantity) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.volume = volume;
        this.quantity = quantity;
    }

    public Book(Long id, String title, String author, String genre_name, short year, short volume, short quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre_name = genre_name;
        this.year = year;
        this.volume = volume;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public String getGenre_name() {
        return genre_name;
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

    public Long getGenre() {
        return genre;
    }

    public void setGenre(Long genre) {
        this.genre = genre;
    }


    public short getYear() {
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
                "Genre: " + genre + "\n" +
                "Year: " + year + "\n" +
                "Volume: " + volume + "\n" +
                "Quantity: " + quantity + "\n\n";
    }
}
