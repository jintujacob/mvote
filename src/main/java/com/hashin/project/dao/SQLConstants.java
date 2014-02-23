package com.hashin.project.dao;

public class SQLConstants {

	public static String INSERT_NEW_ELECTION = "insert into elections "
			+ "(ele_title, ele_start_dt, ele_end_dt, ele_desc, vtr_enrl_stat, delete_stat) "
			+ "values (?, ?, ?, ?, ?, ?)" ;
	

/*	public static String BATCH_CREATE_UNIT_ELECTIONS_BY_EID_STATES = "INSERT INTO elections_consts "
		+ "(ele_id, const_id) "
		+ "SELECT ? , const_id FROM "
		+ "(select CON.const_id from constituencies CON, elections_states STA "
		+ "where CON.const_state = STA.st_id and STA.st_id in(?)) as test";
*/	
	
	public static String GET_ELECTION_DETAIL_BY_ID = " select e.ele_id, e.ele_title, e.ele_start_dt, "
		+ "e.ele_end_dt, e.ele_desc, e.vtr_enrl_stat "
		+ "from elections e "
		+ "where e.ele_id= ? and e.delete_stat <> 'Y' " ;
	
	public static String GET_LAST_ELECTION_BASIC_ADDED = " select e.ele_id, e.ele_title, e.ele_start_dt, "
		+ "e.ele_end_dt, e.ele_desc, e.vtr_enrl_stat "
		+ "from elections e "
		+ "where e.ele_title= ? and e.ele_desc = ? and e.ele_start_dt = ? "
		+ "and e.delete_stat <> 'Y' " ;
		 
	
	public static String GET_ELECTIONS_BY_CONST_ID = " select ec.unit_ele_id, ec.ele_id, ec.const_id, "
		+ "e.ele_title, e.ele_start_dt, e.ele_end_dt, e.ele_desc "
		+ "from elections_consts ec left join elections e "
		+ "on ec.ele_id=e.ele_id where ec.const_id= ?"
		+ "and e.delete_stat <> 'Y' " ; 

        public static String GET_CANDIDATES_BY_UNIT_ELE_ID = " select ec.ele_cand_id, ec.cand_id, "
        		+ "c.cand_name, c.cand_logo, c.cand_bio "
        		+ "from elections_candidates ec join candidates c "
        		+ "on ec.cand_id=c.cand_id where ec.unit_ele_id = ? ";
        
        public static String INCREMENT_VOTECOUNT_BY_CANDIDATE_ID = "update elections_results "
        		+ "set vote_count = vote_count+1 "
        		+ "where ele_cand_id = ( "
        		+ 		"select ele_cand_id from elections_candidates where cand_id = ? "
        		+ ")";
        		
        public static String GET_CANDIDATE_DETAIL_BY_ID = "select a.cand_id, a.cand_name, a.cand_logo, a.cand_bio, " 
        		+ "d.const_name, d.const_state, " 
        		+ "e.ele_id, c.unit_ele_id, e.ele_title, e.ele_desc " 
        		+ "from candidates a , elections_candidates b, elections_consts c, constituencies d, elections e " 
        		+ "where a.cand_id = b.cand_id and b.unit_ele_id = c.unit_ele_id " 
        		+ "and c.const_id = d.const_id and e.ele_id = c.ele_id " 
        		+ "a.cand_id = ?";

	public static String GET_CANDIDATES_BY_NAME = "select c.cand_id, c.cand_name, c.cand_logo, c.cand_bio " 
			+ "from candidates c join elections_candidates e " 
			+ "on c.cand_id = e.cand_id where c.cand_name like '%?%';"; 
	
	public static String GET_CANDIDATES_BY_CONSTITUENCY_ID = "select a.*, c.unit_ele_id " 
			+ "from candidates a, elections_candidates b, elections_consts c " 
			+ "where a.cand_id = b.cand_id and b.unit_ele_id = c.unit_ele_id " 
			+ "and c.const_id = ?" ;
	
	//candidate register
	public static String INSERT_CANDIDATE_IN_CANDIDATES = "insert into candidates (cand_name, cand_logo, cand_bio) " 
			+ "values ( '?', '?', '?' )";
	
	//candidates enrolled
	public static String INSERT_CANDIDATE_IN_ELECTION_CANDIDATES = "insert into elections_candidates "
			+ "(unit_ele_id, cand_id) values (?, ?) " ;
	
	public static String INSERT_CANDIDATE_IN_ELECTION_RESULTS = " insert into elections_results " 
			+ "(unit_ele_id, ele_cand_id, vote_count) values (?, ?, ?) " ;
	
	public static String INSERT_CONST_IN_CONSTITUENCIES = " insert into constituencies (const_name, const_state) " 
			+ "values('?', '?') "; 
	
	public static String INSERT_CONST_IN_ELECTION_CONSTITUENCIES = " insert into elections_consts " 
			+ "(ele_id, const_id) values ( ?, ? ) " ;
	
	
/*	public static String SEARCH_ELECTIONS_BY_ALL_CRITERIA =  "select ELE.*, ELECON.unit_ele_id, CON.const_id "
			+ "from elections ELE, elections_consts ELECON, constituencies CON "
			+ "where ELE.ele_id = ELECON.ele_id and ELECON.const_id = CON.const_id "
			+ "and ELE.ele_title like ? "
			+ "and CON.const_id like ? "
			+ "and CON.const_state like ? "
			+ "and ELE.delete_stat <> 'Y' "
			+ "group by ELE.ele_id";
*/
	
