package com.hashin.project.service;

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


}
