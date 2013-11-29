package com.hashin.project.dao;

public class SQLConstants {

	public static String INSERT_NEW_ELECTION = "insert into elections "
			+ "(ele_title, ele_start_dt, ele_end_dt, ele_desc) "
			+ "values (?, ?, ?, ?)" ;
	
	public static String GET_ELECTION_DETAIL_BY_ID = "select * from elections where ele_id = ?";
	
	//public static String GET_ALL_ELECTIONS = "select * from elections";
	
	public static String GET_ALL_ELECTIONS_BY_CRITERIA = "select * from elections where ele_title like %?%" ;
	
	public static String DELETE_ELECTION_BY_ID = "delete from user where user_id= ?";
	
	public static String GET_ELECTIONS_BY_CONST_ID = " select ec.unit_ele_id, ec.ele_id, ec.const_id, "
			+ "e.ele_title, e.ele_start_dt, e.ele_end_dt, e.ele_desc "
			+ "from elections_consts ec left join elections e "
			+ "on ec.ele_id=e.ele_id where ec.const_id= ?" ; 
	
	public static String GET_CANDIDATES_BY_UNIT_ELE_ID = " select ec.ele_cand_id, ec.cand_id, "
			+ "c.cand_name, c.cand_logo, c.cand_bio "
			+ "from elections_candidates ec join candidates c "
			+ "on ec.cand_id=c.cand_id where ec.unit_ele_id = ? ";
	
	public static String INCREMENT_VOTECOUNT_BY_CANDIDATE_ID = "update elections_results "
			+ "set vote_count = vote_count+1 "
			+ "where ele_cand_id = ( "
			+ 		"select ele_cand_id from elections_candidates where cand_id = ? "
			+ ")";
			
	
}
