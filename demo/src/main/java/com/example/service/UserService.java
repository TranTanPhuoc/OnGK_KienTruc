package com.example.service;

import com.example.auth.UserPrincipal;
import com.example.model.User;

public interface UserService {
	User createUser(User user);
	UserPrincipal findByUsername(String username);
}
