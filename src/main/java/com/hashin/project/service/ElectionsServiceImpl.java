package com.hashin.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.hashin.project.bean.ElectionsBean;
import com.hashin.project.dao.ElectionsDAO;
import com.hashin.project.dao.ElectionsDAOImpl;

public class ElectionsServiceImpl implements ElectionsService 
{
    	@Autowired
	private ElectionsDAO electionsDao;
    	
    	//have to autowire since the spring is intializing the bean with datasource properties
	
    	public void create(ElectionsBean election)
	{
    	electionsDao.create(election);
	}
	
	public ElectionsBean getById(int electId)
	{
	    ElectionsBean election = electionsDao.getById(electId);
	    return election;
	}
	
	public List<ElectionsBean> listAll()
	{
	    List<ElectionsBean> electionList = electionsDao.listAll();
	    return electionList;
	}
	
	public List<ElectionsBean> searchByTitle(String electTitle){
	    List<ElectionsBean> electionList = electionsDao.searchByTitle(electTitle);
	    return electionList;
	}

}
