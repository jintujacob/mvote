package com.hashin.project.service;

import com.hashin.project.bean.AdhaarUserBean;
import com.hashin.project.bean.UserFormBean;
import com.hashin.project.bean.VotersAdhaarUserBean;
import com.hashin.project.bean.VotersUserBean;

public interface UserEnrollmentService
{
    /**
     * manageUserEnrollement(user)
     * starter method
     * External methods should call this
     * rest of the methods are internal.
     */
    public VotersAdhaarUserBean manageUserEnrollement(String votersId, String adhaarId);
    
    
    /**
     * Method fetches Adhaar information for the user for provided adhaar id
     * @param adhaarId
     * @return null / AdhaarUserBean
     * public method =>  reusability
     */
    public AdhaarUserBean getAdhaarUserById(String adhaarId);
    
    
    public VotersUserBean getVoterUserById(String votersId);
    
    
    // --can be private can be removed from the interface since it is not externally required
    //public String generateVotingPin();
  
   
}
