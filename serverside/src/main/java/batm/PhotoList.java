package batm;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONValue;

@WebServlet(urlPatterns = "/photoList")
public class PhotoList extends HttpServlet {

	private static Logger logger = Logger.getLogger(PhotoList.class);


	/**
	 * Request for a rectangle.
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

//		SimpleDateFormat format = new SimpleDateFormat("yyyyy-MM-dd hh:mm");
//		Date dateInf;
//		try {
//			dateInf = format.parse(request.getParameter("dateinf"));
//		} catch (ParseException e) {
//			throw new ServletException(e);
//		}
		String param = request.getParameter("dateinf");
		long ts = param != null ? Long.parseLong( param ) : 0;
		
		//critère de date
		List<Photo> photoList = PhotoDao.INSTANCE.loadByDate(new Timestamp(ts));
		logger.info("items in photo list: "+ photoList.size());

		Map map =new LinkedHashMap();

//		response.setContentType("application/json");
		ServletOutputStream output = response.getOutputStream();
		OutputStreamWriter writer = new OutputStreamWriter(output);
		
		writer.append('[');
		boolean first = true;
		for (Photo photo : photoList) {
			if(first){
				first = false;
			}else{
				writer.append(',');
			}
			map.clear();
			map.put("longitude",photo.getLongitude());
			map.put("latitude",photo.getLatitude());
			map.put("radius",photo.getRadius());
			map.put("photo", "photoGet?file="+photo.getPhoto());
			map.put("comment",photo.getComment());
			map.put("timestamp", photo.getTimestamp().getTime());

			JSONValue.writeJSONString(map, writer);
		}
		writer.append(']');
		writer.flush();
		
		response.setStatus(HttpServletResponse.SC_OK);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		throw new ServletException("unexpected POST");
	}

}
