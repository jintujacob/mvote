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
import com.hashin.project.util.VotersAdvancedRowMapper;
import com.hashin.project.util.VotersRowMapper;

/**
 * @author jintu.jacob@gmail.com Oct 29, 2013 VoterListManagementDAOImpl
 */
public class VoterListManagementDAOImpl implements VoterListManagementDAO {
	private static final Logger logger = Logger
			.getLogger(VoterListManagementDAOImpl.class);
	private JdbcTemplate jdbcTemplate;

	@Resource(name = "dataSource")
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public VotersUserBean getVoterUserById(String voterID) {
		List<VotersUserBean> userList = null;

		Object[] parameters = new Object[] { voterID };
		userList = jdbcTemplate.query(SQLConstants.GET_VOTER_INFO_BY_VOTERID,
				parameters, new VotersRowMapper());

		logger.info("VoterListManagementDAOImpl #getVoterUserById Query=> executed");
		logger.info("VoterListManagementDAOImpl #getVoterUserById resultCount => "
				+ userList.size());

		if (userList.size() == 0) {
			return null;
		}
		return userList.get(0);
	}

	@Override
	public int insertNewVoter(VotersUserBean voterUser) {
		int numRows = 0;
		Object[] parameters = new Object[] { voterUser.getVotersId(),
				voterUser.getName(), voterUser.getConstituency(),
				voterUser.getPlace() };

		numRows = jdbcTemplate.update(SQLConstants.INSERT_NEW_VOTER_IN_VOTERS,
				parameters);
		logger.info("VoterListManagementDAOImpl #insertNewVoter Query=> "
				+ SQLConstants.INSERT_NEW_VOTER_IN_VOTERS + "executed");
		logger.info("VoterListManagementDAOImpl #insertNewVoter result => "
				+ numRows);

		return numRows;
		// non zero - insert is success
	}

	@Override
	public VotersUserBean updateVotersById(VotersUserBean voterUser) {
		// to be implemented
		return null;
	}

	@Override
	public List<VotersUserBean> searchVoter(VotersUserBean userToSearch) {
		Object[] parameters = new Object[] {
				"%" + userToSearch.getVotersId() + "%",
				"%" + userToSearch.getConstituency() + "%",
				"%" + userToSearch.getName() + "%",
				"%" + userToSearch.getLockOutFlag() + "%" };

		List<VotersUserBean> userList = null;

		userList = jdbcTemplate.query(
				SQLConstants.SEARCH_VOTERS_BY_MULTIPLE_PARAMS, parameters,
				new VotersAdvancedRowMapper());

		logger.info("VoterListManagementDAOImpl #searchVoter Query=> executed");
		logger.info("VoterListManagementDAOImpl #searchVoter result count=> "
				+ userList.size());
		return userList;
	}

	@Override
	public VotersUserBean getVoterDetailById(VotersUserBean userToSearch) 
	{
		Object[] parameters = new Object[] { userToSearch.getVotersId() };

		List<VotersUserBean> userList = null;

		userList = jdbcTemplate.query(SQLConstants.GET_VOTER_DETAIL_BY_ID,
				parameters, new VotersAdvancedRowMapper());

		logger.info("VoterListManagementDAOImpl #getVoterDetailById Query=> executed");
		logger.info("VoterListManagementDAOImpl #getVoterDetailById result count=> "
				+ userList.size());
		if (userList.size() == 0) {
			return null;
		}

		return userList.get(0);
	}

	@Override
	public int changeVoterStatusByVoterId(String votersId, String status) 
	{
		int numRows = 0;
		Object[] parameters = new Object[] { 
				status,
				votersId
		};

		numRows = jdbcTemplate.update(SQLConstants.UPDATE_VOTER_STATUS_IN_ENROLLMENT_TBL,
				parameters);
		logger.info("VoterListManagementDAOImpl #insertNewVoter resultCount => "+ numRows);

		return numRows;
	}

	

}
