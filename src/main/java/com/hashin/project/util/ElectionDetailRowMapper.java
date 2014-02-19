package com.hashin.project.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.hashin.project.bean.ElectionsBean;

public class ElectionDetailRowMapper implements RowMapper<ElectionsBean>
{

    @Override
    public ElectionsBean mapRow(ResultSet resultSet, int rowNum) throws SQLException
    {
	ElectionDetailExtractor electionsExtractor = new ElectionDetailExtractor();
	return electionsExtractor.extractData(resultSet);
    }
}
