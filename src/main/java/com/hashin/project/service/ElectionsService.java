package com.hashin.project.service;

import java.util.List;
import com.hashin.project.bean.ElectionsBean;

public interface ElectionsService
{
	public int create(ElectionsBean election);
	public ElectionsBean getById(int electId);
	public List<ElectionsBean> listAll();
	public List<ElectionsBean> searchWildCard(String electTitle);
}
