package com.sample.vdapp;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
	private String fName,lName;

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
			fName = edt_fname.getText().toString();
			lName = edt_lname.getText().toString();
			
			/*if(null != fName && fName.length() > 0 && null != lName && lName.length() > 0)
			{
				updateProfile(fName,lName);
			}*/
			Intent intent = new Intent(UserProfileActivity.this,
					ListActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}

	}

	private void updateProfile(String fName2, String lName2) {
		// TODO Auto-generated method stub
		try {
			new MyAsyncTask().execute(fName2,lName2);
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
		case R.id.action_change_password:
			Intent chnageIntent = new Intent(UserProfileActivity.this,ChangePasswordMenuActivity.class);
			startActivity(chnageIntent);
			return true;
		case R.id.action_logout:
			Toast.makeText(UserProfileActivity.this, "logout", Toast.LENGTH_SHORT)
					.show();
			return true;
		default:
			return super.onMenuItemSelected(featureId, item);
		}

	}
	
	private class MyAsyncTask extends AsyncTask<String, Void, String> {
		String resp;

		@Override
		protected String doInBackground(String... params) {
			// Create a new HttpClient and Post Header
			String firstName = null,lastName = null;
			byte[] result;
			if(null != params)
			{
				firstName = params[0];
				lastName = params[1];
			}else{
				// do nothing
			}
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					"http://2factor.in/API/V1/f4602156-9448-11e6-96db-00163ef91450");

			try {
				// Add your data
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("firstname",firstName));
				nameValuePairs.add(new BasicNameValuePair("lastname",lastName));
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

				// Execute HTTP Post Request
				HttpResponse response = httpclient.execute(httppost);
				StatusLine statusLine = response.getStatusLine();
	            if(statusLine.getStatusCode() == HttpURLConnection.HTTP_OK){
	                result = EntityUtils.toByteArray(response.getEntity());
	                resp = new String(result, "UTF-8");
	            }
	            Log.d("TAG",""+resp);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
			return resp;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			try {
				Intent intent = new Intent(UserProfileActivity.this,
						ListActivity.class);
				startActivity(intent);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
