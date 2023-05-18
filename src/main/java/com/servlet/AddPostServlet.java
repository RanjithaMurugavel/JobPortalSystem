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
 * Servlet implementation class AddPostServlet
 */
@WebServlet("/add_jobs")
public class AddPostServlet extends HttpServlet {
	//private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
   // public AddPostServlet() {
      //  super();
        // TODO Auto-generated constructor stub
  //  }

	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
            	
            	String title=request.getParameter("title");
            	String location=request.getParameter("location");
            	String category=request.getParameter("category");
            	String status=request.getParameter("status");
            	String description=request.getParameter("description");
            	
            	
            Jobs j=new Jobs();
            j.setTitle(title);
            j.setDescription(description);
            j.setLocation(location);
            j.setStatus(status);
            j.setCategory(category);
            
            HttpSession session=request.getSession();
            
            JobDao dao=new JobDao(DBConnect.getConn());
           boolean f= dao.addJobs(j);
           if(f)
           {
        	   session.setAttribute("succMsg","Job posted successfully");
        	   response.sendRedirect("add_jobs.jsp");
           } else {
        	   session.setAttribute("succMsg","Something went wrong on server");
        	   response.sendRedirect("add_jobs.jsp");
        	   
           }
            	
            	
            } catch(Exception e)
            {
            	e.printStackTrace();
            }
	}

}
