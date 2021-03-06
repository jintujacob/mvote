/**
 * 
 */
package com.hashin.project.dao;

import java.util.List;

import javax.sql.DataSource;

import com.hashin.project.bean.ConstituenciesBean;
import com.hashin.project.bean.ElectionStatesBean;
import com.hashin.project.bean.ElectionsBean;
import com.hashin.project.bean.ElectionsCandidatesBean;
import com.hashin.project.bean.ElectionsConstsBean;
import com.hashin.project.bean.SystemUserBean;

/**
 * @author jintu.jacob@gmail.com Oct 29, 2013 ElectionManagementDAO
 */
public interface AccessControlDAO {

	public void setDataSource(DataSource dataSource);

	public int validatedSystemUserCount(String uid, String password);

	public int addSystemUser(SystemUserBean toadd);

	
}
