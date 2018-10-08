package homework1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Image;
import models.PhotoAlbum;

@WebServlet("/photos/AlbumPage")
public class AlbumPage extends HttpServlet {
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
        out.println("    <title>Document</title>");
        out.println("    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
        out.println("</head>");
        out.println("<body>");
        
        out.println("<nav class=\"navbar navbar-dark bg-dark sticky-top mb-5\">");
        out.println("	<span class=\"navbar-text font-weight-bold text-light\">Photo Albums</span>");
        out.println("</nav>");
        
        out.println("<div class=\"container m-3\">");
        
        String albumName = request.getParameter("albumName");
		@SuppressWarnings("unchecked")
		List<PhotoAlbum> albums = (ArrayList<PhotoAlbum>) this.getServletContext().getAttribute("albumList");
		
        out.println("<h2 class=\"mb-1\">"+albumName+"</h2>");
        
        for(PhotoAlbum album: albums) {
        	if (album.getName().equals(albumName)) {
        		out.println("<h4 class=\"mb-5\">"+album.getDescription()+"</h4>");
        		break;
        	}
        }
        

		for(PhotoAlbum album: albums) {
			if (album.getName().equals(albumName)) {
				//display contents of album
				List<Image> images = album.getPhotos();
				
				for (Image img: images) {
//					System.out.println(img.getImagePath());
					out.println("<a href=\"ImageView?imgPath="+img.getImagePath()+"\"><img src=\""+img.getImagePath()+"\" class=\"img-thumbnail figure-img img-fluid rounded\"></a>");
				}
				
				//give option to upload images to album
				out.println("<h3 class=\"mt-5 mb-3\">Add An Image</h3>");
				out.println("<form action=\"Upload?albumName="+albumName+"&albumDescription="+album.getDescription()+"\" method=\"post\" enctype=\"multipart/form-data\">"); 
				out.println("File: <input type=\"file\" name=\"file\" /> <br />");
				out.println("<input class=\"btn btn-primary mt-2\" type=\"submit\" name=\"upload\" value=\"Upload\" />");
				out.println("</form>");
				
				break;
			}
			
		}
        

        
        out.println("</div>");
        out.println("</body>");
        out.println("</html>"); 
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
