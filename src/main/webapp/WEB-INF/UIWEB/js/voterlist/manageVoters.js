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
	votersId = "v444";
	
	obj = { "votersId":votersId	}
	jsonString =JSON.stringify(obj);
	
	$.ajax({
	    type: "POST",
	    url: 'http://localhost:8080/mvote/voterslist/getVoterInfoById',
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    data: jsonString,
	    success: function(response) {
	    	populateVoterDetail(response);
	    }
	});		
}

function populateVoterDetail(voterDetail){
	alert(JSON.stringify(voterDetail));
}


function processVoterSearch(){
	votersId = "";
	lockOutFlag = "F";
	name = ""; 
	constituency = "2";
	
	obj = { "votersId":votersId,
			"lockOutFlag":lockOutFlag,
			"name":name,
			"constituency":constituency 
		}
	
	jsonString =JSON.stringify(obj);
	
	$.ajax({
	    type: "POST",
	    url: 'http://localhost:8080/mvote/voterslist/searchVoter',
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    data: jsonString,
	    success: function(response) {
	    	populateVoterList(response);
	    }
	});		

}

function populateVoterList(voterList){
	alert(JSON.stringify(voterList));
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



