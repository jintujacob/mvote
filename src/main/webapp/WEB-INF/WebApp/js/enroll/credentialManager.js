electionId = "";
adhaarId = "";
newPin = "";
$(document).ready(function(){
	showPage('#pagePinManagerLogin');
	
	$('#btn_login').click(function(){
		electionId = $('#in_electionId').val();
		adhaarId = $('#in_adhaarId').val();
		
		$('#in_electionId').val("");
		$('#in_adhaarId').val("");
		
		if( (electionId != "") && (adhaarId != "") )
			doProcessLogin(electionId);
	});
	
	
	$('#clk_changePin').click(function(){
		$(this).hide();
		$('#form_pinChange').show();

	});
	
	$('#btn_changePin').click(function(){
		newPin =$('#in_PIN').val();
		$('#in_PIN').val("");
		if(newPin.length == 6)
			doChangePIN(electionId, newPin);
		else{
			alert("Minimum 6 characters required to proceed");
		}
	});

	$('.btn_homepage').click(function(){
		window.location.reload(true);
	});
	
	
});

function doChangePIN(electionId, newPin)
{
		obj = { 
				"eElectionId":electionId,
				"votingPIN":newPin,
				"adhaarId":adhaarId 
			  }
		jsonString =JSON.stringify(obj);
		
		$.ajax({
		    type: "POST",
		    url: 'http://localhost:8080/mvote/enroll/changePinById',
		    contentType: "application/json; charset=utf-8",
		    dataType: "json",
		    data: jsonString,
		    success: function(response) {
		    	populatePINChangeStatus(response)
		    }
		});

}


function doProcessLogin(electionId)
{
	if(electionId != "")
	{
		obj = {"eElectionId":electionId, "adhaarId":adhaarId }
		jsonString =JSON.stringify(obj);
		
		$.ajax({
		    type: "POST",
		    url: 'http://localhost:8080/mvote/enroll/pinManagerLogin',
		    contentType: "application/json; charset=utf-8",
		    dataType: "json",
		    data: jsonString,
		    success: function(response) {
		    	populatePinManagerHome(response);
		    }
		});
	}
}


function populatePinManagerHome(enrolledUsr)
{

	if(enrolledUsr.customMessage == "SUCCESS")
	{
		showPage('#pagePinManagerHome');
		$('#pinBox').html(enrolledUsr.votingPIN);
	}else{
		showPage('#pagePinManagerLogin');
		$('#errormsg_login').html(enrolledUsr.customMessage);
	}	
}




function populatePINChangeStatus(modifiedUser)
{
	str = 	"";
	if(modifiedUser.customMessage == "SUCCESS")
	{
		str+= " Successfully Changed the PIN number for election Id " + electionId	 ;
	}else{
		str+= modifiedUser.customMessage ;	
	}	
	
	showPage('#pagePinManagerSubmitResponse');
	$('#responseMessage').html(str);
}



function hideAll(){
	$('#pagePinManagerLogin').hide();
	$('#pagePinManagerHome').hide();
	$('#pagePinManagerSubmitResponse').hide();
	
	$('#form_pinChange').hide();
}

function showPage(pageId){
	hideAll();
	$(pageId).show();
}



/*

*/