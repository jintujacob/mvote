package com.hashin.project.bean;

import java.util.List;

public class FormListBean
{

    private List<ElectionsConstsBean> electionList ;
    private List<ElectionsCandidatesBean> candidateList ;
    private String customMessage;
    
    
    public String getCustomMessage()
    {
        return customMessage;
    }
    public void setCustomMessage(String customMessage)
    {
        this.customMessage = customMessage;
    }
    public List<ElectionsConstsBean> getElectionList()
    {
        return electionList;
    }
    public void setElectionList(List<ElectionsConstsBean> electionList)
    {
        this.electionList = electionList;
    }
    public List<ElectionsCandidatesBean> getCandidateList()
    {
        return candidateList;
    }
    public void setCandidateList(List<ElectionsCandidatesBean> candidateList)
    {
        this.candidateList = candidateList;
    }  
    
    
    
}
