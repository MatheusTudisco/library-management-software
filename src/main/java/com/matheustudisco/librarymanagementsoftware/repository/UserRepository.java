package com.matheustudisco.librarymanagementsoftware.repository;

import java.util.ArrayList;
import java.util.List;

import com.matheustudisco.librarymanagementsoftware.model.User;

public class UserRepository {
    private List<User> usersList = new ArrayList<>();

    public void saveUser(User user) {
        usersList.add(user);
    }

    public List<User> showUser() {
        return usersList;
    }

}
