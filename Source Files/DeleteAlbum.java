package homework1;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.PhotoAlbum;

@WebServlet("/photos/DeleteAlbum")
public class DeleteAlbum extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String albumName = request.getParameter("albumName");
		
		@SuppressWarnings("unchecked")
		List<PhotoAlbum> albums = (ArrayList<PhotoAlbum>) this.getServletContext().getAttribute("albumList");
		
		for (int i = 0; i < albums.size(); i++) {
			if (albums.get(i).getName().equals(albumName)) {
				albums.remove(i);
			}
		}
		
		this.getServletContext().setAttribute("albumList", albums);
		
		File albumFile = new File(getServletContext().getRealPath( "/WEB-INF/uploads/"+albumName));
	
		String[]entries = albumFile.list();
		
		if (entries!=null) {
			for(String s: entries){
			
				File currentFile = new File(albumFile.getPath(),s);
				currentFile.delete();
			}
		}
		
		albumFile.delete();
		
		response.sendRedirect("albums");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		doGet(request, response);
	}

}
