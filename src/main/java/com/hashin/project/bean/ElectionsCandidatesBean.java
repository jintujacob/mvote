
package com.hashin.project.bean;

/**
 * @author jintu.jacob@gmail.com
 * Oct 9, 2013
 * ElectionsCandidatesBean
 */
public class ElectionsCandidatesBean {

	private String eleCandidateId; //PK
	private String unitEleId;
	private String candId;    //FK
	
	
	public String getEleCandidateId() {
		return eleCandidateId;
	}
	public void setEleCandidateId(String eleCandidateId) {
		this.eleCandidateId = eleCandidateId;
	}
	public String getUnitEleId() {
		return unitEleId;
	}
	public void setUnitEleId(String unitEleId) {
		this.unitEleId = unitEleId;
	}
	public String getCandId() {
		return candId;
	}
	public void setCandId(String candId) {
		this.candId = candId;
	}
	
	
}
