package com.hashin.project.service;

import java.util.UUID;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.hashin.project.bean.AdhaarUserBean;
import com.hashin.project.bean.VotersAdhaarUserBean;
import com.hashin.project.bean.VotersUserBean;
import com.hashin.project.dao.UserEnrollmentDAO;
import com.hashin.project.dao.VoterListManagementDAO;
import com.hashin.project.util.Encryption;

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
			VotersAdhaarUserBean verifiedUser) throws Exception 
	{
		logger.info(">>______________ UserEnrollmentServiceImpl.manageUserEnrollement service methode called____________> ");
		VotersAdhaarUserBean enrolled = null;

		verifiedUser.setLockOutFlag("F");
		String generatedPin =  generatePin();
		String encryptedPin= Encryption.getInstance().encrypt(generatedPin);
		verifiedUser.setVotingPIN(encryptedPin);

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
	public VotersAdhaarUserBean getUserEnrollmentInfo(VotersAdhaarUserBean usrToFind) 
			throws Exception
	{
		VotersAdhaarUserBean userBean =  userEnrollmentDao.getEnrolledUserDetail(usrToFind);
		logger.info("___________________encrypted PIN from DB >> "+ userBean.getVotingPIN());
		if(userBean !=null){
			String encryptedPin = userBean.getVotingPIN(); //pin stored in DB
			String decryptedPin =  Encryption.getInstance().decrypt(encryptedPin); 
			userBean.setVotingPIN(decryptedPin); // storing decrypted pin 
			
			logger.info("__________________decrypted PIN set to bean >> "+userBean.getVotingPIN());
		}
		return userBean;
	}

	
	public VotersAdhaarUserBean updatePinForEnrolledUser(VotersAdhaarUserBean user) 
			throws Exception
	{
		
		logger.info("__________________PIN set by User >> "+user.getVotingPIN());
		String encryptedPin = Encryption.getInstance().encrypt(user.getVotingPIN());
		user.setVotingPIN(encryptedPin);
		logger.info("__________________encrypted PIN  >> "+user.getVotingPIN());
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
