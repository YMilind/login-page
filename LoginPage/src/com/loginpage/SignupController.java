package com.loginpage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signup_result")
public class SignupController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		boolean signupSuccessful;
		String message;
		if (username.isEmpty() || password.isEmpty()) {
			signupSuccessful = false;
			message = "Username and password can't be empty";
		} else {
			if (!(password.equals(password2))) {
				signupSuccessful = false;
				message = "Passwords don't match";
			} else {
				UserDAO userDAO = new UserDAO();
				User user = new User(username, password);
				if (!userDAO.usernameAvailable(user)) {
					signupSuccessful = false;
					message = "This username is already taken";
				} else {
					signupSuccessful = userDAO.createUser(user);
					message = signupSuccessful ? "User successfully registered!" : "Something went wrong";
				}
			}
		}

		request.setAttribute("signupSuccessful", signupSuccessful);
		request.setAttribute("message", message);
		request.getRequestDispatcher("/signup_result.jsp").forward(request, response);
	}

}
