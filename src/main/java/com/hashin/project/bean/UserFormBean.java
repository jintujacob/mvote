/**
 * 
 */
package com.hashin.project.bean;

/**
 * @author jintu.jacob@gmail.com
 * Oct 29, 2013
 * UserFormBean
 */
public class UserFormBean
{
    // what all things user going to input in the form as a part of the registration
    // this information is verified against votersList and adhaar 
    
    String adhaarID;
    String votersID;
    String telephone;
    
    public String getAdhaarID()
    {
        return adhaarID;
    }
    public void setAdhaarID(String adhaarID)
    {
        this.adhaarID = adhaarID;
    }
    public String getVotersID()
    {
        return votersID;
    }
    public void setVotersID(String votersID)
    {
        this.votersID = votersID;
    }
    public String getTelephone()
    {
        return telephone;
    }
    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }
    
    
    
    
    
}
