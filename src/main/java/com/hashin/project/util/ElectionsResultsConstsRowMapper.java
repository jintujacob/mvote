package com.hashin.project.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.hashin.project.bean.ElectionsResultsBean;

public class ElectionsResultsConstsRowMapper implements
	RowMapper<ElectionsResultsBean>
{

    @Override
    public ElectionsResultsBean mapRow(ResultSet rs, int rowNum)
	    throws SQLException
    {
	ElectionsResultsConstsExtractor extractor =  new ElectionsResultsConstsExtractor();
	return extractor.extractData(rs);
    }

}
