/**
 * 
 */
package com.hashin.project.dao;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author jintu.jacob@gmail.com
 * Oct 29, 2013
 * OnlineVoteMgmtDAOImpl
 */
public class OnlineVoteMgmtDAOImpl implements OnlineVoteMgmtDAO
{

    private static final Logger logger = Logger.getLogger(OnlineVoteMgmtDAOImpl.class);
    private JdbcTemplate jdbcTemplate;
    
    @Resource(name="dataSource")
    public void setDataSource(DataSource dataSource)
    {
	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Boolean getVotingStatus(String votingPIN, String electionId)
    {
	// TODO Auto-generated method stub
	return null;
    }

}
