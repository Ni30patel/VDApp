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
import android.widget.Toast;

public class ChangePasswordMenuActivity extends Activity implements OnClickListener{
	
	private EditText edt_password,edt_cpassword;
	private Button btn_changepass;
	private String password, changePassword;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.change_pass_menu);
		
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		try {
			btn_changepass = (Button) findViewById(R.id.btn_changepass);
			btn_changepass.setOnClickListener(this);

			edt_cpassword = (EditText) findViewById(R.id.edt_password);
			edt_password = (EditText) findViewById(R.id.edt_cpassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_changepass:
			password = edt_password.getText().toString();
			changePassword = edt_cpassword.getText().toString();
			
			if (changePassword.length() > 0 && password.length() > 0) {
				if (changePassword.equalsIgnoreCase(password)) {
					// call new Post Request to change password
					Intent intent = new Intent(ChangePasswordMenuActivity.this,
							MainActivity.class);
					startActivity(intent);
				} else {
					Toast.makeText(ChangePasswordMenuActivity.this,
							"Both Passwords Must Be Same", Toast.LENGTH_SHORT)
							.show();
				}
			}else{
				Toast.makeText(ChangePasswordMenuActivity.this, "Enter Valid Credentials", Toast.LENGTH_SHORT).show();
			}
			break;

		default:
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.action_user_profile:
			Intent i = new Intent(ChangePasswordMenuActivity.this,UserProfileActivity.class);
			startActivity(i);
			return true;
		case R.id.action_video_list:
			Intent intent = new Intent(ChangePasswordMenuActivity.this,ListActivity.class);
			startActivity(intent);
			return true;
		case R.id.action_change_password:
			Intent chnageIntent = new Intent(ChangePasswordMenuActivity.this,ChangePasswordMenuActivity.class);
			startActivity(chnageIntent);
			return true;
		case R.id.action_logout:	
			Toast.makeText(ChangePasswordMenuActivity.this, "logout", Toast.LENGTH_SHORT).show();
			return true;
		default:
			return super.onMenuItemSelected(featureId, item);
		}
		
	}
}
