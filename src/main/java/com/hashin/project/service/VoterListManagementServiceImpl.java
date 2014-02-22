package com.hashin.project.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.hashin.project.bean.VotersUserBean;
import com.hashin.project.dao.UserEnrollmentDAOImpl;
import com.hashin.project.dao.VoterListManagementDAO;
import com.hashin.project.dao.VoterListManagementDAOImpl;

public class VoterListManagementServiceImpl implements
	VoterListManagementService
{
    @Autowired
    private VoterListManagementDAO voterListManagementDao;
    
    private static final Logger logger = Logger.getLogger(VoterListManagementServiceImpl.class);

    @Override
    public VotersUserBean getVoterById(String voterID)
    {
	return voterListManagementDao.getVoterUserById(voterID);
    }

/*    
    @Override
    public List<VotersUserBean> getVotersByNameSearch(String voterName)
    {
	List<VotersUserBean> userList = null; 
	Object[] parameters = new Object[] { voterName };
	userList = voterListManagementDao.getVotersByQueryName("GET_VOTERS_NAME", parameters);
	return userList;
    }

    @Override
    public List<VotersUserBean> getVotersByNameAndConstituency(
	    String voterName, String constId)
    {
	List<VotersUserBean> userList = null; 
	Object[] parameters = new Object[] { voterName, constId };
	userList = voterListManagementDao.getVotersByQueryName("GET_VOTERS_NAME_CONST", parameters);
	return userList;
    }

    @Override
    public List<VotersUserBean> getVotersByNameConstAndLockoutFlag(
	    String voterName, String constId, String flag)
    {
	List<VotersUserBean> userList = null;
	Object[] parameters = new Object[] { voterName, constId, flag };
	userList = voterListManagementDao.getVotersByQueryName("GET_VOTERS_NAME_CONST_FLAG", parameters);
	return userList;
    }

*/
    
    @Override
    public Boolean insertNewVoter(VotersUserBean voterUser)
    {
	Boolean status = false;
	int numRows =  voterListManagementDao.insertNewVoter(voterUser);
	
	if(numRows == 1){
	    status = true;
	}    
	return status;
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
	// id, const, name, locout lag
	logger.debug(">>________________inside searchVoter__________________");
	logger.debug(">>___recieved query params:"+ userToSearch.getVotersId() +" , " 
			+ userToSearch.getConstituency() +" , "+ userToSearch.getName()+ " , "+ userToSearch.getLockOutFlag() );
	
	List<VotersUserBean> voterList =  null;
	
	//modify the query params 
	if(userToSearch.getConstituency() == null )
	    userToSearch.setConstituency("");
	
	if(userToSearch.getVotersId() == null)
	    userToSearch.setVotersId("");
	
	if(userToSearch.getConstituency() == null)
	    userToSearch.setConstituency("");
	
	if(userToSearch.getLockOutFlag() == null)
	    userToSearch.setLockOutFlag("");
	
	voterList = voterListManagementDao.searchVoter(userToSearch);
	
	return voterList;
    }

    @Override
    public VotersUserBean getVoterDetailById(VotersUserBean userToSearch)
    {
	VotersUserBean voterDetail= null;

	voterDetail = voterListManagementDao.getVoterDetailById(userToSearch);
	return voterDetail;

    }

	@Override
	public VotersUserBean activateVoterByVoterId(VotersUserBean voterToActivate) {
		VotersUserBean voterDetail= null;
		int numrows = 0;
		numrows = voterListManagementDao.changeVoterStatusByVoterId(voterToActivate.getVotersId(), "F");
		if(numrows > 0 ){
			voterDetail = getVoterDetailById(voterToActivate);
		}
		
		return voterDetail;
	}

	@Override
	public VotersUserBean deactivateVoterByVoterId(VotersUserBean voterToBlock) {
		VotersUserBean voterDetail= null;
		int numrows = 0;
		numrows = voterListManagementDao.changeVoterStatusByVoterId(voterToBlock.getVotersId(), "T");
		if(numrows > 0 ){
			voterDetail = getVoterDetailById(voterToBlock);
		}
		
		return voterDetail;
	}


}

