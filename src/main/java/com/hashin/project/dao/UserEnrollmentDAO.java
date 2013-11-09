/**
 * 
 */
package com.hashin.project.dao;

import javax.sql.DataSource;

import com.hashin.project.bean.AdhaarUserBean;
import com.hashin.project.bean.VotersAdhaarUserBean;
import com.hashin.project.bean.VotersUserBean;

/**
 * @author jintu.jacob@gmail.com
 * Oct 29, 2013
 * UserEnrollmentDAO
 */
public interface UserEnrollmentDAO
{

    public void setDataSource(DataSource dataSource);
    
    public AdhaarUserBean getAdhaarUserById(String adhaarID);
    
    public VotersUserBean getVoterUserById(String voterID);
    
    /**
     * 
     * @param userToEnroll
     * @return String - the auto generated Id of the user
     */
    public Long createVotersAdhaarUser(VotersAdhaarUserBean userToEnroll);
    
    public String generateVotingPin(); 
    
}
