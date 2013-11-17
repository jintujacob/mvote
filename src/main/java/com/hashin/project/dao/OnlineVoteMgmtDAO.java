/**
 * 
 */
package com.hashin.project.dao;

import javax.sql.DataSource;

import com.hashin.project.bean.VotersAdhaarUserBean;
import com.hashin.project.bean.VotersUserBean;

/**
 * @author jintu.jacob@gmail.com
 * Oct 29, 2013
 * OnlineVoteMgmtDAO
 */
public interface OnlineVoteMgmtDAO
{
    public void setDataSource(DataSource dataSource);
    public int getVotingStatus(String votingPIN, String electionId);
    public int udpateVotingStatusByPin(String votingPIN, String electionId);    
}
