package com.sample.vdapp;

import android.content.Context;
import android.widget.Toast;

public class AppUtil {
	
	public static boolean isValidPassword(String password)
	{
		if(password.length() < 9)
		{
			return false;
		}else{
			return true;
		}				
	}

}
