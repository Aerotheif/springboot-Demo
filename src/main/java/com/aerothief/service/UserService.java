package com.aerothief.service;

import com.aerothief.common.ServerResponse;
import com.aerothief.model.User;

import javax.servlet.ServletRequest;

public interface UserService {
    int addUser(User user);
    ServerResponse<User> login(ServletRequest request);
    ServerResponse checkUsernameExist(ServletRequest request);
    ServerResponse<User> registerExist(ServletRequest request);
}
