package com.hashin.project.service;

import java.util.List;

import com.hashin.project.bean.ConstituenciesBean;
import com.hashin.project.bean.ElectionStatesBean;
import com.hashin.project.bean.ElectionsBean;
import com.hashin.project.bean.ElectionsCandidatesBean;
import com.hashin.project.bean.ElectionsConstsBean;
import com.hashin.project.bean.SystemUserBean;

public interface AccessControlService {

    Boolean validateSystemUser(SystemUserBean loginUser);

	Boolean addSystemUser(SystemUserBean toadd);
	
}
