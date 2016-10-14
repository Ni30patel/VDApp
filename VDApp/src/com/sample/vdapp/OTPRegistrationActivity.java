package com.sample.vdapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class OTPRegistrationActivity extends Activity implements OnClickListener{
	private Button btn_conregister;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.otp_registration);
		
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		try {
			btn_conregister = (Button) findViewById(R.id.btn_conregister);
			btn_conregister.setOnClickListener(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_conregister:
			Intent i = new Intent(OTPRegistrationActivity.this,MainActivity.class);
			startActivity(i);
			break;

		default:
			break;
		}
	}

}
