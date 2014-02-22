package com.hashin.project.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.hashin.project.bean.ConstituenciesBean;
import com.hashin.project.bean.ElectionStatesBean;
import com.hashin.project.bean.ElectionsBean;
import com.hashin.project.bean.ElectionsConstsBean;
import com.hashin.project.controller.ElectionsManager;
import com.hashin.project.dao.ElectionManagementDAO;


public class ElectionManagementServiceImpl implements ElectionManagementService {
	@Autowired
	private ElectionManagementDAO electionsMgmtDao;

	private static final Logger logger = Logger
		.getLogger(ElectionManagementServiceImpl.class);
	
	@Override
	public List<ConstituenciesBean> getAllConsts() {
		return electionsMgmtDao.getAllConstituencies();
	}

	@Override
	public List<ElectionsBean> searchElection(ElectionsBean toSearch) 
	{
		if(toSearch.getElectTitle() == null ){
			toSearch.setElectTitle("");
		}
		
		return electionsMgmtDao.searchElections(toSearch.getElectTitle());
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
	     
	     //add the election into elections constituencies also
	     
	     if(rowCount > 0 ){
		 return eleToFind;
	     }
		 return null;
	}

	@Transactional
	@Override
	public ElectionsBean enrollVotersForElection(ElectionsBean eleToFind)
	{
	    ElectionsBean enrolledStatus = new ElectionsBean();
	    Boolean enrlstatus = electionsMgmtDao.getVoterEnrollStatusByElection(eleToFind.getElectId());
	    int votingstatTableflag = 0;
	    int electionTableflag = 0;
	    
	    if(enrlstatus != true){
		logger.debug("_____________________not enrolled_________________________");
		votingstatTableflag = electionsMgmtDao.enrollVotersForElection(eleToFind.getElectId());
		if(votingstatTableflag >0 ){
			electionTableflag = electionsMgmtDao.updateEnrlmntStatusForElection(eleToFind.getElectId());		    
		}
	    }
	    else{
		logger.debug("_____________________already enrolled_________________________");
		enrolledStatus.setCustomMessage("Unable to perform requested operation. Enrollment already completed");
	    }
	    
	    if(electionTableflag > 0){
		logger.debug("_____________________newly enrolled_________________________");
		enrolledStatus.setCustomMessage("SUCCESS");
	    }else{
		logger.debug("_____________________enroll failed due to sys_________________________");
		enrolledStatus.setCustomMessage("Unable to perform requested operation. Database Error");
	    }
	    return enrolledStatus;
	}

	@Override
	public ElectionsBean deleteElection(ElectionsBean eleToDelete)
	{
	   ElectionsBean deleteStat = new ElectionsBean();
	   int rowCount  = 0;
	   rowCount  = electionsMgmtDao.deleteElectionInElections(eleToDelete.getElectId());
	  
	   if(rowCount> 0 ){
	       deleteStat.setCustomMessage("SUCCESS");
	   }else{
	       deleteStat.setCustomMessage("Delete Operation failed due to Database Error");
	   }
	   return deleteStat;
	}
	
	
	
	
	
}
