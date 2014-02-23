package com.hashin.project.service;

import java.util.List;

import com.hashin.project.bean.ConstituenciesBean;
import com.hashin.project.bean.ElectionStatesBean;
import com.hashin.project.bean.ElectionsBean;
import com.hashin.project.bean.ElectionsCandidatesBean;
import com.hashin.project.bean.ElectionsConstsBean;

public interface ElectionManagementService {
/*

	
	
	 START_______________________________________ Candidate Management API 
	
	public Boolean enrollNewCandidate(ElectionsCandidatesBean candidate);

	public ElectionsCandidatesBean getCandidateInfoById(String candidateId);
	
	public List<ElectionsCandidatesBean> getCandidatesByName(String candName);
	
	public List<ElectionsCandidatesBean> getCandidatesByConstituency(String constId);
	
	 END Candidate Management API 
	
*/	
	/* START_______________________________________ Constituency Management API */
	

	public List<ConstituenciesBean> getAllConsts();

	public List<ElectionsBean> searchElection(ElectionsBean toSearch);

	public ElectionsBean getElectionDetail(ElectionsBean eleToFind);

	public List<ElectionStatesBean> getStatesByElectionId(ElectionsBean eleToFind);

	public List<ElectionStatesBean> getAllStatesForMenu();

	public ElectionsBean addNewElection(ElectionsBean eleToFind, List<ElectionStatesBean> stateList);

	public ElectionsBean enrollVotersForElection(ElectionsBean eleToFind);

	public ElectionsBean deleteElection(ElectionsBean eleToDelete);

	public List<ConstituenciesBean> searchConstsByName(ConstituenciesBean toSearch);

	public List<ElectionsCandidatesBean> searchCandidate(ElectionsCandidatesBean toSearch)throws Exception;

	public ElectionsCandidatesBean deleteCandidate(ElectionsCandidatesBean toDelete)throws Exception;
	
	public List<ElectionsConstsBean> getElectionsListByConst(ConstituenciesBean toFind) ;
	
	/* END Constituency Management API */
	
}
