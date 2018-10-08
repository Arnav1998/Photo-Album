package homework1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/photos/DownloadImage")
public class DownloadImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String imgPath = request.getParameter("imgPath");
		imgPath = imgPath.substring(5); //--------
        File file = new File(imgPath);
        response.setContentType( "image/jpg" );
        response.setHeader( "Content-Length", "" + file.length() );
        response.setHeader( "Content-Disposition","inline" );

        FileInputStream in = new FileInputStream( file );
        OutputStream out = response.getOutputStream();
        byte buffer[] = new byte[2048];
        int bytesRead;
        while( (bytesRead = in.read( buffer )) > 0 )
            out.write( buffer, 0, bytesRead );
        in.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
