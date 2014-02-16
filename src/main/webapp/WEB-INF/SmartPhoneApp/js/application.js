_elctionList = "";
_eleSelected = "";
_votingPin = "";
_candidateList = "";
_candSelected = "";
_eElectionId = "";


host = "http://localhost:8080/mvote";


$(document).ready(function(){
	//alert("page loaded");
	hideAll();
	showContent('#ContentLogin');

	$("#btn_login").click(function(){
		_eElectionId =$('#in_eleid').val();
		pin=$('#in_pin').val();
	
		if($('#in_eleid').val() == "" || $('#in_pin').val() == "")
		{
			$(".errortext").html("information missing");
		}
		else{
			_votingPin = $('#in_pin').val();
			manageLogin(_eElectionId, _votingPin);
		}
	});
	
	$("#btn_elelist").click(function(){
		manageElectionSelection();
	
	});

	$("#btn_candlist").click(function(){
		confirmUserSelection();
	});
	
	$("#btn_overview").click(function(){
		processVoteSubmission();
	});
	
	$("#btn_candlist_back").click(function(){
		showContent('#ContentElectionList');
	});
	
	$("#btn_overview_back").click(function(){
			showContent('#ContentCandidateList');
	});
	
	$(".btn_confirm_back").click(function(){
		showContent('#ContentOverview');
	});

	$(".btn_start_back").click(function(){
		window.location.reload(true);
	});
	
});

function processVoteSubmission(){

	obj = { "electionId"  : _eleSelected.electId,
			"candidateId" : _candSelected.candId,
			"eElectionId" : _eElectionId  };
	
	jsonString =JSON.stringify(obj);
	
	$.ajax({
	    type: "POST",
	    url: host + '/vote/submitVote',
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    data: jsonString,
	    success: function(response) {
	    	populateVotingResponse(response);
	    },
	    error: function(){
			showContent('#ContentError');
	    } 
	   
	});		

}

function populateVotingResponse(response)
{
	if(response.customMessage == "SUCCESS")
	{
		showContent('#ContentConfirmation');
	}else{
		
		showError("ERR_VOTE");	
	}
}

function showError(msg)
{
	showContent('#ContentError');
	
	$('#btnarea_confirm_back').hide();
	$("#btnarea_start_back").hide();

	if(msg == "ERR_LOGIN")
	{
		$("#errorMsg").html(
			"<b>Unable to login due to incorrect credentials. Please try again!</b>"
		);
		
		$("#btnarea_start_back").show();
	}
	
	else if(msg == "ERR_NO_ELEC")
	{
		$("#errorMsg").html(
			"<b>No Elections Available for you!</b>"
		);
		
		$("#btnarea_start_back").show();
	}

	else if(msg == "ERR_VOTE")
	{
		$("#errorMsg").html(
			"<b>Unable to submit vote due to server error. Please try again after some time report at the helpdesk!</b>"
		);
		
		$('#btnarea_confirm_back').show();
	}
	
	return false;
}



function confirmUserSelection()
{
	var index = $('input[name=candidateSelected]:checked').val();
	_candSelected = _candidateList[index];
	
	str_ele = _eleSelected.electTitle;
	str_cand = _candSelected.candName;
	
	showContent('#ContentOverview');
	$("#overviewContentElec").html(str_ele);
	$("#overviewContentCand").html(str_cand);
}


function manageElectionSelection()
{
	var index = $('input[name=electionSelected]:checked').val();
	_eleSelected = _elctionList[index];

	obj = {"eElectionId":_eElectionId,"electionId":_eleSelected.electId ,"unitElectionId":_eleSelected.unitEleId };
	jsonString =JSON.stringify(obj);
	
	$.ajax({
	    type: "POST",
	    url: host + '/vote/getCandidates',
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    data: jsonString,
	    success: function(response) {
	    	populateCandidateList(response);
	    }
	});	
}


function populateCandidateList(response){
	str = ""; 
	if(response.customMessage == "SUCCESS")
	{
		_candidateList = response.candidateList;
		if(_candidateList.length == 0)
		{
			str += "<p> No Candidates available for the user !</p>";
		}else{
			for(i=0; i< _candidateList.length; i++){
				
				str += 	"<input type='radio' name='candidateSelected' " ;
				str += 	"value='"+ i +"' checked='checked' /> " ;
				str +=	 _candidateList[i].candName + ":" + _candidateList[i].candBio + "<br>" ;
					
			}
		}
	}else{
		str += "<p>"+ response.customMessage +"</p>";
	}
	
	showContent('#ContentCandidateList');
	$("#fieldCandidatesList").html(str);
}


function manageLogin(eid, pin){
	obj = {"eElectionId":_eElectionId, "votingPIN":pin  };
	jsonString =JSON.stringify(obj);
	
	$.ajax({
	    type: "POST",
	    url: host + '/vote/verifyLogin',
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    data: jsonString,
	    success: function(response) {
	    	populateElectionList(response);
	    },
		error: function(response){
			showError("ERR_LOGIN");
		}
	});	
}


function populateElectionList(response){
	str = "";
	if(response.customMessage == "SUCCESS")
	{
		_elctionList = response.electionList;
		if(_elctionList.length == 0)
		{
			str += "<p> No elections available for the user !</p>";
		}else{
			for(i=0; i< _elctionList.length; i++){
				str += 	"<input type='radio' name='electionSelected' " ;
				str += 	"value='"+ i +"' checked='checked' /> " ;
				str +=	_elctionList[i].electTitle ;
			}
		}
	}else if(response.customMessage == "FAILED"){
		showError("ERR_LOGIN");
	}
	else{
		showError("ERR_NO_ELEC");
	}
	
	showContent('#ContentElectionList');
	$("#fieldElectionList").html(str);
	
}
 
function hideAll(){
	 $("#ContentLogin").hide();
	  $("#ContentElectionList").hide();
	  $("#ContentCandidateList").hide();
	  $("#ContentOverview").hide();
	  $("#ContentConfirmation").hide();
	  $("#ContentError").hide();
	  $("#ContentAjaxLoader").hide();
}

function showContent(contentid){
	hideAll();
	$(contentid).show();
}
