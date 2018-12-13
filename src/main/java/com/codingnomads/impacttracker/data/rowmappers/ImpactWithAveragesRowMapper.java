package com.codingnomads.impacttracker.data.rowmappers;

import com.codingnomads.impacttracker.model.ImpactWithAverage;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImpactWithAveragesRowMapper implements RowMapper<ImpactWithAverage> {
    @Override
    public ImpactWithAverage mapRow(ResultSet rs, int rowNum) throws SQLException {
        ImpactWithAverage impactWithAverage = new ImpactWithAverage();

        impactWithAverage.getImpact().setId(rs.getInt("i_id"));
        impactWithAverage.getImpact().setReductionId(rs.getInt("i_r_id"));
        impactWithAverage.getImpact().setImpactPerUnit(rs.getDouble("i_impact_per_unit"));
        impactWithAverage.getImpact().setImpactUnit(rs.getString("i_impact_unit"));
        impactWithAverage.getImpact().setImpactType(rs.getString("i_impact_type"));
        impactWithAverage.setAveragePerDay(rs.getDouble("r_average_per_day"));
        return impactWithAverage;
    }
}
