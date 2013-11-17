/**
 * 
 */
package com.hashin.project.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hashin.project.bean.ElectionsBean;
import com.hashin.project.bean.ElectionsCandidatesBean;
import com.hashin.project.bean.ElectionsConstsBean;
import com.hashin.project.util.ElectionsCandidatesRowMapper;
import com.hashin.project.util.ElectionsConstsMapper;
import com.hashin.project.util.ElectionsRowMapper;

/**
 * @author jintu.jacob@gmail.com
 * Oct 29, 2013
 * ElectionManagementDAOImpl
 */
public class ElectionManagementDAOImpl implements ElectionManagementDAO
{
    private static final Logger logger = Logger.getLogger(ElectionManagementDAOImpl.class);
    private JdbcTemplate jdbcTemplate;
    
    
    @Resource(name="dataSource")
    public void setDataSource(DataSource dataSource)
    {
	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
        
    public int addNewElection(ElectionsBean election)
    {
	String query = "insert into elections (ele_title, ele_start_dt, ele_end_dt, ele_desc) values (?, ?, ?, ?)";
	Object[] parameters = new Object[] 
	{ 	election.getElectTitle(),
		election.getElectStartDate(),
	    	election.getElectEndDate(),
	    	election.getElectDescription() 
	};
	int numRows = jdbcTemplate.update( query, parameters);
	
	logger.debug( "Query =>  insert into elections " +
			"(ele_title, ele_start_dt, ele_end_dt, ele_desc) " +
			"values (?, ?, ?, ?)");
	logger.debug( "Result => rowCount= "+numRows);
	
	return numRows;
    }
	
	

    public List<ElectionsBean> getAllElections()
    {
	String query = "select * from elections";
	List<ElectionsBean> electionsList = jdbcTemplate.query(query, new ElectionsRowMapper());
	return electionsList;
    }
	
	
    public ElectionsBean getElectionById(int electId)
    {
	String query = "select * from elections where ele_id = ?";
	Object[] parameters = new Object[] {electId};
	List<ElectionsBean> electionsList = jdbcTemplate.query(query, parameters, new ElectionsRowMapper());
	return electionsList.get(0);
    }
	
	
    public List<ElectionsBean> searchElectionsWildCard(String searckKey)
    {
	searckKey = "%"+searckKey+"%";
	String query = "select * from elections where ele_title like ?";
	Object[] parameters = new Object[] {searckKey};
	List<ElectionsBean> electionList = jdbcTemplate.query(query, parameters, new ElectionsRowMapper());
	return electionList;
    }
    
    
    public int removeElectionById(int electId)
    {
	String query = "delete from user where user_id= ?" ; 
	Object[] parameters = new Object[] {electId};
	int numRows = jdbcTemplate.update(query);
	return numRows;
    }


    @Override
    public List<ElectionsConstsBean> getElectionsListByConst(String constId)
    {
	/*
	 select ec.unit_ele_id, ec.ele_id, ec.const_id, 
	 	e.ele_title, e.ele_start_dt, e.ele_end_dt, e.ele_desc 
	 from elections_consts ec  
	 left join elections e  
	 on ec.ele_id=e.ele_id  
	 where ec.const_id=4 
	 */
	String query = " select ec.unit_ele_id, ec.ele_id, ec.const_id, " +
				" e.ele_title, e.ele_start_dt, e.ele_end_dt, e.ele_desc " +
			" from elections_consts ec " +
			" left join elections e " +
			" on ec.ele_id=e.ele_id " +
			" where ec.const_id= ?" ;

	Object[] parameters = new Object[] {constId};
	List<ElectionsConstsBean> electionList = jdbcTemplate.query(query, parameters, new ElectionsConstsMapper());
	return electionList;
    }


    @Override
    public List<ElectionsCandidatesBean> getCandidatesListByUnitId(int unitElectionId)
    {
	List<ElectionsCandidatesBean> candidateList = null;
	String query = " select ec.ele_cand_id, ec.cand_id, " +
			       " c.cand_name, c.cand_logo, c.cand_bio " +
			" from elections_candidates ec " +
			" join candidates c " +
			" on ec.cand_id=c.cand_id " +
			" where ec.unit_ele_id = ? ";

	Object[] parameters = new Object[] {unitElectionId};
	candidateList = jdbcTemplate.query(query, parameters, new ElectionsCandidatesRowMapper());
	return candidateList;
    }


    @Override
    public int increamentVoteCountByCandidate(String candidateId)
    {
	int numRows = 0;
	String query = "update elections_results " +
			"set vote_count = vote_count+1 " +
			"where ele_cand_id = ( " +
				"select ele_cand_id " +
				"from elections_candidates " +
				"where cand_id = ? )";
	Object[] parameters = new Object[] {candidateId };
	numRows = jdbcTemplate.update( query, parameters);

	//non zero return ==> update is successfully executed.
	//exception/zero on return ==> update failed 
	return numRows;
    }


}
