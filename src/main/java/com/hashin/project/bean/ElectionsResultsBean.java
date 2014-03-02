
package com.hashin.project.bean;

/**
 * @author jintu.jacob@gmail.com
 * Oct 9, 2013
 * ElectionsResultsBean
 */
public class ElectionsResultsBean {

	private String constId;
	private String unitEleId;
	private String customMessage;
	private String totalVoteCount;
	
	private String candId;
	private String candName;
	private String constVoteCount;
	
	
	
	
	public String getCandId()
	{
	    return candId;
	}




	public void setCandId(String candId)
	{
	    this.candId = candId;
	}




	public String getCandName()
	{
	    return candName;
	}




	public void setCandName(String candName)
	{
	    this.candName = candName;
	}




	public String getConstVoteCount()
	{
	    return constVoteCount;
	}




	public void setConstVoteCount(String constVoteCount)
	{
	    this.constVoteCount = constVoteCount;
	}




	public String getTotalVoteCount()
	{
	    return totalVoteCount;
	}




	public void setTotalVoteCount(String totalVoteCount)
	{
	    this.totalVoteCount = totalVoteCount;
	}




	public String getCustomMessage()
	{
	    return customMessage;
	}




	public void setCustomMessage(String customMessage)
	{
	    this.customMessage = customMessage;
	}




	public String getConstId()
	{
	    return constId;
	}




	public void setConstId(String constId)
	{
	    this.constId = constId;
	}




	public String getUnitEleId()
	{
	    return unitEleId;
	}




	public void setUnitEleId(String unitEleId)
	{
	    this.unitEleId = unitEleId;
	}




	@Override
	public String toString()
	{
	    return "ElectionsResultsBean [constId=" + constId + ", unitEleId="
		    + unitEleId + ", customMessage=" + customMessage
		    + ", totalVoteCount=" + totalVoteCount + ", candId="
		    + candId + ", candName=" + candName + ", constVoteCount="
		    + constVoteCount + "]";
	}
	
	



	
}
