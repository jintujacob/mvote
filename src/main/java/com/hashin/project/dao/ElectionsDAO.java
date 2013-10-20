package com.hashin.project.dao;

import java.util.List;
import javax.sql.DataSource;
import com.hashin.project.bean.ElectionsBean;

public interface ElectionsDAO {
	
    	public void setDataSource(DataSource dataSource);
	public void create(ElectionsBean election);
	public ElectionsBean getById(int electId);
	public List<ElectionsBean> listAll();
	public List<ElectionsBean> searchByTitle(String electTitle);

}
