package com.st.fubio_android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class PracticeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_practice);

		String practiceTitle = getIntent().getStringExtra("HOP");
		try{
		System.out.println(practiceTitle);
		}catch(Exception ex){
			Toast.makeText(getApplicationContext(), "Cannot get byte array.", 5);
		}
	}
}
