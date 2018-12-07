package com.codingnomads.impacttracker.data;

import com.codingnomads.impacttracker.logic.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class MySqlUserRoleRepository implements UserRoleRepository {

    private static final String TABLE_NAME = "user_role";

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public MySqlUserRoleRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addUserRoleLink(int userId, int roleId) {
        String query = "INSERT INTO " + TABLE_NAME + " VALUES(null, :userId, :roleId)";
        KeyHolder key = new GeneratedKeyHolder();
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("userId", userId)
                .addValue("roleId",roleId);

        jdbcTemplate.update(query, namedParameters, key);
    }







}