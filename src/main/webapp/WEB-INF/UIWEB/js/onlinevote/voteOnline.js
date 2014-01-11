msg_login_submit = "Please wait! application is validating the credentials";
msg_get_election = "Logged In Successfully! Fetching list of elections";
msg_get_candidate = "Please wait!. Fetching list of candidates";
msg_submit_vote = "Please wait!. Vote is being submitted";

$(document).ready(function(){
	 
	showPage('#pageloginForm');
	
	$('.menuitem').menu();
	
	
	$('#btn_submitform').click(function(){
		processLoginFormSubmit();
		showPage('#pageElectionList');
		
	});
	
	
	$('#btn_clk_election').click(function(){
		processElectionSelection();
		showPage('#pageCandidateList');
		
	});
	
	$('#btn_clk_candidate').click(function(){
		processCandidateSelection();
		showPage('#pageVerifySelection');
		
	});
	
	$('#btn_clk_submitVote').click(function(){
		processVoteSubmission();
		showPage('#pageConfimationResp');
		
	});
	
//	idea recharge 34598152 
	
	
	
	$(".menuitem>li").click(function(event) {
		var radio = $(this).find("input:radio");
	    if (radio[0] !== event.target) {
	        radio.click();
	    }
	});
});


function processLoginFormSubmit(){
	showAjaxLoader(msg_login_submit);

	votersId = "v444";
	adhaarId = "UID444";
	votingPIN = "VPIN444"; 
	
	obj = {"votersId":votersId,"adhaarId":adhaarId,"votingPIN":votingPIN  }
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
/*		chkFlag = true;
	if(chkFlag==true){
		showAjaxLoader(msg_get_election);
		
		 * Ajax to get the list of elections;
		 
	//	if(list is not empty)
	 		showPage('#pageElectionList');
	//	else (list is empty)
	//		showPage('#pageErrorNoOpenElection');
		
	}
	else{
		showPage('#pageErrorLogin');
	}
*/	}

function  populateElectionList(){
	alert("populating elections list");
}

function populateCandidateList(){
	alert("populating candidates list");
}

function processVoteSubmission(){
	
	
	return true;
}



function processElectionSelection(){
	var selValue = $('input[name=election]:checked').val(); 
	alert(selValue);
	
	//get list of candidates here
	electionId = "1";
	unitElectionId = "301";
	votingPIN = "VPIN444"; 

	
	obj = {"votingPIN":votingPIN,"electionId":electionId,"unitElectionId":unitElectionId }
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


function processCandidateSelection(){
	var selValue = $('input[name=candidate]:checked').val(); 
	alert(selValue);
	return true;
}



function hideAll(){
	$('#pageAjaxLoader').hide();
	$('#pageloginForm').hide();
	$('#pageElectionList').hide();
	$('#pageCandidateList').hide();
	$('#pageVerifySelection').hide();
	$('#pageConfimationResp').hide();
	$('#pageErrorLogin').hide();
	$('#pageErrorNoOpenElection').hide();
}

function showPage(pageId){
	hideAll();
	$(pageId).show();
}


function showAjaxLoader(msg){
	hideAll();
	$('#pageAjaxLoader p').html(msg);
	$('#pageAjaxLoader').show();
}


