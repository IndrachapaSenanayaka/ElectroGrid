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
	
	
	
	
	
	
	
	
}
