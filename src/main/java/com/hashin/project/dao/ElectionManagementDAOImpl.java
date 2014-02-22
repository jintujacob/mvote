/**
 * 
 */
package com.hashin.project.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hashin.project.bean.ConstituenciesBean;
import com.hashin.project.bean.ElectionStatesBean;
import com.hashin.project.bean.ElectionsBean;
import com.hashin.project.bean.ElectionsCandidatesBean;
import com.hashin.project.bean.ElectionsConstsBean;
import com.hashin.project.util.CandidatesRowMapper;
import com.hashin.project.util.ConstituencyMapper;
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
		election.getElectDescription() 
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
    public ElectionsCandidatesBean getCandidateInfoById(String candidateId)
    {
	Object[] parameters = new Object[] { candidateId };
	List<ElectionsCandidatesBean> candidateList = jdbcTemplate.query(
		SQLConstants.GET_CANDIDATE_DETAIL_BY_ID, parameters,
		new ElectionsCandidatesRowMapper());
	return candidateList.get(0);

    }

    @Override
    public List<ElectionsCandidatesBean> getCandidatesByName(String candName)
    {
	Object[] parameters = new Object[] { candName };
	List<ElectionsCandidatesBean> candidateList = jdbcTemplate.query(
		SQLConstants.GET_CANDIDATES_BY_NAME, parameters,
		new ElectionsCandidatesRowMapper());
	return candidateList;
    }

    @Override
    public List<ElectionsCandidatesBean> getCandidatesByConstituency(
	    String constId)
    {
	Object[] parameters = new Object[] { constId };
	List<ElectionsCandidatesBean> candidateList = jdbcTemplate.query(
		SQLConstants.GET_CANDIDATES_BY_CONSTITUENCY_ID, parameters,
		new ElectionsCandidatesRowMapper());
	return candidateList;
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
    public List<ElectionsConstsBean> searchElections(
	    ElectionsConstsBean toSearch)
    {
	Object[] parameters = new Object[] {
		"%" + toSearch.getElectTitle() + "%",
		"%" + toSearch.getConstId() + "%",
		"%" + toSearch.getStateId() + "%", };

	List<ElectionsConstsBean> electionList = jdbcTemplate.query(
		SQLConstants.SEARCH_ELECTIONS_BY_ALL_CRITERIA, parameters,
		new ElectionsConstsMapper());
	return electionList;
    }

    @Override
    public ElectionsConstsBean getElectionDetail(String  electId)
    {
	Object[] parameters = new Object[] { electId };
	List<ElectionsConstsBean> electionList = jdbcTemplate.query(
		SQLConstants.GET_ELECTION_DETAIL_BY_ID, parameters,
		new ElectionsConstsMapper());

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


}
