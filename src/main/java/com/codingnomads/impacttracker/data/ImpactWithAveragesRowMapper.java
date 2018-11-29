package com.codingnomads.impacttracker.data;

import com.codingnomads.impacttracker.logic.impact.ImpactWithAverage;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImpactWithAveragesRowMapper implements RowMapper<ImpactWithAverage> {
    @Override
    public ImpactWithAverage mapRow(ResultSet rs, int rowNum) throws SQLException {
        ImpactWithAverage impactWithAverage = new ImpactWithAverage();

        impactWithAverage.setId(rs.getInt("i_id"));
        impactWithAverage.setReductionId(rs.getInt("i_r_id"));
        impactWithAverage.setImpactPerUnit(rs.getDouble("i_impact_per_unit"));
        impactWithAverage.setImpactUnit(rs.getString("i_impact_unit"));
        impactWithAverage.setImpactType(rs.getString("i_impact_type"));
        impactWithAverage.setReductionUnit(rs.getString("i_reduction_unit"));
        impactWithAverage.setAveragePerDay(rs.getDouble("r_average_per_day"));
        return impactWithAverage;
    }
}
