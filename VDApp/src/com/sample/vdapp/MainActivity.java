package com.sample.vdapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	private Button button;
	private EditText edt_uname,edt_password;
	private boolean validateFlag;
	 //Test comment
	 //Test comment1
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the layout from video_main.xml
		setContentView(R.layout.activity_main);
 
		init();
		
		
 
		/*// Capture button clicks
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
 
				// Start NewActivity.class
				Intent myIntent = new Intent(MainActivity.this,
						VideoViewActivity.class);
				startActivity(myIntent);
			}
		});*/
	}
	private void init() {
		try {
			edt_uname = (EditText) findViewById(R.id.edt_uname);
			edt_password = (EditText) findViewById(R.id.edt_password);
			
			button = (Button) findViewById(R.id.MyButton);
			button.setOnClickListener(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.MyButton:
			
			String uname = edt_uname.getText().toString();
			String password = edt_password.getText().toString();
			
			if (validateCredential(uname, password)) {
				// Start NewActivity.class
				Intent myIntent = new Intent(MainActivity.this,
						VideoViewActivity.class);
				startActivity(myIntent);
			} else {
				Toast.makeText(MainActivity.this, "Enter Valid Credentials", Toast.LENGTH_SHORT).show();
			}			
			break;

		default:
			break;
		}
	}
	
	private boolean validateCredential(String uname, String password) {
		try {
			if(uname.length() > 0 && password.length() > 0)
			{
				validateFlag = true;
			}else{
				validateFlag = false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return validateFlag;
		
	}

}
