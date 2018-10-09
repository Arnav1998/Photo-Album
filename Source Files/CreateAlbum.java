package homework1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.PhotoAlbum;

@WebServlet("/photos/CreateAlbum")
public class CreateAlbum extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // Set my content type
        response.setContentType("text/html");
        
        // Get a reference to the Print Writer
        PrintWriter out = response.getWriter();
        
        // Generate our content
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("    <meta charset=\"UTF-8\">");
        out.println("    <title>Create Album</title>");
        out.println("    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
        out.println("</head>");
        out.println("<body>");
        
        out.println("<nav class=\"navbar navbar-dark bg-dark sticky-top mb-5\">");
        out.println("	<span class=\"navbar-text font-weight-bold text-light\">Photo Albums</span>");
        out.println("</nav>");
        
        out.println("<div class=\"container\">");
        
        out.println("<h3 class=\"mb-5\">Create A New Album</h3>");
        
        out.println("<form action=\"CreateAlbum\" method=\"post\">");
        out.println("<div class=\"form-group\">");
        out.println("	<label for=\"albumName\">Album Name</label>");
        out.println("	<input type=\"text\" class=\"form-control\" id=\"albumName\" placeholder=\"Enter Album Name\" name=\"albumName\">");
        out.println("</div>");
        out.println("<div class=\"form-group\">");
        out.println("	<label for=\"albumDecsription\">Album Description</label>");
        out.println("	<input type=\"text\" class=\"form-control\" id=\"albumDescription\" placeholder=\"Enter Album Description\" name=\"albumDescription\">");
        out.println("</div>");
        out.println("<button type=\"submit\" class=\"btn btn-primary\">Create Album</button>");
        out.println("</form>");

        out.println("</div>");
        out.println("</body>");
        out.println("</html>"); 
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//create a photo album using input
		@SuppressWarnings("unchecked")
		List<PhotoAlbum> albums = (ArrayList<PhotoAlbum>) this.getServletContext().getAttribute("albumList");
		
		albums.add(new PhotoAlbum(request.getParameter("albumName"), request.getParameter("albumDescription")));
		this.getServletContext().setAttribute("albumList", albums);
		
		File dir = new File(getServletContext().getRealPath( "/WEB-INF/uploads" ), request.getParameter("albumName"));
		dir.mkdir();
		
		File descriptionsFile=new File(dir.getAbsolutePath(), "description.txt");
        if(!descriptionsFile.exists()) {
        	descriptionsFile.createNewFile();
        }
        FileWriter fw = new FileWriter(descriptionsFile);
        fw.write(request.getParameter("albumDescription"));
        fw.flush();
        fw.close();
		response.sendRedirect("albums");
	}

}
