package com.sample.vdapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ChangePasswordActivity extends Activity implements OnClickListener{
	
	private Button btn_changepass;
	
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_changepass:
			Intent intent = new Intent(ChangePasswordActivity.this,MainActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

}
