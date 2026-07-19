package com.matheustudisco.librarymanagementsoftware.service;

import com.matheustudisco.librarymanagementsoftware.exception.CelularInvalidoException;
import com.matheustudisco.librarymanagementsoftware.exception.CpfInvalidoException;
import com.matheustudisco.librarymanagementsoftware.exception.DataNascInvalidoException;
import com.matheustudisco.librarymanagementsoftware.exception.EmailInvalidoException;
import com.matheustudisco.librarymanagementsoftware.exception.NomeInvalidoException;
import com.matheustudisco.librarymanagementsoftware.model.User;
import com.matheustudisco.librarymanagementsoftware.repository.UserRepository;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean validarNome(String name) {
        if (name.isEmpty()) {
            throw new NomeInvalidoException("""
                    -----------------------------------------------------------------------
                    Erro! O campo Nome não pode estar vazio.
                    -----------------------------------------------------------------------""");
        } else if (!name.matches("^[a-zA-Z]+$")) {
            /*
             * Este Regex verifica se a string possui apenas caracteres de A a Z,
             * minúsculos ou maiúsculos, caso tenha algum caractere que não seja o
             * especificado, ele lança uma exceção.
             */
            throw new NomeInvalidoException("""
                    -----------------------------------------------------------------------
                    Erro! O campo Nome deve ser preenchido apenas com letras.
                    -----------------------------------------------------------------------""");
        } else if (name.length() <= 2) {
            throw new NomeInvalidoException("""
                    -----------------------------------------------------------------------
                     Erro! O campo Nome não pode conter apenas 1 ou 2 letras.
                     -----------------------------------------------------------------------""");
        } else {
            return true;
        }
    }

    public boolean validarSobrenome(String lastName) {
        if (lastName.isEmpty()) {
            throw new NomeInvalidoException("""
                    -----------------------------------------------------------------------
                    Erro! O campo Sobrenome não pode estar vazio.
                    -----------------------------------------------------------------------""");
        } else if (!lastName.matches("[a-zA-Z ]+")) {
            throw new NomeInvalidoException("""
                    -----------------------------------------------------------------------
                    Erro! O campo Sobrenome deve ser preenchido apenas com letras.
                    -----------------------------------------------------------------------""");
        } else if (lastName.length() <= 2) {
            throw new NomeInvalidoException("""
                    -----------------------------------------------------------------------
                    Erro! O campo Sobrenome não pode conter apenas 1 ou 2 letras.
                    -----------------------------------------------------------------------""");
        } else {
            return true;
        }
    }

    public boolean validarCPF(String cpf) {
        if (cpf.isEmpty()) {
            throw new CpfInvalidoException("""
                    -----------------------------------------------------------------------
                    Erro! O campo CPF não pode estar vazio.
                    -----------------------------------------------------------------------""");
        } else if (!cpf.matches("\\d+")) {
            /*
             * Este regex verifica se cada caractere é numérico
             * caso tenha algum não numérico, ele lança uma exceção.
             */
            throw new CpfInvalidoException("""
                    -----------------------------------------------------------------------
                    Erro! O campo CPF deve ser preenchido apenas com números.
                    -----------------------------------------------------------------------""");
        } else if (cpf.length() != 11) {
            throw new CpfInvalidoException("""
                    -----------------------------------------------------------------------
                    Erro! O CPF deve conter 11 números.
                    -----------------------------------------------------------------------""");
        } else {
            return true;
        }
    }

    public boolean validarDataNasc(String dataNasc, DateTimeFormatter formatter) {
        if (dataNasc.isEmpty()) {
            throw new DataNascInvalidoException("""
                    -----------------------------------------------------------------------
                    Erro! O campo Data de nascimento não pode estar vazio.
                    -----------------------------------------------------------------------""");
        } else {
            try{
                LocalDate.parse(dataNasc, formatter);
                return true;
            } catch (DateTimeException e){
                throw new DataNascInvalidoException("""
                                -----------------------------------------------------------------------
                                Erro! Formato inválido ou data inexistente
                                -----------------------------------------------------------------------""");
            }
        }
    }

    public boolean validarCelular(String celular) {
        if (celular.isEmpty()) {
            throw new CelularInvalidoException("""
                    -----------------------------------------------------------------------
                    Erro! O campo Número de celular não pode estar vazio.
                    -----------------------------------------------------------------------""");
        } else if (!celular.matches("\\d{11}")) {
            /*
             * Este Regex verifica se contém 11 números e se são todos numéricos,
             * caso não sejam 11 ou contenha algo não numérico ele lança uma exceção.
             */
            throw new CelularInvalidoException("""
                    -----------------------------------------------------------------------
                    Erro! O campo número de celular deve ser preenchido apenas com 11 números.
                    -----------------------------------------------------------------------""");
        } else {
            return true;
        }
    }

    public boolean validarEmail(String email) {
        if (email.isEmpty()) {
            throw new EmailInvalidoException("""
                    -----------------------------------------------------------------------
                    Erro! O campo Email não pode estar vazio.
                    -----------------------------------------------------------------------""");
        } else if (!email.contains("@")) {
            //O .contains() verifica se na String fornecida contém o caractere que eu especifiquei.
            throw new EmailInvalidoException("""
                    -----------------------------------------------------------------------
                    Erro! O Email deve conter @.
                    -----------------------------------------------------------------------""");
        } else if (!email.endsWith(".com")) {
            //O .endsWith() verifica se a String termina exatamente com a sequência fornecida.
            throw new EmailInvalidoException("""
                    -----------------------------------------------------------------------
                    Erro! O email deve ter o final ".com".
                    -----------------------------------------------------------------------""");
        } else {
            return true;
        }
    }

    public void registrationService(User newUser) {
        userRepository.saveUser(newUser);
        System.out.println("Usuário: " + newUser.getName() + " cadastrado com sucesso!");
    }

    public List<User> showService(){
        return userRepository.showUser();
    }
}
