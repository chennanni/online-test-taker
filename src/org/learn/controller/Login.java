package org.learn.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.learn.database.DB;

// testing
// username: alice@gmail.com
// password: 123

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private DB db;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		
		//db.listAllUser();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
			
		if (db.login(email, password)) { // sign up successfully
			request.setAttribute("email", email);
			RequestDispatcher rd = request.getRequestDispatcher("mainPage.jsp");
			rd.forward(request, response);
		} else { // sign up fail
			request.setAttribute("message", "login fail, please try again...");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
	}

}
