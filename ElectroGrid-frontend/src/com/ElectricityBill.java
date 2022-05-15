package com;

import java.sql.*;

public class ElectricityBill {

	Connection con = null;
	
//	Aouto genatete the Electricity Bill
	public String generateElectricityBill(String meterCode, String monthStartedUnitsAmount, String monthEndedUnitsAmount, String electricityAccount) {
		
		String output = "";
		
		try
		{
			//open database connection
			con = DBconfig.getConnection();
			
			if (con == null)
			{
				return "Error while connecting to the database for inserting."; 
			}
			
			//Generate bill id
			String billID = CommonDButil.IDGenerator();
			//get the current date and time
			String issuedDate = CommonDButil.DateTime();
			
			//get the passing data from API
			int endedUnits =Integer.parseInt(monthEndedUnitsAmount);
			int startedUnits = Integer.parseInt(monthStartedUnitsAmount);
			int unitsConsumed = endedUnits - startedUnits;
			
			//get charge For Units consumed
			double chargeForUnitsconsumed = BillDButil.ChargeCalculator(unitsConsumed);
			
			//get fixed Charge
			double fixedCharge = BillDButil.FixedCharge();
			
			//calculate total Cost Of Supply
			double totalCostOfSupply = chargeForUnitsconsumed + fixedCharge;
			
			//get current month
			String month = CommonDButil.Month();
			
			int electricityAccountNo =Integer.parseInt(electricityAccount);
			
			
			// create a prepared statement
			String query = " insert into Bill(`billID`,`electricityAccountNo`,`meterCode`,`month`,`issuedDate`,`unitsConsumed`,`chargeForUnitsconsumed`,`fixedCharge`,`totalCostOfSupply`,`startedUnits`,`endedUnits`,`status`)"
							+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, billID);
			preparedStmt.setInt(2, electricityAccountNo);
			preparedStmt.setString(3, meterCode);
			preparedStmt.setString(4, month);
			preparedStmt.setString(5, issuedDate);
			preparedStmt.setInt(6, unitsConsumed);
			preparedStmt.setDouble(7, chargeForUnitsconsumed);
			preparedStmt.setDouble(8, fixedCharge);
			preparedStmt.setDouble(9, totalCostOfSupply);
			preparedStmt.setInt(10, startedUnits);
			preparedStmt.setInt(11, endedUnits);
			preparedStmt.setString(12, "Not Paid");
			
			// execute the statement
			preparedStmt.execute();
			//close database connection
			con.close();
			
			String newBills = viewBills();
			output = "{\"status\":\"success\", \"data\": \"" + newBills + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while generating the bill.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
//	View all bill details as an HTML table
	public String viewBills()
	{
		String output = "";
		
		try
		{
				//open database connection
				con = DBconfig.getConnection();
				
				if (con == null)
				{
					return "Error while connecting to the database for reading."; 
				}
	
				// Prepare the html table to be displayed
				output = "<table class='table table-hover bg-white'><thead class='table-active'><tr><th scope='col'>Electricity Account&nbsp;No</th>" +
						"<th scope='col'>Month&nbsp;&nbsp;&nbsp;&nbsp;.</th>" +
						"<th scope='col'>Units Consumed</th>" +
						"<th scope='col'>Total&nbsp;Cost Of Supply Rs/=</th>" + 
						"<th scope='col'>Status&nbsp;&nbsp;&nbsp;&nbsp;.</th>" +
						"<th scope='col'>Update</th><th scope='col'>View</th><th scope='col'>Remove</th></tr></thead><tbody>";
				
				// create a prepared statement
				String query = "select * from Bill";
				Statement stmt = con.createStatement();
				
				// execute the statement
				ResultSet rs = stmt.executeQuery(query);
				
				// iterate through the rows in the result set
				while (rs.next())
				{
					String billID = rs.getString("billID");
					String electricityAccountNo = rs.getString("electricityAccountNo");
					String month = rs.getString("month");
					String unitsConsumed = Integer.toString(rs.getInt("unitsConsumed"));
					String totalCostOfSupply = Double.toString(rs.getDouble("totalCostOfSupply"));
					String status = rs.getString("status");
					String startedUnits = Integer.toString(rs.getInt("startedUnits"));
					String endedUnits = Integer.toString(rs.getInt("endedUnits"));
					String 	meterCode = rs.getString("meterCode");
					
					// Add into the html table
					output += "<tr><td>" + electricityAccountNo + "</td>";
					output += "<td>" + month + "</td>";
					output += "<td>" + unitsConsumed + "</td>";
					output += "<td>" + totalCostOfSupply + "</td>";
					output += "<td>" + status + "</td>";
	
					// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' "
							+ "data-billid='" + billID + "' "
							+ "data-metercode='" + meterCode + "' "
							+ "data-startedunits='" + startedUnits + "' "
							+ "data-endedunits='" + endedUnits + "'></td>"
							+ "<td><input name='btnView' type='button' value='View' class='btnView btn btn-primary' data-billid='" + billID + "' data-accountno='" + electricityAccountNo + "'></td>"
							+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-billid='" + billID + "'></td></tr>";
				}
				
				//close database connection
				con.close();
				
				// Complete the html table
				output += "</tbody></table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the bills.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
//	View one bill details in the HTML template
	public String viewBill(String billID, String city, String firstName)
	{
		String bill = "";
		String output = "";
		
		try
		{
				//open database connection
				con = DBconfig.getConnection();
				
				if (con == null)
				{return "Error while connecting to the database for reading."; }
	
				
				// create a prepared statement
				String query = "select * from Bill WHERE billID=?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setString(1, billID);
				
				
				// execute the statement
				ResultSet rs = preparedStmt.executeQuery();
				
				
				// iterate through the rows in the result set
				while (rs.next())
				{
					String electricityAccountNo = rs.getString("electricityAccountNo");
					String month = rs.getString("month");
					String issuedDate = rs.getString("issuedDate");
					String unitsConsumed = Integer.toString(rs.getInt("unitsConsumed"));
					String chargeForUnitsconsumed = Double.toString(rs.getDouble("chargeForUnitsconsumed"));
					String fixedCharg = Double.toString(rs.getDouble("fixedCharge"));
					String totalCostOfSupply = Double.toString(rs.getDouble("totalCostOfSupply"));
					String startedUnits = Integer.toString(rs.getInt("startedUnits"));
					String endedUnits = Integer.toString(rs.getInt("endedUnits"));
					String status = rs.getString("status");
					
					bill = BillDButil.BillDesign(month, issuedDate, city, firstName, electricityAccountNo, startedUnits, endedUnits, unitsConsumed, chargeForUnitsconsumed, fixedCharg, totalCostOfSupply, status);
					                       
				}
				//close database connection
				con.close();
				String newBills = viewBills();
				output = "{\"status\":\"success\", \"data\": \"" + bill + "\"}";
				
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while reading the bill.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
//	Update the bill
	public String updateBill(String billID, String monthStartedUnitsAmount, String monthEndedUnitsAmount)
	{
		String output = "";
		
		try
		{
			//open database connection
			con = DBconfig.getConnection();
			
			if (con == null)
			{
				return "Error while connecting to the database for updating."; 
			}
			
			//get the passing data from API
			int endedUnits =Integer.parseInt(monthEndedUnitsAmount);
			int startedUnits = Integer.parseInt(monthStartedUnitsAmount);
			int unitsConsumed = endedUnits - startedUnits;
			
			//get charge For Units consumed
			double chargeForUnitsconsumed = BillDButil.ChargeCalculator(unitsConsumed);
			
			//get fixed Charge
			double fixedCharge = BillDButil.FixedCharge();
			
			//calculate total Cost Of Supply
			double totalCostOfSupply = chargeForUnitsconsumed + fixedCharge;
			
			
			// create a prepared statement
			String query = "UPDATE Bill SET unitsConsumed=?,chargeForUnitsconsumed=?,totalCostOfSupply=?,startedUnits=?,endedUnits=? WHERE billID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, unitsConsumed);
			preparedStmt.setDouble(2, chargeForUnitsconsumed);
			preparedStmt.setDouble(3, totalCostOfSupply);
			preparedStmt.setInt(4, startedUnits);
			preparedStmt.setInt(5, endedUnits);
			preparedStmt.setString(6, billID);
			
			// execute the statement
			preparedStmt.execute();
			//close database connection
			con.close();
			
			String newBills = viewBills();
			output = "{\"status\":\"success\", \"data\": \"" + newBills + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while updating the bill.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
//	Delete the bill
	public String deleteBill(String billID)
	{
		String output = "";
		
		try
		{
			//open database connection
			con = DBconfig.getConnection();;
			
			if (con == null)
			{return "Error while connecting to the database for deleting."; }
			
			// create a prepared statement
			String query = "delete from Bill where billID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, billID);
			
			// execute the statement
			preparedStmt.execute();
			//close database connection
			con.close();
			
			String newBills = viewBills();
			output = "{\"status\":\"success\", \"data\": \"" + newBills + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the bill.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
}
