 package com.hashin.project.controller;


import java.util.List;
import org.apache.log4j.Logger; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import com.hashin.project.bean.ElectionsBean;
import com.hashin.project.service.ElectionsService;
import com.hashin.project.util.ElectionsExtractor;


/**
 * @author jintu.jacob@gmail.com
 * Oct 9, 2013
 * ElectionsController
 * Handles all default inputs ending with /
 */

@Controller
public class ElectionsController {

    @Autowired
    private ElectionsService electionsService; 
    private static final Logger logger = Logger.getLogger(ElectionsController.class);

    
    @RequestMapping(value="elections/home", method = RequestMethod.GET)
    public ModelAndView listAllElections() 
    {
	logger.debug("ElectionsController.listAllElections() -  Controller methode mapping done!");
	List<ElectionsBean> electionsList= electionsService.listAll();
	return new ModelAndView("ElectionsHome", "electionsList", electionsList);
    }   
    
    
    @RequestMapping(value="elections/getById", method = RequestMethod.GET)
    public ModelAndView getElectionById(@RequestParam int electId) 
    {
	ElectionsBean election= electionsService.getById(electId);
	return new ModelAndView("ElectionsHome", "election", election);  
    }

    
    @RequestMapping(value="elections/search", method = RequestMethod.GET)
    public ModelAndView getElectionBySearchKey(@RequestParam String searchKey) 
    {
	List<ElectionsBean> electionsList = electionsService.searchWildCard(searchKey);
	return new ModelAndView("ElectionsHome", "electionsList", electionsList);  
    }

    @RequestMapping(value="elections/getForm", method = RequestMethod.GET)
    public ModelAndView getElectionsForm(@ModelAttribute("election") ElectionsBean election) 
    {
	//manage drop down elements for jsp into map object and pass as mav 
	return new ModelAndView("ElectionsForm", "command", election);  
    }
    
    
   @RequestMapping(value="elections/create", method = RequestMethod.GET)
   public String createElectionEvent(@ModelAttribute("election") ElectionsBean election)
   {
       logger.debug("Inside > createElectionEvent");
       int numRows = 0 ;
       if(election != null)
       {
	   numRows = electionsService.create(election);
       }
       
       if(numRows <= 0)
       {
	   return "redirect:electionsError?msgError=Unable to complete request due to database error"; 
	   //manageElectionsError("Unable to complete request due to database error!");
	   //this should handle the exceptions as well
	   
	   
       }else{
	   return "redirect:/elections";   //return type should be String
       }
   }
   

   @RequestMapping(value="elections/electionsError", method = RequestMethod.GET)
   public ModelAndView manageElectionsError(@RequestParam String msgError) 
   {
	return new ModelAndView("ElectionsError", "msgError", msgError);  
   }
   
   
   
   /*
    * add method for the deleteById methode
    */
    
}
