package com.codingnomads.impacttracker.data;


import com.codingnomads.impacttracker.logic.commitment.Commitment;
import com.codingnomads.impacttracker.logic.commitment.CommitmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Repository
public class MySqlCommitmentRepository implements CommitmentRepository {

    private NamedParameterJdbcTemplate jdbcTemplate;
    private final String commitmentTable = "commitments";
    private final CommitmentRowMapper rowMapper = new CommitmentRowMapper();

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

    @Override
    public List<Commitment> getCommitmentsFromUserId(int userId) {
        String query = "SELECT * from " + commitmentTable + " where c_u_id = :userId";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("userId", userId );
        return jdbcTemplate.query(query, namedParameters, rowMapper);
    }


}
