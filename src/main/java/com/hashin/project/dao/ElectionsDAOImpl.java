package com.hashin.project.dao;

 import java.sql.PreparedStatement;
import java.util.List;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;


import com.hashin.project.bean.ElectionsBean;
import com.hashin.project.controller.ElectionsController;
import com.hashin.project.util.ElectionsRowMapper;
import com.hashin.project.dao.ElectionsDAO;


import java.sql.*;

import javax.annotation.Resource;
import javax.naming.*;
import javax.sql.*;


public class ElectionsDAOImpl implements ElectionsDAO
{
    
    private static final Logger logger = Logger.getLogger(ElectionsDAOImpl.class);
    private JdbcTemplate jdbcTemplate;
    
    @Resource(name="dataSource")
    public void setDataSource(DataSource dataSource)
    {
	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
        
        
    public void create(ElectionsBean election)
    {
	String query = "insert into elections (ele_title, ele_start_dt, ele_end_dt, ele_desc) values (?, ?, ?, ?)";
	jdbcTemplate.update( query, 
		    	     election.getElectTitle(),
		    	     election.getElectStartDate(),
		    	     election.getElectEndDate(),
		    	     election.getElectDescription() );
	 return;
    }
	
	

    public List<ElectionsBean> listAll()
    {
	String query = "select * from elections";
	List<ElectionsBean> electionsList = jdbcTemplate.query(query, new ElectionsRowMapper());
	return electionsList;
    }
	
	
    public ElectionsBean getById(int electId)
    {
	String query = "select * from elections where ele_id = ?";
	Object[] parameters = new Object[] {electId};
	List<ElectionsBean> electionsList = jdbcTemplate.query(query, parameters, new ElectionsRowMapper());
	return electionsList.get(0);
    }
	
	
    public List<ElectionsBean> searchByTitle(String searckKey)
    {
	searckKey = "%"+searckKey+"%";
	String query = "select * from elections where ele_title like ?";
	Object[] parameters = new Object[] {searckKey};
	List<ElectionsBean> electionList = jdbcTemplate.query(query, parameters, new ElectionsRowMapper());
	return electionList;
    }
	
}
