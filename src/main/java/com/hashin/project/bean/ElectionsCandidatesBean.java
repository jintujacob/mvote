
package com.hashin.project.bean;

/**
 * @author jintu.jacob@gmail.com
 * Oct 9, 2013
 * ElectionsCandidatesBean
 */
public class ElectionsCandidatesBean extends CandidatesBean {

	private String eleCandidateId; //PK
	private String unitEleId;
	private String candId;    //FK
	
	/*---------------------  Properties from Parent
	private String candId;
	private String candName;
	private String candLogo;
	private String candBio;
	-------------------------------------------- */
	
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
