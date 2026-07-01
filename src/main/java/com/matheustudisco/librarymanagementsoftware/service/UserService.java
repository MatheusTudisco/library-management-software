package com.matheustudisco.librarymanagementsoftware.service;

import com.matheustudisco.librarymanagementsoftware.model.User;
import com.matheustudisco.librarymanagementsoftware.repository.UserRepository;

public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registrationService(User newUser){
            
        if(newUser.getName() == null){
                System.out.println("Erro! O campo nome não pode estar vazio.");
                return;
            } else if (newUser.getLastName() == null){
                System.out.println("Erro! O campo sobrenome não pode estar vazio.");
                return;
            } 
            else if (newUser.getCpf() == null){
                System.out.println("Erro! O campo cpf não pode estar vazio.");
                return;
            } else if (newUser.getDateOfBirth() == null){
                System.out.println("Erro! O campo data de nascimento não pode estar vazio.");
                return;
            } else if (newUser.getCellphone() == null){
                System.out.println("Erro! O campo numero de telefone não pode estar vazio.");
                return;
            } else if (newUser.getEmail() == null){
                System.out.println("Erro! O campo email não pode estar vazio.");
                return;
            } else {
                userRepository.saveUser(newUser);
                System.out.println("Usuário: " + newUser.getName() + " cadastrado com sucesso!");
                
            }
        }

}
