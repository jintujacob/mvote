package com.hashin.project.bean;

import org.springframework.web.bind.annotation.RequestParam;

public class FormBeanGetCandidates
{

    private String votingPIN ; 
    private String electionId ; 
    private String unitElectionId ;
    private String candidateId;
    private String customMessage;
    
    public String getCandidateId()
    {
        return candidateId;
    }
    public void setCandidateId(String candidateId)
    {
        this.candidateId = candidateId;
    }
    public String getVotingPIN()
    {
        return votingPIN;
    }
    public void setVotingPIN(String votingPIN)
    {
        this.votingPIN = votingPIN;
    }
    public String getElectionId()
    {
        return electionId;
    }
    public void setElectionId(String electionId)
    {
        this.electionId = electionId;
    }
    public String getUnitElectionId()
    {
        return unitElectionId;
    }
    public void setUnitElectionId(String unitElectionId)
    {
        this.unitElectionId = unitElectionId;
    }
    
    
    
}
