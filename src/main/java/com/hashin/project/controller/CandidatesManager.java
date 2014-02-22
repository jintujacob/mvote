package com.hashin.project.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hashin.project.bean.ElectionsBean;
import com.hashin.project.bean.ElectionsCandidatesBean;
import com.hashin.project.service.ElectionManagementService;

/**
 * @author jintu.jacob@gmail.com Oct 9, 
 * 2013 ElectionsManager Handles all
 * default inputs ending with /candidates
 * it refers to the electionsManagementService for service layer funcitons
 * 
 */

@Controller
@RequestMapping("candidates")
public class CandidatesManager {

	@Autowired
	private ElectionManagementService electionMgmtService;
	private static final Logger logger = Logger.getLogger(CandidatesManager.class);

	
	@RequestMapping(value= "/getCandidateById", method = RequestMethod.GET)
	public ModelAndView getCandidateInfoById(@RequestParam String candId)
	{
		logger.debug("CandidatesManager.getCandidateById() -  Controller methode mapping done!");
		
		
		return null;
	}
	
	
	@RequestMapping(value = "/getCandidatesByName", method = RequestMethod.GET)
	public ModelAndView getCandidatesByName() 
	{
		logger.debug("CandidatesManager.getCandidatesByName() -  Controller methode mapping done!");
		
		return new ModelAndView("", "", "");
	}
	
	
	@RequestMapping(value = "/getCandidatesByConst", method = RequestMethod.GET)
	public ModelAndView getCandidatesByConst(@RequestParam String constId)
	{
		logger.debug("CandidatesManager.getCandidatesByConst() -  Controller methode mapping done!");
		
		return null;
	}
	
	
	@RequestMapping(value = "/enrollNewCandidate", method = RequestMethod.GET)
	public  ModelAndView enrollNewCandidate(@RequestParam ElectionsCandidatesBean candidate)
	{
		logger.debug("CandidatesManager.enrollNewCandidate() -  Controller methode mapping done!");
		return null;
	}
	
	
	/*
	@RequestMapping(value = "/updateCandidateInfo", method = RequestMethod.GET)
	public ModelAndView udpateCandidateInfo(@RequestParam  ElectionsCandidatesBean candidate)
	{
		logger.debug("CandidatesManager.updateCandidateInfo() -  Controller methode mapping done!");
		return null;
	}
	*/
	
	

	
}
