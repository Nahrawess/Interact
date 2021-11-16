package com.socialnetwork;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class SQL 
{
	public static void main(String[] args)
	{
		String url="jdbc:mysql://localhost:3306/SocialNetwork";
		String username="root";
		String password="root";
				try 
		{
		Connection connection = DriverManager.getConnection( url, username, password);
		}
				
		        catch (SQLException e)
		{
		System.out.println("oops, error!");
		e.printStackTrace();
		}

}
}
