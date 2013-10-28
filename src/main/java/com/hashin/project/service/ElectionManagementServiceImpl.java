package com.hashin.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hashin.project.bean.ElectionsBean;
import com.hashin.project.dao.ElectionsDAO;

public class ElectionManagementServiceImpl implements ElectionManagementService
{
	@Autowired
	private ElectionsDAO electionsDao;
	
	
	public int addNewElection(ElectionsBean election)
	{
	    return electionsDao.addNewElection(election); 
	    //todo check if the the return value ==0 or <0; then then throw some database related custome exception
	}
	
	public ElectionsBean getElectionById(int electId)
	{
	    ElectionsBean election = electionsDao.getElectionById(electId);
	    return election;
	}
	
	public List<ElectionsBean> getAllElections()
	{
	    List<ElectionsBean> electionList = electionsDao.getAllElections();
	    return electionList;
	}
	
	public List<ElectionsBean> searchElectionsWildCard(String electTitle){
	    List<ElectionsBean> electionList = electionsDao.searchElectionsWildCard(electTitle);
	    return electionList;
	}
	
	public int removeElectionById(int electId)
	{
	    return electionsDao.removeElectionById(electId);
	}    
}
