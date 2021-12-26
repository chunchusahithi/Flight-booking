package com.flyhigh;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class DisplayForm
 */
public class DisplayForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public DisplayForm() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String i = request.getParameter("dpo");
		HttpSession s = request.getSession();
		DisplayOp dispop = new DisplayOp();
		dispop = (DisplayOp) s.getAttribute("disop".concat(i));
		s.setAttribute("final", dispop);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("info.html");
		rd.forward(request, response);
		
	}

}
