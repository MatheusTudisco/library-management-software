package com.matheustudisco.librarymanagementsoftware.service;

import com.matheustudisco.librarymanagementsoftware.model.User;
import com.matheustudisco.librarymanagementsoftware.repository.UserRepository;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registrationService(User newUser) {

            userRepository.saveUser(newUser);
            System.out.println("Usuário: " + newUser.getName() + " cadastrado com sucesso!");

        }
    }
