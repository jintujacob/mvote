/**
 * 
 */
package com.hashin.project.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.hashin.project.bean.VotersUserBean;

/**
 * @author jintu.jacob@gmail.com
 * Nov 9, 2013
 * VotersExtractor
 */
public class VotersExtractor implements ResultSetExtractor<VotersUserBean>
{
    @Override
    public VotersUserBean extractData(ResultSet rs) throws SQLException,
	    DataAccessException
    {
	VotersUserBean user = new VotersUserBean();
	
	user.setVotersId(rs.getString("fk_voters_id"));
	user.setAdhaarId(rs.getString("fk_adhaar_id"));
	user.setVotingPin(rs.getString("voting_pin"));
	user.setGenDate(rs.getString("gen_date"));
	user.setLockOutFlag(rs.getString("lockout_flag"));
	user.setName(rs.getString("name"));
	user.setPlace(rs.getString("place"));
	user.setNameFirst(rs.getString("firstname")); // to be added to the db
	user.setNameLast(rs.getString("lastname"));	// to be added to the db
	user.setConstituency(rs.getString("const_name"));
	user.setState(rs.getString("const_state"));
	
	return user;
    }

}
