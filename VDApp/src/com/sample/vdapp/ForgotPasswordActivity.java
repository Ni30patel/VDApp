package com.sample.vdapp;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sample.datamodel.OTPBean;

public class ForgotPasswordActivity extends Activity implements OnClickListener {

	private Button btn_next;
	private TextView txt_newuser, txt_exits;
	private EditText edt_uname;
	private String mobNo;

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
			
			edt_uname = (EditText) findViewById(R.id.edt_uname);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_next:
			mobNo = edt_uname.getText().toString();
			
			if(verifyNumber(mobNo))
			{
				sendOTP();
			}else{
				Toast.makeText(ForgotPasswordActivity.this, "Mobile Number is not registered", Toast.LENGTH_SHORT).show();
			}			
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

	private boolean verifyNumber(String mobNo) {
		// TODO Auto-generated method stub
		return true;
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
					+ mobNo + "/AUTOGEN";

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
					Intent intent = new Intent(ForgotPasswordActivity.this,
							ChangePasswordActivity.class);
					Bundle b = new Bundle();
					String sessionId = mBean.getDetails();
					if(null != sessionId)
					{
						b.putString("SessionId", mBean.getDetails());
						b.putString("phno", mobNo);
					}
					intent.putExtras(b);
					startActivity(intent);
				}else{
					Toast.makeText(ForgotPasswordActivity.this, "Not Get OTP", Toast.LENGTH_SHORT).show();
				}
			}
			}catch(Exception e)
			{e.printStackTrace();}
		}

	}
}
