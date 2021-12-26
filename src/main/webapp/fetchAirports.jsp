<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="jakarta.servlet.*, java.sql.*"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Fly High</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://kit.fontawesome.com/0f0c6581c2.js" crossorigin="anonymous"></script>
    <style>
        body {
            background-image: url("./aurora.jpg");
            background-size: 1300px 630px;
        }
    </style>
</head>
<body>
	<%! ResultSet rs; %>
	<nav class="bg-black mb-10 w-full">
        <div class="flex flex-row text-white p-3">
            <div class="basis-1/3 uppercase font-serif font-semibold"><span><a href="./home.html">Fly High</a></span>
            </div>
            <div class="basis-1/3"><a href="./fetchInfo.html"><span>Track Ticket</span></a></div>
            <div class="basis-1/3"><a href="./explore.html"><span>Explore Airports</span></a></div>
        </div>
    </nav>
    <div class="container px-28">
        <div class="flex flex-row mt-10 gap-5 text-white">
            <div class="basis-1/4 p-2 rounded-full bg-indigo-500 drop-shadow-lg">IATA Code</div>
            <div class="basis-3/4 p-2 rounded-full bg-indigo-500 drop-shadow-lg">Airport Name</div>
        </div>
   
	<%
	String country = request.getParameter("country");
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airlinedb", "root", "root");
		
		PreparedStatement airports = con.prepareStatement("SELECT Code,Name FROM airport_locations WHERE `Country Name`=?;");
		airports.setString(1,country);
		rs = airports.executeQuery();
		while(rs.next()){
			String code =rs.getString("Code");
			String airport = rs.getString("Name");
			
		
	%>
	<div class="flex flex-row p-2 gap-5">
            <div class="basis-1/4"><%=code %></div>
            <div class="basis-3/4"><%=airport %></div>
    </div>
	<%
	}
	rs.close();
	airports.close();
	con.close();
	} catch (Exception e) {
 	   e.printStackTrace();
 	   } 
 	%>
 	 </div>
</body>
</html>