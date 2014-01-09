$(document).ready(function(){
	showPage('#pageEnrollUserSearchForm');
	
	$('#btn_search').click(function(){
		processSearchFormClick();
		showPage('#pageEnrollUserSearchResults');
	});
	
	
	$('#btn_verified').click(function(){
		processVerifyButtonClick();
		showPage('#pageEnrollCredentialsForm');
	});
	
	
	$('#btn_cred').click(function(){
		processCredentialsSubmit();
		showPage('#pageEnrollResponseStatus');
	});
	
	$('#back_to_searchform').click(function(){
		showPage('#pageEnrollUserSearchForm');
	});
	
	$('#btn_reset_cred').click(function(){
		$('#in_username').val("");
		$('#in_password').val("");
	});
	
});

function processSearchFormClick(){
	votersId = $('#in_votersId').val();
	adhaarId = $('#in_adhaarId').val();
	/*
	 * submit ajax request and get the json resonse
	 * process the response and set it to #pageEnrollUserSearchResults -> table fields inside the page
	 */
	return true;
}

function processVerifyButtonClick(){
	return true;
}

function processCredentialsSubmit(){
	
	return true;
}


function hideAll(){
	$('#pageAjaxLoader').hide();
	$('#pageEnrollUserSearchForm').hide();
	$('#pageEnrollUserSearchResults').hide();
	$('#pageEnrollCredentialsForm').hide();
	$('#pageEnrollResponseStatus').hide();
}

function showPage(pageId){
	hideAll();
	$(pageId).show();
}



/*

*/