/**
 * 
 */
package com.hashin.project.dao;

import javax.sql.DataSource;

import com.hashin.project.bean.AdhaarUserBean;
import com.hashin.project.bean.VotersAdhaarUserBean;

/**
 * @author jintu.jacob@gmail.com
 * Oct 29, 2013
 * UserEnrollmentDAO
 */
public interface UserEnrollmentDAO
{

    public void setDataSource(DataSource dataSource);
    
    public AdhaarUserBean getAdhaarUserById(String adhaarID);
    
    
    
    /**
     * 
     * @param userToEnroll
     * @return String - the auto generated Id of the user
     */
    public Long createVotersAdhaarUser(VotersAdhaarUserBean userToEnroll);
    
    public int udpateVotersAdhaarUserByPIN(VotersAdhaarUserBean userToUpdate);
    
    public VotersAdhaarUserBean getEnrolledUserDetail(VotersAdhaarUserBean userToFind);
    
    public int getEnrollmentStatus(String votingPin, String adhaarId,
	    String voterId);

    
}
