/**
 * 
 */
package com.hashin.project.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hashin.project.bean.VotersUserBean;
import com.hashin.project.util.VotersRowMapper;

/**
 * @author jintu.jacob@gmail.com
 * Oct 29, 2013
 * VoterListManagementDAOImpl
 */
public class VoterListManagementDAOImpl implements VoterListManagementDAO
{
    private static final Logger logger = Logger.getLogger(VoterListManagementDAOImpl.class);
    private JdbcTemplate jdbcTemplate;
    
    @Resource(name="dataSource")
    public void setDataSource(DataSource dataSource)
    {
	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    
    @Override
    public VotersUserBean getVoterUserById(String voterID)
    {
	String query = "select * from voters where voters_id= ?";
	Object[] parameters = new Object[] {voterID};
	List<VotersUserBean> userList= jdbcTemplate.query(query, parameters, new VotersRowMapper());

	logger.debug("VoterListManagementDAOImpl #getVoterUserById Query=> executed" );
	logger.debug("VoterListManagementDAOImpl #getVoterUserById result => "+ userList.get(0).getConstituency());
	return userList.get(0);        
    }


}
