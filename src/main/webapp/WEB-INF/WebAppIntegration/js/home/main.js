
$(document).ready(function(){
	$('#home_load_candidate_btn').click(function(){
		$('#dataLoadContainer').load('./electionmgmt/candidatemanager.html');
	});
	
	$('#home_load_constituency_btn').click(function(){
		$('#dataLoadContainer').load('./electionmgmt/constituencymanager.html');
	});

	
	
});


