
$(document).ready(function(){
	$('#home_load_candidate_btn').click(function(){
		$('#dataLoadContainer').load('./ui/candidatemanager.html');
	});
	
	$('#home_load_constituency_btn').click(function(){
		$('#dataLoadContainer').load('./ui/constituencymanager.html');
	});

	$('#home_load_elections_btn').click(function(){
		$('#dataLoadContainer').load('./ui/electionsmanager.html');
	});
	
	$('#home_load_results_btn').click(function(){
		$('#dataLoadContainer').load('./ui/resultsmanager.html');
	});

	$('#home_load_credmanager_btn').click(function(){
		$('#dataLoadContainer').load('./ui/credentialManager.html');
	});

	$('#home_load_enrollvoter_btn').click(function(){
		$('#dataLoadContainer').load('./ui/enrollUser.html');
	});
	
	$('#home_load_votermgmt_btn').click(function(){
		$('#dataLoadContainer').load('./ui/manageVoters.html');
	});

	
});


