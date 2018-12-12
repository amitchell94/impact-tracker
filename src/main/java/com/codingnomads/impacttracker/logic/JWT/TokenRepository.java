package com.codingnomads.impacttracker.logic.JWT;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("tokenRepository")
public interface TokenRepository extends JpaRepository<Token, Integer> {
    boolean existsByValue(String value);

}
