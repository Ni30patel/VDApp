package com.sample.vdapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private Button button;
	private EditText edt_uname, edt_password;
	private boolean validateFlag;
	private TextView txt_newuser, txt_forgotpass;

	// Test comment
	// Test comment1
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the layout from video_main.xml
		setContentView(R.layout.activity_main);

		init();

		/*
		 * // Capture button clicks button.setOnClickListener(new
		 * OnClickListener() { public void onClick(View arg0) {
		 * 
		 * // Start NewActivity.class Intent myIntent = new
		 * Intent(MainActivity.this, VideoViewActivity.class);
		 * startActivity(myIntent); } });
		 */
	}

	private void init() {
		try {
			edt_uname = (EditText) findViewById(R.id.edt_uname);
			edt_password = (EditText) findViewById(R.id.edt_password);

			txt_newuser = (TextView) findViewById(R.id.txt_newuser);
			txt_forgotpass = (TextView) findViewById(R.id.txt_forgotpass);
			txt_newuser.setOnClickListener(this);
			txt_forgotpass.setOnClickListener(this);

			button = (Button) findViewById(R.id.btn_login);
			button.setOnClickListener(this);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_login:

			String uname = edt_uname.getText().toString();
			String password = edt_password.getText().toString();

//			if (AppUtil.isValidPassword(password)) {
			if(uname.length() > 0 && password.length() > 0)
			{
				if(password.length() < 8)
				{
					Toast.makeText(MainActivity.this,"Minimum 8 Characters Required", Toast.LENGTH_SHORT).show();
				}else{				
					validateCredential(uname, password);
				}
				/*if (validateCredential(uname, password)) {
					// Start NewActivity.class
					Intent myIntent = new Intent(MainActivity.this,
							ListActivity.class);
					startActivity(myIntent);
				} else {
					Toast.makeText(MainActivity.this,
							"Enter Valid Credentials", Toast.LENGTH_SHORT)
							.show();
				}*/
			}else{
				Toast.makeText(MainActivity.this,
						"Enter Valid Credentials", Toast.LENGTH_SHORT)
						.show();
			}
//			} else {
//				Toast.makeText(MainActivity.this,
//						"Minimum 8 Characters Required", Toast.LENGTH_SHORT)
//						.show();
//			}
			break;

		case R.id.txt_newuser:
			Intent myIntent = new Intent(MainActivity.this,
					RegistrationActivity.class);
			startActivity(myIntent);
			break;
		case R.id.txt_forgotpass:
			Intent in = new Intent(MainActivity.this,
					ForgotPasswordActivity.class);
			startActivity(in);
			break;
		default:
			break;
		}
	}

	private void validateCredential(String uname, String password) {
		try {
			//call webserivce to verify login
			Intent myIntent = new Intent(MainActivity.this,
					ListActivity.class);			
			startActivity(myIntent);
			finish();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}
