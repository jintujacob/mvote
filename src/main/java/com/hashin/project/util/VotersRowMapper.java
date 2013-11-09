package com.hashin.project.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hashin.project.bean.VotersUserBean;

public class VotersRowMapper implements RowMapper<VotersUserBean>
{

    @Override
    public VotersUserBean mapRow(ResultSet rs, int rowNum) throws SQLException
    {
	VotersExtractor extractor = new VotersExtractor();
	return extractor.extractData(rs);
    }

}
