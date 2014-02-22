package com.hashin.project.service;

import java.util.List;

import com.hashin.project.bean.CandidatesBean;
import com.hashin.project.bean.ElectionsBean;
import com.hashin.project.bean.ElectionsCandidatesBean;
import com.hashin.project.bean.ElectionsConstsBean;

public interface OnlineVotingService
{
    

    public Boolean isValidUser(String eElectionId, String votingPin) throws Exception;
    
    public List<ElectionsConstsBean> manageVoterEntry(String eElectionId, String votingPin ) throws Exception;
    
    public List<ElectionsCandidatesBean> getCandidatesList(String votingPIN, String electionId, String unitElectionId);
    
    public String submitVoteforCandidate(String voterEid, String candidateId, String electionId);
    
    
}
