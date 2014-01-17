package com.hashin.project.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hashin.project.bean.ConstituenciesBean;
import com.hashin.project.bean.ElectionsCandidatesBean;
import com.hashin.project.bean.ElectionsConstsBean;
import com.hashin.project.bean.FormBeanGetCandidates;
import com.hashin.project.bean.FormListBean;
import com.hashin.project.bean.VotersAdhaarUserBean;
import com.hashin.project.service.ElectionManagementService;
import com.hashin.project.service.OnlineVotingService;
import com.hashin.project.bean.OnlineVoteManagerBean;

@Controller
@RequestMapping("consts")
public class ConstituencyManager {

	@Autowired
	private ElectionManagementService electionMgmtService ;
	private static final Logger logger = Logger.getLogger(ConstituencyManager.class);
	private static final String CUSTOM_MSG = "SUCCESS";

	@RequestMapping(method = RequestMethod.GET)
	public String getHomeAction() {
		logger.debug("in ConstituencyManager");
		return null;
	}


	@RequestMapping(value = "/getAllConsts", method = RequestMethod.POST)
	public @ResponseBody
	FormListBean getAllConstituencies() {
		logger.debug(">>_____________fetch all consts ");
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

	

}
