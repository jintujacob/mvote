package com.hashin.project.bean;

/**
 * @author jintu.jacob@gmail.com
 * Oct 9, 2013
 * ConstituenciesBean
 */
public class ConstituenciesBean {

	private String constId;
	private String constName;
	private String constState;
	
	public String getConstId() {
		return constId;
	}
	public void setConstId(String constId) {
		this.constId = constId;
	}
	public String getConstName() {
		return constName;
	}
	public void setConstName(String constName) {
		this.constName = constName;
	}
	public String getConstState() {
		return constState;
	}
	public void setConstState(String constState) {
		this.constState = constState;
	}
	@Override
	public String toString()
	{
	    return "ConstituenciesBean [constId=" + constId + ", constName="
		    + constName + ", constState=" + constState + "]";
	}
	
	
	
	
	
}
