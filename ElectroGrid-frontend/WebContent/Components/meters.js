$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	{
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});








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