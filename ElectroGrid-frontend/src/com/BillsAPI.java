package com;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BillsAPI
 */
@WebServlet("/BillsAPI")
public class BillsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BillsAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			//create Meter object 
			Meter meterObj = new Meter();
			
			//caling the getMeterr method for get the meter owner's details
			HashMap<String, String> meter = meterObj.getMeter(request.getParameter("meterCode"));
			
			//get electricityAccountNo
			String electricityAccount = meter.get("electricityAccountNo");
			
			//create ElectricityBill object 
			ElectricityBill billObj = new ElectricityBill();
			
			//caling the generateElectricityBill method
			String output = billObj.generateElectricityBill(request.getParameter("meterCode"),
					request.getParameter("monthStartedUnitsAmount"),
					request.getParameter("monthEndedUnitsAmount"),
					electricityAccount);
			
			response.getWriter().write(output);
							
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request);
		
		//create ElectricityBill object
		ElectricityBill billObj = new ElectricityBill();

		//caling the updateBill method
		String output = billObj.updateBill(paras.get("hidBillIDSave").toString(),
							paras.get("monthStartedUnitsAmount").toString(),
							paras.get("monthEndedUnitsAmount").toString());
		
		response.getWriter().write(output);
		
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
	}

	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request)
	{
		Map<String, String> map = new HashMap<String, String>();
		try
		{
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ?
								scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			
			String[] params = queryString.split("&");
			for (String param : params)
			{
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		}
		catch (Exception e)
		{
		}
		return map;
	}	
	
}
