constList = "";

$(document).ready(function(){
	getHomeContents();
	
	
	$('#goto_search_voter').click(function(){
		 $('#contentAddVoterForm').hide();
		 $('#contentSearchVoterForm').show();
	});
	 
	$('#goto_add_voter').click(function(){
		 $('#contentSearchVoterForm').hide();
		 $('#contentAddVoterForm').show();
	});
	 
	$('#btn_submit_search').click(function(){
		
		votersId = $('#in_srch_voterid').val();
		lockOutFlag = $("input[name='in_srch_lockflag']:checked").val();
		name = $('#in_srch_name').val();
		constituency = $('#in_const').val();

		/*	votersId = "";
			lockOutFlag = "F";
			name = ""; 
			constituency = "2";
		 */
		
		if(votersId!= "" || lockOutFlag!="" || name != "" || constituency != ""){
			constituency = (constituency == 'none' )? "":constituency;
			console.log("|"+votersId+"|"+lockOutFlag+"|"+name+"|"+constituency+"|");
			processVoterSearch(votersId, lockOutFlag, name, constituency );
		}
		
	});
	
	$('#btn_add_voter').click(function(){
		processVoterAddition();
		//emulate the search for recently added voterId and show in list
		showPage('#pageVoterListView');
	});
	 
	$('#pageVoterListView a').click(function(){
		getVoterDetail();
		showPage('#pageVoterDetailView');
	});
});

function getVoterDetail(){
	votersId = "v444";
	
	obj = { "votersId":votersId	}
	jsonString =JSON.stringify(obj);
	
	$.ajax({
	    type: "POST",
	    url: 'http://localhost:8080/mvote/voterslist/getVoterInfoById',
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    data: jsonString,
	    success: function(response) {
	    	populateVoterDetail(response);
	    }
	});		
}

function populateVoterDetail(voterDetail){
	alert(JSON.stringify(voterDetail));
}


function processVoterSearch(votersId, lockOutFlag, name, constituency ){

	obj = { "votersId":votersId,
			"lockOutFlag":lockOutFlag,
			"name":name,
			"constituency":constituency 
		}
	
	jsonString =JSON.stringify(obj);
	
	$.ajax({
	    type: "POST",
	    url: 'http://localhost:8080/mvote/voterslist/searchVoter',
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    data: jsonString,
	    success: function(response) {
	    	populateVoterList(response);
	    }
	});		

}

function populateVoterList(voterList){
	alert(JSON.stringify(voterList));
	showPage('#pageVoterListView');
	
	//check the custom message value before making printing
}


//test

function processVoterAddition(){
	return true;
}


function hideAll(){
	$('#pageVoterManagerHome').hide();
	$('#pageVoterListView').hide();
	$('#pageVoterDetailView').hide();
}

function showPage(pageId){
	hideAll();
	$(pageId).show();
}



function getHomeContents(){
	$.ajax({
	    type: "POST",
	    url: 'http://localhost:8080/mvote/consts/getAllConsts',
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    //data: jsonString,
	    success: function(response) {
	    	populateHomeConstsDropDown(response.constsList);
	    }
	});		

}


function populateHomeConstsDropDown(list)
{
	hideAll();
	$('#pageVoterManagerHome').show();
 	$('#contentAddVoterForm').hide();
 	$('#contentSearchVoterForm').show();
 	
 	str = "" ;
 	
 	
 	for(var i=0; i<list.length; i++){
 	 	str += "<option value='"+ list[i].constId+ "'>"+ list[i].constName +", " +list[i].constState;
 	 	str += "</option>";
 	}
 	
 	
 	
 	$('#in_const').html(str);
}

