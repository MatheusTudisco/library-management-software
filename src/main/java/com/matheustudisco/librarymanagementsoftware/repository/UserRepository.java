package com.matheustudisco.librarymanagementsoftware.repository;

import java.util.ArrayList;
import java.util.List;

import com.matheustudisco.librarymanagementsoftware.model.User;

public interface UserRepository {
    public void saveUser(User user);

    public List<User> showUser();
}
