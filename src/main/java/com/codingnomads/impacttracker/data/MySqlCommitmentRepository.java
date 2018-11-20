package com.codingnomads.impacttracker.data;


import com.codingnomads.impacttracker.logic.Commitment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class MySqlCommitmentRepository implements CommitmentRepository{

    private NamedParameterJdbcTemplate jdbcTemplate;
    private final String commitmentTable = "commitments";

    @Autowired
    public MySqlCommitmentRepository(NamedParameterJdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Commitment save(Commitment commitment) {
        String query = "INSERT INTO " + commitmentTable + " VALUES(null, :userId, :reductionId, :startDate, :endDate, :amountToReduceBy)";
        KeyHolder key = new GeneratedKeyHolder();
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(commitment);
        jdbcTemplate.update(query, namedParameters, key);
        commitment.setId(key.getKey().intValue());
        return commitment;
    }
}
