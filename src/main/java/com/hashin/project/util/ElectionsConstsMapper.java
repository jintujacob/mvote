package com.hashin.project.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hashin.project.bean.ElectionsConstsBean;

public class ElectionsConstsMapper implements RowMapper<ElectionsConstsBean>
{

    @Override
    public ElectionsConstsBean mapRow(ResultSet rs, int rowNum)
	    throws SQLException
    {
	ElectionsConstsExtractor extractor =  new ElectionsConstsExtractor();
	return extractor.extractData(rs);
    }

}