	public static String SEARCH_ELECTIONS_BY_ALL_CRITERIA =  "select * from elections ELE "
		+ "where ELE.ele_title like ? "
		+ "and ELE.delete_stat <> 'Y' "; 

	
	public static String GET_STATES_BY_ELECTION_ID = "select EST.* "
		+ "from elections ELE, elections_consts ECO, constituencies CON, elections_states EST "
		+ "where ELE.ele_id = ECO.ele_id and ECO.const_id = CON.const_id and CON.const_state = EST.st_id "
		+ "and ELE.ele_id = ? and "
		+ "ELE.delete_stat <> 'Y' "
		+ "group by EST.st_id";
	
	
	public static String GET_ALL_STATES = "select * from elections_states";
	
	public static String GET_ELECTIONS_VOTER_ENRLMNT_STATUS = "select count(*) from elections where vtr_enrl_stat='Y' "
		+ "and ele_id = ? ";
	
	public static String UPDATE_VOTER_ENRLMNT_STAT_FOR_ELE = "update elections set vtr_enrl_stat = ? where ele_id = ? " ;
	
	public static String BATCH_RUN_INSERT_VOTERS_TO_VOTINGSTAT = "INSERT INTO elections_votingstats "
		+ "(e_election_id, ele_id, voting_stat) "
		+ "SELECT e_election_id, ? , 'N' FROM voters_adhaar";
	
	
	
	public static String DELETE_ELE_IN_ELECTIONS = "update elections set delete_stat = ? where ele_id = ? " ;
	
	
	/**
	 * ------------------------------------------------------------------------------------------------------------
	 * Queries for the Constituency table;
	 */
	
	public static String GET_ALL_CONSTS = "select CON.const_id, CON.const_name, ST.st_name "
		+ "from constituencies CON, elections_states ST "
		+ "where CON.const_state = ST.st_id " ;
	
	public static String GET_CONSTS_BY_NAME = "select CON.const_id, CON.const_name, ST.st_name "
		+ "from constituencies CON, elections_states ST "
		+ "where CON.const_state = ST.st_id and CON.const_name like ? " ;
	
	
	
	
	
	/**
	 * ------------------------------------------------------------------------------------------------------------
	 * Queries for the OnlineVoting module;
	 */
	
	public static String GET_VOTINGSTAT_BY_VOTINGPIN = " select count(*) from elections_votingstats " 
			+ " where e_election_id = ? and ele_id = ? and voting_stat = 'Y' ";
	
	public static String UPDATE_VOTINGSTAT_BY_VOTINGPIN = "update elections_votingstats " 
			+ "set voting_stat = 'Y' " 
			+ "where e_election_id = ? and ele_id = ? " ;
	
	
	
		
	
	/**
	 * ------------------------------------------------------------------------------------------------------------
	 * Queries for the UserEnrollment module;
	 */
	
	public static String GET_ADHAAR_USER_BY_ADHAAR_ID = " select * from adhaarDB where adhaar_id= ? " ;
	
	public static String INSERT_ENRLD_USER_IN_VOTERSADHAAR = "insert into voters_adhaar " 
			+ " (e_election_id, fk_voters_id, fk_adhaar_id, voting_pin, lockout_flag, gen_date) " 
			+ " values (NULL, ?, ?, ?, ?, CURDATE()) ";
	
	public static String GET_ENRLMENT_STAT_BY_ALL_CRITERIA = "select count(*) from voters_adhaar " 
			+ "where e_election_id = ?  and voting_pin = ? and lockout_flag <> 'T' ";



	
	public static String UPDATE_PIN_FOR_ENRLD_USR_VTRS_ADHR = "update voters_adhaar set voting_pin= ? "
			+ " where e_election_id = ? and fk_adhaar_id = ? ";
	
	
	public static String GET_USR_ENRLMNT_DETAIL = "select * from voters_adhaar "
			+ "where e_election_id = ? and fk_adhaar_id = ? ";
	
	
	public static String GET_VOTERS_ID_BY_eELECTIONID =  "select * from voters where "
			+ "voters_id = (select fk_voters_id from voters_adhaar where e_election_id = ? ) ";
		
	/**
	 * ------------------------------------------------------------------------------------------------------------
	 * Queries for the VoterlistManagement module;
	 */
	public static String GET_VOTER_INFO_BY_VOTERID = "select * from voters where voters_id = ? " ;
	

	public static String SEARCH_VOTERS_BY_MULTIPLE_PARAMS = "select " 
        		+ "v.voters_id,va.fk_adhaar_id, va.voting_pin, va.gen_date, va.lockout_flag, " 
        		+ "v.name,v.place, c.const_name, c.const_state " 
        		+ "from voters_adhaar va, voters v, constituencies c " 
        		+ "where va.fk_voters_id = v.voters_id and v.const=c.const_id " 
        		+ "and v.voters_id like ? and v.const like ? and v.name like ? and va.lockout_flag like ? ";

	public static String GET_VOTER_DETAIL_BY_ID = "select " 
        		+ "v.voters_id,va.fk_adhaar_id, va.voting_pin, va.gen_date, va.lockout_flag, " 
        		+ "v.name,v.place, c.const_name, c.const_state " 
        		+ "from voters_adhaar va, voters v, constituencies c " 
        		+ "where va.fk_voters_id = v.voters_id and v.const=c.const_id " 
        		+ "and v.voters_id = ? ";
	
	public static String INSERT_NEW_VOTER_IN_VOTERS = "insert into voters " 
			+ "( voters_id, name, const, place) " 
			+ "values ('?', '?', ?, '?')" ;

	public static String UPDATE_VOTER_STATUS_IN_ENROLLMENT_TBL = "update  voters_adhaar set lockout_flag= ? "
			+ "where fk_voters_id = ? " ;


}
