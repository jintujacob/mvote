package com.hashin.project.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hashin.project.bean.ConstituenciesBean;

public class ConstituencyMapper implements RowMapper<ConstituenciesBean> {

	@Override
	public ConstituenciesBean mapRow(ResultSet rs, int rowCount)
			throws SQLException {
		ConstituencyExtractor extractor = new ConstituencyExtractor();
		return extractor.extractData(rs);
	}

}
