package com.hashin.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hashin.project.bean.ConstituenciesBean;
import com.hashin.project.bean.ElectionsBean;
import com.hashin.project.bean.ElectionsCandidatesBean;
import com.hashin.project.dao.ElectionManagementDAO;


public class ElectionManagementServiceImpl implements ElectionManagementService {
	@Autowired
	private ElectionManagementDAO electionsMgmtDao;

/*	public int addNewElection(ElectionsBean election) {
		return electionsMgmtDao.addNewElection(election);
		// todo check if the the return value ==0 or <0; then then throw some
		// change to boolean return
	}

	public ElectionsBean getElectionById(int electId) {
		ElectionsBean election = electionsMgmtDao.getElectionById(electId);
		return election;
	}

	public List<ElectionsBean> getAllElections() {
		List<ElectionsBean> electionList = electionsMgmtDao.getAllElections();
		return electionList;
	}

	public List<ElectionsBean> searchElectionsWildCard(String electTitle) {
		List<ElectionsBean> electionList = electionsMgmtDao
				.searchElectionsWildCard(electTitle);
		return electionList;
	}

	public int removeElectionById(int electId) {
		return electionsMgmtDao.removeElectionById(electId);
	}

	@Override
	public Boolean enrollNewCandidate(ElectionsCandidatesBean candidate) {
		
		return null;
	}

	@Override
	public ElectionsCandidatesBean getCandidateInfoById(String candidateId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ElectionsCandidatesBean> getCandidatesByName(String candName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ElectionsCandidatesBean> getCandidatesByConstituency(
			String constId) {
		// TODO Auto-generated method stub
		return null;
	}
*/
	@Override
	public List<ConstituenciesBean> getAllConsts() {
		return electionsMgmtDao.getAllConstituencies();
	}
}
