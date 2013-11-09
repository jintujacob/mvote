package com.hashin.project.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hashin.project.bean.ElectionsConstsBean;
import com.hashin.project.service.OnlineVotingService;

@Controller
@RequestMapping("vote")
public class OnlineVoteManager
{

    @Autowired
    private OnlineVotingService onlineVotingService; 
    private static final Logger logger = Logger.getLogger(ElectionsManager.class);

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView getHomeAction() 
    {
	logger.debug("in Online Voting Module");
	// do something
	return new ModelAndView("OnlineVotingHome", "testvar", "testval");
    }   
    
    
    
    //form submit voting pin, adhaarid, votersId
    @RequestMapping(value="/verifyLogin", method = RequestMethod.GET)
    public ModelAndView verifyUserLogin(@RequestParam String votingPIN, 
	    @RequestParam String adhaarId, @RequestParam String votersId)
    {
	//verify enrollment
	//verify if already voted or not, internally identify the constituency
	//if verified get list of elections for the constituency
	
	List<ElectionsConstsBean> electionList = onlineVotingService.manageVoterEntry(votingPIN, adhaarId, votersId);
	if(electionList != null){
	    // do the return the elections list to the ui
	}
	else{
	    //return the error message to the Ui that user is either not enrolled/ or no elections available for the user
	}
	return null;
    }
    
    @RequestMapping(value="/getCandidates", method=RequestMethod.GET)
    public ModelAndView getCandidatesByUnitElection(@RequestParam String unitElectionId)
    {
	//get list of candidates
	return null;
    }
    
    @RequestMapping(value="/submitVote", method=RequestMethod.GET)
    public ModelAndView submitVoteforCandidate(@RequestParam String candidateId)
    {
	//update votecount
	//update voting status
	//return succes of fail
	return null;
    }
    
    
    
    
}
