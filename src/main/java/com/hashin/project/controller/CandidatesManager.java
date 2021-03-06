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

import com.hashin.project.bean.ConstituenciesBean;
import com.hashin.project.bean.ElectionsBean;
import com.hashin.project.bean.ElectionsCandidatesBean;
import com.hashin.project.bean.ElectionsConstsBean;
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
		logger.info(">>___________ /searchCandidate - Search Params : " + toSearch.toString());

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
			logger.info("Exception from backend -------> " + e.getMessage());
			srchResult.setCustomMessage("Unable to perform requested opertation due to system exception");
		}
		logger.info("<<___________ /searchCandidate - Results : " + srchResult.toString());
		return srchResult;
	}
	
	@RequestMapping(value = "/deleteCandidate", method = RequestMethod.POST)
	public @ResponseBody
	FormListBean deleteCandidate(@RequestBody ElectionsCandidatesBean toDelete) {
		logger.info(">>___________ /searchCandidate - Search Params : " + toDelete.toString());
		FormListBean resultBean = new FormListBean();
		
		try {
			ElectionsCandidatesBean ecBean = electionMgmtService.deleteCandidate(toDelete);
			if (ecBean != null ) {
			    resultBean.setCustomMessage(CUSTOM_MSG);
			}
			else{
			    resultBean.setCustomMessage("Unable to perform requested operation. "
			    	+ "Requested candidate not found!");
			}
		} catch (Exception e) {
			logger.info("Exception from backend -------> " + e.getMessage());
			resultBean.setCustomMessage("Unable to perform requested opertation due to system exception");
		}
		
		logger.info("<<___________ /searchCandidate - Results : " + resultBean.toString());
		return resultBean;
	}

	
	@RequestMapping(value = "/getElectionsByConst", method = RequestMethod.POST)
	public @ResponseBody
	FormListBean getAvailableElections(@RequestBody ConstituenciesBean toFind) {
		logger.info(">>___________ /getElectionsByConst "+ toFind.toString());
		FormListBean resultBean = new FormListBean();
		
		try {
			List<ElectionsConstsBean> eleList = electionMgmtService.getElectionsListByConst(toFind);
			if (eleList != null ) {
			    resultBean.setCustomMessage(CUSTOM_MSG);
			    resultBean.setElectionList(eleList);
			}
			else{
			    resultBean.setCustomMessage("No Available Elections.");
			}
		} catch (Exception e) {
			logger.info("Exception from backend -------> " + e.getMessage());
			resultBean.setCustomMessage("Unable to perform requested opertation due to system exception");
		}
		
		logger.info("<<___________ /getElectionsByConst - Results : " + resultBean.toString());
		return resultBean;
	}

	@RequestMapping(value = "/addNewCandidate", method = RequestMethod.POST)
	public @ResponseBody
	FormListBean addNewCandidate(@RequestBody ElectionsCandidatesBean beanToAdd) {
		logger.info(">>___________ /addNewCandidate : recieved params:: "+ beanToAdd.toString());
		FormListBean resultBean = new FormListBean();
		
		try {
			ElectionsCandidatesBean cand = electionMgmtService.addNewCandidate(beanToAdd);
			if (cand != null ) {
			    resultBean.setCustomMessage(CUSTOM_MSG);	
			 }
			else{
			    resultBean.setCustomMessage("Unable to add candidate information to database");
			}
		} catch (Exception e) {
			logger.info("Exception from backend -------> " + e.getMessage());
			resultBean.setCustomMessage("Unable to perform requested opertation due to system exception");
		}
		
		logger.info("<<___________ /addNewCandidate - Results : " + resultBean.toString());
		return resultBean;
	}

	
	
//end of the class	
}
