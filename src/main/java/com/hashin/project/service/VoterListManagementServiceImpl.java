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
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<VotersUserBean> getVotersByNameAndConstituency(
	    String voterName, String constId)
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<VotersUserBean> getVotersByNameConstAndLockoutFlag(
	    String voterName, String constId, String flag)
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String insertNewVoter(VotersUserBean voterUser)
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public VotersUserBean updateVotersById(VotersUserBean voterUser)
    {
	// TODO Auto-generated method stub
	return null;
    }


}
