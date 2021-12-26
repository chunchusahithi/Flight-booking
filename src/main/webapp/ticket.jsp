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
    <%@page import="jakarta.servlet.*, com.flyhigh.TicketInfo, java.time.*,java.time.format.DateTimeFormatter " language="java"%>
</head>

<body>
    <nav class="bg-black mb-10">
        <div class="flex flex-row text-white p-3">
            <div class="basis-1/3 uppercase font-serif font-semibold"><span><a href="./home.html">Fly High</a></span></div>
            <div class="basis-1/3"><a href="./fetchInfo.html"><span>Track Ticket</span></a></div>
            <div class="basis-1/3"><a href="./explore.html"><span>Explore Airports</span></a></div>
        </div>
    </nav>
    <%! TicketInfo nticket = new TicketInfo(); 
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH : mm");
		LocalTime localTime = LocalTime.now();
    %>
    <%
    	nticket = (TicketInfo) request.getAttribute("nticket") ;
    %>
    <div class="bg-slate-200/40 mx-32 rounded-xl px-14 py-16 font-serif">
        <header class="mb-10 w-full border-b text-center">
            <h1 class="text-5xl w-full font-medium text-indigo-700 tracking-tight">Ticket Information</h1>
        </header>
        <div class="mb-5"><p>Ticket ID:  <b><%=nticket.getTicketId() %></b> </p></div>
        <div class="mb-10 w-full border-b">
            <h2 class="text-xl font-semibold text-indigo-700 mb-5">Passenger Information</h2>
            <div class="flex flex-row py-1">
                <p class="basis-1/2">Passenger Name</p>
                <p class="basis-1/2"><%=nticket.getPassengername() %></p>
            </div>
            <div class="flex flex-row py-1">
                <p class="basis-1/2">No. of Passengers</p>
                <p class="basis-1/2"><%=nticket.getNoe() %></p>
            </div>
            <div class="flex flex-row py-1">
                <p class="basis-1/2">Email</p>
                <p class="basis-1/2"><%=nticket.getEmail() %></p>
            </div>
        </div>
        <div class="mb-10 w-full border-b text-left">
            <h2 class="text-xl font-semibold text-indigo-700 mb-5">Flight Information</h2>
            <div class="flex flex-row py-1">
                <p class="basis-1/2">Flight Id</p>
                <p class="basis-1/2"><%=nticket.getFlightId() %></p>
            </div>
            <div class="flex flex-row py-1">
                <p class="basis-1/2">Airline Name</p>
                <p class="basis-1/2"><%=nticket.getAirlinename() %></p>
            </div>
            <div class="flex flex-row py-1">
                <p class="basis-1/2">Departure Airport</p>
                <p class="basis-1/2"><%=nticket.getSourceairportid()+"  "+nticket.getSourceairport() +", "+nticket.getSourcecountry() %></p>
            </div>
            <div class="flex flex-row py-1">
                <p class="basis-1/2">Arrival Airport</p>
                <p class="basis-1/2"><%=nticket.getDestairportid()+"  "+nticket.getDestairport() +", "+nticket.getDestcountry() %></p>
            </div>
            <div class="flex flex-row py-1">
                <p class="basis-1/2">Equipment</p>
                <p class="basis-1/2"><%=nticket.getAircraft() %></p>
            </div>
        </div>
        <div class="mb-10 w-full border-b text-left">
            <h2 class="text-xl font-semibold text-indigo-700 mb-5">Flight Details</h2>
            <div class="flex flex-row py-1 mx-auto">
                <p class="basis-1/2">Departure Date</p>
                <p class="basis-1/2"><%=nticket.getDate() %></p>
            </div>
            <div class="flex flex-row py-1 mx-auto">
                <p class="basis-1/2">Departure Time</p>
                <p class="basis-1/2"><%=dtf.format(localTime) %></p>
            </div>
            <div class="flex flex-row py-1">
                <p class="basis-1/2">Duration</p>
                <p class="basis-1/2"><%=nticket.getHours()+" hr :"+nticket.getMinutes()+" mins" %></p>
            </div>
            <div class="flex flex-row py-1">
                <p class="basis-1/2">Class</p>
                <p class="basis-1/2"><%=nticket.getSize() %></p>
            </div>
            <div class="flex flex-row py-1">
                <p class="basis-1/2">Seat</p>
                <p class="basis-1/2"><%=nticket.getSeat() %></p>
            </div>
            <div class="flex flex-row py-1">
                <p class="basis-1/2">Cost</p>
                <p class="basis-1/2"><%="Rs. "+nticket.getCost() %></p>
            </div>
        </div>
        <div class="text-rose-500"><p>Remember to save your Ticket ID for future reference!</p></div>
        <button class="bg-indigo-600 text-white mt-5 rounded px-5 py-3" onclick="window.print()">PRINT</button>
    </div>


</body>

</html>