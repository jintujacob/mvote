package com.hashin.project.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.hashin.project.bean.ElectionsResultsBean;

public class ElectionsResultsConstsExtractor implements
	ResultSetExtractor<ElectionsResultsBean>
{

    @Override
    public ElectionsResultsBean extractData(ResultSet rs) throws SQLException,
	    DataAccessException
    {
	ElectionsResultsBean consts = new ElectionsResultsBean();
	
	//ER.unit_ele_id, ER.vote_count, C.cand_id,  "C.cand_name
	consts.setUnitEleId(rs.getString("unit_ele_id"));
	consts.setConstVoteCount(rs.getInt("vote_count")+"");
	consts.setCandId(rs.getInt("cand_id")+"");
	consts.setCandName(rs.getString("cand_name"));
	
	return consts;
    }

}
