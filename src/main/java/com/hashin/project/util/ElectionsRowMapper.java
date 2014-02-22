package com.hashin.project.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.hashin.project.bean.ElectionsBean;

public class ElectionsRowMapper implements RowMapper<ElectionsBean>
{

    @Override
    public ElectionsBean mapRow(ResultSet resultSet, int rowNum) throws SQLException
    {
	ElectionsExtractor electionsExtractor = new ElectionsExtractor();
	return electionsExtractor.extractData(resultSet);
    }
}
