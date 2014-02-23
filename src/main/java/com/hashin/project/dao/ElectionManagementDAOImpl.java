/**
 * 
 */
package com.hashin.project.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

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
public class ElectionManagementDAOImpl implements ElectionManagementDAO
{
    private static final Logger logger = Logger
	    .getLogger(ElectionManagementDAOImpl.class);
    private JdbcTemplate jdbcTemplate;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource)
    {
	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int addNewElection(ElectionsBean election)
    {
	Object[] parameters = new Object[] { 
		election.getElectTitle(),
		election.getElectStartDate(), 
		election.getElectEndDate(),
		election.getElectDescription(), 
		"N",
		"N"
		};
	int numRows = jdbcTemplate.update(SQLConstants.INSERT_NEW_ELECTION,
		parameters);
	return numRows;
    }

    @Override
    public List<ElectionsConstsBean> getElectionsListByConst(String constId)
    {
	Object[] parameters = new Object[] { constId };
	List<ElectionsConstsBean> electionList = jdbcTemplate.query(
		SQLConstants.GET_ELECTIONS_BY_CONST_ID, parameters,
		new ElectionsConstsMapper());
	return electionList;
    }

    @Override
    public List<ElectionsCandidatesBean> getCandidatesListByUnitId(
	    int unitElectionId)
    {
	List<ElectionsCandidatesBean> candidateList = null;
	Object[] parameters = new Object[] { unitElectionId };
	candidateList = jdbcTemplate.query(
		SQLConstants.GET_CANDIDATES_BY_UNIT_ELE_ID, parameters,
		new CandidatesRowMapper());
	return candidateList;
    }

    @Override
    public int increamentVoteCountByCandidate(String candidateId)
    {
	int numRows = 0;
	Object[] parameters = new Object[] { candidateId };
	numRows = jdbcTemplate.update(
		SQLConstants.INCREMENT_VOTECOUNT_BY_CANDIDATE_ID, parameters);

	// non zero return ==> update is successfully executed.
	// exception/zero on return ==> update failed
	return numRows;
    }

    
    @Override
    public List<ConstituenciesBean> getAllConstituencies()
    {
	List<ConstituenciesBean> constList = null;
	constList = jdbcTemplate.query(SQLConstants.GET_ALL_CONSTS,
		new ConstituencyMapper());
	logger.debug("_______________________Fetched the constituecies list >> "
		+ constList.size());
	return constList;
    }

    @Override
    public List<ElectionsBean> searchElections(String titleToSearch)
    {
	Object[] parameters = new Object[] {
		"%" + titleToSearch + "%"  };

	List<ElectionsBean> electionList = jdbcTemplate.query(
		SQLConstants.SEARCH_ELECTIONS_BY_ALL_CRITERIA, parameters,
		new ElectionDetailRowMapper());
	return electionList;
    }

    @Override
    public ElectionsBean getElectionDetail(String  electId)
    {
	Object[] parameters = new Object[] { electId };
	List<ElectionsBean> electionList = jdbcTemplate.query(
		SQLConstants.GET_ELECTION_DETAIL_BY_ID, parameters,
		new ElectionDetailRowMapper());

	if (electionList.size() > 0)
	{
	    return electionList.get(0);
	}
	return null;
    }
    
    
    @Override
    public ElectionsBean getElectionDetail(String eleTitle, String eleDesc,
		String eleStartDt)
    {
	Object[] parameters = new Object[] { 
			eleTitle,
			eleDesc,
			eleStartDt
		};
	List<ElectionsBean> electionList = jdbcTemplate.query(
		SQLConstants.GET_LAST_ELECTION_BASIC_ADDED, parameters,
		new ElectionDetailRowMapper());

	if (electionList.size() > 0)
	{
	    return electionList.get(0);
	}
	return null;
    }
    
    

    @Override
    public List<ElectionStatesBean> getStatesListByEleId(String electId)
    {
	Object[] parameters = new Object[] { electId };
	List<ElectionStatesBean> statesList =  jdbcTemplate.query(
		SQLConstants.GET_STATES_BY_ELECTION_ID, parameters,
		new ElectionsStatesMapper());
	
	if(statesList.size() > 0 ){
	    return statesList;
	}

	return null;
    }

	@Override
	public List<ElectionStatesBean> getAllStatesForMenu() 
	{
		List<ElectionStatesBean> statesList =  jdbcTemplate.query(
			SQLConstants.GET_ALL_STATES, new ElectionsStatesMapper());
		
		if(statesList.size() > 0 ){
		    return statesList;
		}

		return null;
	}

	@Override
	public int enrollVotersForElection(String electId)
	{
	    Object[] parameters = new Object[] { electId };
	    int numRows = jdbcTemplate.update(SQLConstants.BATCH_RUN_INSERT_VOTERS_TO_VOTINGSTAT,
			parameters);
	    return numRows;
	 
	}

	@Override
	public Boolean getVoterEnrollStatusByElection(String electId)
	{
	    Boolean enrlmntStatus = false;
	    int enrldCount = 0;
	    Object[] parameters = new Object[] {   electId  	};
	    
	    enrldCount =  jdbcTemplate.queryForInt(SQLConstants.GET_ELECTIONS_VOTER_ENRLMNT_STATUS, parameters);
	    if(enrldCount != 0 ){
		enrlmntStatus = true;
	    }
	    return enrlmntStatus;
	}

	@Override
	public int updateEnrlmntStatusForElection(String electId)
	{
	    Object[] parameters = new Object[] { 
		    "Y",
		    electId 
	    	};
	    int numRows = jdbcTemplate.update(SQLConstants.UPDATE_VOTER_ENRLMNT_STAT_FOR_ELE,
			parameters);
	    return numRows;
	}

	@Override
	public int deleteElectionInElections(String electId)
	{
	    //Not deleting changing the status elections:vtr_enrlmnt_stat to "N"
	    //key change testing is still pending;
	    Object[] parameters = new Object[] { 
		    "Y",
		    electId 
	    	};
	    int numRows = jdbcTemplate.update(SQLConstants.DELETE_ELE_IN_ELECTIONS,
			parameters);
	    return numRows;
	}

	@Override
	public int createUnitConstituencyElections(String electId,
		List<ElectionStatesBean> stateList)
	{
	    List<String> paramList = new ArrayList<String>();
	    Object[] parameters = new Object[] {};
	    int i = 0;
	    
	    paramList.add(electId);	//in first position.add(electId);	
	    parameters[i++] = electId;
	    logger.debug("++++++++++++++++++++++++++++++++++"+paramList.toString());
	   
	    
	    String qMarks = "";
	    int itr = 0;
	    for (ElectionStatesBean state: stateList) 
	    {
		 qMarks += " ? ";
		 
		 paramList.add(state.getStateId());
		 parameters[i++] = state.getStateId(); 
		 if(itr != stateList.size()-1){
		     qMarks += "," ;
		 }
		 itr++;
		 logger.debug("______________________________"+paramList.toString());
	    }
	    
	    String SQL = "INSERT INTO elections_consts (ele_id, const_id) SELECT ? , const_id FROM "
			+ "(select CON.const_id from constituencies CON, elections_states STA "
			+ "where CON.const_state = STA.st_id and STA.st_id in("+ qMarks + ")) as test";

	    logger.debug("______________>> constructed Query :"+SQL);
	    logger.debug("--------------->parameters---->>>"+parameters.toString());
	    
	    
	    int numRows = jdbcTemplate.update(SQL,parameters);
	    return numRows;
	}

	@Override
	public List<ConstituenciesBean> searchConstsByName(String constName)
	{
	    	constName = "%" + constName + "%";
	    	logger.debug("___________ /searchConsts/DAO"+ constName);
	    	Object[] parameters = new Object[] { constName };
		List<ConstituenciesBean> constList =  jdbcTemplate.query(
			SQLConstants.GET_CONSTS_BY_NAME, parameters,
			new ConstituencyMapper());
		
		if(constList.size() > 0 ){
		    return constList;
		}
		return null;

	}

	@Override
	public List<ElectionsCandidatesBean> searchCandidateByNameConst(
		String candName, String constId)
	{
	    	logger.debug("___________ /searchCandidateByNameConst/DAO"+ candName +"|"+ constId);
	    	Object[] parameters = new Object[] { 
	    		"%"+ candName + "%", 
	    		"%"+ constId + "%"
	    	     };
		List<ElectionsCandidatesBean> candList =  jdbcTemplate.query(
			SQLConstants.GET_CANDIDATE_LIST_BY_NAME_CONST, parameters,
			new ElectionsCandidatesRowMapper());
		
		if(candList.size() > 0 ){
		    return candList;
		}
		return null;	
	}



}
