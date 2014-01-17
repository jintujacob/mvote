package com.hashin.project.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.hashin.project.bean.ConstituenciesBean;

public class ConstituencyExtractor implements
		ResultSetExtractor<ConstituenciesBean> {

	@Override
	public ConstituenciesBean extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		ConstituenciesBean bean = new ConstituenciesBean();
		
		bean.setConstId(rs.getString("const_id"));
		bean.setConstName(rs.getString("const_name"));
		bean.setConstState(rs.getString("const_state"));
		
		return bean;
	}

}
