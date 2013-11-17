package com.hashin.project.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hashin.project.bean.ElectionsCandidatesBean;
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
    public ModelAndView getCandidatesByUnitElection( @RequestParam String votingPIN, 
	    @RequestParam String electionId,  @RequestParam String unitElectionId)
    {
	//User selects the election,and submits. service layer should check if the user is already voted or not
	List<ElectionsCandidatesBean> candidateList = onlineVotingService.getCandidatesList(votingPIN, 
		electionId, unitElectionId);
	
	if(candidateList != null){
	    // do return the candidate list to the UI
	}else{
	    //return the error message to the UI that the user is either voted 
	    //or not able to pull the candidate for the election
	}
	return null;
    }
    
    @RequestMapping(value="/submitVote", method=RequestMethod.GET)
    public ModelAndView submitVoteforCandidate(@RequestParam String votingPIN, 
	    @RequestParam String candidateId, @RequestParam String electionId)
    {
	String message = onlineVotingService.submitVoteforCandidate(votingPIN, candidateId, electionId);
	if (message != null){
	    //show the confirmation message in UI that voting is completed successfully
	}else{
	    //show the confirmation message in UI to submit the request again.
	    //it should not ask the user to input all the details again. 
	    //Cache the details in the UI and ask him to just click the resubmit button again.
	    //check if the transaction works 
	}
	return null;
    }
    
    
    
    
}
