package com.hashin.project.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.hashin.project.bean.CandidatesBean;
import com.hashin.project.bean.ElectionsBean;
import com.hashin.project.bean.ElectionsCandidatesBean;
import com.hashin.project.bean.ElectionsConstsBean;
import com.hashin.project.bean.VotersAdhaarUserBean;
import com.hashin.project.bean.VotersUserBean;
import com.hashin.project.dao.ElectionManagementDAO;
import com.hashin.project.dao.OnlineVoteMgmtDAO;
import com.hashin.project.dao.UserEnrollmentDAO;
import com.hashin.project.dao.UserEnrollmentDAOImpl;
import com.hashin.project.dao.VoterListManagementDAO;
import com.hashin.project.util.Encryption;

public class OnlineVotingServiceImpl implements OnlineVotingService {

	@Autowired
	private VoterListManagementDAO voterListManagementDao;

	@Autowired
	private UserEnrollmentDAO userEnrollmentDao;

	@Autowired
	private OnlineVoteMgmtDAO onlineVoteMgmtDao;

	@Autowired
	private ElectionManagementDAO electionsMgmtDao;

	private static final Logger logger = Logger
			.getLogger(UserEnrollmentDAOImpl.class);

	
	public Boolean isValidUser(String eElectionId, String votingPin) throws Exception
	{
	    	Boolean isValidUser = false;
	    	
	    	logger.debug("__________1 - calling getEnrollmentStatus(votingPin, adhaarId, voterId)");
		votingPin =  Encryption.getInstance().encrypt(votingPin);
		if (getEnrollmentStatus(eElectionId, votingPin)) {
		    isValidUser = true;
		}
	    
		return isValidUser;
	}
	
	
	@Override
	public List<ElectionsConstsBean> manageVoterEntry(String eElectionId,	String votingPin)throws Exception {
		List<ElectionsConstsBean> electionsList = null;
		VotersUserBean voter = userEnrollmentDao.getVoterIdByEnrolledEID(eElectionId) ;
		
		electionsList = getElectionsListByConst(voter.getConstituency());
		return electionsList;
	}

	@Override
	public List<ElectionsCandidatesBean> getCandidatesList(String voterEid,
			String electionId, String unitElectionId) {
		List<ElectionsCandidatesBean> candidateList = null;

		// check if the user already voted or not // allow if not voted
		if (!getVotingStatus(voterEid, electionId)) {
			// get list of candidates for the unit election id
			candidateList = electionsMgmtDao
					.getCandidatesListByUnitId(new Integer(unitElectionId));
			if (candidateList.isEmpty())
				candidateList = null;
		}
		return candidateList;
	}

	/**
	 * transaction managed service methode. update votecount and
	 * statGET_VOTERS_BY_CONSTITUENCYus change should happen parallel.
	 */
	@Override
	@Transactional
	public String submitVoteforCandidate(String voterEid, String candidateId,
			String electionId) {
		String returnMessage = null;

		int rowCount1 = electionsMgmtDao
				.increamentVoteCountByCandidate(candidateId);
		int rowCount2 = onlineVoteMgmtDao.udpateVotingStatusByPin(voterEid,
				electionId);

		if (rowCount1 == 1 && rowCount2 == 1)
			returnMessage = "Vote is updated Successfully";

		return returnMessage;
	}

	/*private Boolean getEnrollmentStatus(String votingPin, String adhaarId,
			String voterId) {
		Boolean enrollStatus = false;
		int rowCount = userEnrollmentDao.getEnrollmentStatus(votingPin,
				adhaarId, voterId);
		if (rowCount == 1)
			enrollStatus = true;

		return enrollStatus;
	}
*/	
	private Boolean getEnrollmentStatus(String eElectionId, String votingPin ) {
		Boolean enrollStatus = false;
		int rowCount = userEnrollmentDao.getEnrollmentStatus(eElectionId,votingPin);
		if (rowCount == 1)
			enrollStatus = true;

		return enrollStatus;
	}
	

	private List<ElectionsConstsBean> getElectionsListByConst(String constId) {
		List<ElectionsConstsBean> electionsList = null;
		electionsList = electionsMgmtDao.getElectionsListByConst(constId);
		return electionsList;
	}

	private Boolean getVotingStatus(String voterEid, String electionId) {
		Boolean votingStatus = false;
		int rowCount = onlineVoteMgmtDao.getVotingStatus(voterEid, electionId);
		if (rowCount != 0) {
			// not zero means, user is already voted
			votingStatus = true;
		}
		return votingStatus;
	}

}
