package com.hashin.project.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.hashin.project.bean.ElectionsCandidatesBean;

public class CandidatesExtractor implements ResultSetExtractor<ElectionsCandidatesBean>
{
    @Override
    public ElectionsCandidatesBean extractData(ResultSet rs)
	    throws SQLException, DataAccessException
    {
	ElectionsCandidatesBean bean = new ElectionsCandidatesBean();
	
	bean.setEleCandidateId(Integer.toString(rs.getInt("ele_cand_id")));
	bean.setCandId(Integer.toString(rs.getInt("cand_id")));
	bean.setCandName(rs.getString("cand_name"));
	bean.setCandLogo(rs.getString("cand_logo"));
	bean.setCandBio(rs.getString("cand_bio"));

	return bean;
    }
}
