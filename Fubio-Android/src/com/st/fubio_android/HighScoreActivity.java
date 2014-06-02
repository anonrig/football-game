package com.st.fubio_android;

import com.st.fubio_android.Models.PracticeCategory;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

public class HighScoreActivity extends MainFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_high_score);

		String userScore = getIntent().getStringExtra("score");
		
		TextView scoreText = (TextView)findViewById(R.id.userscore);
		scoreText.setText(userScore);
	}
	
	@Override 
	public void onBackPressed() {
		//Just override it.
	}

}
