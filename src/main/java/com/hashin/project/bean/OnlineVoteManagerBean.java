package com.hashin.project.bean;

import java.io.Serializable;

public class OnlineVoteManagerBean 
{
    private String votingPIN;
    private String adhaarId;
    private String votersId;
    private String electionId;
    private String unitElectionId;
    private String candidateId;
    public String getVotingPIN()
    {
        return votingPIN;
    }
    public void setVotingPIN(String votingPIN)
    {
        this.votingPIN = votingPIN;
    }
    public String getAdhaarId()
    {
        return adhaarId;
    }
    public void setAdhaarId(String adhaarId)
    {
        this.adhaarId = adhaarId;
    }
    public String getVotersId()
    {
        return votersId;
    }
    public void setVotersId(String votersId)
    {
        this.votersId = votersId;
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
    public String getCandidateId()
    {
        return candidateId;
    }
    public void setCandidateId(String candidateId)
    {
        this.candidateId = candidateId;
    }

    
}
