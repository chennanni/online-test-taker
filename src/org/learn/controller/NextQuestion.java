package org.learn.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.learn.data.Option;
import org.learn.data.Question;
import org.learn.data.Test;
import org.learn.database.DB;

/**
 * Servlet implementation class NextQuestion
 */
@WebServlet("/NextQuestion")
public class NextQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NextQuestion() {
        super();
        // TODO Auto-generated constructor stub
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
		HttpSession session = request.getSession(true);
		Test test = (Test) session.getAttribute("test");
		int qId = (int) session.getAttribute("qId");
		String testName = test.getName();
		
		//System.out.println(testName);
		
		Question question = DB.getInstance().getNextQuestion(testName, qId);
		List<Option> options = DB.getInstance().getOptions(testName, qId);
		String answer = DB.getInstance().getAnswer(testName, qId);
		
		request.setAttribute("question", question);
		request.setAttribute("options", options);
		request.setAttribute("answer", answer);
		
		request.getRequestDispatcher("testing.jsp").forward(request, response);
	}

}
