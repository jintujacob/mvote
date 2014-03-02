package com.hashin.project.bean;

import java.util.List;

/**
 * @author jintu.jacob@gmail.com
 * Feb 19, 2014
 * FormListBean
 */
public class FormListBean
{

    private String customMessage;
    private List<ElectionsConstsBean> electionList;
    private List<ElectionsCandidatesBean> candidateList;
    private List<VotersUserBean> voterList;
    private List<ConstituenciesBean> constsList;
    private List<ElectionStatesBean> statesList;
    private List<ElectionsBean> electionBasicDetailList;
    private List<ElectionsResultsBean> resultList;
    
   
    
    
    
    public List<ElectionsResultsBean> getResultList()
    {
        return resultList;
    }

    public void setResultList(List<ElectionsResultsBean> resultList)
    {
        this.resultList = resultList;
    }

    public List<ElectionsBean> getElectionBasicDetailList()
    {
        return electionBasicDetailList;
    }

    public void setElectionBasicDetailList(
    	List<ElectionsBean> electionBasicDetailList)
    {
        this.electionBasicDetailList = electionBasicDetailList;
    }

    public List<ConstituenciesBean> getConstsList()
    {
	return constsList;
    }

    public void setConstsList(List<ConstituenciesBean> constsList)
    {
	this.constsList = constsList;
    }

    public List<VotersUserBean> getVoterList()
    {
	return voterList;
    }

    public void setVoterList(List<VotersUserBean> voterList)
    {
	this.voterList = voterList;
    }

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

    public List<ElectionStatesBean> getStatesList()
    {
        return statesList;
    }

    public void setStatesList(List<ElectionStatesBean> statesList)
    {
        this.statesList = statesList;
    }

    @Override
    public String toString()
    {
	return "FormListBean [customMessage=" + customMessage
		+ ", electionList=" + electionList + ", candidateList="
		+ candidateList + ", voterList=" + voterList + ", constsList="
		+ constsList + ", statesList=" + statesList
		+ ", electionBasicDetailList=" + electionBasicDetailList
		+ ", resultList=" + resultList + "]";
    }

    
    
    
}
