package com.hashin.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hashin.project.bean.ElectionsBean;
import com.hashin.project.bean.ElectionsConstsBean;
import com.hashin.project.bean.VotersUserBean;
import com.hashin.project.dao.ElectionManagementDAO;
import com.hashin.project.dao.OnlineVoteMgmtDAO;
import com.hashin.project.dao.UserEnrollmentDAO;
import com.hashin.project.dao.VoterListManagementDAO;

public class OnlineVotingServiceImpl implements OnlineVotingService
{

        
    @Autowired
    private VoterListManagementDAO voterListManagementDao;
    
    @Autowired
    private UserEnrollmentDAO userEnrollmentDao;

    @Autowired
    private OnlineVoteMgmtDAO onlineVoteMgmtDao;
    
    @Autowired
    private ElectionManagementDAO electionsMgmtDao;
    
    
    @Override
    public List<ElectionsConstsBean> manageVoterEntry(String votingPin,
	    String adhaarId, String voterId)
    {
	//identify the constituency for the user
	//get the list of elections applicable for this voters constituency
	List<ElectionsConstsBean> electionsList = null;
	
	if(getEnrollmentStatus(votingPin, adhaarId, voterId))
	{
	    VotersUserBean voter = voterListManagementDao.getVoterUserById(voterId);
	    electionsList = getElectionsListByConst(voter.getConstituency());
	}
	return electionsList;
    }

   
    private Boolean getEnrollmentStatus(String votingPin, String adhaarId, String voterId)
    {
	Boolean enrollStatus = false;
	int rowCount = userEnrollmentDao.getEnrollmentStatus(votingPin, adhaarId, voterId);
	if(rowCount==1)
	    enrollStatus = true;
	
	return enrollStatus;
    }
    
    private List<ElectionsConstsBean> getElectionsListByConst(String constId){
	List<ElectionsConstsBean> electionsList = null;
	electionsList = electionsMgmtDao.getElectionsListByConst(constId);
	return electionsList;
    }
    
    
    private Boolean getVotingStatus(String votingPIN, String electionId)
    {
	onlineVoteMgmtDao.getVotingStatus(votingPIN, electionId);
	
	// TODO Auto-generated method stub
	return null;
    }

}
