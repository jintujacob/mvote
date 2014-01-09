msg_login_submit = "Please wait! application is validating the credentials";
msg_get_election = "Logged In Successfully! Fetching list of elections";
msg_get_candidate = "Please wait!. Fetching list of candidates";
msg_submit_vote = "Please wait!. Vote is being submitted";

$(document).ready(function(){
	 
	showPage('#pageloginForm');
	
	
	
	$('.menuitem').menu();
	
	$('#btn_submitform').click(function(){
		//processLoginFormSubmit();
		
		$.get(
				'http://localhost:8080/springjson/rest/kfc/brands/kfc-kampar', 
				function(response) {
					alert('hello');
					alert(response);
		});
		
		/*$.ajax({
			type : "GET",
			url : "http://localhost:8080/mvote/vote/formBean",
			data : JSON.stringify({
				votersId:"444",
				adhaarId:"445"
			}),
			contentType : 'application/json',
			success : function(resp) {
				alert('in success');
			},
			error : function(resp){
				alert('in error');
			}
		
		});*/
	
	
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
	
	
	function processLoginFormSubmit(){
		showAjaxLoader(msg_login_submit);
		/*
		 * Ajax to verify the login - return true or false
		 */
		
		$.ajax({
			type : "GET",
			url : "http://localhost:8080/mvote/",
			data : JSON.stringify({
				votersId:"testing votersId 444",
				adhaarId:"testing adhaar id 445"
			}),
			contentType : 'application/json',
			success : function(resp) {
				alert(resp);
			},
			error : function(resp){
				alert(resp);
			}
		
		});
		
		alert('ajaxed');
		
		chkFlag = true;
		if(chkFlag==true){
			showAjaxLoader(msg_get_election);
			/*
			 * Ajax to get the list of elections;
			 */
		//	if(list is not empty)
		 		showPage('#pageElectionList');
		//	else (list is empty)
		//		showPage('#pageErrorNoOpenElection');
			
		}
		else{
			showPage('#pageErrorLogin');
		}
	}

	
	
});

function processVoteSubmission(){
	
	
	return true;
}



function processElectionSelection(){
	var selValue = $('input[name=election]:checked').val(); 
	alert(selValue);
	return true;
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


