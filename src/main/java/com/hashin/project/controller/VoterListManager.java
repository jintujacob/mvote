package com.hashin.project.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hashin.project.bean.VotersUserBean;
import com.hashin.project.service.VoterListManagementService;;

@Controller
@RequestMapping("voterslist")
public class VoterListManager
{

    @Autowired
    private VoterListManagementService voterListMgmtService; 
    private static final Logger logger = Logger.getLogger(VoterListManager.class);

    @RequestMapping(value="/voterlisthome", method = RequestMethod.GET)
    public ModelAndView getHomeAction() 
    {
	logger.debug("in VotersList management Module");
	// do something
	return new ModelAndView("", "", "");
    }   
    
    
    @RequestMapping(value="/getVoterInfoById", method = RequestMethod.GET)
    public ModelAndView getVoterInfoById(@RequestParam String voterId )
    {
	VotersUserBean voter = voterListMgmtService.getVoterById(voterId);
	if(voter != null){
	    //do something
	}
	else{
	    //voter not found/ invalid voterId
	}
	return null;
    }
    
    
    @RequestMapping(value="/getVotersByNameSearch", method = RequestMethod.GET)
    public ModelAndView getVotersByNameSearch(@RequestParam String voterName )
    {
	List<VotersUserBean> votersList = voterListMgmtService.getVotersByNameSearch(voterName);
	if(votersList != null){
	    //do something
	}
	else{
	    //voter not found/ invalid voterId
	}
	return null;
    }
    
    
    @RequestMapping(value="/getVotersByNameConst", method = RequestMethod.GET)
    public ModelAndView getVotersByNameAndConstituency(@RequestParam String voterName, @RequestParam String constId )
    {
	List<VotersUserBean> votersList = voterListMgmtService.getVotersByNameAndConstituency(voterName, constId);
	if(votersList != null){
	    //do something
	}
	else{
	    //voter not found/ invalid voterId
	}
	return null;
    }


    
    @RequestMapping(value="/getVotersByNameConstAndFlag", method = RequestMethod.GET)
    public ModelAndView getVotersByNameConstAndFlag(@RequestParam String voterName, 
	    @RequestParam String constId, @RequestParam String lockOutflag )
    {
	List<VotersUserBean> votersList = voterListMgmtService.getVotersByNameConstAndLockoutFlag(voterName, constId, lockOutflag);
	if(votersList != null){
	    //do something
	}
	else{
	    //voter not found/ invalid voterId
	}
	return null;
    }

    

    @RequestMapping(value="/insertNewVoter", method = RequestMethod.GET)
    public ModelAndView getVotersByNameConstAndFlag(@RequestParam VotersUserBean voterUser )
    {
	Boolean insertStatus = voterListMgmtService.insertNewVoter(voterUser);
	if(insertStatus != false){
	    //do something
	}
	else{
	    //voter not found/ invalid voterId
	}
	return null;
    }



    
}
