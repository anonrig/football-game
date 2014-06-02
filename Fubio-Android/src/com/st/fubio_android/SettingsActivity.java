package com.st.fubio_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SettingsActivity extends MainFragment {


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_settings);
		
		FB_Login facebookLogin = (FB_Login) getSupportFragmentManager().findFragmentById(android.R.id.content);
	}


	public void chooseTeam(View v) {
		startActivity(new Intent(this, ChooseTeamActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
	}
}
