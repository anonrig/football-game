package com.st.fubio_android;

import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.st.fubio_android.ServerConnections.RequestManager;
import com.st.fubio_android.ServerConnections.TinyDB;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.os.Build;

public class SettingsActivity extends MainFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_settings);

		
		RequestManager req = new RequestManager(this);
		req.get("questions/gs", null, new AsyncHttpResponseHandler() {
        	//ProgressDialog progress = new ProgressDialog(getApplicationContext());
        	
        	@Override
			public void onStart() {
//				progress.setTitle("Loading");
//				progress.setMessage("Thank you for your patience.");
//				progress.show();
        		System.out.println("basladi");
			}

        	
			@Override
			public void onSuccess(String response) {
				System.out.println(response);
		
//				try {
//					JSONObject jObject = new JSONObject(response);
//					
//					TinyDB tinydb = new TinyDB(getCallingActivity().getApplicationContext());
//					tinydb.putString("name", jObject.getString("name"));
//					tinydb.putString("teamImageName", jObject.getString("teamImageName"));
//					tinydb.putInt("id", jObject.getInt("id"));
//					tinydb.putInt("facebookId", jObject.getInt("facebookId"));
//					
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
			}

			
			@Override
			public void onFailure(Throwable error, String content) {
				System.out.println(content);
				//Toast.makeText(getApplicationContext(), content, 5).show();
			}

			
			@Override
			public void onFinish() {
				System.out.println("bitti");
				//progress.dismiss();
			}
        });
//		// Array of choices
//				String colors[] = {"Red","Blue","White","Yellow","Black", "Green","Purple","Orange","Grey"};
//
//				// Selection of the spinner
//				Spinner spinner = (Spinner) findViewById(R.id.teamSelection);
//
//				// Application of the Array to the Spinner
//				ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, colors);
//				spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
//				spinner.setAdapter(spinnerArrayAdapter);
	}
	
}
