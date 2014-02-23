/**
 * 
 */
package com.hashin.project.dao;

import java.util.List;

import javax.sql.DataSource;

import com.hashin.project.bean.ConstituenciesBean;
import com.hashin.project.bean.ElectionStatesBean;
import com.hashin.project.bean.ElectionsBean;
import com.hashin.project.bean.ElectionsCandidatesBean;
import com.hashin.project.bean.ElectionsConstsBean;

/**
 * @author jintu.jacob@gmail.com Oct 29, 2013 ElectionManagementDAO
 */
public interface ElectionManagementDAO {

	public void setDataSource(DataSource dataSource);

	public int addNewElection(ElectionsBean election);


	public List<ElectionsConstsBean> getElectionsListByConst(String constituency);

	public List<ElectionsCandidatesBean> getCandidatesListByUnitId(int unitElectionId);

	public int increamentVoteCountByCandidate(String candidateId);
	
	
	
	//_____________________________constituencies___________________________//
	
	public List<ConstituenciesBean> getAllConstituencies();

	public List<ElectionsBean> searchElections(String eleTitle);
	
	public ElectionsBean getElectionDetail( String electId);
	
	public ElectionsBean getElectionDetail(String  eleTitle, String eleDesc, String eleStartDt);

	public List<ElectionStatesBean> getStatesListByEleId(String electId);

	public List<ElectionStatesBean> getAllStatesForMenu();

	public int enrollVotersForElection(String electId);

	public Boolean getVoterEnrollStatusByElection(String electId);

	public int updateEnrlmntStatusForElection(String electId);

	public int deleteElectionInElections(String electId);

	public int createUnitConstituencyElections(String electId,
		List<ElectionStatesBean> stateList);

	public List<ConstituenciesBean> searchConstsByName(String constName);
	
	
}
