package com.hgcode.bookstore.service;

import com.hgcode.bookstore.model.Login;
import com.hgcode.bookstore.model.User;
import com.hgcode.bookstore.response.LoginResponse;

public interface IUserService {
    String createUser(User user);

    LoginResponse loginUser(Login login);
}
