constsDropDownContent = "";
candidatResults = "";


$(document).ready(function(){
	getHomeContents();
	populateHomePage();
	
	
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
			console.log(name, constituency);
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

function processCandidateSearch(name, constituency )
{
	obj = { "candName":name,
			"constName":constituency 
		};
	jsonString =JSON.stringify(obj);
	
	$.ajax({
	    type: "POST",
	    url: 'http://localhost:8080/mvote/candidates/searchCandidate',
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    data: jsonString,
	    success: function(response) {
	    	candidatResults = response.candidateList;
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
		candidateList = response.candidateList;
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

function deleteCandidate(index)
{
	obj = { "candId":candidatResults[index].candId };
	jsonString =JSON.stringify(obj);
	
	$.ajax({
	    type: "POST",
	    url: 'http://localhost:8080/mvote/candidates/deleteCandidate',
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    data: jsonString,
	    success: function(response) {
	    	populateDeletionResponse(response);
	    },
	    error:function(){
	    	alert("Connection Error. Network failure or Server unavailable");
	    }
	});		
}

function populateDeletionResponse(response){
	if(response.customMessage == "SUCCESS"){
		alert("Deleted Candidate Successfully!");
	}else{
		alert(response.customMessage);
	}
	
}


function populateHomePage()
{
	showPage("#pageCandidateManagerHome");
	 $('#contentAddCandForm').hide();
	 $('#contentSearchCandForm').show();
}



function hideAll(){
	$('#pageCandidateManagerHome').hide();
	$('#pageCandidateListView').hide();
	$('#pageCandDetailView').hide();
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
	showPage("#pageCandidateManagerHome");
	 $('#contentAddCandForm').hide();
	 $('#contentSearchCandForm').show();
	$('#in_srch_candconst').html(constsDropDownContent);
	$('#in_add_consts').html(constsDropDownContent);
}



