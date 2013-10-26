package com.hashin.project.dao;

import javax.sql.DataSource;
import com.hashin.project.bean.AdhaarUserBean;

public interface AdhaarDAO
{
    public void setDataSource(DataSource dataSource);
    public AdhaarUserBean getUserById(String adhaarID);
}
