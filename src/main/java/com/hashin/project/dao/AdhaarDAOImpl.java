/**
 * 
 */
package com.hashin.project.dao;
	 

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hashin.project.bean.AdhaarUserBean;
import com.hashin.project.util.AdhaarRowMapper;

/**
 * @author jintu.jacob@gmail.com
 * Oct 26, 2013
 * AdhaarDAOImpl
 */
public class AdhaarDAOImpl implements AdhaarDAO
{

    private static final Logger logger = Logger.getLogger(ElectionsDAOImpl.class);
    private JdbcTemplate jdbcTemplate;
    
    @Resource(name="dataSource")
    public void setDataSource(DataSource dataSource)
    {
	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    
    @Override
    public AdhaarUserBean getUserById(String adhaarID)
    {
	String query = "select * from adhaarDB where adhaar_id= ?";
	Object[] parameters = new Object[] {adhaarID};
	List<AdhaarUserBean> userList= jdbcTemplate.query(query, parameters, new AdhaarRowMapper());

	logger.debug("AdhaarDAOImpl #getUserById Query=> executed" );
	logger.debug("AdhaarDAOImpl #getUserById result => "+ userList.get(0).getNameFirst());
	return userList.get(0);    
    }

}
