package homework1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/photos/ImageView")
public class ImageView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String imgPath = request.getParameter("imgPath");
		
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
        out.println("<div class=\"container\">");
        
        out.println("<img src=\""+imgPath+"\">");
        out.println("<a href=\"DownloadImage?imgPath="+imgPath+"\">Download Image</a>");//download image
        out.println("<a href=\"DeleteImage?imgPath="+imgPath+"\">Delete Image</a>");//delete image
        
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");   
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
