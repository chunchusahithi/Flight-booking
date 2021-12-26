package com.flyhigh;

import java.io.IOException;
import java.sql.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class DisplayOptions
 */
public class DisplayOptions extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public DisplayOptions() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public static double distance(double lat1, double lat2, double lon1, double lon2) {
		lon1 = Math.toRadians(lon1);
		lon2 = Math.toRadians(lon2);
		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);

		double dlon = lon2 - lon1;
		double dlat = lat2 - lat1;
		double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);

		double c = 2 * Math.asin(Math.sqrt(a));

		double r = 6371;

		return (c * r);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String to = request.getParameter("to");
		String from = request.getParameter("from");
		String date = request.getParameter("date");
		String size = request.getParameter("size");
		Double tolat, tolong, fromlat, fromlong, cost,costf;
		int flightId;
		String airline, name;
		Double time;
		int minutes;
		
		HttpSession session = request.getSession(true);
		session.setAttribute("date", date);
		session.setAttribute("to", to);
		session.setAttribute("from", from);
		session.setAttribute("size", size);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airlinedb", "root", "root");

			PreparedStatement loc = con
					.prepareStatement("select Latitude,Longitude from airport_locations where Code=?;");

			loc.setString(1, to);
			ResultSet loc1 = loc.executeQuery();
			loc1.next();
			tolat = loc1.getDouble("Latitude");
			tolong = loc1.getDouble("Longitude");

			loc.setString(1, from);
			loc1 = loc.executeQuery();
			loc1.next();
			fromlat = loc1.getDouble("Latitude");
			fromlong = loc1.getDouble("Longitude");

			
			Double dist = distance(tolat,fromlat,tolong,fromlong);

			PreparedStatement pricing = con.prepareStatement("select price from pricing where class=?;");
			pricing.setString(1, size);
			loc1 = pricing.executeQuery();
			loc1.next();
			cost = loc1.getDouble("price");
			cost = cost*dist;
			Double speed;

			PreparedStatement ps = con.prepareStatement(
					"select flightId, airline from routes where `source airport`= ? and `destination airport` = ?;",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ps.setString(1, from);
			ps.setString(2, to);
			ResultSet flights = ps.executeQuery();

			PreparedStatement airlinename = con
					.prepareStatement("select `Airline Name` as name from airlines_names where Code=?;");
			
			int i=1;
			int sizers = 0;
			if(flights != null) {
				flights.last();
				sizers = flights.getRow();
			}
			flights.beforeFirst();
			
			request.setAttribute("sizers", sizers);
			
			if(!flights.next()) {
				RequestDispatcher rd= request.getRequestDispatcher("home.html");
				rd.forward(request, response);
			}
			else
				flights.previous();
			
			while (flights.next()) {
				flightId = flights.getInt("flightId");
				airline = flights.getString("airline");
				airlinename.setString(1, airline);
				loc1 = airlinename.executeQuery();
				loc1.next();
				name = loc1.getString("name");
				costf = cost*( Math.random()*(0.5)+1);
				
				costf = costf * Math.pow(10, 2);
				costf = Math.floor(costf);
				costf = costf / Math.pow(10, 2);
				
				speed = (Math.random()*46+880);
				time = dist/speed;
				
				time = time * Math.pow(10, 2);
				time = Math.floor(time);
				time = time / Math.pow(10, 2);
				
				String doubleAsString = String.valueOf(time);
				int indexOfDecimal = doubleAsString.indexOf(".");
				int hours = Integer.parseInt(doubleAsString.substring(0, indexOfDecimal));
				Double mins = Double.parseDouble(doubleAsString.substring(indexOfDecimal));
				mins=mins*60;
				
				String minss = String.valueOf(mins);
				int index = minss.indexOf(".");
				minutes = Integer.parseInt(minss.substring(0, index));
				
				
				DisplayOp dispop = new DisplayOp(flightId,costf,name,hours,minutes);
				session.setAttribute("disop".concat(Integer.toString(i)), dispop);
				i++;
			}
			
			
			loc.close();
			pricing.close();
			ps.close();
			airlinename.close();
			con.close();
			RequestDispatcher aage = request.getRequestDispatcher("options.jsp");
			aage.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

	}

}
