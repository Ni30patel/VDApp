package com.sample.vdapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class ListActivity extends Activity{
	private ListView listView;
	String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry","WebOS","Ubuntu","Windows7","Max OS X"};
	private Context mContext = ListActivity.this;
	
	@Override
	public void onBackPressed() {
		//call logout webservice
		finish();		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);
		
		VideoURLAdapter adapter = new VideoURLAdapter(mContext,mobileArray);
		listView = (ListView) findViewById(R.id.list);
		listView.setAdapter(adapter);
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
			Intent i = new Intent(ListActivity.this,UserProfileActivity.class);
			startActivity(i);
			return true;
		case R.id.action_video_list:
			Intent intent = new Intent(ListActivity.this,ListActivity.class);
			startActivity(intent);
			return true;
		case R.id.action_change_password:
			Intent chnageIntent = new Intent(ListActivity.this,ChangePasswordMenuActivity.class);
			startActivity(chnageIntent);
			return true;
		case R.id.action_logout:	
			Toast.makeText(ListActivity.this, "logout", Toast.LENGTH_SHORT).show();
			return true;
		default:
			return super.onMenuItemSelected(featureId, item);
		}
		
	}
}
