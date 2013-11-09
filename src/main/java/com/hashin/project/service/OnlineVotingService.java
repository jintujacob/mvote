package com.hashin.project.service;

import java.util.List;

import com.hashin.project.bean.ElectionsBean;
import com.hashin.project.bean.ElectionsConstsBean;

public interface OnlineVotingService
{
    
    public List<ElectionsConstsBean> manageVoterEntry(String votingPin, String adhaarId, String voterId);
}
