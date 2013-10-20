package com.hashin.project.bean;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import com.hashin.project.controller.MainController;



public class TestBean {

	@Resource(name="jdbc/mkyongdb")
	private DataSource ds;
	
	private static final Logger logger = Logger.getLogger(MainController.class);
	
	
	public TestBean() {
		
		  //get database connection
		  try {
			Connection con = ds.getConnection();
			if(logger.isDebugEnabled()){
				logger.debug("testbean:jintu : "+con.getMetaData());
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}


 
 
 
