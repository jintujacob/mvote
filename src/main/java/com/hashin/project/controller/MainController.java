package com.hashin.project.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hashin.project.bean.CandidatesBean;

/**
 * @author jintu.jacob@gmail.com
 * Oct 9, 2013
 * MainController
 * Handles all default inputs ending with /
 */

@Controller
@RequestMapping("/main")
public class MainController {
	
    private static final Logger logger = Logger.getLogger(ElectionsManager.class);

    
/*	@RequestMapping(value = "/")
	public ModelAndView helloworld(){
		String testmessage = "Hi There, welcome to Mobile Voting application";
		return new ModelAndView("index", "message", testmessage);
	}
*/	
	
   	// http://localhost:8080/mvote/main/
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody CandidatesBean get() 
	{
	     logger.debug("####################################");
	      CandidatesBean person = new CandidatesBean();
	      person.setCandId("1");
	      person.setCandName("Jintu");
	      person.setCandBio("Jintu's bio");
	      return person;
	 }
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String dopost() 
	{
	 
	    logger.debug("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	    JSONObject json = new JSONObject();
	    json.put("city", "Mumbai");
	    json.put("country", "India");
	    
	    String retStr = json.toString();
	    return retStr;
	}
	
	
	
	
}
