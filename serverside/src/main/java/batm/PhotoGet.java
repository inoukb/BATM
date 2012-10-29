package batm;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

@WebServlet(urlPatterns = "/photoGet")
public class PhotoGet extends HttpServlet {

	private static Logger logger = Logger.getLogger(PhotoGet.class);


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String fileParam = request.getParameter("file");
		String filepath = Utils.UPLDIR + fileParam;
		FileInputStream input = new FileInputStream(filepath);
		response.setContentType("image/jpeg");
		ServletOutputStream output = response.getOutputStream();
		IOUtils.copy(input, output);
		IOUtils.closeQuietly(input);
		
		output.flush();
		response.setStatus(HttpServletResponse.SC_OK);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		throw new ServletException("unexpected POST");
	}

}
