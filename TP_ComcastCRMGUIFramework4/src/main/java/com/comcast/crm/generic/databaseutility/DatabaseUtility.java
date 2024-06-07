package com.comcast.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;
/**
 * Contains methods to connect to database and perform queries
 */
public class DatabaseUtility 
{
	Connection con;
	/**
	 * Contains steps to connect to database
	 * @param url
	 * @param username
	 * @param password
	 * @throws SQLException
	 */
	public void getDbConnection(String url , String username , String password) throws SQLException
	{
		try
		{
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		
		con = DriverManager.getConnection(url ,username , password);
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}
		
	}
	/**
	 * Used to get connect
	 * @throws SQLException
	 */
	public void getDbConnection() throws SQLException
	{
		try
		{
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			con = DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects","root@%", "root");
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}
		
	}
	public void closeDbConnection() throws SQLException
	{
		con.close();
	}
	
	public ResultSet executeSelectQuery(String query) throws SQLException
	{
		ResultSet result =null;
		try
		{
		Statement stat = con.createStatement();
		result = stat.executeQuery(query);
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}
		return result;
		
	}
	
	public int executeNonSelectQuery(String query)
	{
		int result =0;
		try
		{
		Statement stat = con.createStatement();
		result = stat.executeUpdate(query);
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}
		return result;
		
	}

}
