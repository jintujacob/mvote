package com.hashin.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hashin.project.bean.VotersUserBean;
import com.hashin.project.dao.VoterListManagementDAO;

public class VoterListManagementServiceImpl implements
	VoterListManagementService
{
    @Autowired
    private VoterListManagementDAO voterListManagementDao;
    

    @Override
    public VotersUserBean getVoterById(String voterID)
    {
	return voterListManagementDao.getVoterUserById(voterID);
    }

    
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


}
