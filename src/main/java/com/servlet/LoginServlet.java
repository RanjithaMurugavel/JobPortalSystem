package com.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.DB.DBConnect;
import com.dao.UserDao;
import com.entity.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String em=request.getParameter("email");
			String ps=request.getParameter("password");
			User user;
			HttpSession session=request.getSession();
			
			if("admin@gmail.com".equals(em) && "admin@123".equals(ps))
			{
				user = new User();
				session.setAttribute("userobj",user);
			     user.setRole("admin");
			     response.sendRedirect("admin.jsp");
			}
			else
			{
			     UserDao dao=new UserDao(DBConnect.getConn());
                 user = dao.login(em, ps);
                 
                 if(user!=null)
                 {
                	 session.setAttribute("userobj",user);
                	 response.sendRedirect("home.jsp");
                 }
                 else {
                	 session.setAttribute("succMsg","Invalid Username and Password");
                	 response.sendRedirect("login.jsp");
                 }
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
