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

import models.Image;
import models.PhotoAlbum;

@WebServlet("/photos/DeleteImage")
public class DeleteImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		@SuppressWarnings("unchecked")
		List<PhotoAlbum> albums = (ArrayList<PhotoAlbum>) this.getServletContext().getAttribute("albumList");
		
		String imgPath = request.getParameter("imgPath");
		
		File imgFile = new File(imgPath);
		
		String albumName = imgFile.getParentFile().getName();
		
		for (PhotoAlbum album: albums) {
			if (album.getName().equals(albumName)) {
				List<Image> images = album.getPhotos();
				
				for (Image img: images) {
					if (img.getImagePath().equals(imgPath)) {
						album.deleteImage(img);
						break;
					}
				}
			}
		}
		
		this.getServletContext().setAttribute("albumList", albums);
        
        imgFile.delete();
        
        response.sendRedirect("AlbumPage?albumName="+request.getParameter("albumName"));
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
