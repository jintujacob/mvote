package com.hashin.project.controller;

import java.util.List;

import javax.jws.WebParam.Mode;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hashin.project.bean.ElectionsBean;
import com.hashin.project.service.UserEnrollmentService;


/**
 * @author jintu.jacob@gmail.com
 * Oct 9, 2013
 * UserEnrollmentManager
 * Handles all default inputs ending with /enroll
 */

@Controller
@RequestMapping("enroll")
public class UserEnrollmentManager
{
    @Autowired
    private UserEnrollmentService userEnrollmentService; 
    private static final Logger logger = Logger.getLogger(ElectionsManager.class);

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView getHomeAction() 
    {
	logger.debug("in enrollement home action");
	//userEnrollmentService.
	return new ModelAndView("EnrollMentHome", "testvar", "testval");
    }   
    
    
    //get Adhaar information for the provided adhaarId
    @RequestMapping(value="/getAdhaarInfo", method = RequestMethod.GET)
    public ModelAndView getAdhaarInfobyId(@RequestParam String adhaarId){
	
	return null;
    }
    
    //get Voters information for the provided adhaarId
    @RequestMapping(value="/getVoterInfo", method = RequestMethod.GET)
    public ModelAndView getVoterInfobyId(@RequestParam String voterId){
	
	return null;
    }


    /* Once the VotersInfo from /getVotersInfo and adhaarInformation from /getAdhaarInfo
     * are verified enroll the user, enrollment tables requires only voterId and adhaarId
     */
    @RequestMapping(value="/enrollUser", method = RequestMethod.GET)
    public ModelAndView getElectionById(@RequestParam String votersId, @RequestParam String adhaarId){
	logger.debug("Inside UserEnrollmentManager, votersId="+votersId+" AdhaarId="+adhaarId);
	if(userEnrollmentService.manageUserEnrollement(votersId, adhaarId) != null){
	   //return message that enrollment was success
	}
	else{
	    //return message that enrollment failed 
	}
	return new ModelAndView("ReturnPage", "message", "message");
    }
    
}
