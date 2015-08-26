package org.muthu;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.muthu.service.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName =   request.getParameter("userId");
		String passWord = request.getParameter("password");
		
		LoginService service = new LoginService();
		String responseMessage = service.authenticate(userName, passWord);
		
		if(responseMessage.equalsIgnoreCase("Success")) {
			response.sendRedirect("Success.jsp");
			return;
		}else {
			PrintWriter writer = response.getWriter();
			writer.print(responseMessage);
			response.sendRedirect("login.jsp");
			return;
		}
		
	}

}
