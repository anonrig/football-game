package com.st.fubio_android;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.st.fubio_android.Models.Team;
import com.st.fubio_android.ServerConnections.RequestManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SettingsActivity extends MainFragment {

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_settings);

		
	}
	
	public void chooseTeam(View v) {
		startActivity(new Intent(this, ChooseTeamActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
	}
}
