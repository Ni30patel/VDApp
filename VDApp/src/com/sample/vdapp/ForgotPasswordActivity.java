package com.sample.vdapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ForgotPasswordActivity extends Activity implements OnClickListener {

	private Button btn_next;
	private TextView txt_newuser, txt_exits;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forgot_password);

		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		try {
			txt_newuser = (TextView) findViewById(R.id.txt_newuser);
			txt_exits = (TextView) findViewById(R.id.txt_exits);

			txt_newuser.setOnClickListener(this);
			txt_exits.setOnClickListener(this);

			btn_next = (Button) findViewById(R.id.btn_next);
			btn_next.setOnClickListener(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_next:
			Intent intent = new Intent(ForgotPasswordActivity.this,
					ChangePasswordActivity.class);
			startActivity(intent);
			break;

		case R.id.txt_newuser:
			Intent in = new Intent(ForgotPasswordActivity.this,
					RegistrationActivity.class);
			startActivity(in);
			break;

		case R.id.txt_exits:
			Intent i = new Intent(ForgotPasswordActivity.this,
					MainActivity.class);
			startActivity(i);
			break;
		default:
			break;
		}
	}
}
