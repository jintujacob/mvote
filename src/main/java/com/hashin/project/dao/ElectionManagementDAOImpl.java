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
import com.hashin.project.bean.ElectionsCandidatesBean;
import com.hashin.project.bean.ElectionsConstsBean;
import com.hashin.project.util.ElectionsCandidatesRowMapper;
import com.hashin.project.util.ElectionsConstsMapper;
import com.hashin.project.util.ElectionsRowMapper;

/**
 * @author jintu.jacob@gmail.com Oct 29, 2013 ElectionManagementDAOImpl
 */
public class ElectionManagementDAOImpl implements ElectionManagementDAO {
	private static final Logger logger = Logger
			.getLogger(ElectionManagementDAOImpl.class);
	private JdbcTemplate jdbcTemplate;

	@Resource(name = "dataSource")
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public int addNewElection(ElectionsBean election) {
		//String query = "insert into elections (ele_title, ele_start_dt, ele_end_dt, ele_desc) values (?, ?, ?, ?)";
		Object[] parameters = new Object[] { 
				election.getElectTitle(),
				election.getElectStartDate(), 
				election.getElectEndDate(),
				election.getElectDescription() };
		int numRows = jdbcTemplate.update(SQLConstants.INSERT_NEW_ELECTION, parameters);

		logger.debug("Query =>  insert into elections "
				+ "(ele_title, ele_start_dt, ele_end_dt, ele_desc) "
				+ "values (?, ?, ?, ?)");
		logger.debug("Result => rowCount= " + numRows);

		return numRows;
	}

	public List<ElectionsBean> getAllElections() {
		List<ElectionsBean> electionsList = jdbcTemplate.query(SQLConstants.GET_ALL_ELECTIONS_BY_CRITERIA,
				new ElectionsRowMapper());
		return electionsList;
	}

	public ElectionsBean getElectionById(int electId) {
		Object[] parameters = new Object[] { electId };
		List<ElectionsBean> electionsList = jdbcTemplate.query(SQLConstants.GET_ELECTION_DETAIL_BY_ID,
				parameters, new ElectionsRowMapper());
		return electionsList.get(0);
	}

	public List<ElectionsBean> searchElectionsWildCard(String searckKey) {
		Object[] parameters = new Object[] { searckKey };
		List<ElectionsBean> electionList = jdbcTemplate.query(SQLConstants.GET_ALL_ELECTIONS_BY_CRITERIA,
				parameters, new ElectionsRowMapper());
		return electionList;
	}

	public int removeElectionById(int electId) {
		String query = "delete from user where user_id= ?";
		Object[] parameters = new Object[] { electId };
		int numRows = jdbcTemplate.update(query);
		return numRows;
	}

	@Override
	public List<ElectionsConstsBean> getElectionsListByConst(String constId) {
		Object[] parameters = new Object[] { constId };
		List<ElectionsConstsBean> electionList = jdbcTemplate.query(SQLConstants.GET_ELECTIONS_BY_CONST_ID,
				parameters, new ElectionsConstsMapper());
		return electionList;
	}

	@Override
	public List<ElectionsCandidatesBean> getCandidatesListByUnitId(
			int unitElectionId) {
		List<ElectionsCandidatesBean> candidateList = null;
		Object[] parameters = new Object[] { unitElectionId };
		candidateList = jdbcTemplate.query(SQLConstants.GET_CANDIDATES_BY_UNIT_ELE_ID, parameters,
				new ElectionsCandidatesRowMapper());
		return candidateList;
	}

	@Override
	public int increamentVoteCountByCandidate(String candidateId) {
		int numRows = 0;
		Object[] parameters = new Object[] { candidateId };
		numRows = jdbcTemplate.update(SQLConstants.INCREMENT_VOTECOUNT_BY_CANDIDATE_ID, parameters);

		// non zero return ==> update is successfully executed.
		// exception/zero on return ==> update failed
		return numRows;
	}

	@Override
	public Boolean enrollNewCandidate(ElectionsCandidatesBean candidate)
	{
	    
	    return null;
	}

	@Override
	public ElectionsCandidatesBean getCandidateInfoById(String candidateId)
	{
	    Object[] parameters = new Object[] { candidateId};
	    List<ElectionsCandidatesBean> candidateList = jdbcTemplate.query(SQLConstants.GET_CANDIDATE_DETAIL_BY_ID,
				parameters, new ElectionsCandidatesRowMapper());
	    return candidateList.get(0);
		
	}

	@Override
	public List<ElectionsCandidatesBean> getCandidatesByName(String candName)
	{
	    // TODO Auto-generated method stub
	    return null;
	}

	@Override
	public List<ElectionsCandidatesBean> getCandidatesByConstituency(
		String constId)
	{
	    // TODO Auto-generated method stub
	    return null;
	}

}
