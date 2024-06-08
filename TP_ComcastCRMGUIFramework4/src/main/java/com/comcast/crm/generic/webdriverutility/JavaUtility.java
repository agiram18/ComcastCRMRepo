package com.comcast.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;



public class JavaUtility 
{
	public int getRandomNumber()
	{
		Random random = new Random();
		int randomint = random.nextInt(5000);
		return randomint;
	}
	
	public String getSystemDateYYYYDDMM()
	{
		Date dateObj = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(dateObj);
		return date;
		
	}
	
	public String getRequiredDateYYYYDDMM(int days)
	{
		Date dateObj = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.format(dateObj);
		Calendar cal = sdf.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String enddate = sdf.format(cal.getTime());
		return enddate;

	}
	
	public StringBuilder getRandomAlphaNumericData(int size)
	{
		int n=size;
		String Alphanumeric = "ABCDEFGHIJKLMONPQRSTUVWXYZ0123456789abcdefghijklmonpqrstuvwxyz";
		StringBuilder sb = new StringBuilder(n);
		for(int i=0 ; i<=n ; i++)
		{
			int index = (int)(Alphanumeric.length()*Math.random());
			sb.append(Alphanumeric.charAt(index));
		}
		StringBuilder random = sb;
		return random;
	}
	
	public StringBuilder getRandomPhoneNumber()
	{
		int n=10;
		String Alphanumeric = "1234567890";
		StringBuilder sb = new StringBuilder(n);
		for(int i=0 ; i<=n ; i++)
		{
			int index = (int)(Alphanumeric.length()*Math.random());
			sb.append(Alphanumeric.charAt(index));
		}
		StringBuilder random = sb;
		return random;
	}
	
	
	


	
}
