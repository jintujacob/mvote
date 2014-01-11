/**
 * 
 */
package com.hashin.project.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hashin.project.bean.ElectionsBean;
import com.hashin.project.util.ElectionsRowMapper;

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

    
    
    /**
     * returns non-zero/1 when the user is voted for the provided election
     * returns zero when user is not voted.
     */
    @Override
    public int getVotingStatus(String votingPIN, String electionId)
    {
	Object[] parameters = new Object[] {votingPIN, electionId };
	int rowCount = jdbcTemplate.queryForInt(SQLConstants.GET_VOTINGSTAT_BY_VOTINGPIN, parameters);
	return rowCount;
    }

    
    /**
     * returns non-zero/1 when update is successful
     * returns zero when update fails
     */
    @Override
    public int udpateVotingStatusByPin(String votingPIN, String electionId)
    {
	String query = "update elections_votingstats " +
			"set voting_stat = 'Y' " +
			"where voting_pin = '?' " +
			"and ele_id = ?";
	Object[] parameters = new Object[] {votingPIN, electionId};
	int rowCount = jdbcTemplate.update(SQLConstants.UPDATE_VOTINGSTAT_BY_VOTINGPIN, parameters);
	logger.debug("____________________rowcount for the update____________"+rowCount);
	
	return rowCount;
    }
    
    

}
