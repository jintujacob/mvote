// Global variables

VOTERSID = "";
ADHAARID = "";


$(document).ready(function(){
	showPage('#pageEnrollUserSearchResults');
	
	$('#btn_search_voter').click(function(){
		processSearchVoterClick();
	});
	
	$('#btn_search_adhaar').click(function(){
		processSearchAdhaarClick();
	});
	
	
	$('#btn_verified').click(function(){
		processVerifyButtonClick();
	});
	

	$('#back_to_searchform').click(function(){
		showPage('#pageEnrollUserSearchForm');
	});
	
});

function processSearchVoterClick(){
	votersId = $('#in_votersId').val();
	if(votersId != "")
	{
		obj = {"votersId":votersId}
		jsonString =JSON.stringify(obj);
		
		$.ajax({
		    type: "POST",
		    url: 'http://localhost:8080/mvote/enroll/getVoterInfo',
		    contentType: "application/json; charset=utf-8",
		    dataType: "json",
		    data: jsonString,
		    success: function(response) {
		    	populateVoterInfo(response);
		    }
		});
	}
}

function processSearchAdhaarClick(){
	adhaarId = $('#in_adhaarId').val();

	if(adhaarId != "")
	{
		obj = {"adhaarID":adhaarId}
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
	return true;
}


function populateVoterInfo(voterUser){
	str = 	"";
	if(voterUser.customMessage == "success")
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
	
	$('#tableVoterData').append(str);
}


function populateAdhaarInfo(adhaarUser){
	str = 	"";
	if(adhaarUser.customMessage == "success")
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
		
	$('#tableAdhaarData').append(str);
}

function processVerifyButtonClick()
{
	obj = { "adhaarId":$('#in_adhaarId').val(),
			"votersId":$('#in_votersId').val()	}
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
	str = 	"";
	if(enrolledUser.customMessage == "success")
	{
		str+= 	'<tr> <td> Voters ID </td> <td> : '			+ enrolledUser.votersId + '</td> </tr>' ;
		str+= 	'<tr> <td> Adhaar ID </td> <td> : '			+ enrolledUser.adhaarId + '</td> </tr>' ;
		str+= 	'<tr> <td> E - Election ID </td> <td> : '	+ enrolledUser.eElectionId + '</td> </tr>'	;
	}else
	{
		str+= 	'<tr> <td> Enrollment Failed. Please Enroll again! </td> </tr>' ;
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