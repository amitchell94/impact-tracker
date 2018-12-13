package com.codingnomads.impacttracker.data.rowmappers;

import com.codingnomads.impacttracker.model.Impact;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImpactRowMapper implements RowMapper<Impact> {
    @Override
    public Impact mapRow(ResultSet rs, int rowNum) throws SQLException {
        Impact impact = new Impact();

        impact.setId(rs.getInt("i_id"));
        impact.setReductionId(rs.getInt("i_r_id"));
        impact.setImpactPerUnit(rs.getDouble("i_impact_per_unit"));
        impact.setImpactUnit(rs.getString("i_impact_unit"));
        impact.setImpactType(rs.getString("i_impact_type"));
        return impact;
    }
}
