package batm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class UserDao {

	private static Logger logger = Logger.getLogger(UserDao.class);

	public static UserDao INSTANCE = new UserDao();
	
	
	private User mapUserFromResultset(ResultSet rs) throws SQLException{
		User user = new User();
		user.setUid(rs.getLong(1));
		user.setIdtel(rs.getString(2));
		user.setGtoken(rs.getString(3));
		user.setEmail(rs.getString(4));
		user.setPassword(rs.getString(5));
		user.setRank(rs.getInt(6));
		return user;
	}

	/**
	 * @param photo
	 * @throws SQLException
	 */
	public User getOrCreateUserByIdtel(String idtel)  {
		if(idtel == null || idtel.trim().length() == 0){
			throw new IllegalArgumentException("missing idtel parameter");
		}
		User user = null;
		Connection connection = Utils.getConnection();
		try {
			PreparedStatement st1 = connection.prepareStatement(
					"select * from USER where idtel = ?"
					);
			st1.setString(1, idtel);
			ResultSet rs = st1.executeQuery();
			if(rs.next()){
				user = mapUserFromResultset(rs);
			}else{
				//création
				user = new User();
				user.setIdtel(idtel);
				//persistence
				PreparedStatement st2 = connection.prepareStatement("insert into USER(idtel) values(?)", Statement.RETURN_GENERATED_KEYS);
				st2.setString(1, idtel);
				st2.executeUpdate();
				ResultSet rs2 = st2.getGeneratedKeys();
				if (rs2.next()) {
					user.setUid(rs2.getLong(1));
				}
				
			}
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
			}
		}
		return user;
	}

	
	
	
}
