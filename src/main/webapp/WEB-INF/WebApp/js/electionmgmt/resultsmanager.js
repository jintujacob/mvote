constsDropDownContent = "";
constsElectionsResults = "";
electionListResponse = "" ;
constsListResponse = "";

_consts_name = "";
_election_name = "";


$(document).ready(function(){
	getHomeContents();
	populateHomePage();
	
	$('#toggle_list_const_form').click(function(){
		$('#content_srchform_byconst').show();
		$('#content_srchform_bystate').hide();
	});
	
	$('#toggle_list_state_form').click(function(){
		$('#content_srchform_byconst').hide();
		$('#content_srchform_bystate').show();
	});	
	
	$('#in_srch_consts').change(function(){
		constSelected = $(this).val();
		if(constSelected != "none"){
			getElectionsByConstituency(constSelected);
		}
		
	});

	$('#btn_search_list_const').click(function(){
		constSeletected = $('#in_srch_consts').val();
		unitEleSelected = $('#in_srch_elections').val();
		if(constSelected != "" && unitEleSelected != ""){
			getElectionResultList(constSelected, unitEleSelected);
		}
		
	});
	
	
});


function getElectionResultList(constSelected, unitEleSelected)
{
	obj = { "constId":constSelected,
			"unitEleId":unitEleSelected 
		};
	jsonString =JSON.stringify(obj);
	
	$.ajax({
	    type: "POST",
	    url: 'http://localhost:8080/mvote/elections/getElectionResultsDetail',
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    data: jsonString,
	    success: function(response) {
	    	constsElectionsResults = response;
	    	populateConstituencyResult(response,constSelected,unitEleSelected);
	    },
	    error:function(){
	    	alert("Connection Error. Network failure or Server unavailable");
	    }
	});		
}

function populateConstituencyResult(response,constSelected,unitEleSelected)
{
	str1  = "";
	str2 = ""; 
	lead = 0;
	if(response.customMessage == "SUCCESS")
	{
		candidateResults = response.resultList;
		
		if(candidateResults.length != 0 ){
			for(i=0; i<candidateResults.length; i++){
				str1 += "<tr>" ;
				str1 += 	"<td>"+ candidateResults[i].candId + "</td>";
				str1 += 	"<td>"+ candidateResults[i].candName + "</td>";
				str1 += 	"<td>"+ candidateResults[i].totalVoteCount + "</td>";
				str1 += 	"<td>"+ candidateResults[i].constVoteCount + "</td>";
				str1 += "</tr>";
				
				if(i==1)
					lead = candidateResults[0].constVoteCount - candidateResults[i].constVoteCount;
			}

			
			str2 += "<tr> <td>Winner </td> <td>: </td> <td> " + candidateResults[0].candName + "</td></tr>" ;
			str2 += "<tr> <td>Lead </td> <td>: </td> <td> " + lead + "</td></tr>" ;
			str2 += "<tr> <td>Scored Votes / Total  </td> <td>: </td> <td> " + candidateResults[0].constVoteCount+ 
						"/"+candidateResults[0].totalVoteCount + "</td></tr>" ;

			
		}else{
			alert("No results found for the constituency selected");
		}
			

		electionName  = getElectionNamePrivate(unitEleSelected);
		constName = getConstNamePrivate(constSelected);
		
		showPage('#pageElectionResultDetailView');
		$("#divResultsListStates").hide();
		
		$(".titleElectionName").html("<b>"+electionName+"</b>");
		$(".titleLocationSelected").html("<b>"+constName+"</b>");
		$(".tableWinnerDetail").html(str2);
		$("#tableResultsListConsts tbody").html(str1);
	}
}


function populateHomePage()
{
	showPage("#pageElectionResultsHome");
}



function hideAll(){
	$('#pageElectionResultsHome').hide();
	$('#pageElectionResultsListView').hide();
	$('#pageElectionResultDetailView').hide();
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
	    	str = "<option value='none'> Select any </option>" ;
	    	if(response.customMessage == "SUCCESS"){
		    	list = response.constsList;
		     	for(var i=0; i<list.length; i++){
		     		str += "<option value='"+ list[i].constId+ "'>"+ list[i].constName +", " +list[i].constState;
		     	 	str += "</option>";
		     	}
	    	}else{
	    		str = "<option value='none'> Information Unavailable</option>";
	    	} 	
	    	constsDropDownContent = str; //to global
	    	constsListResponse = response;
	    	populateHomeConstsDropDown();
	    },
	    error:function(){
		    str = "<option value='none'> Information Unavailable</option>";
	    	
	     	constsDropDownContent = str; //to global
	    	populateHomeConstsDropDown();
	    }
	});		

}


function populateHomeConstsDropDown()
{
	showPage("#pageElectionResultsHome");
	$('#in_srch_consts').html(constsDropDownContent);
}

function getConstNamePrivate(constId){
	constituencies = constsListResponse.constsList;
	for(i=0; i< constituencies.length; i++){
		if (constituencies[i].constId == constId){
			name = constituencies[i].constName +", " +constituencies[i].constState ;
			return name;
		}
	}
	
}

function getElectionsByConstituency(constId)
{

	obj = { "constId":constId };
	jsonString =JSON.stringify(obj);
	
	$.ajax({
	    type: "POST",
	    url: 'http://localhost:8080/mvote/candidates/getElectionsByConst',
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    data: jsonString,
	    success: function(response) {
	    	electionListResponse = response;
	    	populateElectionDropDown(response);
	    },
	    error:function(){
		    populateElectionDropDownError();
	    }
	});		
}


function populateElectionDropDown(response)
{
	str = "<option value='none'> Select any </option>" ;
	if(response.customMessage == "SUCCESS"){
    	list = response.electionList;
     	for(var i=0; i<list.length; i++){
     		str += "<option value='"+ list[i].unitEleId+ "'>"+ list[i].electTitle ;
     	 	str += "</option>";
     	}
	}else{
		str = "<option value='none'> Information Unavailable</option>";
	} 	
	
	$("#in_srch_elections").html(str);
	
}


function populateElectionDropDownError(){
	str = "<option value='none'> Information Unavailable</option>";
	$("#in_add_eles").html(str);
}


function getElectionNamePrivate(unitEleId){
	elections = electionListResponse.electionList;
	for(i=0; i< elections.length; i++){
		if (elections[i].unitEleId == unitEleId){
			name = elections[i].electTitle ;
			return name;
		}
	}
	
}


