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

public class UserProfileActivity extends Activity implements OnClickListener {

	private EditText edt_fname, edt_lname;
	private Button btn_update;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_profile);

		init();
	}

	private void init() {
		try {
			edt_fname = (EditText) findViewById(R.id.edt_fname);
			edt_lname = (EditText) findViewById(R.id.edt_lname);

			btn_update = (Button) findViewById(R.id.btn_update);
			btn_update.setOnClickListener(this);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_update:
			Intent intent = new Intent(UserProfileActivity.this,
					ListActivity.class);
			startActivity(intent);
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
			Intent i = new Intent(UserProfileActivity.this, UserProfileActivity.class);
			startActivity(i);
			return true;
		case R.id.action_video_list:
			Intent intent = new Intent(UserProfileActivity.this, ListActivity.class);
			startActivity(intent);
			return true;
		case R.id.action_logout:
			Toast.makeText(UserProfileActivity.this, "logout", Toast.LENGTH_SHORT)
					.show();
			return true;
		default:
			return super.onMenuItemSelected(featureId, item);
		}

	}
}
