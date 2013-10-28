package com.hashin.project.service;

import java.util.List;

import com.hashin.project.bean.ElectionsBean;

public interface ElectionManagementService
{
	
	public int addNewElection(ElectionsBean election);

	public ElectionsBean getElectionById(int electId);
	
	public List<ElectionsBean> getAllElections();
	
	public List<ElectionsBean> searchElectionsWildCard(String electTitle);
	
	public int removeElectionById(int electId);

}
