package com.hashin.project.bean;

/**
 * 
 * @author jintu.jacob@gmail.com
 * represents a user in the adhaarDB table
 */

public class AdhaarUserBean {

	private String adhaarID;
	private String nameFirst;
	private String nameLast;
	private String address;
	private String phone;
	
	public String getAdhaarID() {
		return adhaarID;
	}
	public String getNameFirst() {
		return nameFirst;
	}
	public String getNameLast() {
		return nameLast;
	}
	public String getAddress() {
		return address;
	}
	public String getPhone() {
		return phone;
	}
	public void setAdhaarID(String adhaarID)
	{
	    this.adhaarID = adhaarID;
	}
	public void setNameFirst(String nameFirst)
	{
	    this.nameFirst = nameFirst;
	}
	public void setNameLast(String nameLast)
	{
	    this.nameLast = nameLast;
	}
	public void setAddress(String address)
	{
	    this.address = address;
	}
	public void setPhone(String phone)
	{
	    this.phone = phone;
	}
}
