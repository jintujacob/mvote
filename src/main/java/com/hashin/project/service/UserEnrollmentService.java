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
    public Long manageUserEnrollement(String votersId, String adhaarId);
    
    public VotersAdhaarUserBean verifyUser(UserFormBean user);
    
    
    /**
     * Method verifies user against the adhaarDB
     * @param user
     * @return VotersAdhaarUserBean if succeeds
     * @return null if the user is not found /verified in the Adhaar
     */
    //public AdhaarUserBean verifyUserInAdhaar(UserFormBean user);
    
    
    /**
     * Method verifies user agains the VotersList
     * @param user
     * @return VotersUserBean if succeeds
     * @return null if user not found/verified in the Voters List
     */
    //public VotersUserBean verifyUserInVoterList(UserFormBean user);
    
    
    /**
     * Method fetches VotersList information for a user for a provided votersId
     * @param votersId
     * @return VotersUserBean / null
     * public method due to reusability
     */
    public VotersUserBean getVoterById(String votersId);
    
    
    /**
     * Method fetches Adhaar information for the user for provided adhaar id
     * @param adhaarId
     * @return null / AdhaarUserBean
     * public method =>  reusability
     */
    public AdhaarUserBean getAdhaarUserById(String adhaarId);
    
    
    
    // --can be private can be removed from the interface since it is not externally required
    //public String generateVotingPin();
  
   
}
