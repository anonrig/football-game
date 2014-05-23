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
import android.widget.Toast;
import android.os.Build;

public class PractiseCategoriesActivity extends MainFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_practise_categories);

		RequestManager rm = new RequestManager(getApplicationContext());
		
		rm.get("practiseCategories", null, new AsyncHttpResponseHandler() {
            	
            	@Override
				public void onStart() {
            		System.out.println("Practise Categories onStart");
				}

            	
				@Override
				public void onSuccess(String response) {
					System.out.println(response);
			
				}

				
				@Override
				public void onFailure(Throwable error, String content) {
					System.out.println(content);
				}

				
				@Override
				public void onFinish() {
					System.out.println("Practise Categories onFinish");
				}
            });
	}

}
