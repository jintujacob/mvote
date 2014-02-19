package com.hashin.project.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.hashin.project.bean.ElectionStatesBean;

public class ElectionStatesExtractor implements
	ResultSetExtractor<ElectionStatesBean>
{

    @Override
    public ElectionStatesBean extractData(ResultSet rs) throws SQLException,
	    DataAccessException
    {
	ElectionStatesBean state = new ElectionStatesBean();
	state.setStateId(rs.getString("st_id"));
	state.setStateName(rs.getString("st_name"));
	state.setStateDesc(rs.getString("st_desc"));
	
	state.toString();
	
	return state;
	
    }

}
