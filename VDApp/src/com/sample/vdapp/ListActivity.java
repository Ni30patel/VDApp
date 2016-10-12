package com.sample.vdapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

public class ListActivity extends Activity{
	private ListView listView;
	String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry","WebOS","Ubuntu","Windows7","Max OS X"};
	private Context mContext = ListActivity.this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);
		
		VideoURLAdapter adapter = new VideoURLAdapter(mContext,mobileArray);
		listView = (ListView) findViewById(R.id.list);
		listView.setAdapter(adapter);
	}

}
