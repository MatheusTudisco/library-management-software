package com.matheustudisco.librarymanagementsoftware.repository;

import com.matheustudisco.librarymanagementsoftware.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryList implements UserRepository{
    private final List<User> usersList = new ArrayList<>();

    @Override
    public void saveUser(User user) {
        usersList.add(user);
    }

    @Override
    public List<User> showUser() {
        return usersList;
    }
}
