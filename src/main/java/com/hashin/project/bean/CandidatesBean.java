
package com.hashin.project.bean;

/**
 * @author jintu.jacob@gmail.com
 * Oct 9, 2013
 * CandidatesBean
 */
public class CandidatesBean {

	private String candId;
	private String candName;
	private String candLogo;
	private String candBio;
	
	
	public String getCandId() {
		return candId;
	}
	public void setCandId(String candId) {
		this.candId = candId;
	}
	public String getCandName() {
		return candName;
	}
	public void setCandName(String candName) {
		this.candName = candName;
	}
	public String getCandLogo() {
		return candLogo;
	}
	public void setCandLogo(String candLogo) {
		this.candLogo = candLogo;
	}
	public String getCandBio() {
		return candBio;
	}
	public void setCandBio(String candBio) {
		this.candBio = candBio;
	}
	@Override
	public String toString()
	{
	    return "CandidatesBean [candId=" + candId + ", candName="
		    + candName + ", candLogo=" + candLogo + ", candBio="
		    + candBio + "]";
	}

	
	
}
