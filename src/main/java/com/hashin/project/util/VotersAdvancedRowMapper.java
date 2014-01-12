package com.hashin.project.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hashin.project.bean.VotersUserBean;

public class VotersAdvancedRowMapper implements RowMapper<VotersUserBean>
{

    @Override
    public VotersUserBean mapRow(ResultSet rs, int rowNum) throws SQLException
    {
	VotersAdvancedExtractor extractor =  new VotersAdvancedExtractor();
	return extractor.extractData(rs);
    }

}
