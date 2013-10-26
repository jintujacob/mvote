package com.hashin.project.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import com.hashin.project.bean.AdhaarUserBean;

public class AdhaarExtractor implements ResultSetExtractor<AdhaarUserBean>
{

    @Override
    public AdhaarUserBean extractData(ResultSet resultSet) throws SQLException,
	    DataAccessException
    {
	AdhaarUserBean user = new AdhaarUserBean();
	
	user.setAdhaarID(resultSet.getString("adhaar_ID"));
	user.setNameFirst(resultSet.getString("nameFirst"));
	user.setNameLast(resultSet.getString("nameLast"));
	user.setPhone(resultSet.getString("phone"));
	user.setAddress(resultSet.getString("address"));
	
	return user;
	
    }

}
