package com.hashin.project.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hashin.project.bean.AdhaarUserBean;
import com.hashin.project.bean.ElectionsBean;
import com.hashin.project.dao.AdhaarDAO;
import com.hashin.project.service.AdhaarService;
import com.hashin.project.service.ElectionsService;

/**
 * @author jintu.jacob@gmail.com
 * Oct 9, 2013
 * AdhaarController
 * Handles all default inputs ending with adhaar
 */

@Controller
@RequestMapping("adhaar")
public class AdhaarController {
	
    @Autowired
    private AdhaarService adhaarService; 
    
    private static final Logger logger = Logger.getLogger(AdhaarController.class);
    
    
    /*
     * http://localhost:8080/mvote/adhaar/getAdhaarUser?adhaarID=001002003004
     */
    @RequestMapping(value="/getAdhaarUser", method = RequestMethod.GET)
    public String getAdhaarUserById(@RequestParam String adhaarID) 
    {
	AdhaarUserBean adhaarUser = adhaarService.getUserById(adhaarID);
	if(adhaarUser != null){
	    logger.debug("AdhaarController output => "+ adhaarUser.getNameFirst()+
			" " + adhaarUser.getNameLast() +
			" " + adhaarUser.getAddress() +
			" " + adhaarUser.getPhone()	);
	    
	    //map to a properview

	}
	else{
	    populateAdhaarError("AdhaarId not found in the Adhaar Database");
	}
	return adhaarUser.getAdhaarID();
    }
    
    
    @RequestMapping(value="/adhaarError")
    public ModelAndView populateAdhaarError(String msgError){
	//have to defne view
	return new ModelAndView("AdhaarError", "msgError", msgError);
    }
    
}
