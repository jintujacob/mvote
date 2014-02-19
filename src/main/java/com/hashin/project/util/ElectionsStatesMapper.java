package com.hashin.project.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hashin.project.bean.ElectionStatesBean;

public class ElectionsStatesMapper implements RowMapper<ElectionStatesBean>
{

    @Override
    public ElectionStatesBean mapRow(ResultSet rs, int rowNum)
	    throws SQLException
    {
	ElectionStatesExtractor extractor = new ElectionStatesExtractor();
	return extractor.extractData(rs);
    }

}
