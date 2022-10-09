package com.example.service;

import com.example.model.Token;

public interface TokenService {
    Token createToken(Token token);

    Token findByToken(String token);
    
}
