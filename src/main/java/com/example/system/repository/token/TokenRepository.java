package com.example.system.repository.token;

import com.example.system.model.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    @Query("""
            select t from Token t inner join User u on t.user.userId = u.userId
            where u.userId = :userId and (t.expired = false or t.revoked = false)
            """
    )
    List<Token> findAllValidTokenByUser(Long userId);
    Optional<Token> findByToken(String token);
}
