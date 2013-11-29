
package com.hashin.project.bean;

/**
 * @author jintu.jacob@gmail.com
 * Oct 9, 2013
 * ElectionsCandidatesBean
 */
public class ElectionsCandidatesBean extends CandidatesBean {

	/*---------------------  Properties inherited from Parent
	private String candId;
	private String candName;
	private String candLogo;
	private String candBio;
	-------------------------------------------- */

    
	private String eleCandidateId; 
	private String unitEleId;
	
	private String constName;
	private String constState;
	
	private String eleId;
	private String eleTitle;
	private String eleDesc;
	public String getEleCandidateId()
	{
	    return eleCandidateId;
	}
	public void setEleCandidateId(String eleCandidateId)
	{
	    this.eleCandidateId = eleCandidateId;
	}
	public String getUnitEleId()
	{
	    return unitEleId;
	}
	public void setUnitEleId(String unitEleId)
	{
	    this.unitEleId = unitEleId;
	}
	public String getConstName()
	{
	    return constName;
	}
	public void setConstName(String constName)
	{
	    this.constName = constName;
	}
	public String getConstState()
	{
	    return constState;
	}
	public void setConstState(String constState)
	{
	    this.constState = constState;
	}
	public String getEleId()
	{
	    return eleId;
	}
	public void setEleId(String eleId)
	{
	    this.eleId = eleId;
	}
	public String getEleTitle()
	{
	    return eleTitle;
	}
	public void setEleTitle(String eleTitle)
	{
	    this.eleTitle = eleTitle;
	}
	public String getEleDesc()
	{
	    return eleDesc;
	}
	public void setEleDesc(String eleDesc)
	{
	    this.eleDesc = eleDesc;
	}
	
	

	
	
}
