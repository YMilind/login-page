package com.loginpage;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/login_result")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name = "jdbc/myoracle")
	DataSource dataSource;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = new User(username, password);

		LoginDAO loginDAO = new LoginDAO(dataSource);
		Boolean loginSuccessful = null;
		loginSuccessful = loginDAO.validateUser(user);

		request.setAttribute("loginSuccessful", loginSuccessful);
		request.setAttribute("username", username);
		request.getRequestDispatcher("/login_result.jsp").forward(request, response);
	}
}
