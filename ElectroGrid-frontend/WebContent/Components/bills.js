$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	{
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});


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