package com.uniquedeveloper.registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Registrationservlet
 */
@WebServlet("/Register")
public class Registrationservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uname = request.getParameter("name");
		String uemail = request.getParameter("email");
		String upwd = request.getParameter("pass");
		String umobile = request.getParameter("contact");
		RequestDispatcher dispatcher = null;
		
		try {
			Class.forName("co.mysql.cj.jdbc.Driver");
			Connection  con = DriverManger.getconnection("jdbc:mysql://localhost:3306/userdata1","root","Y1012Jqkhkp");
			PreparedStatement pst = con.prepareStatement("insert into user(uname,uemail,upwd,umobile) value(?,?,?,?)");
			pst.setString(1,uname);
			pst.setString(2, uemail);
			pst.setString(3,upwd);
			pst.setString(4, umobile);
			
			
			int rowCount = pst.executeUpdate();
			
			if (rowCount >0) {
				request.setAttribute("status","success");
				
			} else {
				request.setAttribute("status","failed");
				
			}
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
				
			}
		}
		
		
		
		
	}

}
