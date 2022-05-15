package com;
import java.sql.*;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Nimesha
 *
 */

public class Consumer {
	private Connection connect()
	{
		Connection con = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electro_grid", "root", "");
		}
		catch (Exception e)
		{e.printStackTrace();}
		
		return con;
	}


	//get one Consumer data
	public HashMap<String, String> getConsumer(String accountNum)
	{
		String output = "";
		HashMap<String, String> mete =null;
		
		try
		{
				Connection con = connect();
				
				if (con == null)
				{return null; }
	
				
				// create a prepared statement
				String query = "select * from consumer WHERE accountNo=?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(accountNum));
				
				// execute the statement
				ResultSet rs = preparedStmt.executeQuery();
				
				// create hashMap object
				mete = new HashMap<String, String>();
				
				// iterate through the rows in the result set
				while (rs.next())
				{
					
					String consumerNo  = Integer.toString(rs.getInt("consumerNo"));
					String accountNo = Integer.toString(rs.getInt("accountNo"));
					String firstName = rs.getString("firstName");
					String lastName = rs.getString("lastName");
					String gender = rs.getString("gender");
					String phoneNumber = rs.getString("phoneNumber");
					String email = rs.getString("email");
					String password = rs.getString("password");
					String province = rs.getString("province");
					String city  = rs.getString("city");
					String street = rs.getString("street");
					String postalCode = Double.toString(rs.getDouble("postalCode"));
					
					mete.put("consumerNo", consumerNo);
					mete.put("accountNo", accountNo);
					mete.put("firstName", firstName);
					mete.put("lastName", lastName);
					mete.put("gender", gender);	
					mete.put("phoneNumber", phoneNumber);	
					mete.put("email", email);	
					mete.put("password", password);	
					mete.put("province", province);	
					mete.put("city", city);	
					mete.put("street", street);	
					mete.put("postalCode", postalCode);	
			
				}
				
				con.close();
				
				//convert hashmap to json using gson
				Gson gson = new GsonBuilder().disableHtmlEscaping().create();
				String jsonString = gson.toJson(mete);
				
				// Complete the html table
				output = jsonString;
		}
		catch (Exception e)
		{
			output = "Error while reading the consumer.";
			System.err.println(e.getMessage());
		}
		
		return mete;
	}

}