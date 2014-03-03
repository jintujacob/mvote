adhaarId = "";
votersId = "";
$(document).ready(function(){
	showPage('#pageEnrollUserSearchResults');
	
	$('#btn_search_voter').click(function(){
		votersId = $('#in_votersId').val();
		$('#in_votersId').val("");
		if(votersId != ""){
			processSearchVoterClick(votersId);			
		}

	});
	
	$('#btn_search_adhaar').click(function(){
		adhaarId = $('#in_adhaarId').val();	
		$('#in_adhaarId').val("");
		if(adhaarId != ""){
			processSearchAdhaarClick(adhaarId);
		}
	});
	
	$('#reset_form').click(function(){
		window.location.reload(true);
	});
	
	
	$('#btn_verified').click(function(){
		if(confirm("You are about to enroll. Please confirm?"))
		{
			if( (adhaarId != "") && (votersId != '') )
				processVerifyButtonClick(adhaarId, votersId);
			else
				alert("Verification of User in Adhaar Sytem and Voter List is mandatory.");
		}
	});
	
	$('#btn_homepage').click(function(){
		window.location.reload(true);
	});
	
});

function processSearchVoterClick(votersId){
	//votersId1 = "v444";
	obj = { "votersId":votersId};
	jsonString =JSON.stringify(obj);
	
	$.ajax({
	    type: "POST",
	    url: 'http://localhost:8080/mvote/enroll/getVoterInfoById',
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    data: jsonString,
	    success: function(response) {
	    	populateVoterInfo(response);
	    }
	});		
	
}

function processSearchAdhaarClick(adhaarId){
		//adhaarId='UID444';
		obj = {"adhaarID":adhaarId};
		jsonString =JSON.stringify(obj);
		$.ajax({
		    type: "POST",
		    url: 'http://localhost:8080/mvote/enroll/getAdhaarInfo',
		    contentType: "application/json; charset=utf-8",
		    dataType: "json",
		    data: jsonString,
		    success: function(response) {
		    	populateAdhaarInfo(response);
		    }
		});
}

function populateVoterInfo(voterUser){

	str = 	"";
	if(voterUser.customMessage == "SUCCESS")
	{
		$('#tableVoterForm').hide();
		str+= 	'<tr> <td> Voter Number </td> <td> : '	+ voterUser.votersId + '</td> </tr>' ;
		str+= 	'<tr> <td> Name </td> <td> : '			+ voterUser.name + '</td> </tr>' ;
		str+= 	'<tr> <td> Place </td> <td> : '			+ voterUser.place + '</td> </tr>'	;
		str+= 	'<tr> <td> Constituency </td> <td> : '	+ voterUser.constituency + '</td> </tr>' ;
	}else{
		$('#in_votersId').val("");
		str+= 	'<tr> <td> No results for the search. Please search again! </td> </tr>' ;
	}
	
	$('#tableVoterData').html(str);
}


function populateAdhaarInfo(adhaarUser){
	str = 	"";
	if(adhaarUser.customMessage == "SUCCESS")
	{
		$('#tableAdhaarForm').hide();
		str+= 	'<tr> <td> Adhaar Number </td> <td> : '+ adhaarUser.adhaarID + '</td> </tr>' ;
		str+= 	'<tr> <td> First Name </td> <td> : '+ adhaarUser.nameFirst + '</td> </tr>' ;
		str+= 	'<tr> <td> Last Name</td> <td> : '+ adhaarUser.nameLast + '</td> </tr>'	;
		str+= 	'<tr> <td> Address</td> <td> : '+ adhaarUser.address + '</td> </tr>' ;
		str+= 	'<tr> <td> Phone</td> <td> : '+ adhaarUser.phone + '</td> </tr>'  ;

	}else{
		$('#in_adhaarId').val("");
		str+= 	'<tr> <td> No results for the search. Please search again! </td> </tr>' ;
	}
		
	$('#tableAdhaarData').html(str);
}

function processVerifyButtonClick()
{
	obj = { "adhaarId":adhaarId, "votersId":votersId	}
	jsonString =JSON.stringify(obj);
	
	$.ajax({
	    type: "POST",
	    url: 'http://localhost:8080/mvote/enroll/enrollUser',
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    data: jsonString,
	    success: function(response) {
	    	populateElectionSlip(response);
	    }
	});
}

function populateElectionSlip(enrolledUser)
{
	
	$('#tableElectionCard').html('');
	str = 	"";
	if(enrolledUser.customMessage == "SUCCESS")
	{
		eid = enrolledUser.eElectionId ;
		electionId = eid.substring(0,4)+ "-" + eid.substring(4,8) +"-" + eid.substring(8,12);
		
		str+= 	'<tr> <td> Voters ID </td> <td> : '			+ enrolledUser.votersId + '</td> </tr>' ;
		str+= 	'<tr> <td> Adhaar ID </td> <td> : '			+ enrolledUser.adhaarId + '</td> </tr>' ;
		str+= 	'<tr> <td> E - Election ID </td> <td> : '	+ electionId + '</td> </tr>'	;
	}else
	{
		str+= 	'<tr> <td>' +enrolledUser.customMessage+ '</td> </tr>' ;
	}
	showPage('#pageEnrollResponseStatus');
	$('#tableElectionCard').html(str);
}



function hideAll(){
	$('#pageAjaxLoader').hide();
	$('#pageEnrollUserSearchResults').hide();
	$('#pageEnrollResponseStatus').hide();
}

function showPage(pageId){
	hideAll();
	$(pageId).show();
}



/*

*/