package com;

import java.sql.*;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Meter {
	
	Connection con = null;
	
	//Meter registration(insert meter details)
	public String insertMeter(String meterCode, String premisesID, String electricityAccountNo, String manufactureDate)
	{
		String output = "";
		
		try
		{
			//open database connection
			con = DBconfig.getConnection();
			
			
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			
			// create a prepared statement
			String query = " insert into Meter(`meterID`,`meterCode`,`premisesID`,`electricityAccountNo`,`manufactureDate`)"
							+ " values (?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, meterCode);
			preparedStmt.setString(3, premisesID);
			preparedStmt.setInt(4, Integer.parseInt(electricityAccountNo));
			preparedStmt.setString(5, manufactureDate);
			
			// execute the statement
			preparedStmt.execute();
			//close database connection
			con.close();
			
			String newMeters = readMeters();
			output = "{\"status\":\"success\", \"data\": \"" + newMeters + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the meter.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
//	View all meter details as an HTML table
	public String readMeters()
	{
		String output = "";
		
		try
		{
				//open database connection
				con = DBconfig.getConnection();
				
				if (con == null)
				{return "Error while connecting to the database for reading."; }
	
				// Prepare the html table to be displayed
				output = "<table class='table table-hover bg-white'><thead class='table-active'><tr><th>Meter Code</th><th>Premises ID</th>" +
						"<th>Electricity Account No</th>" +
						"<th>Manufacture Date</th>" +
						"<th>Update</th><th>Remove</th></tr></thead><tbody>";
				
				// create a prepared statement
				String query = "select * from Meter";
				
				Statement stmt = con.createStatement();
				
				// execute the statement
				ResultSet rs = stmt.executeQuery(query);
				
				// iterate through the rows in the result set
				while (rs.next())
				{
					String meterID = Integer.toString(rs.getInt("meterID"));
					String meterCode = rs.getString("meterCode");
					String premisesID = rs.getString("premisesID");
					String electricityAccountNo = Integer.toString(rs.getInt("electricityAccountNo"));
					String manufactureDate = rs.getString("manufactureDate");
					
					// Add into the html table
					output += "<tr><td>" + meterCode + "</td>";
					output += "<td>" + premisesID + "</td>";
					output += "<td>" + electricityAccountNo + "</td>";
					output += "<td>" + manufactureDate + "</td>";
	
					// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-meterid='" + meterID + "'></td>"
					+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-meterid='" + meterID + "'>" + "</td></tr>";
				}
				
				//close database connection
				con.close();
				
				// Complete the html table
				output += "</tbody></table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the meters.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
//	View one meter details in the HTML template	
	public HashMap<String, String> getMeter(String Code)
	{
		String output = "";
		HashMap<String, String> mete = null;
		
		try
		{		//open database connection
				con = DBconfig.getConnection();
				
				if (con == null)
				{return null; }
	
				
				// create a prepared statement
				String query = "select * from Meter WHERE meterCode=?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setString(1, Code);
				
				// execute the statement
				ResultSet rs = preparedStmt.executeQuery();
				
				// create hashMap object
				mete = new HashMap<String, String>();
				
				// iterate through the rows in the result set
				while (rs.next())
				{
					String meterID = Integer.toString(rs.getInt("meterID"));
					String meterCode = rs.getString("meterCode");
					String premisesID = rs.getString("premisesID");
					String electricityAccountNo = Integer.toString(rs.getInt("electricityAccountNo"));
					String manufactureDate = rs.getString("manufactureDate");
					
					mete.put("meterID", meterID);
					mete.put("meterCode", meterCode);
					mete.put("premisesID", premisesID);
					mete.put("electricityAccountNo", electricityAccountNo);
					mete.put("manufactureDate", manufactureDate);					
			
				}
				
				//close database connection
				con.close();
				
				//convert hashmap to json using gson
				Gson gson = new GsonBuilder().disableHtmlEscaping().create();
				String jsonString = gson.toJson(mete);
				
				// Complete the html table
				output = jsonString;
		}
		catch (Exception e)
		{
			output = "Error while reading the meter.";
			System.err.println(e.getMessage());
		}
		
		return mete;
	}
	
//	Update the meter details
	public String updateMeter(String meterID, String meterCode, String premisesID, String electricityAccountNo, String manufactureDate)
	{
		String output = "";
		
		try
		{	
			//open database connection
			con = DBconfig.getConnection();
			
			if (con == null)
			{return "Error while connecting to the database for updating."; }
			
			// create a prepared statement
			String query = "UPDATE Meter SET meterCode=?,premisesID=?,electricityAccountNo=?,manufactureDate=? WHERE meterID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, meterCode);
			preparedStmt.setString(2, premisesID);
			preparedStmt.setInt(3, Integer.parseInt(electricityAccountNo));
			preparedStmt.setString(4, manufactureDate);
			preparedStmt.setInt(5, Integer.parseInt(meterID));
			
			// execute the statement
			preparedStmt.execute();
			//close database connection
			con.close();
			
			String newMeters = readMeters();
			output = "{\"status\":\"success\", \"data\": \"" + newMeters + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while updating the meter details.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
//	Delete the meter details
	public String deleteMeter(String meterID)
	{
		String output = "";
		
		try
		{
			//open database connection
			con = DBconfig.getConnection();;
			
			if (con == null)
			{return "Error while connecting to the database for deleting."; }
			
			// create a prepared statement
			String query = "delete from Meter where meterID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(meterID));
			
			// execute the statement
			preparedStmt.execute();
			//close database connection
			con.close();
			
			String newMeters = readMeters();
			output = "{\"status\":\"success\", \"data\": \"" + newMeters + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the meter.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}

}
