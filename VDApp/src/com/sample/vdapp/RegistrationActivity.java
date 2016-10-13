package com.sample.vdapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class RegistrationActivity extends Activity implements OnClickListener{
	
	private TextView txt_exits;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration);
		
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		try {
			txt_exits = (TextView) findViewById(R.id.txt_exits);
			txt_exits.setOnClickListener(this);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.txt_exits:
			Intent myIntent = new Intent(RegistrationActivity.this,
					MainActivity.class);
			startActivity(myIntent);
			break;

		default:
			break;
		}
	}

}
