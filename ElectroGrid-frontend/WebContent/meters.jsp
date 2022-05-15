<%@page import="com.Meter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>CEB</title>
		
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<script src="Components/jquery-3.2.1.min.js"></script>
		<script src="Components/meters.js"></script>
	</head>
	<body>
		
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
							         	
							        </div>
							        <input id="meterCode" name="meterCode" type="text" class="form-control" placeholder="Enter Meter Code">
		      					</div>
		      					<!-- Premises ID input -->
								<br><strong>Premises ID:</strong>
								<div class="input-group mb-2">
			        				<div class="input-group-prepend">
							         	
							        </div>
							        <input id="premisesID" name="premisesID" type="text" class="form-control" placeholder="Enter Premises ID">
		      					</div>
		      					<!-- Electricity AccountNo input -->
								<br><strong>Electricity AccountNo:</strong>
								<div class="input-group mb-2">
			        				<div class="input-group-prepend">
							         	
							        </div>
							        <input id="electricityAccountNo" name="electricityAccountNo" type="text" class="form-control" placeholder="Enter Electricity AccountNo">
		      					</div>
		      					<!-- Manufacture Date input -->
								<br><strong>Manufacture Date:</strong>
								<div class="input-group mb-2">
			        				<div class="input-group-prepend">
							         	
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