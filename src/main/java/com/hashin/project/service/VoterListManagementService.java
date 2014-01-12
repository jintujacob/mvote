package com.hashin.project.service;

import java.util.List;

import com.hashin.project.bean.VotersUserBean;

public interface VoterListManagementService
{
    /**
     * Method fetches VotersList information for a user for a provided votersId
     * @param votersId
     * @return VotersUserBean / null
     * public method due to reusability
     */
    public VotersUserBean getVoterById(String votersId);
    
/*    public List<VotersUserBean> getVotersByNameSearch(String voterName);
    
    public List<VotersUserBean> getVotersByNameAndConstituency(String voterName, String constId);
    
    public List<VotersUserBean> getVotersByNameConstAndLockoutFlag(String voterName, 
	    String constId, String flag);
*/
    public List<VotersUserBean> searchVoter(VotersUserBean userToSearch);
    
    public Boolean insertNewVoter(VotersUserBean voterUser);
    
    public VotersUserBean updateVotersById(VotersUserBean voterUser);
    
    
    
}
