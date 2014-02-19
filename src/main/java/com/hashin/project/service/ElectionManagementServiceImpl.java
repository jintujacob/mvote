package com.hashin.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hashin.project.bean.ConstituenciesBean;
import com.hashin.project.bean.ElectionsBean;
import com.hashin.project.bean.ElectionsCandidatesBean;
import com.hashin.project.bean.ElectionsConstsBean;
import com.hashin.project.dao.ElectionManagementDAO;


public class ElectionManagementServiceImpl implements ElectionManagementService {
	@Autowired
	private ElectionManagementDAO electionsMgmtDao;


	@Override
	public List<ConstituenciesBean> getAllConsts() {
		return electionsMgmtDao.getAllConstituencies();
	}

	@Override
	public List<ElectionsConstsBean> searchElection(ElectionsConstsBean toSearch) {
		if(toSearch.getElectTitle() == null ){
			toSearch.setElectTitle("");
		}
		if(toSearch.getConstId()== null) {
			toSearch.setConstId("");
		}
		if(toSearch.getStateId() == null ){
			toSearch.setStateId("");
		}
		
		return electionsMgmtDao.searchElections(toSearch);
	}
}
