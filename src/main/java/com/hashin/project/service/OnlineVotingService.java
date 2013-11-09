package com.hashin.project.service;

public interface OnlineVotingService
{
    public String manageOnlineVoting();
    
    public String getEnrollmentStatus(String votingPin, String adhaarId, String voterId);
    
    public Boolean getVotingStatus(String votingPIN);
    
    
}
