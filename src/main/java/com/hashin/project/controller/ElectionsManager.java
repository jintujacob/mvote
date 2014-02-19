package com.hashin.project.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hashin.project.bean.ElectionsConstsBean;
import com.hashin.project.bean.FormListBean;
import com.hashin.project.service.ElectionManagementService;

/**
 * @author jintu.jacob@gmail.com Oct 9, 2013 ElectionsManager Handles all
 *         default inputs ending with /elections
 */

@Controller
@RequestMapping("elections")
public class ElectionsManager {

	@Autowired
	private ElectionManagementService electionMgmtService;
	private static final Logger logger = Logger
			.getLogger(ElectionsManager.class);
	private static final String CUSTOM_MSG = "SUCCESS";

	@RequestMapping(value = "/searchElection", method = RequestMethod.POST)
	public @ResponseBody
	FormListBean searchElection(@RequestBody ElectionsConstsBean toSearch) {
		logger.debug(">>___________ Search Params : " + toSearch.toString());

		List<ElectionsConstsBean> electionList = null;
		FormListBean elections = new FormListBean();

		try {
			electionList = electionMgmtService.searchElection(toSearch);

		} catch (Exception e) {
			logger.debug("Exception from backend -------> " + e.getMessage());
			elections.setCustomMessage("Unable to perform requested Operation");
		}
		if (electionList != null) {
			elections.setElectionList(electionList);
			elections.setCustomMessage(CUSTOM_MSG);
		}
		if (electionList.size() < 1) {
			// User is either not enrolled/ or no elections available for the
			// user
			elections.setCustomMessage("No Elections Available");
		}
		return elections;
	}
	
	
	@RequestMapping(value = "/getElectionDetail", method = RequestMethod.POST)
	public @ResponseBody
	FormListBean getElectionDetailById(@RequestBody ElectionsConstsBean toSearch) {
		logger.debug(">>___________ Search Params : " + toSearch.toString());

		List<ElectionsConstsBean> electionList = null;
		FormListBean elections = new FormListBean();

		try {
			electionList = electionMgmtService.searchElection(toSearch);

		} catch (Exception e) {
			logger.debug("Exception from backend -------> " + e.getMessage());
			elections.setCustomMessage("Unable to perform requested Operation");
		}
		if (electionList != null) {
			elections.setElectionList(electionList);
			elections.setCustomMessage(CUSTOM_MSG);
		}
		if (electionList.size() < 1) {
			// User is either not enrolled/ or no elections available for the
			// user
			elections.setCustomMessage("No Elections Available");
		}
		return elections;
	}
	
	

}
