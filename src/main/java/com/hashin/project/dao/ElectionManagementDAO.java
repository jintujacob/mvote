/**
 * 
 */
package com.hashin.project.dao;

import java.util.List;

import javax.sql.DataSource;

import com.hashin.project.bean.ConstituenciesBean;
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
	
	
	
	//public Boolean enrollNewCandidate(ElectionsCandidatesBean candidate);

	public ElectionsCandidatesBean getCandidateInfoById(String candidateId);
	
	public List<ElectionsCandidatesBean> getCandidatesByName(String candName);
	
	public List<ElectionsCandidatesBean> getCandidatesByConstituency(String constId);

	
	
	//_____________________________constituencies___________________________//
	
	public List<ConstituenciesBean> getAllConstituencies();

	public List<ElectionsConstsBean> searchElections(ElectionsConstsBean toSearch);
	
}
