
host  = "http://localhost:8080/mvote"
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
		getElectionDetailBasic();
		getElectionDetailStates();
		showPage('#pageElectionDetail');
	});
	
	$('#btn_add_states').click(function(){
		//emulate searching  == same as $('#btn_search_ele').click()
		processElectionsSearch();
		showPage('#pageElectionSearchSummary');
	});
	
	 
});

function getElectionDetailBasic()
{
	obj = {	"electId":"1" };
	jsonString =JSON.stringify(obj);
	
	$.ajax({
	    type: "POST",
	    url: host + '/elections/getElectionDetail',
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    data: jsonString,
	    success: function(response) {
	    	populateElectionList(response);
	    },
		error: function(response){
			alert("unable to connect");
		}
	});	
}

function getElectionDetailStates()
{
	obj = {	"electId":"4" };
	jsonString =JSON.stringify(obj);
	
	$.ajax({
	    type: "POST",
	    url: host + '/elections/getStatesListByElection',
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    data: jsonString,
	    success: function(response) {
	    	//populateElectionList(response);
	    },
		error: function(response){
			alert("unable to connect");
		}
	});	
}



function processElectionsBasicForm(){
	//get inputs , write to db, store info in js var
	return true;
}

function processElectionsSearch(){
	obj = {
			"electTitle":"Lo", 
			"constId" : "",
			"stateId" : ""
		  };
	jsonString =JSON.stringify(obj);
	
	$.ajax({
	    type: "POST",
	    url: host + '/elections/searchElection',
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    data: jsonString,
	    success: function(response) {
	    	populateElectionList(response);
	    },
		error: function(response){
			alert("unable to connect");
		}
	});	
}



function populateElectionList(response){
/* str = "";
	if(response.customMessage == "SUCCESS")
	{
		_elctionList = response.electionList;
		if(_elctionList.length == 0)
		{
			str += "<tr><td colspan='5'> No Matches found! Please try again !</td></tr>";
		}else{
			for(i=0; i< _elctionList.length; i++){
				str += "<tr>" ;
				str += 	"<td><a onclick=getVoterDetail('"+voterList[i].votersId+"')>"+voterList[i].votersId+"</a></td>" ;
				str +=	"<td>"+ voterList[i].name+"</td><td>"+ voterList[i].constituency+"</td>" ;
				str += 	"<td>"+voterList[i].place+"</td><td>"+ voterList[i].lockOutFlag+"</td>" ;
				str += "</tr>" ;
			}
		}
	}else if(response.customMessage == "FAILED"){
		str += "<tr><td colspan='5'>"+ response.customMessage +"</td></tr>"; 
	}
	
*/	
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



