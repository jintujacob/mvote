package com.hashin.project.controller;

import java.util.List;

import javax.jws.WebParam.Mode;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hashin.project.bean.AdhaarUserBean;
import com.hashin.project.bean.ElectionsBean;
import com.hashin.project.bean.VotersAdhaarUserBean;
import com.hashin.project.bean.VotersUserBean;
import com.hashin.project.service.UserEnrollmentService;
import com.hashin.project.service.VoterListManagementService;

/**
 * @author jintu.jacob@gmail.com Oct 9, 2013 UserEnrollmentManager Handles all
 *         default inputs ending with /enroll
 */

@Controller
@RequestMapping("/enroll")
public class UserEnrollmentManager {
	@Autowired
	private VoterListManagementService voterListMgmtService;

	@Autowired
	private UserEnrollmentService userEnrollmentService;
	private static final Logger logger = Logger
			.getLogger(UserEnrollmentManager.class);
	private static final String CUSTOM_MSG = "SUCCESS";

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getHomeAction() {
		logger.debug("in UserEnrollmentManager default");
		return null;
	}

	// get Adhaar information for the provided adhaarId
	@RequestMapping(value = "/getAdhaarInfo", method = RequestMethod.POST)
	public @ResponseBody
	AdhaarUserBean getAdhaarInfobyId(@RequestBody AdhaarUserBean formBean) {
		AdhaarUserBean adhaarDetail = null;
		AdhaarUserBean uiResponse = new AdhaarUserBean();

		adhaarDetail = userEnrollmentService.getAdhaarUserById(formBean
				.getAdhaarID());

		if (adhaarDetail != null) {
			uiResponse = adhaarDetail;
			uiResponse.setCustomMessage(CUSTOM_MSG);
		} else {
			uiResponse
					.setCustomMessage("User not found in Adhaar Database! Please search again");
		}
		return uiResponse;
	}

	@RequestMapping(value = "/getVoterInfoById", method = RequestMethod.POST)
	public @ResponseBody
	VotersUserBean getVoterInfoById(@RequestBody VotersUserBean voterToSearch) {
		logger.debug(">>__________________recieved query params:"
				+ voterToSearch.getVotersId());

		VotersUserBean voterDetail = null;
		VotersUserBean uiResponse = new VotersUserBean();

		if (!voterToSearch.equals("")) {
			voterDetail = userEnrollmentService.getVoterUserById(voterToSearch
					.getVotersId());
			if (voterDetail != null) {
				uiResponse = voterDetail;
				uiResponse.setCustomMessage(CUSTOM_MSG);
			} else {
				uiResponse
						.setCustomMessage("No results for the search. Please search again! ");
			}
		} else {
			uiResponse
					.setCustomMessage("Invalid Search Criteria. Please search again ! ");
		}

		return uiResponse;
	}

	/*
	 * Once the VotersInfo from /getVotersInfo and adhaarInformation from
	 * /getAdhaarInfo are verified enroll the user, enrollment tables requires
	 * only voterId and adhaarId
	 */
	@RequestMapping(value = "/enrollUser", method = RequestMethod.POST)
	public @ResponseBody
	VotersAdhaarUserBean generateElectionId(
			@RequestBody VotersAdhaarUserBean userToEnroll) {
		logger.debug(">>_________recieved params___> "
				+ userToEnroll.getVotersId() + "," + userToEnroll.getAdhaarId());

		VotersAdhaarUserBean enrolledUser = new VotersAdhaarUserBean();

		if (!(userToEnroll.getVotersId().equals(""))
				&& !(userToEnroll.getAdhaarId().equals(""))) {
			userToEnroll = userEnrollmentService
					.manageUserEnrollement(userToEnroll);
			if (userToEnroll != null) {
				enrolledUser = userToEnroll;
				enrolledUser.setCustomMessage(CUSTOM_MSG);
			} else {
				enrolledUser
						.setCustomMessage("User already enrolled in the Database");
			}
		} else {
			enrolledUser
					.setCustomMessage("Invalid VotersId or Adhaar ID submitted! Please resubmit");
		}

		return enrolledUser;
	}

	
	/* LoginWith Election Id & adhaar id*/
	@RequestMapping(value = "/pinManagerLogin", method = RequestMethod.POST)
	public @ResponseBody
	VotersAdhaarUserBean getUserEnrollmentInfo(@RequestBody VotersAdhaarUserBean usrToFind) 
	{
		logger.debug(">>________________ Recieved > " + usrToFind.geteElectionId()
				+ "," + usrToFind.getAdhaarId());

		VotersAdhaarUserBean usrDetail = new VotersAdhaarUserBean();
		
		if( !("".equals(usrToFind.geteElectionId())) && 
				!("".equals(usrToFind.getAdhaarId()))) {
			
			usrToFind = userEnrollmentService.getUserEnrollmentInfo(usrToFind);
			if(usrToFind != null){
				usrDetail = usrToFind;
				usrDetail.setCustomMessage(CUSTOM_MSG);
			}else{
				usrDetail.setCustomMessage("Login failed! User not found in the system!");
			}
		}else{
			usrDetail.setCustomMessage("Invalid Election Id or Voter ID. Please Try Again!");
		}
		return usrDetail;
	}

	
	/* change pin for Election Id */
	@RequestMapping(value = "/changePinById", method = RequestMethod.POST)
	public @ResponseBody
	VotersAdhaarUserBean changeUserPin(@RequestBody VotersAdhaarUserBean updateUser) {
		logger.debug(">>________________ Recieved >" + updateUser.geteElectionId()
				+ "," + updateUser.getVotingPIN()+ "," + updateUser.getAdhaarId());

		VotersAdhaarUserBean uiResponse = new VotersAdhaarUserBean();
		if(userEnrollmentService.updatePinForEnrolledUser(updateUser) != null){
			uiResponse.setCustomMessage(CUSTOM_MSG);
		}else{
			uiResponse.setCustomMessage("Unable to update PIN now ! Please try again later!");
		}
		
		return uiResponse;
	}

}
