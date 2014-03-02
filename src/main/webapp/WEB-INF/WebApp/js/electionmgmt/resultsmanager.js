constsDropDownContent = "";
constsElectionsResults = "";

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
	    	populateSearchResults(response);
	    },
	    error:function(){
	    	alert("Connection Error. Network failure or Server unavailable");
	    }
	});		
}




function populateSearchResults(response)
{
	str = "";
	if(response.customMessage == "SUCCESS")
	{
		searchResults = response.candidateList;
		if(candidateList.length == 0)
		{
			str += "<tr><td colspan='5'> No Matches found! Please try again !</td></tr>";
		}else
		{
			for(i=0; i<candidateList.length; i++){
				str += "<tr>" ;
				str += 	"<td><a onclick=getCandidateDetail("+i+")>"+candidateList[i].candId+"</a></td>" ;
				str +=	"<td>"+ candidateList[i].candName+"</td><td>"+ candidateList[i].eleTitle+"</td>" ;
				str += 	"<td>"+candidateList[i].constName+","+ candidateList[i].constState +"</td>" ;
				str += "</tr>" ;
			}
		}
	}else{
		str += "<tr><td colspan='5'>"+ response.customMessage +"</td></tr>"; 
	}
	
	showPage('#pageCandidateListView');
	$("#tableCandidateList tbody").html(str);
}

function getCandidateDetail(i)
{
	str  = "";
	str += "<tr><td> Candidate ID </td> <td>:</td> 	<td>"+ candidatResults[i].candId + "</td></tr>";
	str += "<tr><td> Candidate Name </td> <td>:</td><td>"+ candidatResults[i].candName + "</td></tr>";
	str += "<tr><td> Candidate Bio </td> <td>:</td> <td>"+ candidatResults[i].candBio + "</td></tr>";
	str += "<tr><td> Constituency </td> <td>:</td> 	<td>"+ candidatResults[i].constName + "</td></tr>";
	str += "<tr><td> State </td> <td>:</td> 		<td>"+ candidatResults[i].constState + "</td></tr>";
	str += "<tr><td> Enrolled Election</td> <td>:</td> <td>"+ candidatResults[i].eleTitle + "</td></tr>";
	str += "<tr><td> Enrolled Election Description</td> <td>:</td> 	<td>"+ candidatResults[i].eleDesc + "</td></tr>";
	
	showPage('#pageCandDetailView');
	$("#tableCandidateDetail").html(str);
	
	//populate hyperlinks
	str = "";
	str += "<button onclick=deleteCandidate("+i+")>Delete </button>"; 
	$("#buttonArea").html(str);
	
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

