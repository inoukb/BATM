package batm;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * @author dimitri
 *
 */
public class Utils {

//	public static String UPLDIR = "../../app-root/runtime/data/photos/";
	public static String UPLDIR = "/var/lib/stickshift/xxxxxx/app-root/runtime/data/photos/";

	public static final DataSource MYSQL_DS = mysqlDs();

	public static DataSource mysqlDs() {
		try {

			InitialContext cxt = new InitialContext();
			DataSource ds = (DataSource) cxt
					.lookup("java:jboss/datasources/MysqlDS");
			return ds;
		} catch (Exception e) {
			throw new IllegalStateException("can't access datasource", e);
		}
	}

	/**
	 * @return
	 */
	public static Connection getConnection() {
		try {
			return MYSQL_DS.getConnection();
		} catch (SQLException e1) {
			throw new IllegalStateException("can't get connection");
		}
	}

}
