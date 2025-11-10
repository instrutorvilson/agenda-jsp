package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaDB {
	public static Connection getConexao() {
		String user = System.getenv("DB_USER"); //"root";
		String password = System.getenv("DB_PASSWORD");//"root";
		String url = System.getenv("DATABASE_URL");  //"jdbc:mysql://localhost:3306/bd_agenda";
		System.out.println(System.getenv("DB_USER"));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
			return null;
		} 
	}
}
