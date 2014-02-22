package com.hashin.project.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hashin.project.bean.ElectionStatesBean;
import com.hashin.project.bean.ElectionsBean;
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
	FormListBean searchElection(@RequestBody ElectionsBean toSearch) {
		logger.debug(">>___________ /searchElection - Search Params : " + toSearch.toString());

		List<ElectionsBean> electionList = null;
		FormListBean elections = new FormListBean();

		try {
			electionList = electionMgmtService.searchElection(toSearch);

		} catch (Exception e) {
			logger.debug("Exception from backend -------> " + e.getMessage());
			elections.setCustomMessage("Unable to perform requested Operation");
		}
		if (electionList != null) {
			elections.setElectionBasicDetailList(electionList);
			elections.setCustomMessage(CUSTOM_MSG);
		}
		if (electionList.size() < 1) {
			// User is either not enrolled/ or no elections available for the
			// user
			elections.setCustomMessage("No Elections Available");
		}
		logger.debug("<<___________ /searchElection - Results : " + elections.toString());
		return elections;
	}
	
	
	@RequestMapping(value = "/getElectionDetail", method = RequestMethod.POST)
	public @ResponseBody
	ElectionsBean getElectionDetailById(@RequestBody ElectionsBean eleToFind) 
	{
		logger.debug(">>___________ /getElectionDetail - Search Params : " + eleToFind.toString());
		
		ElectionsBean eleDetails =  electionMgmtService.getElectionDetail(eleToFind);
		if(eleDetails != null) {
		    eleDetails.setCustomMessage(CUSTOM_MSG);
		}
		else{
		    eleDetails = new ElectionsBean();
		    eleDetails.setCustomMessage("No Election Detail available!"); 
		}
		
		logger.debug("<<___________ /getElectionDetail - Results : " + eleDetails.toString());
		return eleDetails;
	}

	@RequestMapping(value = "/getStatesListByElection", method = RequestMethod.POST)
	public @ResponseBody FormListBean getStatesListByElection(@RequestBody ElectionsBean eleToFind) 
	{
		logger.debug(">>___________ Search Params : " + eleToFind.toString());
		FormListBean formBean = new FormListBean();
		
		List<ElectionStatesBean> statesList = electionMgmtService.getStatesByElectionId(eleToFind);
		
		if(statesList != null){
		    formBean.setStatesList(statesList);
		    formBean.setCustomMessage(CUSTOM_MSG);
		}else{
		    formBean.setCustomMessage("No states Added for the searched Election!");
		}

		logger.debug("<<____________ /getStatesListByElection Results"+ formBean.toString());
		return formBean;
	}

	
	@RequestMapping(value = "/getAllStates", method = RequestMethod.POST)
	public @ResponseBody FormListBean getAllStates() 
	{
		logger.debug(">>___________ /getAllStates-> Search Params : " + null);
		FormListBean formBean = new FormListBean();
		
		List<ElectionStatesBean> statesList = electionMgmtService.getAllStatesForMenu();
		
		if(statesList != null){
		    formBean.setStatesList(statesList);
		    formBean.setCustomMessage(CUSTOM_MSG);
		}else{
		    formBean.setCustomMessage("Unable to fetch States List!");
		}

		logger.debug("<<____________ /getAllStates -> Results"+ formBean.toString());
		return formBean;
	}
	
	@RequestMapping(value = "/addNewElection", method = RequestMethod.POST)
	public @ResponseBody FormListBean addNewElection(@RequestBody ElectionsBean eleToFind) 
	{
		logger.debug(">>___________ /addNewElection -> Search Params : " + eleToFind);
		FormListBean formBean = new FormListBean();
		try{
		    ElectionsBean eleResp = electionMgmtService.addNewElection(eleToFind);
		    if(eleResp != null){
			    formBean.setCustomMessage(CUSTOM_MSG);
		    }else{
			formBean.setCustomMessage("Unable to perform requested operation.");
		    }
		}catch(Exception e){
		    formBean.setCustomMessage("System Exception.Unable to update data on the server side");
		}

		logger.debug("<<____________ /getAllStates -> Results"+ formBean.toString());
		return formBean;
	}
	
	@RequestMapping(value = "/enrollVotersForElection", method = RequestMethod.POST)
	public @ResponseBody FormListBean enrollVotersForElection(@RequestBody ElectionsBean eleToFind) 
	{
		logger.debug(">>___________ /enrollVotersForElection -> Search Params : " + eleToFind);
		FormListBean formBean = new FormListBean();
		try{
		    ElectionsBean eleResp = electionMgmtService.enrollVotersForElection(eleToFind);
		    formBean.setCustomMessage(eleResp.getCustomMessage());
		    
		}catch(Exception e){
		    formBean.setCustomMessage("System Exception.Unable to update data on the server side");
		}

		logger.debug("<<____________ /enrollVotersForElection -> Results"+ formBean.toString());
		return formBean;
	}


	@RequestMapping(value = "/deleteElection", method = RequestMethod.POST)
	public @ResponseBody FormListBean deleteElection(@RequestBody ElectionsBean eleToDelete) 
	{
		logger.debug(">>___________ /deleteElection -> Search Params : " + eleToDelete);
		FormListBean formBean = new FormListBean();
		try{
		    ElectionsBean eleResp = electionMgmtService.deleteElection(eleToDelete);
		    formBean.setCustomMessage(eleResp.getCustomMessage());
		    
		}catch(Exception e){
		    formBean.setCustomMessage("System Exception.Unable to update data on the server side");
		}

		logger.debug("<<____________ /deleteElection -> Results"+ formBean.toString());
		return formBean;
	}


// END of the class
}
