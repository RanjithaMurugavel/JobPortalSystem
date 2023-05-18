package com.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.DB.DBConnect;
import com.dao.JobDao;
import com.entity.Jobs;

/**
 * Servlet implementation class UpdateJobServlet
 */

@WebServlet("/update")
public class UpdateJobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id=Integer.parseInt(request.getParameter("id"));
			String title=request.getParameter("title");
        	String location=request.getParameter("location");
        	String category=request.getParameter("category");
        	String status=request.getParameter("status");
        	String description=request.getParameter("description");
        	
        	Jobs j=new Jobs();
        	j.setId(id);
        	j.setTitle(title);
        	j.setDescription(description);
        	j.setLocation(location);
        	j.setCategory(category);
        	j.setStatus(status);
        	
HttpSession session=request.getSession();
            
            JobDao dao=new JobDao(DBConnect.getConn());
           boolean f= dao.updateJob(j);
           if(f)
           {
        	   session.setAttribute("succMsg","Job updated successfully");
        	   response.sendRedirect("view_jobs.jsp");
           } else {
        	   session.setAttribute("succMsg","Something went wrong on server");
        	   response.sendRedirect("view_jobs.jsp");
           }
        	
        	
        	
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
