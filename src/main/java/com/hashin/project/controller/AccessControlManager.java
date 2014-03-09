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

import com.hashin.project.bean.ElectionsCandidatesBean;
import com.hashin.project.bean.FormListBean;
import com.hashin.project.bean.SystemUserBean;
import com.hashin.project.service.AccessControlService;



@Controller
@RequestMapping("access")
public class AccessControlManager {

	@Autowired
	private AccessControlService accessControlService;
	private static final Logger logger = Logger
			.getLogger(AccessControlManager.class);
	private static final String CUSTOM_MSG = "SUCCESS";

	@RequestMapping(value = "/validateSystemUser", method = RequestMethod.POST)
	public @ResponseBody
	SystemUserBean validateSystemUser(@RequestBody SystemUserBean toValidate) 
	{
	    logger.info("inside accessuser");
	    
	    logger.info(">>___________ /validateSystemUser - credentials : " + toValidate.toString());
	    SystemUserBean response = new SystemUserBean();
	    
	    Boolean loginStat = accessControlService.validateSystemUser(toValidate);
		
	    if(loginStat){
		response.setCustomMessage(CUSTOM_MSG);
	    }else{
		response.setCustomMessage("Unable to login. Invalid Credentials provided. ");
	    }
	
	    return response;
	}
	
	@RequestMapping(value = "/addSystemUser", method = RequestMethod.POST)
	public @ResponseBody
	SystemUserBean addSystemUser(@RequestBody SystemUserBean toadd) 
	{
	    logger.info("inside accessuser");
	    
	    logger.info(">>___________ /addSystemUser - credentials : " + toadd.toString());
	    SystemUserBean response = new SystemUserBean();
	    
	    Boolean addStat = accessControlService.addSystemUser(toadd);
		
	    if(addStat){
		response.setCustomMessage(CUSTOM_MSG);
	    }else{
		response.setCustomMessage("Unable to login. Invalid Credentials provided. ");
	    }
	
	    return response;
	}
	
	

}
