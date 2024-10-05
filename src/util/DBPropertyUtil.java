package util;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
public class DBPropertyUtil
{
public static String getConnString(String propFileName)
{
	String connString=null;
	Properties propsObject=new Properties();
	
	try(FileInputStream fis=new FileInputStream(propFileName))
	{
	propsObject.load(fis);
	connString=propsObject.getProperty("db.url")+"?user="+propsObject.getProperty("db.username")+"&password="+propsObject.getProperty("db.password");
	}catch (IOException e) {
		e.printStackTrace();
		System.out.println("Error: Not able to read Properties from File");
	}
	return connString;
	}
}
