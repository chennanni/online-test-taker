package org.learn.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.learn.database.DB;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private DB db;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
        super();
        db = DB.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if (db.signup(email, password)) { // sign up successfully
			request.setAttribute("email", email);
			RequestDispatcher rd = request.getRequestDispatcher("mainPage.jsp");
			rd.forward(request, response);
		} else { // sign up fail
			request.setAttribute("message", "sign up fail, please choose another email...");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
	}

}
