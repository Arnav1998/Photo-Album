package homework1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Image;
import models.PhotoAlbum;


@WebServlet(urlPatterns = {"/photos/albums"}, loadOnStartup=1)
public class MainPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static List<PhotoAlbum> albums;
       
	@SuppressWarnings("unchecked")
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		if(this.getServletContext().getAttribute("albumList")!=null) 
			albums = (ArrayList<PhotoAlbum>) this.getServletContext().getAttribute("albumList");
		else {//create a albumList using stored albums in server and assign it to the servlet context
			albums = new ArrayList<PhotoAlbum>();
			
			String dirPath = getServletContext().getRealPath( "/WEB-INF/uploads" );
			File dir = new File(dirPath);
			File[] folders = dir.listFiles();
			if (folders.length == 0) {
			    System.out.println("The directory is empty");
			} else {
			    for (File folder : folders) {

			        albums.add(new PhotoAlbum(folder.getName(),""));
			        
			        if (folder.listFiles()!=null) {
				        for (File file: folder.listFiles()) {
				        	for (PhotoAlbum album: albums) {
				        		if(album.getName().equals(folder.getName())) {
				        			if (file.getName().equals("description.txt")) {
				        				
				        				Scanner in;
				        				try {
											in = new Scanner(new FileReader(file.getAbsolutePath()));
											
					        				StringBuilder sb = new StringBuilder();
					        				while(in.hasNext()) {
					        				    sb.append(in.next());
					        				}
					        				in.close();
					        				
					        				String description = sb.toString();
					        				album.setDescription(description);
					        				
										} catch (FileNotFoundException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}

				        			}
				        			album.addImage(new Image("file://"+file.getAbsolutePath()));
//				        			album.addImage(new Image(file.getAbsolutePath()));
				        		}
				        	}
				        }
			        }
			    }
			}
			
			this.getServletContext().setAttribute("albumList", albums);
		}
	}

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
        
        out.println("<div class=\"container\">");
          
       // out.println("<h1 class=\"my-4 text-center text-lg-left\">Photo Albums</h1>");
        
        if (albums.size()==0) { //if no albums exists, give user the option to create one
        	
        	out.println("<a href=\"CreateAlbum\">Create Album</a>");
        	
        } else { //Populate the page if existing albums, if they exist
        	
        	out.println("<div class=\"row text-center text-lg-left\">");
        	
        	for (int i = 0; i < albums.size(); i++) {
                out.println("<div class=\"row\">");
        		out.println("<a href=\"AlbumPage?albumName="+albums.get(i).getName()+"\"class=\"col\">");
            	out.println("	<figure class=\"figure\">");
                out.println("		<img src=\"../resources/photoAlbumImage.jpg\" class=\"img-thumbnail figure-img img-fluid rounded\">");
                out.println("		<figcaption class=\"figure-caption text-center\">"+albums.get(i).getName()+" |<a href=\"DeleteAlbum?albumName="+albums.get(i).getName()+"\">Delete Album</a>"+"</figcaption>");
//                out.println("		<figcaption class=\"figure-caption text-center\"><a href=\"DeleteAlbum?albumName="+albums.get(i).getName()+"\">Delete Album</a></figcaption>");
                out.println("	</figure>");
                out.println("</a>");
                out.println("</div>");
        	}

        	out.println("</div>");
        	
        	out.println("<br>");
        	
        	out.println("<a href=\"CreateAlbum\"class=\"btn btn-primary\">Create Album</a>");
        
        }
        

        
        out.println("</div>");
        out.println("</body>");
        out.println("</html>"); 
        
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
