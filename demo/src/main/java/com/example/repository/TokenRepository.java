package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Token;


public interface TokenRepository
        extends JpaRepository<Token, Long> {
    Token findByToken(String token);
}
