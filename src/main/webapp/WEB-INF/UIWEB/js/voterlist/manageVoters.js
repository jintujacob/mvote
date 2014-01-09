$(document).ready(function(){
	showPageHome();
	
	
	$('#goto_search_voter').click(function(){
		 $('#contentAddVoterForm').hide();
		 $('#contentSearchVoterForm').show();
	});
	 
	$('#goto_add_voter').click(function(){
		 $('#contentSearchVoterForm').hide();
		 $('#contentAddVoterForm').show();
	});
	 
	$('#btn_submit_search').click(function(){
		processVoterSearch();
		showPage('#pageVoterListView');
	});
	
	$('#btn_add_voter').click(function(){
		processVoterAddition();
		//emulate the search for recently added voterId and show in list
		showPage('#pageVoterListView');
	});
	 
	$('#pageVoterListView a').click(function(){
		getVoterDetail();
		showPage('#pageVoterDetailView');
	});
	
	 
});

function getVoterDetail(){
	return true;
}

function processVoterSearch(){
	return true;
}

function processVoterAddition(){
	return true;
}


function hideAll(){
	$('#pageVoterManagerHome').hide();
	$('#pageVoterListView').hide();
	$('#pageVoterDetailView').hide();
}

function showPage(pageId){
	hideAll();
	$(pageId).show();
}

function showPageHome(){
	hideAll();
	$('#pageVoterManagerHome').show();
	 	$('#contentAddVoterForm').hide();
	 		$('#contentSearchVoterForm').show();
}



