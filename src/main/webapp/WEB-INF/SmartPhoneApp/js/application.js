_elctionList = "";
_eleSelected = "";
_votingPin = "";
_candidateList = "";
_candSelected = "";

$(document).ready(function(){
	//alert("page loaded");
	hideAll();
	showContent('#ContentLogin');
	$("#btn_login").click(function(){
		eid=$('#in_eleid').val();
		pin=$('#in_pin').val();
		if(eid==""){
			alert("eid is null"); 		$(".errortext").html("election id is empty");
		}
		if(pin==""){
			alert("pin is null");		$(".errortext").html("pin is empty");
		}
		if(eid==""&&pin==""){
			$(".errortext").html("information missing");
		}
		else{
			_votingPin = pin;
			manageLogin(eid,pin);
		}
	});
	
	$("#btn_elelist").click(function(){
		manageElectionSelection();
	
	});
	$("#btn_candlist").click(function(){
		showContent('#ContentOverview');
	});
	$("#btn_overview").click(function(){
		showContent('#ContentConfirmation');
	});
	
	$("#btn_candlist_back").click(function(){
		showContent('#ContentElectionList');
	});
	$("#btn_overview_back").click(function(){
			showContent('#ContentCandidateList');
		});
	$("#btn_confirm_back").click(function(){
		showContent('#ContentOverview');
	});
  });


function manageElectionSelection()
{
	var index = $('input[name=electionSelected]:checked').val();
	_eleSelected = _elctionList[index];

	obj = {"votingPIN":_votingPin,"electionId":_eleSelected.electId ,"unitElectionId":_eleSelected.unitEleId }
	jsonString =JSON.stringify(obj);
	
	$.ajax({
	    type: "POST",
	    url: 'http://localhost:8080/mvote/vote/getCandidates',
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
				
				str += 	"<input type='radio' name='canidateSelected' " ;
				str += 	"value='"+ _candidateList[i].candId +"' checked='checked' /> " ;
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
	obj = {"eElectionId":eid, "votingPIN":pin  }
	jsonString =JSON.stringify(obj);
	
	$.ajax({
	    type: "POST",
	    url: 'http://localhost:8080/mvote/vote/verifyLogin',
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    data: jsonString,
	    success: function(response) {
	    	populateElectionList(response);
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
			for(i=0; i<_elctionList.length; i++){
				str += 	"<input type='radio' name='electionSelected' " ;
				str += 	"value='"+ i +"' checked='checked' /> " ;
				str +=	_elctionList[i].electTitle ;
			}
		}
	}else{
		str += "<p>"+ response.customMessage +"</p>";
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
