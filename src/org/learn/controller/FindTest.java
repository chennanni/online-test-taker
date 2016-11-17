package org.learn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.learn.database.DB;

/**
 * Servlet implementation class Test
 */
@WebServlet("/FindTest")
public class FindTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
    DB db;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindTest() {
        super();
        db = DB.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// get test name from the front end
		String testType = request.getParameter("testType");
		// fetch test from the database		
		org.learn.data.Test test = db.getTest(testType);
		if (test != null){
			// push test to the front end
			request.setAttribute("test", test);
			request.getRequestDispatcher("testMain.jsp").forward(request, response);
		} else {
			request.setAttribute("message", "Test Found Err. It's still in progress...");
			request.getRequestDispatcher("err.jsp").forward(request, response);
		}
	}

}
