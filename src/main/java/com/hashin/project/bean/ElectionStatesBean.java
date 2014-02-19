package com.hashin.project.bean;

public class ElectionStatesBean
{

    //st_id | st_name        | st_desc
    
    private String stateId;
    private String stateName;
    private String stateDesc;
    public String getStateId()
    {
        return stateId;
    }
    public void setStateId(String stateId)
    {
        this.stateId = stateId;
    }
    public String getStateName()
    {
        return stateName;
    }
    public void setStateName(String stateName)
    {
        this.stateName = stateName;
    }
    public String getStateDesc()
    {
        return stateDesc;
    }
    public void setStateDesc(String stateDesc)
    {
        this.stateDesc = stateDesc;
    }
    @Override
    public String toString()
    {
	return "ElectionStatesBean [stateId=" + stateId + ", stateName="
		+ stateName + ", stateDesc=" + stateDesc + "]";
    }
    
    
    
    
    
}
