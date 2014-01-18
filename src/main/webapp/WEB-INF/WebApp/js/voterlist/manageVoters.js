constsDropDownContent = "";
searchResult = "";

$(document).ready(function(){
	getHomeContents();
	
	
	$('#goto_search_voter').click(function(){
		 $('#contentAddVoterForm').hide();
		 $('#contentSearchVoterForm').show();
	});
	 
	$('#goto_add_voter').click(function(){
		 $('#contentSearchVoterForm').hide();
		 $('#contentAddVoterForm').show();
		 $('#in_add_consts').html(constsDropDownContent);
		 
	});
	 
	$('#btn_submit_search').click(function()
	{
		votersId = $('#in_srch_voterid').val();
		lockOutFlag = $("input[name='in_srch_lockflag']:checked").val();
		name = $('#in_srch_name').val();
		constituency = $('#in_srch_const').val();

		/*	votersId = "";	lockOutFlag = "F";	name = "";	constituency = "2";		 */
		
		if(votersId!= "" || lockOutFlag!="" || name != "" || constituency != ""){
			constituency = (constituency == 'none' )? "":constituency;
			console.log("|"+votersId+"|"+lockOutFlag+"|"+name+"|"+constituency+"|");
			processVoterSearch(votersId, lockOutFlag, name, constituency );
		}
		
	});
	
	$('#btn_add_voter').click(function()
	{
		votersId = $('#in_add_voterid').val();
		name = $('#in_add_name').val();
		constituency = $('#in_add_consts').val();

		console.log("|"+ votersId +"|"+ name +"|"+ constituency +"|");
		if(votersId != "" && name != "" && constituency != "" && constituency != "none"){
			processVoterAddition();
		}
		else{
			$('.input_error_msg').html("Invalid search criteria! please search again!");
		}
	});
	
	$('.btn_backto_home').click(function(){
		populateHomeConstsDropDown();
	});
	
	$('.btn_backto_reslt').click(function(){
		populateVoterList(searchResult);
	});
	
	
	 
});

function getVoterDetail(votersId)
{
	console.log(votersId);
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
	    },
		error: function(){
			alert("Unable to fetch Information now. System unavailable. Please try after sometime.");
		}
	});		
}

function populateVoterDetail(voterDetail)
{
	str = "";
	status = "none";
	if(voterDetail.customMessage == "SUCCESS")
	{
		status = (voterDetail.lockOutFlag == 'F')? "Active":"Inactive" ;
		
		str += "<tr><td> Name </td> <td>:</td> 			<td>"+ voterDetail.name + "</td></tr>";
		str += "<tr><td> Voters Id </td> <td>:</td> 	<td>"+ voterDetail.votersId + "</td></tr>";
		str += "<tr><td> constituency</td><td>:</td>	<td>"+ voterDetail.constituency+ "</td></tr>";
		str += "<tr><td> Status </td> <td>:</td> 		<td>"+ status + "</td></tr>";
		str += "<tr><td> Place </td> <td>:</td> 		<td>"+ voterDetail.place + "</td></tr>";
		str += "<tr><td> E - Election ID </td><td>:</td> <td>"+ "unavailable" + "</td></tr>";
		str += "<tr><td> adhaar ID </td>  	<td>:</td> 	 <td>"+ voterDetail.adhaarId + "</td></tr>";
	}
	else{
		str += "<tr><td colspan='5'>"+ response.customMessage +"</td></tr>"; 
	}

	showPage('#pageVoterDetailView');
	$("#tableVoterDetail").html(str);
	
	if(status == "Active")
		$("#btn_activate").hide();
	else
		$("#btn_deactivate").hide();
}


function processVoterSearch(votersId, lockOutFlag, name, constituency )
{
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
	    	searchResult = response;  //to global
	    	populateVoterList(response);
	    },
	    error: function(){
	    	alert("Unable to fetch data from the backend. Please try after some time!");
	    }
	
	});		

}

function populateVoterList(response)
{
	str = "";
	if(response.customMessage == "SUCCESS")
	{
		voterList = response.voterList;
		if(voterList.length == 0)
		{
			str += "<tr><td colspan='5'> No Matches found! Please try again !</td></tr>";
		}else
		{
			for(i=0; i<voterList.length; i++){
				str += "<tr>" ;
				str += 	"<td><a onclick=getVoterDetail('"+voterList[i].votersId+"')>"+voterList[i].votersId+"</a></td>" ;
				str +=	"<td>"+ voterList[i].name+"</td><td>"+ voterList[i].constituency+"</td>" ;
				str += 	"<td>"+voterList[i].place+"</td><td>"+ voterList[i].lockOutFlag+"</td>" ;
				str += "</tr>" ;
			}
		}
	}else{
		str += "<tr><td colspan='5'>"+ response.customMessage +"</td></tr>"; 
	}
	
	showPage('#pageVoterListView');
	$("#tableVoterList tbody").html(str);
}


//test

function processVoterAddition(){
	showPage('#pageVoterListView');
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
	hideAll();
	$('#pageVoterManagerHome').show();
 	$('#contentAddVoterForm').hide();
 	$('#contentSearchVoterForm').show();
 	
 	$('#in_srch_const').html(constsDropDownContent);
}

