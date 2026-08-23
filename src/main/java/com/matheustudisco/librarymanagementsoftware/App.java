package com.matheustudisco.librarymanagementsoftware;

import com.matheustudisco.librarymanagementsoftware.controller.BookControllerCli;
import com.matheustudisco.librarymanagementsoftware.controller.LoginControllerCli;
import com.matheustudisco.librarymanagementsoftware.controller.MenuControllerCli;
import com.matheustudisco.librarymanagementsoftware.controller.UserControllerCli;
import com.matheustudisco.librarymanagementsoftware.repository.BookRepositoryPostgre;
import com.matheustudisco.librarymanagementsoftware.repository.GenreRepositoryPostgre;
import com.matheustudisco.librarymanagementsoftware.repository.UserRepositoryPostgre;
import com.matheustudisco.librarymanagementsoftware.service.BookService;
import com.matheustudisco.librarymanagementsoftware.service.GenreService;
import com.matheustudisco.librarymanagementsoftware.service.UserService;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        //Instanciação das classes services com os repositórios que serão utilizados.
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService(new UserRepositoryPostgre());
        BookService bookService = new BookService(new BookRepositoryPostgre());
        GenreService genreService = new GenreService(new GenreRepositoryPostgre());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var bookControllerCli = new BookControllerCli(scanner, bookService, genreService);
        var userControllerCli = new UserControllerCli(scanner, userService, formatter);
        var loginControllerCli = new LoginControllerCli(scanner, userService);
        var menuControllerCli = new MenuControllerCli(scanner, userControllerCli, bookControllerCli, loginControllerCli);

        menuControllerCli.menuInicial();

    }
}

