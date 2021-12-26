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
    <%@page import="jakarta.servlet.*, com.flyhigh.DisplayOp" language="java"%>
</head>

<body>
    <nav class="bg-black mb-10 w-full">
        <div class="flex flex-row text-white p-3">
            <div class="basis-1/3 uppercase font-serif font-semibold"><span><a href="./home.html">Fly High</a></span></div>
            <div class="basis-1/3"><a href="./fetchInfo.html"><span>Track Ticket</span></a></div>
            <div class="basis-1/3"><a href="./explore.html"><span>Explore Airports</span></a></div>
        </div>
    </nav>
	<%! DisplayOp dispop = new DisplayOp(0,0.0,"fdksj",0,0); %>
    <div class="text-3xl border-b text-gray-700 font-serif m-3 mb-12">Flight Information</div>
    <form action="DisplayForm" method="post">
    <%
    	session = request.getSession();
    	int sizers = (int) request.getAttribute("sizers") ;
    	int i;
    	for(i=1;i<=sizers;i++){
    		dispop = (DisplayOp) session.getAttribute("disop".concat(""+i));
    %>
     <div
            class="flex flex-row bg-slate-100 mx-64 shadow-lg mb-7 rounded-3xl border border-indigo-500 text-lg font-serif text-gray-500 py-10 px-9 hover:bg-indigo-500 hover:text-white transition ease-in-out delay-150 hover:-translate-y-1 hover:scale-105">
            <div class="basis-2/12"><%=dispop.flightId%></div>
            <div class="basis-4/12"><%=dispop.name %></div>
            <div class="basis-2/12"><%=dispop.hours+" hr : "+dispop.mins+" mins" %></div>
            <div class="basis-3/12 place-content-end">
            <i class="fas fa-rupee-sign mx-5 text-indigo-200"></i><%=dispop.costf %></div>
            <div class="basis-1/12">
                <button type="submit" name="dpo" value=<%=Integer.toString(i) %>
                 class="ml-4 text-indigo-500 hover:text-slate-100 text-3xl"><i
                        class="fas fa-chevron-circle-right"></i></button>
            </div>
        </div>
        <% } %>
    
    </form>
</body>

</html>