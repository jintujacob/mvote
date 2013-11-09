package com.hashin.project.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.hashin.project.bean.AdhaarUserBean;
import com.hashin.project.bean.UserFormBean;
import com.hashin.project.bean.VotersAdhaarUserBean;
import com.hashin.project.bean.VotersUserBean;
import com.hashin.project.dao.UserEnrollmentDAO;
import com.hashin.project.dao.VoterListManagementDAO;

public class UserEnrollmentServiceImpl implements UserEnrollmentService
{
    @Autowired
    private UserEnrollmentDAO userEnrollmentDao;
    
    @Autowired
    private VoterListManagementDAO voterListManagementDao;
    
    /*
     * On the manual verification completes this method is called to enroll the user
     * - generate voting pin
     * - input the voting pin, adhhaarId, and votersId to the enrollment tables
     */
    @Override
    public Long manageUserEnrollement(String votersId, String adhaarId)
    {
	VotersAdhaarUserBean  userToEnroll = new VotersAdhaarUserBean();
	String votingPIN = userEnrollmentDao.generateVotingPin();
	if(votingPIN !=null){
	    userToEnroll.setAdhaarId(adhaarId);
	    userToEnroll.setVotersId(votersId);
	    userToEnroll.setLockOutFlag("F");
	    userToEnroll.setVotingPIN(votingPIN);
	    //userToEnroll.setGenDate() - db to set the date

	    Long autoGenId = userEnrollmentDao.createVotersAdhaarUser(userToEnroll);
	    return autoGenId;
	}
	return null;
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
     */
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


}
