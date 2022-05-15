<%@page import="com.ElectricityBill"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>CEB</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<script src="Components/jquery-3.2.1.min.js"></script>
		<script src="Components/bills.js"></script>
	</head>
	<body>
		<div class="text-center pt-4">
			<img src="https://firebasestorage.googleapis.com/v0/b/hms-medilanka.appspot.com/o/ceb.png?alt=media&token=b7442ec4-e6e5-454d-98dc-98c1b9985d84" style="width: 400px;">
			<div class="p-3 mb-2 bg-danger text-white"><h1>Electricity Bill Management</h1></div>
		</div>
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
							         	<div class="input-group-text"><i class="fa fa-dashboard"></i></div>
							        </div>
							        <input id="meterCode" name="meterCode" type="text" class="form-control" placeholder="Enter Meter Code">
		      					</div>
								<br><strong> Month Started Units:</strong>
								<div class="input-group mb-2">
			        				<div class="input-group-prepend">
							         	<div class="input-group-text"><i class="fa fa-bolt"></i></div>
							        </div>
							        <input id="monthStartedUnitsAmount" name="monthStartedUnitsAmount" type="text" class="form-control" placeholder="Enter Month Started Units">
		      					</div>
								<br><strong> Month Ended Units:</strong>
								<div class="input-group mb-2">
			        				<div class="input-group-prepend">
							         	<div class="input-group-text"><i class="fa fa-bolt"></i></div>
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
				<div class="row">
					<div class="col-12 pt-5 p-3 mb-2 bg-light text-dark rounded" id="divBillGrid"></div>
				</div>
			</div>
		</div>	
	</body>
</html>