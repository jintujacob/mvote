
package com.hashin.project.bean;

import java.util.Date;

/**
 * @author jintu.jacob@gmail.com
 * Oct 9, 2013
 * ElectionsBean
 */
public class ElectionsBean {

	private String electId;
	private String electTitle;
	private String electStartDate;
	private String electEndDate;
	private String electDescription;
	private String customMessage;
	private String voterEnrollStat; 
	
	
	
	public String getVoterEnrollStat()
	{
	    return voterEnrollStat;
	}
	public void setVoterEnrollStat(String voterEnrollStat)
	{
	    this.voterEnrollStat = voterEnrollStat;
	}
	public String getElectId() {
		return electId;
	}
	public void setElectId(String electId) {
		this.electId = electId;
	}
	public String getElectTitle()
	{
	    return electTitle;
	}
	public void setElectTitle(String electTitle)
	{
	    this.electTitle = electTitle;
	}
	
	public String getElectStartDate()
	{
	    return electStartDate;
	}
	public void setElectStartDate(String electStartDate)
	{
	    this.electStartDate = electStartDate;
	}
	public String getElectEndDate()
	{
	    return electEndDate;
	}
	public void setElectEndDate(String electEndDate)
	{
	    this.electEndDate = electEndDate;
	}
	public String getElectDescription()
	{
	    return electDescription;
	}
	public void setElectDescription(String electDescription)
	{
	    this.electDescription = electDescription;
	}
	
	public String getCustomMessage()
	{
	    return customMessage;
	}
	public void setCustomMessage(String customMessage)
	{
	    this.customMessage = customMessage;
	}
	@Override
	public String toString() {
		return "ElectionsBean [electId=" + electId + ", electTitle="
				+ electTitle + ", electStartDate=" + electStartDate
				+ ", electEndDate=" + electEndDate + ", electDescription="
				+ electDescription + "]";
	}
	

	
	
	
}
