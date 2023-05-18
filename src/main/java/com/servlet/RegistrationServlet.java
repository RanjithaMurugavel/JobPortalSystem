package com.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import jakarta.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.dao.UserDao;
import com.entity.User;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/add_user")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
try {
			
			String name=request.getParameter("name");
			String qua=request.getParameter("qua");
			String email=request.getParameter("email");
			String ps=request.getParameter("ps");
			
			
			UserDao dao=new UserDao(DBConnect.getConn());
			
			User u=new User(name,email,ps,qua,"user");
			boolean f=dao.addUser(u);
			HttpSession session=request.getSession();
			
			if (f)
			{
				session.setAttribute("succMsg", "Registered Successfully");
				response.sendRedirect("signup.jsp");
			} else {
				session.setAttribute("succMsg","Something went wrong on server");
				response.sendRedirect("signup.jsp");
			}
			
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	}


