package com.hashin.project.bean;


/**
 * 
 * @author jintu.jacob@gmail.com
 * represents voters table user
 */
public class VotersUserBean {

	private String votersId;
	private String adhaarId;
	private String votingPin;
	private String genDate;
	private String lockOutFlag;
	private String name;
	private String constituency;
	private String place;
	private String nameFirst;
	private String nameLast;
	private String state;
	
	
	public String getState()
	{
	    return state;
	}
	public void setState(String state)
	{
	    this.state = state;
	}
	public String getAdhaarId()
	{
	    return adhaarId;
	}
	public void setAdhaarId(String adhaarId)
	{
	    this.adhaarId = adhaarId;
	}
	public String getVotingPin()
	{
	    return votingPin;
	}
	public void setVotingPin(String votingPin)
	{
	    this.votingPin = votingPin;
	}
	public String getGenDate()
	{
	    return genDate;
	}
	public void setGenDate(String genDate)
	{
	    this.genDate = genDate;
	}
	public String getLockOutFlag()
	{
	    return lockOutFlag;
	}
	public void setLockOutFlag(String lockOutFlag)
	{
	    this.lockOutFlag = lockOutFlag;
	}
	public String getNameFirst()
	{
	    return nameFirst;
	}
	public void setNameFirst(String nameFirst)
	{
	    this.nameFirst = nameFirst;
	}
	public String getNameLast()
	{
	    return nameLast;
	}
	public void setNameLast(String nameLast)
	{
	    this.nameLast = nameLast;
	}
	public String getVotersId() {
		return votersId;
	}
	public void setVotersId(String votersId) {
		this.votersId = votersId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	
	
}
