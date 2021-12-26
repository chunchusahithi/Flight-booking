package com.flyhigh;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
/**
 * Servlet implementation class StoreInfo
 */
public class StoreInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public StoreInfo() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String name = request.getParameter("name");
		String dob = request.getParameter("dob");
		String email = request.getParameter("email");
		String street = request.getParameter("street");
		String state = request.getParameter("state");
		String country = request.getParameter("country");
		int pincode = Integer.parseInt(request.getParameter("pincode"));
		
		HttpSession session = request.getSession();
		String size = (String) session.getAttribute("size");
		DisplayOp dispop = (DisplayOp) session.getAttribute("final");
		int flightId = dispop.flightId;
		int hours = dispop.hours;
		int minutes = dispop.mins;
		String date = (String) session.getAttribute("date");
		int nop = Integer.parseInt(request.getParameter("nop"));
		String seat = request.getParameter("seat");
		
		Double costf =dispop.costf * nop;
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmmssms");
		LocalTime localTime = LocalTime.now();
		int ID = Integer.parseInt(dtf.format(localTime));
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airlinedb", "root", "root");
			
			PreparedStatement people = con.prepareStatement("insert into people values(?,?,?,?);");
			PreparedStatement passengers = con.prepareStatement("insert into passengers values(?,?);");
			PreparedStatement ticket = con.prepareStatement("insert into ticket values(?,?,?,?,?,?,?,?,?);");
			PreparedStatement address = con.prepareStatement("insert into address values(?,?,?,?,?);");
			
			people.setInt(1, ID);
			people.setString(2, name);
			people.setString(3,dob);
			people.setString(4, email);
			people.executeUpdate();
			people.close();
			
			passengers.setInt(1, nop);
			passengers.setInt(2, ID);
			passengers.executeUpdate();
			passengers.close();
			
			localTime = LocalTime.now();
			int ticketID = Integer.parseInt(dtf.format(localTime))+2;
			
			ticket.setInt(1, ticketID);
			ticket.setDouble(2, costf);
			ticket.setString(3, seat);
			ticket.setInt(4, flightId);
			ticket.setString(5, size);
			ticket.setInt(6, ID);
			ticket.setInt(7, hours);
			ticket.setInt(8, minutes);
			ticket.setString(9,date);
			ticket.executeUpdate();
			ticket.close();
			
			address.setInt(1, ID);
			address.setString(2, country);
			address.setString(3, state);
			address.setString(4, street);
			address.setInt(5, pincode);
			address.executeUpdate();
			address.close();
			
			con.close();
			session.setAttribute("ticketId", ticketID);
			RequestDispatcher rd = request.getRequestDispatcher("RetrieveInfo");
			rd.forward(request, response);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
