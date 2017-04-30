package com.loginpage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Boolean loginSuccessful = null;

		if ((username.equals("1")) && (password.equals("1"))) {
			loginSuccessful = true;
		} else {
			loginSuccessful = false;
		}

		request.setAttribute("loginSuccessful", loginSuccessful);
		request.setAttribute("username", username);
		request.getRequestDispatcher("/login_result.jsp").forward(request, response);
	}
}
