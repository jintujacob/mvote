/**
 * 
 */
package com.hashin.project.dao;

import javax.sql.DataSource;

import com.hashin.project.bean.AdhaarUserBean;

/**
 * @author jintu.jacob@gmail.com
 * Oct 29, 2013
 * UserEnrollmentDAO
 */
public interface UserEnrollmentDAO
{

    public void setDataSource(DataSource dataSource);
    public AdhaarUserBean getUserById(String adhaarID);
}
