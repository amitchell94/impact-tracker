package com.codingnomads.impacttracker.data.rowmappers;

import com.codingnomads.impacttracker.model.CommitmentWithReduction;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommitmentWithReductionRowMapper implements RowMapper<CommitmentWithReduction> {
    @Override
    public CommitmentWithReduction mapRow(ResultSet rs, int rowNum) throws SQLException {
        CommitmentWithReduction commitment = new CommitmentWithReduction();

        commitment.setId(rs.getInt("c_id"));
        commitment.setUserId(rs.getInt("c_u_id"));
        commitment.setReductionId(rs.getInt("c_r_id"));
        commitment.setReduction(rs.getString("r_reduction"));
        commitment.setReductionUnit(rs.getString("r_unit"));
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

