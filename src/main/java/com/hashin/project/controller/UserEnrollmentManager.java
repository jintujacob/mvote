package com.hashin.project.controller;

import java.util.List;

import javax.jws.WebParam.Mode;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hashin.project.bean.AdhaarUserBean;
import com.hashin.project.bean.ElectionsBean;
import com.hashin.project.bean.VotersUserBean;
import com.hashin.project.service.UserEnrollmentService;


/**
 * @author jintu.jacob@gmail.com
 * Oct 9, 2013
 * UserEnrollmentManager
 * Handles all default inputs ending with /enroll
 */

@Controller
@RequestMapping("/enroll")
public class UserEnrollmentManager
{
    @Autowired
    private UserEnrollmentService userEnrollmentService; 
    private static final Logger logger = Logger.getLogger(UserEnrollmentManager.class);

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getHomeAction() 
    {
	logger.debug("in UserEnrollmentManager default");
	return null;
    }   
    
    
    //get Adhaar information for the provided adhaarId
    @RequestMapping(value="/getAdhaarInfo", method = RequestMethod.POST)
    public @ResponseBody AdhaarUserBean getAdhaarInfobyId(@RequestBody AdhaarUserBean inputUser)
    {
	logger.debug(">>_______ Adhaarid  recieved" + inputUser.getAdhaarID());
	//AdhaarUserBean user =  userEnrollmentService.getAdhaarUserById(adhaarId);
	
	AdhaarUserBean user  =  new AdhaarUserBean();
	user.setAdhaarID(inputUser.getAdhaarID());
	user.setNameFirst("Jintu Jacob");
	user.setPhone("9847361387");

	return user;
    }
    
    
    
    
    //get Voters information for the provided adhaarId
    @RequestMapping(value="/getVoterInfo", method = RequestMethod.POST)
    public @ResponseBody VotersUserBean getVoterInfobyId(@RequestBody VotersUserBean inputVoter)
    {
	logger.debug(">>________ VoterId recieved" + inputVoter.getVotersId());
	
	//VotersUserBean votersUserBean =   userEnrollmentService.getVoterUserById(voterId);
	
	VotersUserBean voter = new VotersUserBean();
	voter.setVotersId(inputVoter.getVotersId());
	voter.setConstituency("Piravom");
	voter.setPlace("muvatupuzha");
	voter.setName("Jintu Jacob");

	return voter;
    }
    
    


    /* Once the VotersInfo from /getVotersInfo and adhaarInformation from /getAdhaarInfo
     * are verified enroll the user, enrollment tables requires only voterId and adhaarId
     */
    @RequestMapping(value="/enrollUser", method = RequestMethod.POST)
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
