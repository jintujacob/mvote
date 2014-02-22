package com.hashin.project.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.hashin.project.bean.ElectionsConstsBean;

public class ElectionsConstsExtractor implements ResultSetExtractor<ElectionsConstsBean>
{

    @Override
    public ElectionsConstsBean extractData(ResultSet rs) throws SQLException,
	    DataAccessException
    {
	ElectionsConstsBean constElection = new ElectionsConstsBean();
	
	constElection.setUnitEleId(""+rs.getInt("unit_ele_id"));
	constElection.setElectId(""+rs.getInt("ele_id"));
	constElection.setConstId(""+rs.getInt("const_id"));
	constElection.setElectTitle(rs.getString("ele_title"));
	constElection.setElectStartDate(rs.getDate("ele_start_dt").toString());
	constElection.setElectEndDate(rs.getDate("ele_end_dt").toString());
	constElection.setElectDescription(rs.getString("ele_desc"));
	
	return constElection;
    }

}
