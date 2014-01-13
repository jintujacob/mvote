package com.hashin.project.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.hashin.project.bean.AdhaarUserBean;
import com.hashin.project.bean.UserFormBean;
import com.hashin.project.bean.VotersAdhaarUserBean;
import com.hashin.project.bean.VotersUserBean;
import com.hashin.project.dao.UserEnrollmentDAO;
import com.hashin.project.dao.VoterListManagementDAO;
import com.hashin.project.dao.VoterListManagementDAOImpl;

public class UserEnrollmentServiceImpl implements UserEnrollmentService
{
    @Autowired
    private UserEnrollmentDAO userEnrollmentDao;
    
    @Autowired
    private VoterListManagementDAO voterListManagementDao;
    
    private static final Logger logger = Logger.getLogger(UserEnrollmentServiceImpl.class);
    
    /*
     * On the manual verification completes this method is called to enroll the user
     * - generate voting pin
     * - input the voting pin, adhhaarId, and votersId to the enrollment tables
     */
    @Override
    public VotersAdhaarUserBean manageUserEnrollement(VotersAdhaarUserBean verifiedUser)
    {
	logger.debug(">>______________ UserEnrollmentServiceImpl.manageUserEnrollement service methode called____________> " );
	VotersAdhaarUserBean enrolled = null;
	
	verifiedUser.setLockOutFlag("F");
	verifiedUser.setVotingPIN("0000");
	
	Long eElectionId = userEnrollmentDao.createVotersAdhaarUser(verifiedUser);
	if(eElectionId != null){
	    enrolled = verifiedUser;
	    enrolled.seteElectionId(eElectionId.toString());
	}
	return enrolled;
    }

    
    @Override
    public AdhaarUserBean getAdhaarUserById(String adhaarID)
    {
	return userEnrollmentDao.getAdhaarUserById(adhaarID);
    }


    /**
     * fetch from the VoterListManagementDAO
     */
    @Override
    public VotersUserBean getVoterUserById(String votersId)
    {
	return voterListManagementDao.getVoterUserById(votersId);
    }

    
    /*
     * Out of scope methode - used for auto verification of the user(non-Javadoc)
     * @see com.hashin.project.service.UserEnrollmentService#verifyUser(com.hashin.project.bean.UserFormBean)
     
    @Override
    public VotersAdhaarUserBean verifyUser(UserFormBean user)
    {
	VotersAdhaarUserBean verifiedUsr = new VotersAdhaarUserBean();
	AdhaarUserBean adhaarUsr = getAdhaarUserById(user.getAdhaarID());
	VotersUserBean voterUsr = voterListManagementDao.getVoterUserById(user.getVotersID());
	
	if( adhaarUsr.getNameFirst().equals(voterUsr.getNameFirst()) &&
		adhaarUsr.getNameLast().equals(voterUsr.getNameLast()) &&
		adhaarUsr.getAddress().equals(voterUsr.getPlace())   )
	{
	    verifiedUsr.setAdhaarId(adhaarUsr.getAdhaarID());
	    verifiedUsr.setVotersId(voterUsr.getVotersId());
	    verifiedUsr.setLockOutFlag("F");
		
	    Date date = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	    verifiedUsr.setGenDate(sdf.format(date));
	}
	return verifiedUsr;
    }
    */


}
