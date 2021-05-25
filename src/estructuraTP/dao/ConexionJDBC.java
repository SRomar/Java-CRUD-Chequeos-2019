package estructuraTP.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionJDBC {
	
	private static final String url = "jdbc:mysql://localhost:3306/chequea2?useTimezone=true&serverTimezone=UTC";
	private static final String user = "root";
	private static final String password = "password";

	
	public Connection conectar() {
		
		Connection myConn = null;
		
		try {
			myConn = DriverManager.getConnection(url, user, password);
		}
		catch (Exception e) {

			e.printStackTrace();

		}
		return myConn;
    }
}