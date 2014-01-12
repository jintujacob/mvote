package com.hashin.project.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.hashin.project.bean.VotersUserBean;

public class VotersAdvancedExtractor implements
	ResultSetExtractor<VotersUserBean>
{

    @Override
    public VotersUserBean extractData(ResultSet rs) throws SQLException,
	    DataAccessException
    {
	VotersUserBean user = new VotersUserBean();
	
	user.setVotersId(rs.getString("voters_id"));
	user.setName(rs.getString("name"));
	user.setPlace(rs.getString("place"));
	
	user.setAdhaarId(rs.getString("fk_adhaar_id"));
	user.setVotingPin(rs.getString("voting_pin"));
	user.setLockOutFlag(rs.getString("lockout_flag"));
	user.setConstituency(rs.getString("const_name")+", "+rs.getString("const_state") );
	return user;
    }

}
