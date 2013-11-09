/**
 * 
 */
package com.hashin.project.dao;

import com.hashin.project.bean.VotersAdhaarUserBean;
import com.hashin.project.bean.VotersUserBean;

/**
 * @author jintu.jacob@gmail.com
 * Oct 29, 2013
 * OnlineVoteMgmtDAO
 */
public interface OnlineVoteMgmtDAO
{

    public VotersAdhaarUserBean getEnrollmentStatus(String votingPin, String adhaarId, String voterId);
    
    public Boolean getVotingStatus(String votingPIN, String electionId);
    
}
