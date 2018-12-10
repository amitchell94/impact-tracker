package com.codingnomads.impacttracker.data;

import com.codingnomads.impacttracker.logic.commitment.Commitment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommitmentRowMapper implements RowMapper<Commitment> {

    @Override
    public Commitment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Commitment commitment = new Commitment();

        commitment.setId(rs.getInt("c_id"));
        commitment.setUserId(rs.getInt("c_u_id"));
        commitment.setReductionId(rs.getInt("c_r_id"));
        commitment.setStartDate(rs.getDate("c_start_date").toLocalDate());
        if (rs.getDate("c_end_date") == null) {
            commitment.setEndDate(null);
        } else {
            commitment.setEndDate(rs.getDate("c_end_date").toLocalDate());
        }
        commitment.setAmountToReduceBy(rs.getInt("c_amount"));

        return commitment;
    }
}
