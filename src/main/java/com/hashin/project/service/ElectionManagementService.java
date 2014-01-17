package com.hashin.project.service;

import java.util.List;

import com.hashin.project.bean.ConstituenciesBean;
import com.hashin.project.bean.ElectionsBean;
import com.hashin.project.bean.ElectionsCandidatesBean;

public interface ElectionManagementService {
/*
	 START________________________________________ Election Management API 
	
	public int addNewElection(ElectionsBean election);

	public ElectionsBean getElectionById(int electId);

	public List<ElectionsBean> getAllElections();

	public List<ElectionsBean> searchElectionsWildCard(String electTitle);

	public int removeElectionById(int electId);
	
	 END Election Management API 
	
	
	 START_______________________________________ Candidate Management API 
	
	public Boolean enrollNewCandidate(ElectionsCandidatesBean candidate);

	public ElectionsCandidatesBean getCandidateInfoById(String candidateId);
	
	public List<ElectionsCandidatesBean> getCandidatesByName(String candName);
	
	public List<ElectionsCandidatesBean> getCandidatesByConstituency(String constId);
	
	 END Candidate Management API 
	
*/	
	/* START_______________________________________ Constituency Management API */
	

	public List<ConstituenciesBean> getAllConsts();
	
	/* END Constituency Management API */
	
}
