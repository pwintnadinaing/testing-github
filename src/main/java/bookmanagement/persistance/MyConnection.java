package bookmanagement.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

		static Connection con = null;
		public static Connection getConnection() {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				
				String db = "jdbc:mysql://localhost:3306/bookdb_m_m";
				String username = "root";
				String password ="root";
				 con = DriverManager.getConnection(db, username, password);
				
				System.out.println("Database Connecting...");
			}catch(ClassNotFoundException e) {
				System.out.println("Driver class is not foud");
			}catch(SQLException e) {
				System.out.println("Database connection is not okay" + e);
			}
			return con;
		}
		
}
