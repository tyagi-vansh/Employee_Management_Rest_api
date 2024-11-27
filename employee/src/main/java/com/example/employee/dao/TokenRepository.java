package com.example.employee.dao;

import com.example.employee.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token,String> {
    Token findByToken(String token);
}
