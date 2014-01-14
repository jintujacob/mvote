$(document).ready(function(){
	//alert("page loaded");
	hideAll();
	showContent('#ContentLogin');
	$("#btn_login").click(function(){
		eid=$('#in_eleid').val();
		pin=$('#in_pin').val();
		/*if(eid==""){
			alert("eid is null");
			$(".errortext").html("election id is empty");
		}
		if(pin==""){
			alert("pin is null");
			$(".errortext").html("pin is empty");
		}*/
		if(eid==""&&pin==""){
			$(".errortext").html("information missing");
		}
		else
			showContent('#ContentElectionList');
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
