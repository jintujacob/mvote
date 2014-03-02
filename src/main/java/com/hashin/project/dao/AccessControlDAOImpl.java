/**
 * 
 */
package com.hashin.project.dao;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.hashin.project.bean.ConstituenciesBean;
import com.hashin.project.bean.ElectionStatesBean;
import com.hashin.project.bean.ElectionsBean;
import com.hashin.project.bean.ElectionsCandidatesBean;
import com.hashin.project.bean.ElectionsConstsBean;
import com.hashin.project.util.CandidatesRowMapper;
import com.hashin.project.util.ConstituencyMapper;
import com.hashin.project.util.ElectionDetailRowMapper;
import com.hashin.project.util.ElectionsCandidatesRowMapper;
import com.hashin.project.util.ElectionsConstsMapper;
import com.hashin.project.util.ElectionsStatesMapper;

/**
 * @author jintu.jacob@gmail.com Oct 29, 2013 ElectionManagementDAOImpl
 */
public class AccessControlDAOImpl implements AccessControlDAO
{
    private static final Logger logger = Logger
	    .getLogger(AccessControlDAOImpl.class);
    private JdbcTemplate jdbcTemplate;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource)
    {
	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int validatedSystemUserCount(String uid, String password)
    {
	Object[] parameters = new Object[] {uid, password };
	int rowCount = jdbcTemplate.queryForInt(SQLConstants.VERIFY_SYSTEM_USER_LOGIN_CRED, parameters);
	return rowCount;
    }

    

}
