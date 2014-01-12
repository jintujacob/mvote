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
import com.hashin.project.util.VotersAdvancedExtractor;
import com.hashin.project.util.VotersAdvancedRowMapper;
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
    
    private static String GET_VOTERS_BY_CONSTITUENCY = "GET_VOTERS_BY_CONSTITUENCY";
    private static String GET_VOTERS_BY_NAME_CONST = "GET_VOTERS_BY_NAME_CONST";
    private static String GET_VOTERS_BY_NAME_CONST_FLAG = "GET_VOTERS_BY_NAME_CONST_FLAG";

	    
    
    
    
    //private static String SQL_UPDATE_VOTER_BY_ID = "";

    
    
    @Resource(name="dataSource")
    public void setDataSource(DataSource dataSource)
    {
	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    
    @Override
    public VotersUserBean getVoterUserById(String voterID)
    {
	Object[] parameters = new Object[] {voterID};
	List<VotersUserBean> userList= jdbcTemplate.query(SQLConstants.GET_VOTER_INFO_BY_VOTERID, 
		parameters, new VotersRowMapper());

	logger.debug("VoterListManagementDAOImpl #getVoterUserById Query=> executed" );
	logger.debug("VoterListManagementDAOImpl #getVoterUserById result => "+ userList.get(0).getConstituency());
	return userList.get(0);        
    }


/*    @Override
    public List<VotersUserBean> getVotersByQueryName(String queryName,  Object[] parameters)
    {
	List<VotersUserBean> userList = null;
	String query = null;
	
	if( queryName.equals(GET_VOTERS_BY_CONSTITUENCY )){	
	    query = SQLConstants.GET_VOTERS_BY_CONSTITUENCY ;
	}else if ( queryName.equals(GET_VOTERS_BY_NAME_CONST )){
	    query = SQLConstants.GET_VOTERS_BY_NAME_CONST ;
	}else if ( queryName.equals(GET_VOTERS_BY_NAME_CONST_FLAG )){
	    query = SQLConstants.GET_VOTERS_BY_NAME_CONST_FLAG ;
    	}
	
	
	userList= jdbcTemplate.query(query, parameters, new VotersRowMapper());

	logger.debug("VoterListManagementDAOImpl #getVotersByQueryName Query=> "+ query + "executed" );
	logger.debug("VoterListManagementDAOImpl #getVotersByQueryName result => "+ userList.get(0).getConstituency());
	return userList; 
    }
*/

    @Override
    public int insertNewVoter(VotersUserBean voterUser)
    {
	int numRows = 0;
	Object[] parameters = new Object[] { voterUser.getVotersId(),
		voterUser.getName(), voterUser.getConstituency(),
		voterUser.getPlace() };
	
	numRows = jdbcTemplate.update( SQLConstants.INSERT_NEW_VOTER_IN_VOTERS, parameters);
	logger.debug("VoterListManagementDAOImpl #insertNewVoter Query=> "+ SQLConstants.INSERT_NEW_VOTER_IN_VOTERS+ "executed" );
	logger.debug("VoterListManagementDAOImpl #insertNewVoter result => "+ numRows);
	
	return numRows;
	//non zero - insert is success
    }


    @Override
    public VotersUserBean updateVotersById(VotersUserBean voterUser)
    {
	// to be implemented
	return null;
    }


    @Override
    public List<VotersUserBean> searchVoter(VotersUserBean userToSearch)
    {
	Object[] parameters = new Object[] {
		"%"+userToSearch.getVotersId()+"%", 
		"%"+userToSearch.getConstituency()+"%", 
		"%"+userToSearch.getName()+"%", 
		"%"+userToSearch.getLockOutFlag()+"%" };
	
	List<VotersUserBean> userList = null;
	
	userList= jdbcTemplate.query(SQLConstants.SEARCH_VOTERS_BY_MULTIPLE_PARAMS, 
		parameters, new VotersAdvancedRowMapper());

	logger.debug("VoterListManagementDAOImpl #searchVoter Query=> executed" );
	logger.debug("VoterListManagementDAOImpl #searchVoter result count=> "+ userList.size());
	return userList;        
    }


}
