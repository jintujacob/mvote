package com.hashin.project.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hashin.project.bean.ElectionsCandidatesBean;

public class CandidatesRowMapper implements RowMapper<ElectionsCandidatesBean>
{

    @Override
    public ElectionsCandidatesBean mapRow(ResultSet rs, int rowNum) throws SQLException
    {
	CandidatesExtractor extractor = new CandidatesExtractor();
	return extractor.extractData(rs);
    }

}
