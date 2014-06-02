package com.st.fubio_android;

import com.facebook.Session;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SettingsActivity extends MainFragment {


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_settings);

	}


	public void chooseTeam(View v) {

		if(!Session.getActiveSession().isOpened())
			Toast.makeText(getApplicationContext(), "Please login to continue", 3).show();
		else
			startActivity(new Intent(this, ChooseTeamActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
	}
}
