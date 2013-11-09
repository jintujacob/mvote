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
	
	user.setName(rs.getString("name"));
	user.setNameFirst(rs.getString("firstname")); // to be added to the db
	user.setNameLast(rs.getString("lastname"));	// to be added to the db
	user.setConstituency(rs.getString("const"));
	user.setPlace(rs.getString("place"));
	user.setVotersId(rs.getString("voters_id"));
	
	return user;
    }

}
