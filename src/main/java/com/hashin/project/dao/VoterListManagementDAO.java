package com.hashin.project.dao;

import java.util.List;

import javax.sql.DataSource;

import com.hashin.project.bean.VotersUserBean;

public interface VoterListManagementDAO
{
    public void setDataSource(DataSource dataSource);
    
    public VotersUserBean getVoterUserById(String voterID);
    
    //public List<VotersUserBean> getVotersByQueryName(String queryName, Object[] parameters);
    
    public int insertNewVoter(VotersUserBean voterUser);
    
    public VotersUserBean updateVotersById(VotersUserBean voterUser);
    
    public List<VotersUserBean> searchVoter(VotersUserBean userToSearch);
    
}
