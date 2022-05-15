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
	var status = validateMeterForm();
	if (status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	// If valid------------------------
	var type = ($("#hidMeterIDSave").val() == "") ? "POST" : "PUT";
	$.ajax(
	{
		url : "MetersAPI",
		type : type,
		data : $("#formMeter").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onMeterSaveComplete(response.responseText, status);
		}
	});
});

//onMeterSaveComplete
function onMeterSaveComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Meter registered successfully.");
			$("#alertSuccess").show();
			$("#divMetersGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error")
	{
		$("#alertError").text("Error while registering the meter.");
		$("#alertError").show();
	} else
	{
		$("#alertError").text("Unknown error while registering the meter..");
		$("#alertError").show();
	}
	$("#hidMeterIDSave").val("");
	$("#formMeter")[0].reset();
}


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidMeterIDSave").val($(this).data("meterid"));
	$("#meterCode").val($(this).closest("tr").find('td:eq(0)').text());
	$("#premisesID").val($(this).closest("tr").find('td:eq(1)').text());
	$("#electricityAccountNo").val($(this).closest("tr").find('td:eq(2)').text());
	$("#manufactureDate").val($(this).closest("tr").find('td:eq(3)').text());
});

//DELETE===========================================================
$(document).on("click", ".btnRemove", function(event)
{
	$.ajax(
	{
		url : "MetersAPI",
		type : "DELETE",
		data : "meterID=" + $(this).data("meterid"),
		dataType : "text",
		complete : function(response, status)
		{
			onMeterDeleteComplete(response.responseText, status);
		}
	});
});

//onMeterDeleteComplete
function onMeterDeleteComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Registered meter deleted successfully.");
			$("#alertSuccess").show();
			$("#divMetersGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error")
	{
		$("#alertError").text("Error while deleting the meter.");
		$("#alertError").show();
	} else
	{
		$("#alertError").text("Unknown error while deleting the meter..");
		$("#alertError").show();
	}
}

// CLIENT-MODEL================================================================
function validateMeterForm()
{
	// METER CODE
	if ($("#meterCode").val().trim() == "")
	{
		return "Insert Meter Code.";
	}
	
	// Premises ID
	if ($("#premisesID").val().trim() == "")
	{
		return "Insert Premises ID.";
	}
	
	// Electricity AccountNo-------------------------------
	if ($("#electricityAccountNo").val().trim() == "")
	{
		return "Insert Electricity AccountNo.";
	}
	
	// Manufactur eDate-------------------------------
	if ($("#manufactureDate").val().trim() == "")
	{
		return "Insert ManufactureDate.";
	}
	
	// is numerical value
	var tmpElectricityAccountNo = $("#electricityAccountNo").val().trim();
	
	if (!$.isNumeric(tmpElectricityAccountNo))
	{
		return "Insert a numerical value for Electricity AccountNo.";
	}
	
	
	return true;
}