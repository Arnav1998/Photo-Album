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
        out.println("    <title>"+request.getParameter("albumName")+"</title>");
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
				
                out.println("<div class=\"row\">");
				
				for (Image img: images) {
					
					if (!img.getImagePath().contains(".txt")) {
					
		        		out.println("<a href=\"ImageView?imgPath="+img.getImagePath()+"\" class=\"col\">");
		            	out.println("	<figure class=\"figure\">");
		                out.println("		<img src=\"ImageView?imgPath="+img.getImagePath()+"\" class=\"img-thumbnail figure-img img-fluid rounded\" style=\"width:300px;height:300px;\">");
		                //out.println("		<img src=\"resources/photoAlbumImage.jpg\" class=\"img-thumbnail figure-img img-fluid rounded\">");
		                
		            	out.println("		<figcaption class=\"figure-caption text-center\"><a href=\"DeleteImage?imgPath="+img.getImagePath()+"&albumName="+albumName+"\">Delete</a> | <a href=\"DownloadImage?imgPath="+img.getImagePath()+"\">Download</a></figcaption>");
	//	                out.println("		<figcaption class=\"figure-caption text-center\"><a href=\"DeleteAlbum?albumName="+albums.get(i).getName()+"\">Delete Album</a></figcaption>");
		                out.println("	</figure>");
		                out.println("</a>");
					}
	                
					//out.println("<a href=\"ImageView?imgPath="+img.getImagePath()+"\"><img src=\""+img.getImagePath()+"\" class=\"img-thumbnail figure-img img-fluid rounded\"></a>");
				}
				
                out.println("</div>");
				
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
