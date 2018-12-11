package com.codingnomads.impacttracker.data;

import com.codingnomads.impacttracker.logic.Reduction;
import com.codingnomads.impacttracker.logic.impact.Impact;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReductionRowMapper implements RowMapper<Reduction> {
    @Override
    public Reduction mapRow(ResultSet rs, int rowNum) throws SQLException {
        Reduction reduction = new Reduction();

        reduction.setId(rs.getInt("r_id"));
        reduction.setReduction(rs.getString("r_reduction"));
        reduction.setAveragePerDay(rs.getDouble("r_average_per_day"));
        return reduction;
    }
}
