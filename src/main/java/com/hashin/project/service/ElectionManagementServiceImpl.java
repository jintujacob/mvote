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
	   return  electionsMgmtDao.getElectionDetail(eleToFind.getElectId());
	    
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
	public ElectionsBean addNewElection(ElectionsBean eleToFind, List<ElectionStatesBean> stateList)
	{
 	    logger.debug(">>_____________/addNewElection//service -> Search Params : " + eleToFind.toString());
	    Long genElectionId = electionsMgmtDao.addNewElection(eleToFind);
	    int statsUnitEleCreation = 0;
	    List<ConstituenciesBean> constList = null;
	    
	    if(genElectionId != null){
		 if(stateList == null ){
		     constList = electionsMgmtDao.getAllConstituencies();
		 }else{
		     constList = electionsMgmtDao.getConstsByStatesId(stateList);
		 }
		 statsUnitEleCreation = electionsMgmtDao.createUnitConstituencyElections(""+genElectionId, constList);
	     }
	     
	     if(statsUnitEleCreation > 0){
		 return new ElectionsBean();
	     }
	     else{
		 return null;
	     }

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

	@Override
	public List<ConstituenciesBean> searchConstsByName(ConstituenciesBean toSearch)
	{
	    if(toSearch.getConstName()==null){
		toSearch.setConstName("");
	    }
	    return electionsMgmtDao.searchConstsByName(toSearch.getConstName()) ;
	}

	@Override
	public List<ElectionsCandidatesBean> searchCandidate(ElectionsCandidatesBean toSearch) throws Exception
	{
	    if(toSearch.getCandName() == null){
		toSearch.setCandName("");
	    }
	    if(toSearch.getConstName() == null){
		toSearch.setConstName("");
	    }
	    
	    return electionsMgmtDao.searchCandidateByNameConst(toSearch.getCandName(), toSearch.getConstName()) ;
	}

	@Override
	public ElectionsCandidatesBean deleteCandidate(ElectionsCandidatesBean toDelete) throws Exception
	{
	    if(electionsMgmtDao.deleteCandidate(toDelete.getCandId())>0){
		return toDelete;
	    }else{
		return null;
	    }
	    
	}
	
	@Override
	public List<ElectionsConstsBean> getElectionsListByConst(ConstituenciesBean toFind) {
		List<ElectionsConstsBean> electionsList = null;
		electionsList = electionsMgmtDao.getElectionsListByConst(toFind.getConstId());
		return electionsList;
	}

	@Override
	public ElectionsCandidatesBean addNewCandidate(ElectionsCandidatesBean beanToAdd)
	{
	    Long newCandId = electionsMgmtDao.addCandidateToBaseTable(beanToAdd.getCandName(), beanToAdd.getCandBio());
	    if(newCandId != null){
		Long newEleCandId = electionsMgmtDao.addCandidateToEleCandidates(newCandId.toString(), beanToAdd.getUnitEleId());
		if(newEleCandId != null){
		    int insertStat = electionsMgmtDao.addCandidateToEleResults(newEleCandId.toString(), beanToAdd.getUnitEleId());
		}
	    }
	    return null;
	}

	
	
	
	
	
}
