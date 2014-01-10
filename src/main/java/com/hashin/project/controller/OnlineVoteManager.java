package com.hashin.project.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hashin.project.bean.ElectionsCandidatesBean;
import com.hashin.project.bean.ElectionsConstsBean;
import com.hashin.project.bean.FormBeanGetCandidates;
import com.hashin.project.bean.VotersAdhaarUserBean;
import com.hashin.project.service.OnlineVotingService;
import com.hashin.project.bean.OnlineVoteManagerBean;

@Controller
@RequestMapping("vote")
public class OnlineVoteManager
{

    @Autowired
    private OnlineVotingService onlineVotingService; 
    private static final Logger logger = Logger.getLogger(OnlineVoteManager.class);
    private static final String CUSTOM_MSG = "SUCCESS" ;
    
    @RequestMapping( method = RequestMethod.GET)
    public String getHomeAction() 
    {
	logger.debug("in Online Voting Module");
	return null;
    }   
    
    
    
    //form submit voting pin, adhaarid, votersId
    @RequestMapping(value="/verifyLogin", method = RequestMethod.POST)
    public @ResponseBody List<ElectionsConstsBean> verifyUserLogin(VotersAdhaarUserBean loginUser)
    {
	List<ElectionsConstsBean> electionList = null;
		
	//electionList = onlineVotingService.manageVoterEntry(votingPIN, adhaarId, votersId);
	if(electionList != null){
	    // do the return the elections list to the ui
	}
	else{
	    //return the error message to the Ui that user is either not enrolled/ or no elections available for the user
	}
	return electionList;
    }
    
    @RequestMapping(value="/getCandidates", method=RequestMethod.POST)
    public @ResponseBody List<ElectionsCandidatesBean> getCandidatesByUnitElection(
	    @RequestBody FormBeanGetCandidates formBean)
    {
	List<ElectionsCandidatesBean> candidateList = null;
	//User selects the election,and submits. service layer should check if the user is already voted or not
	candidateList = onlineVotingService.getCandidatesList( formBean.getVotingPIN(), 
		formBean.getElectionId(), formBean.getUnitElectionId());
	
	if(candidateList != null){
	    // do return the candidate list to the UI
	}else{
	    //return the error message to the UI that the user is either voted 
	    //or not able to pull the candidate for the election
	}
	return candidateList;
    }
    
    @RequestMapping(value="/submitVote", method=RequestMethod.POST)
    public @ResponseBody FormBeanGetCandidates submitVoteforCandidate( @RequestBody FormBeanGetCandidates formBean)
    {
	FormBeanGetCandidates votingStat = null;
	
	String message = onlineVotingService.submitVoteforCandidate(formBean.getVotingPIN(), 
		formBean.getCandidateId(), formBean.getElectionId());
	if (message != null){
	    //show the confirmation message in UI that voting is completed successfully
	}else{
	    //show the confirmation message in UI to submit the request again.
	    //it should not ask the user to input all the details again. 
	    //Cache the details in the UI and ask him to just click the resubmit button again.
	    //check if the transaction works 
	}
	return votingStat;
    }
    
    
    
    
}
