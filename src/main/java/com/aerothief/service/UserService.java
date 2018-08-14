package com.aerothief.service;

import com.aerothief.model.User;

public interface UserService {
    int addUser(User user);
    User getUserByKey(int key);
}
