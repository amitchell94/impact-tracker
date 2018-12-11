package com.codingnomads.impacttracker.data.repositories;

import com.codingnomads.impacttracker.data.rowmappers.ImpactRowMapper;
import com.codingnomads.impacttracker.data.rowmappers.ImpactWithAveragesRowMapper;
import com.codingnomads.impacttracker.model.ImpactWithAverage;
import com.codingnomads.impacttracker.model.Impact;
import com.codingnomads.impacttracker.logic.impact.ImpactRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Repository
public class MySqlImpactRepository implements ImpactRepository {

    private NamedParameterJdbcTemplate jdbcTemplate;
    private final String IMPACTS_TABLE_NAME = "impacts";
    private final String REDUCTION_TABLE_NAME = "reductions";
    private final ImpactRowMapper impactRowMapper = new ImpactRowMapper();
    private final ImpactWithAveragesRowMapper impactWithAveragesRowMapper = new ImpactWithAveragesRowMapper();

    public MySqlImpactRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Impact> getImpactsFromReductionId(int reductionId) {
        String query = "SELECT * from " + IMPACTS_TABLE_NAME + " where i_r_id = :reductionId";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("reductionId", reductionId );
        return jdbcTemplate.query(query, namedParameters, impactRowMapper);
    }

    @Override
    public List<ImpactWithAverage> getImpactsWithAveragesFromReductionId(int reductionId) {
        String query = "SELECT i.*, r.r_average_per_day FROM "+ IMPACTS_TABLE_NAME +" AS i " +
                "JOIN " + REDUCTION_TABLE_NAME + " AS r ON r.r_id = i.i_r_id " +
                "WHERE i.i_r_id = :reductionId";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("reductionId", reductionId );
        return jdbcTemplate.query(query, namedParameters, impactWithAveragesRowMapper);
    }
}
