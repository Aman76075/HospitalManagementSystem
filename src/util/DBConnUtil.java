package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnUtil
{
	public static Connection getConnection(String connString)
	{
	Connection con=null;
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection(connString);	
	}catch (ClassNotFoundException e)
	{
	e.printStackTrace();
	System.out.println("Driver Loading Failed");
	}
	catch (SQLException e)
	{
		e.printStackTrace();
		System.out.println("Not Connected to Database");
	}
	return con;
    }
}