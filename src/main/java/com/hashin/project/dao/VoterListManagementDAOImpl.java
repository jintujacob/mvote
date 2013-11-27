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
    
    private static String GET_VOTERS_NAME = "GET_VOTERS_NAME";
    private static String GET_VOTERS_NAME_CONST = "GET_VOTERS_NAME_CONST";
    private static String GET_VOTERS_NAME_CONST_FLAG = "GET_VOTERS_NAME_CONST_FLAG";

    private static String SQL_GET_VOTER_INFO_ID = "select * from voters where voters_id = '?' " ;

/*	    
    private static String SQL_GET_VOTER_INFO_ID =
	    "select va.fk_voters_id,va.fk_adhaar_id, va.voting_pin, va.gen_date, va.lockout_flag, " +
	    "v.name, v.place, " +
	    "c.const_name, c.const_state " +
	    "from voters_adhaar va, voters v, constituencies c " +
	    "where va.fk_voters_id = v.voters_id and v.const=c.const_id " +
	    "and va.fk_voters_id = '?'"; 
*/    
    private static String SQL_GET_VOTERS_NAME = 
	    "select va.fk_voters_id,va.fk_adhaar_id, va.voting_pin, va.gen_date, va.lockout_flag, " +
	    "v.name, v.place, " +
	    "c.const_name, c.const_state " +
	    "from voters_adhaar va, voters v, constituencies c " +
	    "where va.fk_voters_id = v.voters_id and v.const=c.const_id " +
	    "and v.const = '?' "; 
	    
    private static String SQL_GET_VOTERS_NAME_CONST = 
	    "select va.fk_voters_id,va.fk_adhaar_id, va.voting_pin, va.gen_date, va.lockout_flag, " +
	    "v.name,v.place, c.const_name, c.const_state " +
	    "from voters_adhaar va, voters v, constituencies c " +
	    "where va.fk_voters_id = v.voters_id and v.const=c.const_id " +
	    "and v.const = '?' and v.name like '%?%' ";
    
    private static String SQL_GET_VOTERS_NAME_CONST_FLAG = 
	    "select va.fk_voters_id,va.fk_adhaar_id, va.voting_pin, va.gen_date, va.lockout_flag, " +
	    "v.name,v.place, c.const_name, c.const_state " +
	    "from voters_adhaar va, voters v, constituencies c " +
	    "where va.fk_voters_id = v.voters_id and v.const=c.const_id " +
	    "and v.const = '?' and v.name like '%?%' and va.lockout_flag='?";
    
    private static String SQL_INSERT_NEW_VOTER = 
	    "insert into voters ( voters_id, name, const, place) " +
	    "values ('?', '?', ?, '?')" ;

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
	List<VotersUserBean> userList= jdbcTemplate.query(SQL_GET_VOTER_INFO_ID, parameters, new VotersRowMapper());

	logger.debug("VoterListManagementDAOImpl #getVoterUserById Query=> executed" );
	logger.debug("VoterListManagementDAOImpl #getVoterUserById result => "+ userList.get(0).getConstituency());
	return userList.get(0);        
    }


    @Override
    public List<VotersUserBean> getVotersByQueryName(String queryName,  Object[] parameters)
    {
	List<VotersUserBean> userList = null;
	String query = null;
	
	if( queryName.equals(GET_VOTERS_NAME )){	
	    query = SQL_GET_VOTERS_NAME ;
	}else if ( queryName.equals(GET_VOTERS_NAME_CONST )){
	    query = SQL_GET_VOTERS_NAME_CONST ;
	}else if ( queryName.equals(GET_VOTERS_NAME_CONST_FLAG )){
	    query = SQL_GET_VOTERS_NAME_CONST_FLAG ;
    	}
	
	
	userList= jdbcTemplate.query(query, parameters, new VotersRowMapper());

	logger.debug("VoterListManagementDAOImpl #getVotersByQueryName Query=> "+ query + "executed" );
	logger.debug("VoterListManagementDAOImpl #getVotersByQueryName result => "+ userList.get(0).getConstituency());
	return userList; 
    }


    @Override
    public int insertNewVoter(VotersUserBean voterUser)
    {
	int numRows = 0;
	Object[] parameters = new Object[] { voterUser.getVotersId(),
		voterUser.getName(), voterUser.getConstituency(),
		voterUser.getPlace() };
	
	numRows = jdbcTemplate.update( SQL_INSERT_NEW_VOTER, parameters);
	logger.debug("VoterListManagementDAOImpl #insertNewVoter Query=> "+ SQL_INSERT_NEW_VOTER + "executed" );
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


}
