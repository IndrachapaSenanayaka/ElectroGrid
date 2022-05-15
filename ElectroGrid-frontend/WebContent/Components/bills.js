$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	{
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateBillForm();
	if (status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	// If valid------------------------
	var type = ($("#hidBillIDSave").val() == "") ? "POST" : "PUT";
	$.ajax(
	{
		url : "BillsAPI",
		type : type,
		data : $("#formBill").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onBillSaveComplete(response.responseText, status);
		}
	});
});

//onBillSaveComplete
function onBillSaveComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Electricity bill generated successfully.");
			$("#alertSuccess").show();
			$("#divBillsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error")
	{
		$("#alertError").text("Error while generating the bill.");
		$("#alertError").show();
	} else
	{
		$("#alertError").text("Unknown error while generating the bill..");
		$("#alertError").show();
	}
	$("#hidBillIDSave").val("");
	$("#formBill")[0].reset();
	$("#meterCode").removeAttr("disabled");
}


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidBillIDSave").val($(this).data("billid"));
	$("#meterCode").val($(this).data("metercode"));
	$("#meterCode").attr("disabled", "disabled");
	$("#monthStartedUnitsAmount").val($(this).data("startedunits"));
	$("#monthEndedUnitsAmount").val($(this).data("endedunits"));
});

//DELETE===========================================================
$(document).on("click", ".btnRemove", function(event)
{
	$.ajax(
	{
		url : "BillsAPI",
		type : "DELETE",
		data : "billID=" + $(this).data("billid"),
		dataType : "text",
		complete : function(response, status)
		{
			onBillDeleteComplete(response.responseText, status);
		}
	});
});

//onBillDeleteComplete
function onBillDeleteComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Electricity bill Deleted successfully.");
			$("#alertSuccess").show();
			$("#divBillsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error")
	{
		$("#alertError").text("Error while deleting the bill.");
		$("#alertError").show();
	} else
	{
		$("#alertError").text("Unknown error while deleting the bill..");
		$("#alertError").show();
	}
}



//VIEW===========================================================
$(document).on("click", ".btnView", function(event)
{
	$.ajax(
	{
		url : "BillAPI",
		type : "POST",
		data : {"billID" : $(this).data("billid"), "accountNo" : $(this).data("accountno")},
		dataType : "text",
		complete : function(response, status)
		{
			onBillViewComplete(response.responseText, status);
		}
	});
});

//onBillViewComplete
function onBillViewComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{	
			$("#divBillGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text("resultSet.data");
			$("#alertError").show();
		}
	} else if (status == "error")
	{
		$("#alertError").text("Error while viewing the bill.");
		$("#alertError").show();
	} else
	{
		$("#alertError").text("Unknown error while viewing the bill..");
		$("#alertError").show();
	}
}


// CLIENT-MODEL================================================================
function validateBillForm()
{
	// METER CODE
	if ($("#meterCode").val().trim() == "")
	{
		return "Insert Meter ID.";
	}
	
	// Month Started Units Amount
	if ($("#monthStartedUnitsAmount").val().trim() == "")
	{
		return "Insert Month Started Units.";
	}
	
	// Month Ended Units Amount-------------------------------
	if ($("#monthEndedUnitsAmount").val().trim() == "")
	{
		return "Insert Month Ended Units.";
	}
	
	// is numerical value
	var tmpStartedUnits = $("#monthStartedUnitsAmount").val().trim();
	
	if (!$.isNumeric(tmpStartedUnits))
	{
		return "Insert a numerical value for Month Started Units.";
	}
	
	// is numerical value
	var tmpEndedUnits = $("#monthEndedUnitsAmount").val().trim();
	
	if (!$.isNumeric(tmpEndedUnits))
	{
		return "Insert a numerical value for Month Ended Units.";
	}
	
	
	return true;
}