package com.hashin.project.service;

import java.util.List;
import com.hashin.project.bean.ElectionsBean;

public interface ElectionsService
{
	public void create(ElectionsBean election);
	public ElectionsBean getById(int electId);
	public List<ElectionsBean> listAll();
	public List<ElectionsBean> searchByTitle(String electTitle);
}
