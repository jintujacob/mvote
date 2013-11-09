package com.hashin.project.service;

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
}
