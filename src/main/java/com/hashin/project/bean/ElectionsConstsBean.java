
package com.hashin.project.bean;

/**
 * @author jintu.jacob@gmail.com
 * Oct 9, 2013
 * ElectionsConstsBean -> Elections_Constituencies
 */
public class ElectionsConstsBean extends ElectionsBean{  

	private String unitEleId;
	private String constId;
	private String stateId;
	
	/* -----------------------------Properties from Parent
	private String electId;
	private String electTitle;
	private String electStartDate;
	private String electEndDate;
	private String electDescription;
	-----------------------------------------------------*/
	
	
	
	public String getUnitEleId() {
		return unitEleId;
	}

	@Override
	public String toString() {
		
		return super.toString()+"ElectionsConstsBean [unitEleId=" + unitEleId + ", constId="
				+ constId + ", stateId=" + stateId + "]";
	}

	public void setUnitEleId(String unitEleId) {
		this.unitEleId = unitEleId;
	}

	public String getConstId() {
		return constId;
	}

	public void setConstId(String constId) {
		this.constId = constId;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	
	
	
}

