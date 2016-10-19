package com.sample.vdapp;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sample.datamodel.OTPBean;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationActivity extends Activity implements OnClickListener {

	private TextView txt_exits;
	private Button btn_register;
	private EditText edt_mno,edt_fname,edt_lname,edt_password,edt_cpassword;
	private String mobno,firstName,lastName,pass,crnt_pass;

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
			
			edt_mno = (EditText) findViewById(R.id.edt_mno);	
			edt_fname = (EditText) findViewById(R.id.edt_fname);
			edt_lname = (EditText) findViewById(R.id.edt_lname);
			edt_password = (EditText) findViewById(R.id.edt_password);
			edt_cpassword = (EditText) findViewById(R.id.edt_cpassword);
			
			btn_register = (Button) findViewById(R.id.btn_register);
			btn_register.setOnClickListener(this);
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

		case R.id.btn_register:
			mobno = edt_mno.getText().toString();
			firstName = edt_fname.getText().toString();
			lastName = edt_lname.getText().toString();
			pass = edt_password.getText().toString();
			crnt_pass = edt_cpassword.getText().toString();
			if(null != mobno && mobno.length() > 0)
			{
				sendOTP();
			}else{
				Toast.makeText(RegistrationActivity.this, "Please enter Mobile No", Toast.LENGTH_SHORT).show();
			}			
			
			break;
		default:
			break;
		}
	}

	private void sendOTP() {
		AsyncTaskRunner runner = new AsyncTaskRunner();
		runner.execute();
	}

	private class AsyncTaskRunner extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			HttpClient Client = new DefaultHttpClient();

			// Create URL string
			String SetServerString = "";

			
			String URL = "http://2factor.in/API/V1/f4602156-9448-11e6-96db-00163ef91450/SMS/"
					+ mobno + "/AUTOGEN";

			// Log.i("TAG", URL);

			try {
				
				// Create Request to server and get response
				HttpGet httpget = new HttpGet(URL);
				ResponseHandler<String> responseHandler = new BasicResponseHandler();
				SetServerString = Client.execute(httpget, responseHandler);
				// Show response on activity
				Log.d("TAG", "" + SetServerString);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return SetServerString;
		}
		
		@Override
		protected void onPostExecute(String result) {
			try{
			if(null != result)
			{
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				
				OTPBean mBean = gson.fromJson(result, OTPBean.class);
				
				if(null != mBean.getStatus() && ("Success").equalsIgnoreCase(mBean.getStatus()))
				{
					Intent intent = new Intent(RegistrationActivity.this,
							OTPRegistrationActivity.class);
					Bundle b = new Bundle();
					String sessionId = mBean.getDetails();
					if(null != sessionId)
					{
						b.putString("SessionId", mBean.getDetails());
						b.putString("phno", mobno);
					}
					intent.putExtras(b);
					startActivity(intent);
				}else{
					Toast.makeText(RegistrationActivity.this, "Not Get OTP", Toast.LENGTH_SHORT).show();
				}
			}
			}catch(Exception e)
			{e.printStackTrace();}
		}

	}
}
