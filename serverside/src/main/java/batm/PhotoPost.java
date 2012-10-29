package batm;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;

@WebServlet(urlPatterns = "/photoPost")
public class PhotoPost extends HttpServlet {

	private static Logger logger = Logger.getLogger(PhotoPost.class);


	static Random rnd = new Random();


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		throw new ServletException("unexpected GET");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String idtel = request.getParameter("idtel");
		User user = UserDao.INSTANCE.getOrCreateUserByIdtel(idtel);

		
		Photo photo = new Photo();
		photo.setUserId(user.getUid());
		photo.setLongitude(Float.parseFloat(request.getParameter("lng")));
		photo.setLatitude(Float.parseFloat(request.getParameter("lat")));
		photo.setRadius(Float.parseFloat(request.getParameter("radius")));
		photo.setComment(request.getParameter("comment"));
		long millis = System.currentTimeMillis();
		photo.setTimestamp(new Timestamp(millis));
		
		String contentBase64 = request.getParameter("photo");
		byte[] binary = DatatypeConverter.parseBase64Binary(contentBase64);
		
		int rndNum = rnd.nextInt(999999999);
		String filename = millis + "-" + rndNum;
		String filepath = Utils.UPLDIR + filename;
		FileOutputStream output = new FileOutputStream(filepath);
		output.write(binary);
		output.close();
		photo.setPhoto(filename);
		
		PhotoDao.INSTANCE.insert(photo);
		logger.info("stored: "+filepath);
		
		response.setStatus(HttpServletResponse.SC_OK);
	}

}
