package com.hashin.project.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hashin.project.bean.VotersAdhaarUserBean;

public class VotersAdhaarRowMapper implements RowMapper<VotersAdhaarUserBean> {

	@Override
	public VotersAdhaarUserBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		VotersAdhaarExtractor extractor = new VotersAdhaarExtractor();
		return extractor.extractData(rs);
	}

}
