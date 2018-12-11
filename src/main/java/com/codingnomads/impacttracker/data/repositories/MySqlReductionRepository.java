package com.codingnomads.impacttracker.data.repositories;

import com.codingnomads.impacttracker.data.rowmappers.ReductionRowMapper;
import com.codingnomads.impacttracker.model.Reduction;
import com.codingnomads.impacttracker.logic.reduction.ReductionRepository;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MySqlReductionRepository implements ReductionRepository {

    private static final String REDUCTION_TABLE_NAME = "reductions";
    private NamedParameterJdbcTemplate jdbcTemplate;
    private ReductionRowMapper rowMapper = new ReductionRowMapper();

    public MySqlReductionRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Reduction> getAllReductions() {
        String query = "SELECT * from " + REDUCTION_TABLE_NAME;
        return jdbcTemplate.query(query, new EmptySqlParameterSource(),rowMapper);
    }
}
