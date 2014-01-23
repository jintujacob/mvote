$(document).ready(function(){
	showPageHome();
	
	
	$('#goto_search_const').click(function(){
		 $('#contentAddConstsForm').hide();
		 $('#contentSearchConstsForm').show();
	});
	 
	$('#goto_add_const').click(function(){
		 $('#contentSearchConstsForm').hide();
		 $('#contentAddConstsForm').show();
	});
	 
	$('#btn_submit_search').click(function(){
		// function
		showPage('#pageConstsSummary');
	});
	
	$('#btn_add_const').click(function(){
		//function
		//emulate the search for recently added voterId and show in list
		showPage('#pageConstsSummary');
	});
	 
	$('#pageConstsSummary a').click(function(){
		getConstDetail();
		showPage('#pageConstsDetail');
	});
	 
});


function getConstDetail(){
	return true;
}


function hideAll(){
	$('#pageConstsHome').hide();
	$('#pageConstsSummary').hide();
	$('#pageConstsDetail').hide();
}

function showPage(pageId){
	hideAll();
	$(pageId).show();
}

function showPageHome(){
	hideAll();
	$('#pageConstsHome').show();
	 	$('#contentAddConstsForm').hide();
	 		$('#contentSearchConstsForm').show();
}



