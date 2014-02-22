package com.hashin.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hashin.project.bean.ConstituenciesBean;
import com.hashin.project.bean.ElectionStatesBean;
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
	public List<ElectionsConstsBean> searchElection(ElectionsConstsBean toSearch) 
	{
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

	@Override
	public ElectionsBean getElectionDetail(ElectionsBean eleToFind)
	{
	    ElectionsConstsBean eleDao = electionsMgmtDao.getElectionDetail(eleToFind.getElectId());
	    if(eleDao != null){
		eleToFind.setElectId(eleDao.getElectId());
		eleToFind.setElectStartDate(eleDao.getElectStartDate());
		eleToFind.setElectEndDate(eleDao.getElectEndDate());
		eleToFind.setElectTitle(eleDao.getElectTitle());
		eleToFind.setElectDescription(eleDao.getElectDescription());
		
		return eleToFind;
	    }
	    return null;
	}

	@Override
	public List<ElectionStatesBean> getStatesByElectionId(	ElectionsBean eleToFind )
	{
	    List<ElectionStatesBean> statesList = electionsMgmtDao.getStatesListByEleId(eleToFind.getElectId());
	    return statesList;
	}

	@Override
	public List<ElectionStatesBean> getAllStatesForMenu() {
		
		return electionsMgmtDao.getAllStatesForMenu();
	}

	@Override
	public ElectionsBean addNewElection(ElectionsBean eleToFind)
	{
	     int rowCount = electionsMgmtDao.addNewElection(eleToFind);
	     if(rowCount > 0 ){
		 return eleToFind;
	     }
		 return null;
	}
	
	
	
	
	
}
