package com;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonDButil {
	
	static Connection con = null;
	
	//Id Number generator
	public static String IDGenerator(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		Date date = new Date();
		String dateTime = sdf.format(date);
		
		String ID = dateTime;
		
		return ID;
	}
	
	//Date and Time capture
	public static String DateTime(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		Date date = new Date();
		String dateTime = sdf.format(date);
		
		String ID = dateTime;
		
		return ID;
	}
	
	//Month capture
	public static String Month(){
		
		//Get month and year
		SimpleDateFormat sdf = new SimpleDateFormat("MMM-yyyy");
		Date date = new Date();
		String month = sdf.format(date);
		
		return month;
	}
	
	
	//passing the Fixed Charge value
	public static Double FixedCharge(){
		
		Double Charge = 480.0;
		
		return Charge;
		
	}

	
}
