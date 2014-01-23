$(document).ready(function(){
	showPageHome();
	
	$('#goto_search_ele').click(function(){
		$('#contentAddElectionForm').hide();
 		$('#contentSearchElectionForm').show();
	});
	
	$('#goto_add_ele').click(function(){
		$('#contentAddElectionForm').show();
 		$('#contentSearchElectionForm').hide();
	});
	
	$('#btn_search_ele').click(function(){
		processElectionsSearch();
		showPage('#pageElectionSearchSummary');
	});
	
	$('#btn_add_ele').click(function(){
		processElectionsBasicForm();
		showPage('#pageAddElectionStatesForm');
	});
	
	$('#pageElectionSearchSummary a').click(function(){
		getElectionDetail();
		showPage('#pageElectionDetail');
	});
	
	$('#btn_add_states').click(function(){
		//emulate searching  == same as $('#btn_search_ele').click()
		processElectionsSearch();
		showPage('#pageElectionSearchSummary');
	});
	
	 
});

function getElectionDetail(){
	//get ele by id
	return true;
}


function processElectionsBasicForm(){
	//get inputs , write to db, store info in js var
	return true;
}

function processElectionsSearch(){
	//search and get list of matching elections
	return true;
}

function hideAll(){
	$('#pageElectionsHome').hide();
	$('#pageAddElectionStatesForm').hide();
	$('#pageElectionSearchSummary').hide();
	$('#pageElectionDetail').hide();
}

function showPage(pageId){
	hideAll();
	$(pageId).show();
}

function showPageHome(){
	hideAll();
	$('#pageElectionsHome').show();
	 	$('#contentAddElectionForm').hide();
	 		$('#contentSearchElectionForm').show();
}



