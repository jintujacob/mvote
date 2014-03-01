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

import com.hashin.project.bean.FormListBean;
import com.hashin.project.bean.VotersUserBean;
import com.hashin.project.service.VoterListManagementService;


@Controller
@RequestMapping("voterslist")
public class VoterListManager {

	@Autowired
	private VoterListManagementService voterListMgmtService;
	private static final Logger logger = Logger
			.getLogger(VoterListManager.class);
	private static final String CUSTOM_MSG = "SUCCESS";

	@RequestMapping(method = RequestMethod.GET)
	public String getHomeAction() {
		logger.debug("in VotersList management Module");
		// do something
		return null;
	}
	
	

	/**
	 * Search Voter endpoint. Search can be with multiple params
	 * @param voterform
	 * @return
	 */
	@RequestMapping(value = "/searchVoter", method = RequestMethod.POST)
	public @ResponseBody
	FormListBean searchVoter(@RequestBody VotersUserBean voterform) {
		logger.debug(">>__________________recieved query params:"
				+ voterform.getVotersId() + " , " + voterform.getConstituency()
				+ " , " + voterform.getName() + " , "
				+ voterform.getLockOutFlag());

		FormListBean searchResult = new FormListBean();
		List<VotersUserBean> voterList = null;

		voterList = voterListMgmtService.searchVoter(voterform);

		if (voterList != null) {
			searchResult.setVoterList(voterList);
			searchResult.setCustomMessage(CUSTOM_MSG);
		} else {
			searchResult.setCustomMessage("NO RESULT");
		}
		return searchResult;
	}

	@RequestMapping(value = "/getVoterInfoById", method = RequestMethod.POST)
	public @ResponseBody
	VotersUserBean getVoterInfoById(@RequestBody VotersUserBean voterToSearch) {
		logger.debug(">>__________________recieved query params:"
				+ voterToSearch.getVotersId());

		VotersUserBean voterDetail = null;
		voterDetail = voterListMgmtService.getVoterDetailById(voterToSearch);
		if (voterDetail != null) {
			voterDetail.setCustomMessage(CUSTOM_MSG);
		} else {
			// in UI write check if custom message not equal SUCCESS
			// voterDetail.setCustomMessage("Unable to find information in the database");
		}
		return voterDetail;
	}


	@RequestMapping(value = "/activateVoter", method = RequestMethod.POST)
	public @ResponseBody
	VotersUserBean activateVoter(@RequestBody VotersUserBean voterToActivate) {
		logger.debug(">>__________________recieved query params:"
				+ voterToActivate.getVotersId()+ "/" + voterToActivate.getLockOutFlag());

		VotersUserBean voterDetail = null;
		voterDetail = voterListMgmtService.activateVoterByVoterId(voterToActivate);
		if (voterDetail != null) {
			voterDetail.setCustomMessage(CUSTOM_MSG);
		} else {
			voterDetail= new VotersUserBean();
			voterDetail.setCustomMessage("Unable to perform request!");
		}
		return voterDetail;
	}
	
	@RequestMapping(value = "/deActivateVoter", method = RequestMethod.POST)
	public @ResponseBody
	VotersUserBean deActivateVoter(@RequestBody VotersUserBean voterToBlock) {
		logger.debug(">>__________________recieved query params:"
				+ voterToBlock.getVotersId());

		VotersUserBean voterDetail = null;
		voterDetail = voterListMgmtService.deactivateVoterByVoterId(voterToBlock);
		if (voterDetail != null) {
			voterDetail.setCustomMessage(CUSTOM_MSG);
		} else {
			voterDetail= new VotersUserBean();
			voterDetail.setCustomMessage("Unable to perform request!");
		}
		return voterDetail;
	}
	
	
	
	
	
	@RequestMapping(value = "/insertNewVoter", method = RequestMethod.GET)
	public ModelAndView getVotersByNameConstAndFlag(
			@RequestParam VotersUserBean voterUser) {
		Boolean insertStatus = voterListMgmtService.insertNewVoter(voterUser);
		if (insertStatus != false) {
			// do something
		} else {
			// voter not found/ invalid voterId
		}
		return null;
	}

}
