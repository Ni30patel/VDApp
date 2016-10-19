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

public class OTPRegistrationActivity extends Activity implements
		OnClickListener {
	private Button btn_conregister;
	private EditText edt_otp;
	private String mOTP, sessionID,phno;
	private TextView txt_otp_msg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.otp_registration);

		Bundle b = getIntent().getExtras();
		if (null != b) {
			sessionID = b.getString("SessionId");
			phno = b.getString("phno");
		}

		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		try {
			btn_conregister = (Button) findViewById(R.id.btn_conregister);
			btn_conregister.setOnClickListener(this);

			edt_otp = (EditText) findViewById(R.id.edt_otp);
			
			txt_otp_msg = (TextView) findViewById(R.id.txt_otp_msg);
			txt_otp_msg.setText("OTP has been sent to "+ phno +" kindly enter here to register with us.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_conregister:
			mOTP = edt_otp.getText().toString();
			if (null != mOTP && mOTP.length() > 0) {
				AsyncTaskRunner runner = new AsyncTaskRunner();
				runner.execute();
			} else {
				Toast.makeText(OTPRegistrationActivity.this,
						"Enter Valid OTP Code", Toast.LENGTH_SHORT).show();
			}

			break;

		default:
			break;
		}
	}

	private class AsyncTaskRunner extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			String SetServerString = "";
			
			HttpClient Client = new DefaultHttpClient();

			Log.d("TAG", "sessionID:-" + sessionID);
			Log.d("TAG", "mOTP:-" + mOTP);
			String URL = "http://2factor.in/API/V1/f4602156-9448-11e6-96db-00163ef91450/SMS/VERIFY/"
					+ sessionID + "/" + mOTP;
			Log.i("TAG", URL);

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
			try {
				if(null != result)
				{
					Gson gson = new GsonBuilder().setPrettyPrinting().create();
					
					OTPBean mBean = gson.fromJson(result, OTPBean.class);
					
					if(null != mBean.getStatus() && ("Success").equalsIgnoreCase(mBean.getStatus()))
					{
						Toast.makeText(OTPRegistrationActivity.this, "User Registered", Toast.LENGTH_SHORT).show();
						Intent intent = new Intent(OTPRegistrationActivity.this,
								MainActivity.class);
						/*Bundle b = new Bundle();
						String sessionId = mBean.getDetails();
						if(null != sessionId)
						{
							b.putString("SessionId", mBean.getDetails());
						}
						intent.putExtras(b);*/
						startActivity(intent);
					}else{
						Toast.makeText(OTPRegistrationActivity.this, "Enter Valid OTP", Toast.LENGTH_SHORT).show();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
