package com.hashin.project.controller;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hashin.project.bean.ElectionsBean;
import com.hashin.project.bean.ElectionsCandidatesBean;
import com.hashin.project.bean.FormListBean;
import com.hashin.project.service.ElectionManagementService;

/**
 * @author jintu.jacob@gmail.com Oct 9, 
 * 2013 ElectionsManager Handles all
 * default inputs ending with /candidates
 * it refers to the electionsManagementService for service layer funcitons
 * 
 */

@Controller
@RequestMapping("candidates")
public class CandidatesManager {

	@Autowired
	private ElectionManagementService electionMgmtService;
	private static final Logger logger = Logger.getLogger(CandidatesManager.class);

	private static final String CUSTOM_MSG = "SUCCESS";
	
	@RequestMapping(value = "/searchCandidate", method = RequestMethod.POST)
	public @ResponseBody
	FormListBean searchElection(@RequestBody ElectionsCandidatesBean toSearch) {
		logger.debug(">>___________ /searchCandidate - Search Params : " + toSearch.toString());

		List<ElectionsCandidatesBean> candidateList = null;
		FormListBean srchResult = new FormListBean();

		try {
			candidateList = electionMgmtService.searchCandidate(toSearch);
			if (candidateList != null ) {
			    srchResult.setCandidateList(candidateList);
			    srchResult.setCustomMessage(CUSTOM_MSG);
			}
			else{
			    srchResult.setCustomMessage("No Matches found");
			}
		} catch (Exception e) {
			logger.debug("Exception from backend -------> " + e.getMessage());
			srchResult.setCustomMessage("Unable to perform requested opertation due to system exception");
		}
		logger.debug("<<___________ /searchCandidate - Results : " + srchResult.toString());
		return srchResult;
	}

	

	
}
