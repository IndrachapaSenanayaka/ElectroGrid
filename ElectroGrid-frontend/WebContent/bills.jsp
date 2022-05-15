<%@page import="com.ElectricityBill"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>CEB</title>
		
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<script src="Components/jquery-3.2.1.min.js"></script>
		<script src="Components/bills.js"></script>
	</head>
	<body>
		
		<div style="background-color:#EFECBE;">
			<div class="container">
				<div class="row">
					<div class="col-4 pt-5">
						<br>
						<div class="p-3 mb-2 bg-secondary text-white rounded">
							<form id="formBill" name="formBill">
								<strong>Meter Code:</strong>
								<div class="input-group mb-2">
			        				<div class="input-group-prepend">
							         	
							        </div>
							        <input id="meterCode" name="meterCode" type="text" class="form-control" placeholder="Enter Meter Code">
		      					</div>
								<br><strong> Month Started Units:</strong>
								<div class="input-group mb-2">
			        				<div class="input-group-prepend">
							         	
							        </div>
							        <input id="monthStartedUnitsAmount" name="monthStartedUnitsAmount" type="text" class="form-control" placeholder="Enter Month Started Units">
		      					</div>
								<br><strong> Month Ended Units:</strong>
								<div class="input-group mb-2">
			        				<div class="input-group-prepend">
							         	
							        </div>
							        <input id="monthEndedUnitsAmount" name="monthEndedUnitsAmount" type="text" class="form-control" placeholder="Enter Month Ended Units">
		      					</div>
								<br>
								<div class="text-center">
									<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary btn-block">
									<input type="hidden" id="hidBillIDSave" name="hidBillIDSave">
								</div>
							</form>
						</div>
						<div id="alertSuccess" class="alert alert-success"></div>
						<div id="alertError" class="alert alert-danger"></div>
					</div>
					<div class="col-8 pt-5 pl-5">
						<br>
						<div id="divBillsGrid">
							<%
								ElectricityBill billObj = new ElectricityBill();
								out.print(billObj.viewBills());
							%>
						</div>
					</div> 
				</div> 
				<br>
				
			</div>
		</div>	
	</body>
</html>