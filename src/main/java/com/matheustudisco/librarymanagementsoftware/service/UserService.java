package com.matheustudisco.librarymanagementsoftware.service;

import com.matheustudisco.librarymanagementsoftware.exception.CpfInvalidoException;
import com.matheustudisco.librarymanagementsoftware.model.User;
import com.matheustudisco.librarymanagementsoftware.repository.UserRepository;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean validarCPF(String cpf) {
        if (cpf.isEmpty()) {
            throw new CpfInvalidoException("Erro! O campo CPF não pode estar vazio.");
        } else if (!cpf.matches("\\d+")) {
            throw new CpfInvalidoException("Erro! O campo CPF deve ser preenchido apenas com números.");
        } else if (cpf.length() != 11) {
            throw new CpfInvalidoException("Erro! O CPF deve conter 11 números.");
        } else {
            return true;
        }
    }

    public void registrationService(User newUser) {
        userRepository.saveUser(newUser);
        System.out.println("Usuário: " + newUser.getName() + " cadastrado com sucesso!");
    }
}
