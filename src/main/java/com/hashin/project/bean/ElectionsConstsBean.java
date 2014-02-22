
package com.hashin.project.bean;

/**
 * @author jintu.jacob@gmail.com
 * Oct 9, 2013
 * ElectionsConstsBean -> Elections_Constituencies
 */
public class ElectionsConstsBean extends ElectionsBean{  

	private int unitEleId;
	private int constId;
	
	/* -----------------------------Properties from Parent
	private int electId;
	private String electTitle;
	private String electStartDate;
	private String electEndDate;
	private String electDescription;
	-----------------------------------------------------*/
	
	
	public int getUnitEleId() {
		return unitEleId;
	}
	public void setUnitEleId(int unitEleId) {
		this.unitEleId = unitEleId;
	}
	public int getConstId() {
		return constId;
	}
	public void setConstId(int constId) {
		this.constId = constId;
	}

	
}

