package com.sample.vdapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePasswordActivity extends Activity implements OnClickListener{
	
	private Button btn_changepass;
	private EditText edt_cpassword,edt_password;
	private String password,changePassword;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.change_password);
		
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
			
			if(changePassword.equalsIgnoreCase(password))
			{
				Intent intent = new Intent(ChangePasswordActivity.this,MainActivity.class);
				startActivity(intent);
			}else{
				Toast.makeText(ChangePasswordActivity.this, "Both Passwords Must Be Same", Toast.LENGTH_SHORT).show();
			}
			
			break;

		default:
			break;
		}
	}

}
