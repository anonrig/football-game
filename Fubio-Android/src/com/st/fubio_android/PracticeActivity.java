package com.st.fubio_android;

import com.st.fubio_android.Models.PracticeCategory;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class PracticeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_practice);

		PracticeCategory praCatObj = (PracticeCategory)getIntent().getSerializableExtra("PracticeCategory");
	}
}
