package com.hashin.project.service;


import com.hashin.project.bean.AdhaarUserBean;
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
	public VotersAdhaarUserBean manageUserEnrollement(
			VotersAdhaarUserBean verifiedUser) throws Exception;
    
    
    /**
     * Method fetches Adhaar information for the user for provided adhaar id
     * @param adhaarId
     * @return null / AdhaarUserBean
     * public method =>  reusability
     */
    public AdhaarUserBean getAdhaarUserById(String adhaarId);
    
    /**
     * Direct querying of voters table only. Pre enrollment information fetch
     * @param votersId
     * @return
     */
    public VotersUserBean getVoterUserById(String votersId);
    
    public VotersAdhaarUserBean getUserEnrollmentInfo(VotersAdhaarUserBean usrToFind) throws Exception;
    
	public VotersAdhaarUserBean updatePinForEnrolledUser(VotersAdhaarUserBean usrToEnrl) throws Exception;

    
    // --can be private can be removed from the interface since it is not externally required
    //public String generateVotingPin();
  
   
}
