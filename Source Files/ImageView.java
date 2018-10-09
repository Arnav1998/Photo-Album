package homework1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/photos/ImageView")
public class ImageView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String imgPath = request.getParameter("imgPath");
//		
//		// Set my content type
 //       response.setContentType("text/html");
//        
//        // Get a reference to the Print Writer
//        PrintWriter out = response.getWriter();
//        
//        // Generate our content
//        out.println("<!DOCTYPE html>");
//        out.println("<html lang=\"en\">");
//        out.println("<head>");
//        out.println("    <meta charset=\"UTF-8\">");
//        out.println("    <title>Image</title>");
//        out.println("    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
//        out.println("</head>");
//        out.println("<body>");
//        out.println("<div class=\"container\">");
//        
//        out.println("<img src=\""+imgPath+"\">");
//        out.println("<a href=\"DownloadImage?imgPath="+imgPath+"\">Download Image</a>");//download image
//        out.println("<a href=\"DeleteImage?imgPath="+imgPath+"\">Delete Image</a>");//delete image
//        
//        out.println("</div>");
//        out.println("</body>");
//        out.println("</html>");  
//		System.out.println("hey");
//		
//		String imgPath = request.getParameter("imgPath");
////		imgPath = imgPath.substring(5); //--------
//        File file = new File(imgPath);
        response.setContentType( "image/jpeg" );
////        response.setHeader( "Content-Length", "" + file.length() );
////        response.setHeader( "Content-Disposition","inline" );
//
        FileInputStream in = new FileInputStream(request.getParameter("imgPath"));
        OutputStream out = response.getOutputStream();
        byte buffer[] = new byte[2048];
        int bytesRead;
        while( (bytesRead = in.read( buffer )) > 0 )
            out.write( buffer, 0, bytesRead );
        in.close();
        out.close();
        
        
//        
        
        
        
//        response.setContentType("image/jpeg");  
//        ServletOutputStream out;  
//        out = response.getOutputStream();  
//        FileInputStream fin = new FileInputStream(request.getParameter("imgPath"));  
//          
//        BufferedInputStream bin = new BufferedInputStream(fin);  
//        BufferedOutputStream bout = new BufferedOutputStream(out);  
//        int ch =0; ;  
//        while((ch=bin.read())!=-1)  
//        {  
//        bout.write(ch);  
//        }  
//          
//        bin.close();  
//        fin.close();  
//        bout.close();  
//        out.close();  
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
