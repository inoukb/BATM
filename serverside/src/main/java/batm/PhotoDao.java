package batm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class PhotoDao {

	private static Logger logger = Logger.getLogger(PhotoDao.class);

	public static PhotoDao INSTANCE = new PhotoDao();

	/**
	 * @param photo
	 * @throws SQLException
	 */
	public void insert(Photo photo)  {
		Connection connection = Utils.getConnection();
		try {
			PreparedStatement st = connection
					.prepareStatement("insert into PHOTO (longitude, latitude, radius, photo, comment, user, ts) values(?,?,?,?,?,?,?)");
			st.setFloat(1, photo.getLongitude());
			st.setFloat(2, photo.getLatitude());
			st.setFloat(3, photo.getRadius());
			st.setString(4, photo.getPhoto());
			st.setString(5, photo.getComment());
			st.setLong(6, photo.getUserId());
			st.setTimestamp(7, photo.getTimestamp());

			st.execute();

		} catch (Exception e) {
			logger.error("", e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
			}
		}

	}

	
	
	/**
	 * @param lo1
	 * @param la1
	 * @param lo2
	 * @param la2
	 * @return
	 * @throws SQLException
	 */
	public List<Photo> loadByRectangle(float lo1, float la1, float lo2, float la2)  {
		List<Photo> lst = new ArrayList<Photo>();
		Connection connection = Utils.getConnection();
		try {
			PreparedStatement st = connection
					.prepareStatement("select * from PHOTO where longitude>=? and longitude<=? and latitude>=? and latitude<=?");
			st.setFloat(1, lo1);
			st.setFloat(2, lo2);
			st.setFloat(3, la1);
			st.setFloat(4, la2);
			
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				lst.add(mapPhotoFromResultset(rs));
			}

		} catch (Exception e) {
			logger.error("", e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
			}
		}
		return lst;
	}
	
	
	/**
	 * @param dateInf
	 * @return
	 */
	public List<Photo> loadByDate(Timestamp dateInf)  {
		List<Photo> lst = new ArrayList<Photo>();
		Connection connection = Utils.getConnection();
		try {
			PreparedStatement st = connection
					.prepareStatement("select * from PHOTO where ts>=?");
			st.setTimestamp(1, dateInf);
			
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				lst.add(mapPhotoFromResultset(rs));
			}

		} catch (Exception e) {
			logger.error("", e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
			}
		}
		return lst;
	}
	
	/**
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	Photo mapPhotoFromResultset(ResultSet rs) throws SQLException{
		Photo photo = new Photo();
		photo.setPid(rs.getLong(1));
		photo.setLongitude(rs.getFloat(2));
		photo.setLatitude(rs.getFloat(3));
		photo.setRadius(rs.getFloat(4));
		photo.setPhoto(rs.getString(5));
		photo.setComment(rs.getString(6));
		photo.setUserId(rs.getLong(7));
		photo.setTimestamp(rs.getTimestamp(8));
		photo.setStatus(rs.getString(9));
		return photo;
	}
	
}
