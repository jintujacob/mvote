
package com.hashin.project.bean;

import java.util.Date;

/**
 * @author jintu.jacob@gmail.com
 * Oct 9, 2013
 * ElectionsBean
 */
public class ElectionsBean {

	private int electId;
	private String electTitle;
	private Date electStartDate;
	private Date electEndDate;
	private String electDescription;
	
	
	public int getElectId()
	{
	    return electId;
	}
	public void setElectId(int electId)
	{
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
	public Date getElectStartDate()
	{
	    return electStartDate;
	}
	public void setElectStartDate(Date electStartDate)
	{
	    this.electStartDate = electStartDate;
	}
	public Date getElectEndDate()
	{
	    return electEndDate;
	}
	public void setElectEndDate(Date electEndDate)
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
	

	
	
}
