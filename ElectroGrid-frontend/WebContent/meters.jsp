<%@page import="com.Meter"%>
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
		<script src="Components/meters.js"></script>
	</head>
	<body>
		<center class="pt-4">
			<img src="https://firebasestorage.googleapis.com/v0/b/hms-medilanka.appspot.com/o/ceb.png?alt=media&token=b7442ec4-e6e5-454d-98dc-98c1b9985d84" style="width: 400px;">
			<div class="p-3 mb-2 bg-danger text-white"><h1>Automated Meter Management</h1></div>
		</center>
		<div style="background-color:#EFECBE;">
			<div class="container">
				<div class="row">
					<div class="col-4  pt-5">
						<br>
						<div class="p-3 mb-2 bg-secondary text-white rounded">
							<form id="formMeter" name="formMeter">
								<!-- Meter code input -->
								<strong>Meter code:</strong>
								<div class="input-group mb-2">
			        				<div class="input-group-prepend">
							         	<div class="input-group-text"><i class="fa fa-dashboard"></i></div>
							        </div>
							        <input id="meterCode" name="meterCode" type="text" class="form-control" placeholder="Enter Meter Code">
		      					</div>
		      					<!-- Premises ID input -->
								<br><strong>Premises ID:</strong>
								<div class="input-group mb-2">
			        				<div class="input-group-prepend">
							         	<div class="input-group-text"><i class="fa fa-map-marker"></i></div>
							        </div>
							        <input id="premisesID" name="premisesID" type="text" class="form-control" placeholder="Enter Premises ID">
		      					</div>
		      					<!-- Electricity AccountNo input -->
								<br><strong>Electricity AccountNo:</strong>
								<div class="input-group mb-2">
			        				<div class="input-group-prepend">
							         	<div class="input-group-text"><i class="fa fa-user-circle-o"></i></div>
							        </div>
							        <input id="electricityAccountNo" name="electricityAccountNo" type="text" class="form-control" placeholder="Enter Electricity AccountNo">
		      					</div>
		      					<!-- Manufacture Date input -->
								<br><strong>Manufacture Date:</strong>
								<div class="input-group mb-2">
			        				<div class="input-group-prepend">
							         	<div class="input-group-text"><i class="fa fa-calendar"></i></div>
							        </div>
							        <input id="manufactureDate" name="manufactureDate" type="date" class="form-control">
		      					</div>
								<br>
								<!-- Meter details save button -->
								<div class="text-center">
									<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary btn-block">
									<input type="hidden" id="hidMeterIDSave" name="hidMeterIDSave">
								</div>
							</form>
						</div>
						<div id="alertSuccess" class="alert alert-success"></div>
						<div id="alertError" class="alert alert-danger"></div>			
					</div> 
					<!-- Meter details list viewer -->
					<div class="col-8 pt-5 pl-5">
						<br>
						<div id="divMetersGrid">
							<%
								Meter meterObj = new Meter();
								out.print(meterObj.readMeters());
							%>		
						</div>
					</div>		
				</div> 
			</div>
		</div>	
	</body>
</html>