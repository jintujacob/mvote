package com.hashin.project.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.hashin.project.bean.ConstituenciesBean;
import com.hashin.project.bean.ElectionStatesBean;
import com.hashin.project.bean.ElectionsBean;
import com.hashin.project.bean.ElectionsCandidatesBean;
import com.hashin.project.bean.ElectionsConstsBean;
import com.hashin.project.bean.SystemUserBean;
import com.hashin.project.controller.ElectionsManager;
import com.hashin.project.dao.AccessControlDAO;
import com.hashin.project.dao.ElectionManagementDAO;


public class AccessControlServiceImpl implements AccessControlService {
	@Autowired
	private AccessControlDAO accessControlDAO;

	private static final Logger logger = Logger
		.getLogger(AccessControlServiceImpl.class);

	@Override
	public Boolean validateSystemUser(SystemUserBean loginUser)
	{
	    int rowCount = accessControlDAO.validatedSystemUserCount(loginUser.getUid(), 
		    loginUser.getPassword());
	    
	    if(rowCount>0){
		return true;
	    }
	    return false;
	}

	@Override
	public Boolean addSystemUser(SystemUserBean toadd) {
		// TODO Auto-generated method stub
		Boolean returnflag=false;
		int rowCount=accessControlDAO.addSystemUser(toadd);
		if(rowCount>0)
			returnflag=true;
		return returnflag;
	}
	
	
}
