constsDropDownContent = "";
searchResult = "";

$(document).ready(function(){
	getHomeContents();
	
	
	$('#goto_search_cand').click(function(){
		 $('#contentAddCandForm').hide();
		 $('#contentSearchCandForm').show();
	});
	 
	$('#goto_add_cand').click(function(){
		 $('#contentSearchCandForm').hide();
		 $('#contentAddCandForm').show();
		 $('#in_add_consts').html(constsDropDownContent);
		 
	});
	 
	$('#btn_submit_search').click(function()
	{
		name = $('#in_srch_candname').val();
		constituency = $('#in_srch_candconst').val();
		
		if(name != "" || constituency != ""){
			constituency = (constituency == 'none' )? "":constituency;
			processCandidateSearch(name, constituency );
		}
	});
	
	$('#btn_add_cand').click(function()
	{
		name = $('#in_add_name').val();
		bio = $('#in_add_bio').val();
		constituency = $('#in_add_consts').val();

		if(name != "" && constituency != "" && constituency != "none"){
			//
		}
		else{
			$('.input_error_msg').html("Invalid search criteria! please search again!");
		}
	});
	
	$('.btn_backto_home').click(function(){
		populateHomeConstsDropDown();
	});
	
	$('.btn_backto_reslt').click(function(){
		populateCandidateList(searchResult);
	});
	
	$("#btn_delete").click(function(){
		alert("to delete");
	});
	
	 
});


function hideAll(){
	$('#pageCandidateManagerHome').hide();
	$('#pageCandidateListView').hide();
	$('#pageCandDetailView').hide();
}

function showPage(pageId){
	hideAll();
	$(pageId).show();
}



