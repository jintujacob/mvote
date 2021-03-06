package com.hashin.project.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hashin.project.bean.ElectionsCandidatesBean;
import com.hashin.project.bean.ElectionsConstsBean;
import com.hashin.project.bean.FormBeanGetCandidates;
import com.hashin.project.bean.FormListBean;
import com.hashin.project.bean.VotersAdhaarUserBean;
import com.hashin.project.service.OnlineVotingService;

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
	logger.info("in Online Voting Module");
	return null;
    }   
    
    
    
    // form submit voting pin, adhaarid, votersId
    // testing status complete
    @RequestMapping(value="/verifyLogin", method = RequestMethod.POST)
    public @ResponseBody FormListBean verifyUserLogin(@RequestBody VotersAdhaarUserBean loginUser)
    {
	logger.info(">>___________ "+ loginUser.geteElectionId()+" >"+loginUser.getVotingPIN());
	
	List<ElectionsConstsBean> electionList = null;
	FormListBean elections = new FormListBean();
		
	try {
	    if(onlineVotingService.isValidUser( loginUser.geteElectionId(),loginUser.getVotingPIN()))
		electionList = onlineVotingService.manageVoterEntry( loginUser.geteElectionId(),loginUser.getVotingPIN());
	    else
		elections.setCustomMessage("FAILED");
	} catch (Exception e) {
		logger.info("Exception from backend -------> " + e.getMessage());
		elections.setCustomMessage("Unable to perform requested Operation");
	}
	//electionList = onlineVotingService.manageVoterEntry( "VPIN444", "UID444", "v444");
	if(electionList != null){
	    elections.setElectionList(electionList);
	    elections.setCustomMessage(CUSTOM_MSG);
	}
	if(electionList.size() < 1){
	    //User is either not enrolled/ or no elections available for the user
	    elections.setCustomMessage("No Elections Available");
	}
	return elections;
    }
    
    @RequestMapping(value="/getCandidates", method=RequestMethod.POST)
    public @ResponseBody FormListBean getCandidatesByUnitElection(
	    @RequestBody FormBeanGetCandidates formBean)
    {
	logger.info(">________recieved____: " + formBean.getVotingPIN()+", "+ 
		formBean.getElectionId()+", "+ formBean.getUnitElectionId());
	List<ElectionsCandidatesBean> candidateList = null;
	FormListBean candidates = new FormListBean();
	
	//User selects the election,and submits. service layer should check if the user is already voted or not
	candidateList = onlineVotingService.getCandidatesList( formBean.geteElectionId(),  
		formBean.getElectionId(), formBean.getUnitElectionId());
	
	if(candidateList != null){
	    if(candidateList.isEmpty()){
		candidates.setCustomMessage("NO Candidates Enrolled for the election");
	    }else{
		    candidates.setCandidateList(candidateList);
		    candidates.setCustomMessage(CUSTOM_MSG);
	    }
	
	}else{
	    candidates.setCustomMessage("Your Vote is already recorded. You can not vote again for the same election");
	}
	return candidates;
    }
    
    @RequestMapping(value="/submitVote", method=RequestMethod.POST)
    public @ResponseBody FormListBean submitVoteforCandidate( 
	    @RequestBody FormBeanGetCandidates formBean)
    {
	logger.info(">________recieved____: " + formBean.getVotingPIN()+", "+ 
		formBean.getElectionId()+", "+ formBean.getCandidateId());
	FormListBean votingStat = new FormListBean();
	
	String message = onlineVotingService.submitVoteforCandidate(formBean.geteElectionId(), 
		formBean.getCandidateId(), formBean.getElectionId());
	if (message != null){
	    votingStat.setCustomMessage(CUSTOM_MSG);
	}else{
	    //show the confirmation message in UI to submit the request again.
	    //it should not ask the user to input all the details again. 
	    //Cache the details in the UI and ask him to just click the resubmit button again.
	    //check if the transaction works
	    votingStat.setCustomMessage("FAILED");
	}
	return votingStat;
    }
    
    
    
    
}
