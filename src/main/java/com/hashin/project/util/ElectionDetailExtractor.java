package com.hashin.project.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import com.hashin.project.bean.ElectionsBean;

public class ElectionDetailExtractor implements ResultSetExtractor<ElectionsBean>
{

    @Override
    public ElectionsBean extractData(ResultSet rs) throws SQLException,
	    DataAccessException
    {
	  ElectionsBean election =  new ElectionsBean(); 
		    
	  election.setElectId(""+rs.getInt("ele_id"));
	  election.setElectTitle(rs.getString("ele_title"));
	  election.setElectStartDate(rs.getDate("ele_start_dt").toString());
	  election.setElectEndDate(rs.getDate("ele_end_dt").toString());
	  election.setElectDescription(rs.getString("ele_desc"));
	  election.setVoterEnrollStat(rs.getString("vtr_enrl_stat"));
	  
	  return election;    
    }

}
