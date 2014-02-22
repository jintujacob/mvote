__addTitle = "";
__addDesc = "";
__addFromDt = "";
__addToDt = "";


__allStates = "";

host  = "http://localhost:8080/mvote" ;

	
	
$(document).ready(function()
{
	getAllStatesList();
	showPageHome();
	
	$('#goto_search_ele').click(function(){
		$('#contentAddElectionForm').hide();
 		$('#contentSearchElectionForm').show();
	});
	
	$('#goto_add_ele').click(function(){
		$('#contentAddElectionForm').show();
 		$('#contentSearchElectionForm').hide();
	});
	
	$('#btn_search_ele').click(function(){
		processElectionsSearch();
		showPage('#pageElectionSearchSummary');
	});
	
	$('#btn_add_ele').click(function(){
		processElectionAddition();
	});
	
	$('#pageElectionSearchSummary a').click(function(){
		getElectionDetailBasic();
		getElectionDetailStates();
		showPage('#pageElectionDetail');
	});
	
	$('#btn_add_states').click(function(){
		//emulate searching  == same as $('#btn_search_ele').click()
		processElectionsSearch();
		showPage('#pageElectionSearchSummary');
	});
	
	
	$('#panelRightElectionStates input[type=checkbox]').change(function(){
		alert(this.val());
		
	});
	
	
	
	 
});


function getAllStatesList()
{
	$.ajax({
	    type: "POST",
	    url: host + '/elections/getAllStates',
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    success: function(response) {
	    	__allStates = response.statesList;
	    	
	    	loadElectionStates(__allStates);
	    	
	    	//processElectionAddition(__allStates);
	    },
		error: function(response){
			alert("Add Election feature is currently unavailable. Please report the issue or try after sometime!");
		}
	});	
}


function loadElectionStates()
{
 	//populate states list
  	
  	strLeft = "";
  	strRight = "";
  	for(i=0; i < __allStates.length; i++)
  	{
  		console.log(i);
  		if(i%2 != 0 )
  		{
  			strLeft +=  " <input type='checkbox' name='check"+ __allStates[i].stateId 
  					+ "' value='"+ __allStates[i].stateId +"'/> "
  					+ __allStates[i].stateName +" <br>" ;
  			console.log(strLeft);
  		}else{
  			strRight +=  " <input type='checkbox' name='check"+ __allStates[i].stateId 
				+ "' value='"+ __allStates[i].stateId +"'/> "
				+ __allStates[i].stateName +" <br>" ;
  			
  			console.log(strRight);
  		}
  	}
  	
  	$('.statesListLeft').html(strLeft);
  	$('.statesListRight').html(strRight);

  	
  	//load elections states on the add elections form dropdown
}


function getElectionDetailBasic()
{
	obj = {	"electId":"1" };
	jsonString =JSON.stringify(obj);
	
	$.ajax({
	    type: "POST",
	    url: host + '/elections/getElectionDetail',
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    data: jsonString,
	    success: function(response) {
	    	populateElectionList(response);
	    },
		error: function(response){
			alert("unable to connect");
		}
	});	
}

function getElectionDetailStates()
{
	obj = {	"electId":"1" };
	jsonString =JSON.stringify(obj);
	
	$.ajax({
	    type: "POST",
	    url: host + '/elections/getStatesListByElection',
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    data: jsonString,
	    success: function(response) {
	    	//populateElectionList(response);
	    },
		error: function(response){
			alert("unable to connect");
		}
	});	
}



function processElectionAddition(){
	//get inputs , write to db, store info in js var
	
	__addTitle = $('#in_add_title').val();
  	__addDesc = $('#in_add_desc').val();
  	__addFromDt = $('#in_add_fromdt').val();
  	__addToDt = $('#in_add_todt').val();
  	
	__addTitle ="1";
  	__addDesc = "2";
  	__addFromDt = "3";
  	__addToDt = "4";
	

    //alert(addTitle +"|"+ addDesc +"|"+ addFromDt +"|"+ addToDt);
  	
  	//populate entered info in the second page
  	if(__addTitle != "" && __addDesc != "" && __addFromDt != "" && __addToDt != "")
  	{
  	    str  = "";
  	    str += "<tr> <td> Election Title </td> <td>:</td>  <td>"+ __addTitle +"</td>  </tr>";
  	    str += "<tr> <td> Election Description </td> <td>:</td>  <td>"+ __addDesc +"</td>  </tr>";
  	    str += "<tr> <td> Election Start Date </td> <td>:</td>  <td>"+ __addFromDt +"</td>  </tr>";
  	    str += "<tr> <td> Election End Date </td> <td>:</td>  <td>"+ __addToDt +"</td>  </tr>";
  	    
  	    showPage('#pageAddElectionStatesForm');
  	    $('#tbl_basicElectionInfoAdd').html(str);
  	
  	}else{
  		alert("All fields are mandatory. Please resubmit");
  	}
}

function processElectionsSearch(){
	obj = {
			"electTitle":"Lo", 
			"constId" : "",
			"stateId" : ""
		  };
	jsonString =JSON.stringify(obj);
	
	$.ajax({
	    type: "POST",
	    url: host + '/elections/searchElection',
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    data: jsonString,
	    success: function(response) {
	    	populateElectionList(response);
	    },
		error: function(response){
			alert("unable to connect");
		}
	});	
}



function populateElectionList(response){
/* str = "";
	if(response.customMessage == "SUCCESS")
	{
		_elctionList = response.electionList;
		if(_elctionList.length == 0)
		{
			str += "<tr><td colspan='5'> No Matches found! Please try again !</td></tr>";
		}else{
			for(i=0; i< _elctionList.length; i++){
				str += "<tr>" ;
				str += 	"<td><a onclick=getVoterDetail('"+voterList[i].votersId+"')>"+voterList[i].votersId+"</a></td>" ;
				str +=	"<td>"+ voterList[i].name+"</td><td>"+ voterList[i].constituency+"</td>" ;
				str += 	"<td>"+voterList[i].place+"</td><td>"+ voterList[i].lockOutFlag+"</td>" ;
				str += "</tr>" ;
			}
		}
	}else if(response.customMessage == "FAILED"){
		str += "<tr><td colspan='5'>"+ response.customMessage +"</td></tr>"; 
	}
	
*/	
}



function hideAll(){
	$('#pageElectionsHome').hide();
	$('#pageAddElectionStatesForm').hide();
	$('#pageElectionSearchSummary').hide();
	$('#pageElectionDetail').hide();
}

function showPage(pageId){
	hideAll();
	$(pageId).show();
}

function showPageHome(){
	hideAll();
	$('#pageElectionsHome').show();
	 	$('#contentAddElectionForm').hide();
	 		$('#contentSearchElectionForm').show();
}



