package com.hashin.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author jintu.jacob@gmail.com
 * Oct 9, 2013
 * MainController
 * Handles all default inputs ending with /
 */

@Controller
@RequestMapping("/")
public class MainController {
	
	@RequestMapping(value = "/")
	public ModelAndView helloworld(){
		String testmessage = "Hi There, welcome to Mobile Voting application";
		return new ModelAndView("index", "message", testmessage);
	}
	
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ModelAndView testMethod(){
		String testmessage = "Hi There, welcome to Mobile Voting application";
		return new ModelAndView("index", "message", testmessage);
	}
}
