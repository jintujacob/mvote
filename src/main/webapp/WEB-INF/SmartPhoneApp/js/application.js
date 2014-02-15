$(document).ready(function(){
	//alert("page loaded");
	hideAll();
	showContent('#ContentLogin');
	$("#btn_login").click(function(){
		eid=$('#in_eleid').val();
		pin=$('#in_pin').val();
		if(eid==""){
			alert("eid is null");
			$(".errortext").html("election id is empty");
		}
		if(pin==""){
			alert("pin is null");
			$(".errortext").html("pin is empty");
		}
		if(eid==""&&pin==""){
			$(".errortext").html("information missing");
		}
		else{
			manageLogin(eid,pin);
		}
	});
	
	$("#btn_elelist").click(function(){
		showContent('#ContentCandidateList');
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
	    	
	    	//review
	    	populateElectionList(response);
	    	
	    }
	});	
	
	//review
	//showContent('#ContentElectionList');
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
