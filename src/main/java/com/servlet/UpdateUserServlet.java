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

@WebServlet("/update_profile")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    
		       try {
		    	   int id=Integer.parseInt(request.getParameter("id"));
	            	String name=request.getParameter("name");
	            	String qua=request.getParameter("qua");
	            	String email=request.getParameter("email");
	            	String ps=request.getParameter("ps");
	            	
	            	UserDao dao=new UserDao(DBConnect.getConn());
	            	User u=new User();
	            	u.setId(id);
	            	u.setName(name);
	            	u.setQualification(qua);
	            	u.setEmail(email);
	            	u.setPassword(ps);
	            	
	            	boolean f=dao.updateUser(u);
	            	HttpSession session=request.getSession();
	            	if(f)
	            	{
	            		session.setAttribute("succMsg","Profile Updated Successfully");
	            		response.sendRedirect("home.jsp");
	            	}else {
	            		session.setAttribute("succMsg","Something went wrong on server");
	            		response.sendRedirect("home.jsp");
	            	}
		    	   
		       }catch(Exception e)
		       {
		    	   e.printStackTrace();
		       }
	}

}
