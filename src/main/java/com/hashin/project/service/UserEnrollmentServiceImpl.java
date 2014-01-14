package com.hashin.project.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.hashin.project.bean.AdhaarUserBean;
import com.hashin.project.bean.UserFormBean;
import com.hashin.project.bean.VotersAdhaarUserBean;
import com.hashin.project.bean.VotersUserBean;
import com.hashin.project.dao.UserEnrollmentDAO;
import com.hashin.project.dao.VoterListManagementDAO;
import com.hashin.project.dao.VoterListManagementDAOImpl;

public class UserEnrollmentServiceImpl implements UserEnrollmentService {
	@Autowired
	private UserEnrollmentDAO userEnrollmentDao;

	@Autowired
	private VoterListManagementDAO voterListManagementDao;

	private static final Logger logger = Logger
			.getLogger(UserEnrollmentServiceImpl.class);

	/*
	 * On the manual verification completes this method is called to enroll the
	 * user - generate voting pin - input the voting pin, adhhaarId, and
	 * votersId to the enrollment tables
	 */
	@Override
	public VotersAdhaarUserBean manageUserEnrollement(
			VotersAdhaarUserBean verifiedUser) {
		logger.debug(">>______________ UserEnrollmentServiceImpl.manageUserEnrollement service methode called____________> ");
		VotersAdhaarUserBean enrolled = null;

		verifiedUser.setLockOutFlag("F");
		verifiedUser.setVotingPIN(generatePin());

		Long eElectionId = userEnrollmentDao
				.createVotersAdhaarUser(verifiedUser);
		if (eElectionId != null) {
			enrolled = verifiedUser;
			enrolled.seteElectionId(eElectionId.toString());
		}
		return enrolled;
	}

	@Override
	public AdhaarUserBean getAdhaarUserById(String adhaarID) {
		return userEnrollmentDao.getAdhaarUserById(adhaarID);
	}

	/**
	 * fetch from the VoterListManagementDAO/this is a preenrollment fetch/dont join 
	 */
	@Override
	public VotersUserBean getVoterUserById(String votersId) {
		return voterListManagementDao.getVoterUserById(votersId);
	}

	@Override
	public VotersAdhaarUserBean getUserEnrollmentInfo(VotersAdhaarUserBean usrToFind) {
		return userEnrollmentDao.getEnrolledUserDetail(usrToFind);
	}

	
	public VotersAdhaarUserBean updatePinForEnrolledUser(VotersAdhaarUserBean user)
	{
		if(userEnrollmentDao.udpateVotersAdhaarUserByPIN(user) == 0){
			return null;
		}
		return user;
	}
	
	private String generatePin()
	{
		String pin = UUID.randomUUID().toString();
		pin = pin.substring(0, 6).toUpperCase();
		return pin;
	}

}
