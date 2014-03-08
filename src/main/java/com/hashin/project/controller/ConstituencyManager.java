package com.hashin.project.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hashin.project.bean.ConstituenciesBean;
import com.hashin.project.bean.ElectionsBean;
import com.hashin.project.bean.FormListBean;
import com.hashin.project.service.ElectionManagementService;

@Controller
@RequestMapping("consts")
public class ConstituencyManager {

	@Autowired
	private ElectionManagementService electionMgmtService ;
	private static final Logger logger = Logger.getLogger(ConstituencyManager.class);
	private static final String CUSTOM_MSG = "SUCCESS";

	@RequestMapping(method = RequestMethod.GET)
	public String getHomeAction() {
		logger.info("in ConstituencyManager");
		return null;
	}


	@RequestMapping(value = "/getAllConsts", method = RequestMethod.POST)
	public @ResponseBody
	FormListBean getAllConstituencies() {
		logger.info(">>_____________fetch all consts ");
		FormListBean constList =  new FormListBean();
		
		List<ConstituenciesBean> consts =  electionMgmtService.getAllConsts();
		if(consts.size() > 0){
			constList.setConstsList(consts);
			constList.setCustomMessage(CUSTOM_MSG);
		}else{
			constList.setCustomMessage("Infomation Unavailable!");
		}
		
		return constList;
	}

	
	@RequestMapping(value = "/searchConsts", method = RequestMethod.POST)
	public @ResponseBody FormListBean deleteElection(@RequestBody ConstituenciesBean toSearch) 
	{
		logger.info(">>___________ /searchConsts -> Search Params : " + toSearch.toString());
		FormListBean formBean = new FormListBean();
		try{
		    List<ConstituenciesBean> results = electionMgmtService.searchConstsByName(toSearch);
		    if(results != null){
			formBean.setConstsList(results);
			formBean.setCustomMessage(CUSTOM_MSG);
		    }else{
			formBean.setCustomMessage("No Matching constituencies found");
		    }
		}catch(Exception e){
		    formBean.setCustomMessage("System Exception.Unable to retreive data from server.");
		}

		logger.info("<<____________ /searchConsts -> Results"+ formBean.toString());
		return formBean;
	}


	

}
