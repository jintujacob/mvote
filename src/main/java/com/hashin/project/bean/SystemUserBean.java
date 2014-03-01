package com.hashin.project.bean;

public class SystemUserBean
{


    private String uid;
    private String nameFirst;
    private String nameLast;
    private String email;
    private String password;
    private String activeStatus;
    private String customMessage;
    
    @Override
    public String toString()
    {
	return "SystemUserBean [uid=" + uid + ", nameFirst=" + nameFirst
		+ ", nameLast=" + nameLast + ", email=" + email + ", password="
		+ password + ", activeStatus=" + activeStatus + "]";
    }

    public String getUid()
    {
        return uid;
    }

    public void setUid(String uid)
    {
        this.uid = uid;
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

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getActiveStatus()
    {
        return activeStatus;
    }

    public void setActiveStatus(String activeStatus)
    {
        this.activeStatus = activeStatus;
    }

    public String getCustomMessage()
    {
        return customMessage;
    }

    public void setCustomMessage(String customMessage)
    {
        this.customMessage = customMessage;
    }
    
    
    
}
