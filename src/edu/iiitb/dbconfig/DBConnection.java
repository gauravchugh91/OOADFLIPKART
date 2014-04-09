package edu.iiitb.dbconfig;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

public class DBConnection {

	public static String sDriverName = null;
	public static String sServerName = null;
	public static String sPort = null;
	public static String sDatabaseName = null;
	public static String sUserName = null;
	public static String sPassword = null;

	/**
	 * Author : Gaurav
	 * The following function gets the DB connection values from connection_config.properties file
	 * and returns a Connection object
	 * @return
	 * @throws Exception
	 */
	public static Connection getDBConnection() throws Exception {
		Connection conn = null;

		ResourceBundle rb = ResourceBundle.getBundle("connection_config");

		sDriverName = rb.getString("driver.name");
		sServerName = rb.getString("server.name");
		sPort = rb.getString("port.no");
		sDatabaseName = rb.getString("database.name");
		sUserName = rb.getString("user.name");
		sPassword = rb.getString("user.password");

		Class.forName(sDriverName).newInstance();

		String sURL = "jdbc:mysql://" + sServerName + ":" + sPort + "/"
				+ sDatabaseName;
		conn = (Connection) DriverManager.getConnection(sURL, sUserName, sPassword);
		return conn;
	}
}