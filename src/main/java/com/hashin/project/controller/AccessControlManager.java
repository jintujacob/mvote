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
import com.hashin.project.service.AccessControlService;;



@Controller
@RequestMapping("access")
public class AccessControlManager {

	@Autowired
	private AccessControlService accessControlService;
	private static final Logger logger = Logger
			.getLogger(AccessControlManager.class);
	private static final String CUSTOM_MSG = "SUCCESS";

	
/*	@RequestMapping(value = "/insertNewVoter", method = RequestMethod.GET)
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
*/
}
