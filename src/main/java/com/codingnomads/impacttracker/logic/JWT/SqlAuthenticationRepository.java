package com.codingnomads.impacttracker.logic.JWT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class SqlAuthenticationRepository implements AuthenticationRepository{
    private static final String TABLE_NAME = "token";

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public SqlAuthenticationRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Token createToken(String tokenValue) {

        String query = "INSERT INTO " + TABLE_NAME + " VALUES(null, :token)";
        KeyHolder key = new GeneratedKeyHolder();
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("token", tokenValue);

        jdbcTemplate.update(query, namedParameters, key);

        Token token = new Token();

        token.setId(key.getKey().intValue());
        token.setValue(tokenValue);

        return token;
    }

    @Override
    public boolean validateToken(String token) {
        String query = "SELECT token from " + TABLE_NAME + " where token = :token";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("token", token);
        Token found = new Token();
        try {
            found.setValue(jdbcTemplate.queryForObject(query, namedParameters, String.class));
        } catch (EmptyResultDataAccessException e) {
            return false;
        }

        return true;
    }
}
