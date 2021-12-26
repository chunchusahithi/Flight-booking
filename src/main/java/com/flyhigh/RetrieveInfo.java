package com.flyhigh;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class RetrieveInfo
 */
public class RetrieveInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public RetrieveInfo() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int ticketId;
		String value = request.getParameter("ticketId");
		if(value == null) {
			HttpSession session = request.getSession();
			ticketId = (int) session.getAttribute("ticketId");
		}
		else{
			ticketId= Integer.parseInt(value);
		}
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airlinedb", "root", "root");
		
			PreparedStatement ticket = con.prepareStatement("select * from ticket where idticket=?;");
			PreparedStatement routes = con.prepareStatement("SELECT airline,`source airport`,`destination airport`,substr(equipment,1,3) as equipment FROM airlinedb.routes where flightId=?;");
			PreparedStatement airnames = con.prepareStatement("select `Airline Name` from airlines_names where Code=?;");
			PreparedStatement airloc = con.prepareStatement("select Name,`Country Name` from airport_locations where Code=?;");
			PreparedStatement people = con.prepareStatement("select name,email from people where idpeople=?;");
			PreparedStatement passengers = con.prepareStatement("select noOfPassengers from passengers where idpassengers=?;");
			PreparedStatement equipment = con.prepareStatement("SELECT Aircraft from equipment where `IATA Code`=?;");

			ticket.setInt(1, ticketId);
			ResultSet rs = ticket.executeQuery();
			rs.next();
			Double cost = rs.getDouble("cost");
			String seat = rs.getString("seat");
			int flightId = rs.getInt("flightId");
			String size = rs.getString("class");
			int peopleId = rs.getInt("idpeople");
			int hours = rs.getInt("hours");
			int minutes = rs.getInt("minutes");
			String date = rs.getString("date");

			routes.setInt(1, flightId);
			rs = routes.executeQuery();
			rs.next();
			String airline = rs.getString("airline");
			String srcair = rs.getString("source airport");
			String destair = rs.getString("destination airport");
			String eq = rs.getString("equipment");

			airnames.setString(1,airline);
			rs = airnames.executeQuery();
			rs.next();
			String airlinename = rs.getString("Airline Name");

			airloc.setString(1, srcair);
			rs = airloc.executeQuery();
			rs.next();
			String sourceairport = rs.getString("Name");
			String sourcecountry = rs.getString("Country Name");


			airloc.setString(1, destair);
			rs = airloc.executeQuery();
			rs.next();
			String destairport = rs.getString("Name");
			String destcountry = rs.getString("Country Name");


			people.setInt(1, peopleId);
			rs = people.executeQuery();
			rs.next();
			String passengername = rs.getString("name");
			String email = rs.getString("email");

			passengers.setInt(1, peopleId);
			rs = passengers.executeQuery();
			rs.next();
			int nop = rs.getInt("noOfPassengers");

			equipment.setString(1, eq);
			rs = equipment.executeQuery();
			rs.next();
			String aircraft = rs.getString("Aircraft");
			
	        TicketInfo nticket = new TicketInfo(ticketId,passengername, email, nop,flightId, airlinename,sourceairport,sourcecountry,destairport,destcountry,aircraft, cost, size,seat,hours,minutes,date,srcair,destair);
			
			request.setAttribute("nticket", nticket);
			
			RequestDispatcher rd = request.getRequestDispatcher("ticket.jsp");
			rd.forward(request, response);

	       } catch (Exception e) {
	                    e.printStackTrace();
	   }
		
		
	}

}
