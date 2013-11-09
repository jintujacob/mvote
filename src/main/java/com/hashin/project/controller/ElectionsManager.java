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
import com.hashin.project.service.ElectionManagementService;


/**
 * @author jintu.jacob@gmail.com
 * Oct 9, 2013
 * ElectionsManager
 * Handles all default inputs ending with /elections
 */

@Controller
@RequestMapping("elections")
public class ElectionsManager {

    @Autowired
    private ElectionManagementService electionMgmtService; 
    private static final Logger logger = Logger.getLogger(ElectionsManager.class);

    
    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView listAllElections() 
    {
	logger.debug("ElectionsManager.listAllElections() -  Controller methode mapping done!");
	List<ElectionsBean> electionsList= electionMgmtService.getAllElections();
	return new ModelAndView("ElectionsHome", "electionsList", electionsList);
    }   
    
    
    /*
     * http://localhost:8080/mvote/elections/getById?electId=11
     */
    @RequestMapping(value="/getById", method = RequestMethod.GET)
    public ModelAndView getElectionById(@RequestParam int electId) 
    {
	ElectionsBean election= electionMgmtService.getElectionById(electId);
	if(election != null){
	    return new ModelAndView("ElectionsHome", "election", election);
	}
	else{
	    return manageElectionsError("User not found");
	}
	  
    }

    
    // http://localhost:8080/mvote/elections/search?searchKey=x
    @RequestMapping(value="/search", method = RequestMethod.GET)
    public ModelAndView getElectionBySearchKey(@RequestParam String searchKey) 
    {
	List<ElectionsBean> electionsList = electionMgmtService.searchElectionsWildCard(searchKey);
	return new ModelAndView("ElectionsHome", "electionsList", electionsList);  
    }

    
    // http://localhost:8080/mvote/elections/getForm
    @RequestMapping(value="/getForm", method = RequestMethod.GET)
    public ModelAndView getElectionsForm(@ModelAttribute("election") ElectionsBean election) 
    {
	return new ModelAndView("ElectionsForm", "command", election);  
    }
    
    
   @RequestMapping(value="/create", method = RequestMethod.GET)
   public ModelAndView createElectionEvent(@ModelAttribute("election") ElectionsBean election)
   {
       logger.debug("Inside > createElectionEvent");
       int numRows = 0 ;
       if(election != null)
       {
	   numRows = electionMgmtService.addNewElection(election);
       }
       
       if(numRows <= 0)
       {
	   return  manageElectionsError("Unable to complete request due to backend error");
       }
       else
       {
	   return listAllElections();
       }
   }
   

   @RequestMapping(value="/electionsError")
   public ModelAndView manageElectionsError(@RequestParam String msgError) 
   {
	return new ModelAndView("ElectionsError", "msgError", msgError);  
   }
   
   
   
   /*
    * add method for the deleteById methode
    */
    
}
