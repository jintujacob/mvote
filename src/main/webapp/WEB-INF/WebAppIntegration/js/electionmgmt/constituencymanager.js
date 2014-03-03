
_constList= "";

host = "http://localhost:8080/mvote";

$(document).ready(function(){
	showPageHome();
	
	
	$('#goto_search_const').click(function(){
		 //$('#contentAddConstsForm').hide();
		 $('#contentSearchConstsForm').show();
	});
	 
	$('#btn_submit_search').click(function()
	{
		searchkey = $('#in_find_const').val();
		searchForConst(searchkey);
	});
	
	$('#btn_add_const').click(function(){
		showPage('#pageConstsSummary');
	});
	 
	$('#pageConstsSummary a').click(function(){
		getConstDetail();
		showPage('#pageConstsDetail');
	});
	 
});

function searchForConst(key)
{
	obj = {	"constName": key  };
	jsonString =JSON.stringify(obj);
	
	$.ajax({
	    type: "POST",
	    url: host + '/consts/searchConsts',
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    data: jsonString,
	    success: function(response) {
	    	manageConstsSearchResults(response);
	    },
		error: function(response){
			alert("Connection Error. Network failure or Server unavailable");
		}
	});	

}


function manageConstsSearchResults(response)
{
	str = "";
	if(response.customMessage == "SUCCESS")
	{
		_constList = response.constsList;
		if(_constList.length == 0)
		{
			str += "<tr><td colspan='3'> No Matches found! Please try again !</td></tr>";
		}else{
			for(i=0; i< _constList.length; i++){
				str += "<tr>" ;
				str += 	"<td><a onclick=getConstDetail("+_constList[i].constId+")>"+_constList[i].constName+"</a></td>" ;
				str +=	"<td>"+ _constList[i].constState+"</td>" ;
				str += "</tr>" ;
			}
		}
	}else{
		str += "<tr><td colspan='3'>"+ response.customMessage +"</td></tr>"; 
	}
	
	showPage('#pageConstsSummary');
	$("#tbl_search_summary tbody").html(str);
}


function getConstDetail(constId){
	//alert(constId);

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
	 	//$('#contentAddConstsForm').hide();
	 		$('#contentSearchConstsForm').show();
}



