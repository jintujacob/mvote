package com.hashin.project.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.hashin.project.bean.AdhaarUserBean;
import com.hashin.project.dao.AdhaarDAO;

public class AdhaarServiceImpl implements AdhaarService
{
    @Autowired
    private AdhaarDAO adhaarDao;
   
    public AdhaarUserBean getUserById(String adhaarID){
	return adhaarDao.getUserById(adhaarID);
    }
   
}
