
$(document).ready(function(){
	$('#home_load_candidate_btn').click(function(){
		$('#dataLoadContainer').load('./electionmgmt/candidatemanager.html');
	});
	
	$('#home_load_constituency_btn').click(function(){
		$('#dataLoadContainer').load('./electionmgmt/constituencymanager.html');
	});

	$('#home_load_elections_btn').click(function(){
		$('#dataLoadContainer').load('./electionmgmt/electionsmanager.html');
	});
	
	$('#home_load_results_btn').click(function(){
		$('#dataLoadContainer').load('./electionmgmt/resultsmanager.html');
	});

	$('#home_load_credmanager_btn').click(function(){
		$('#dataLoadContainer').load('./enroll/credentialManager.html');
	});

	$('#home_load_enrollvoter_btn').click(function(){
		$('#dataLoadContainer').load('./enroll/enrollUser.html');
	});

	
});


