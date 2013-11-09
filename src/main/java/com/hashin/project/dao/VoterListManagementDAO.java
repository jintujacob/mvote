package com.hashin.project.dao;

import javax.sql.DataSource;

import com.hashin.project.bean.VotersUserBean;

public interface VoterListManagementDAO
{
    public void setDataSource(DataSource dataSource);
    public VotersUserBean getVoterUserById(String voterID);
}
