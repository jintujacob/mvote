
package com.hashin.project.bean;

/**
 * @author jintu.jacob@gmail.com
 * Oct 9, 2013
 * VotersAdhaarUserBean
 */
public class VotersAdhaarUserBean {

	private String votersId;
	private String adhaarId;
	private String eElectionId;
	private String votingPIN;
	private String genDate;
	private String lockOutFlag;
	private String customMessage;

	
	public String getCustomMessage()
	{
	    return customMessage;
	}
	public void setCustomMessage(String customMessage)
	{
	    this.customMessage = customMessage;
	}
	public String geteElectionId()
	{
	    return eElectionId;
	}
	public void seteElectionId(String eElectionId)
	{
	    this.eElectionId = eElectionId;
	}

	
	public String getVotersId() {
		return votersId;
	}
	public void setVotersId(String votersId) {
		this.votersId = votersId;
	}
	public String getAdhaarId() {
		return adhaarId;
	}
	public void setAdhaarId(String adhaarId) {
		this.adhaarId = adhaarId;
	}
	public String getVotingPIN() {
		return votingPIN;
	}
	public void setVotingPIN(String votingPIN) {
		this.votingPIN = votingPIN;
	}
	public String getGenDate() {
		return genDate;
	}
	public void setGenDate(String genDate) {
		this.genDate = genDate;
	}
	public String getLockOutFlag() {
		return lockOutFlag;
	}
	public void setLockOutFlag(String lockOutFlag) {
		this.lockOutFlag = lockOutFlag;
	}
	
}
