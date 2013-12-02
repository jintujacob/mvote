package com.hashin.project.dao;

public class SQLConstants {

	public static String INSERT_NEW_ELECTION = "insert into elections "
			+ "(ele_title, ele_start_dt, ele_end_dt, ele_desc) "
			+ "values (?, ?, ?, ?)" ;
	
	public static String GET_ELECTION_DETAIL_BY_ID = "select * from elections where ele_id = ?";
	
	public static String GET_ALL_ELECTIONS_BY_CRITERIA = "select * from elections where ele_title like %?%" ;
	
	public static String DELETE_ELECTION_BY_ID = "delete from user where user_id= ?";
	
	public static String GET_ELECTIONS_BY_CONST_ID = " select ec.unit_ele_id, ec.ele_id, ec.const_id, "
			+ "e.ele_title, e.ele_start_dt, e.ele_end_dt, e.ele_desc "
			+ "from elections_consts ec left join elections e "
			+ "on ec.ele_id=e.ele_id where ec.const_id= ?" ; 
	
	public static String INCREMENT_VOTECOUNT_BY_CANDIDATE_ID = "update elections_results "
			+ "set vote_count = vote_count+1 "
			+ "where cand_id = ? " ;
			
/*	public static String GET_CANDIDATE_DETAIL_BY_ID = "select a.cand_id, a.cand_name, a.cand_logo, a.cand_bio, " 
			+ "d.const_name, d.const_state, " 
			+ "e.ele_id, c.unit_ele_id, e.ele_title, e.ele_desc " 
			+ "from candidates a , elections_candidates b, elections_consts c, constituencies d, elections e " 
			+ "where a.cand_id = b.cand_id and b.unit_ele_id = c.unit_ele_id " 
			+ "and c.const_id = d.const_id and e.ele_id = c.ele_id " 
			+ "a.cand_id = ?";
	
*/
	public static String GET_CANDIDATE_DEATAIL_BY_ID = "select a.cand_id, a.cand_name, a.cand_logo, a.cand_bio, " 
			+ "c.const_name, c.const_state, "
			+ "d.ele_id, b.unit_ele_id, d.ele_title, d.ele_desc "
			+ "from candidates a, elections_consts b, constituencies c, elections d " 
			+ "where a.cand_id = b.cand_id and b.const_id = c.const_id " 
			+ "and b.ele_id = d.ele_id and a.cand_id = ? ";
	
	public static String GET_CANDIDATES_BY_NAME = "select c.cand_id, c.cand_name, c.cand_logo, c.cand_bio " 
			+ "from candidates c join elections_consts e " 
			+ "on c.cand_id = e.cand_id where c.cand_name like '%?%';"; 
	
	public static String GET_CANDIDATES_BY_CONSTITUENCY_ID = "select c.cand_id, c.cand_name, c.cand_logo, c.cand_bio " 
			+ "from candidates c join elections_consts e " 
			+ "on c.cand_id = e.cand_id where e.const_id = ? "; 

	public static String GET_CANDIDATES_BY_UNIT_ELE_ID = "select b.cand_id, b.cand_name, b.cand_logo, b.cand_bio " 
			+ "from elections_consts a join candidates b " 
			+ "on a.cand_id = b.cand_id " 
			+ "where a.unit_ele_id = ? " ;

	
	
	
	
}
