package com.hashin.project.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.hashin.project.bean.VotersAdhaarUserBean;

public class VotersAdhaarExtractor implements
		ResultSetExtractor<VotersAdhaarUserBean> {

	@Override
	public VotersAdhaarUserBean extractData(ResultSet rs)
			throws SQLException, DataAccessException {
		
		VotersAdhaarUserBean user =  new VotersAdhaarUserBean();
		user.setAdhaarId(rs.getString("fk_adhaar_id"));
		user.seteElectionId(rs.getString("e_election_id"));
		user.setVotingPIN(rs.getString("voting_pin"));
		user.setVotersId(rs.getString("fk_voters_id"));
		user.setGenDate(rs.getDate("gen_date").toString());
		user.setLockOutFlag(rs.getString("lockout_flag"));
		
		return user;
	}

}
