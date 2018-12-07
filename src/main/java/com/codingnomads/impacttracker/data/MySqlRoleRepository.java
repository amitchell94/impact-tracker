package com.codingnomads.impacttracker.data;

import com.codingnomads.impacttracker.logic.Role;
import com.codingnomads.impacttracker.logic.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class MySqlRoleRepository implements RoleRepository {
    private static final String TABLE_NAME = "role";
    private final RoleRowMapper roleRowMapper = new RoleRowMapper();

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public MySqlRoleRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Role findByRole(String role) {
        String query = "SELECT * from " + TABLE_NAME + " where r_name = :role";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("role", role);
        return jdbcTemplate.queryForObject(query, namedParameters, roleRowMapper);

    }

}

