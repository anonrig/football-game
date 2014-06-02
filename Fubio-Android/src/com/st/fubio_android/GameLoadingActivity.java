package com.st.fubio_android;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.st.fubio_android.Adapters.CarouselAdapter;
import com.st.fubio_android.Models.AnswerChoice;
import com.st.fubio_android.Models.PracticeCategory;
import com.st.fubio_android.Models.Question;
import com.st.fubio_android.ServerConnections.RequestManager;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class GameLoadingActivity extends Activity {

	private static String TAG = "GameLoading";
	ArrayList<Question> easyQuestions = new ArrayList<Question>();
	ArrayList<Question> mediumQuestions = new ArrayList<Question>();
	ArrayList<Question> hardQuestions = new ArrayList<Question>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_game_loading);

		PracticeCategory cat = (PracticeCategory)getIntent().getSerializableExtra("PracticeCategory");
		
		RequestManager rm = new RequestManager(this);
		
		rm.get("questions/" + cat.getToken() , null, new AsyncHttpResponseHandler() {
			ProgressDialog progress = new ProgressDialog(GameLoadingActivity.this);
			@Override
			public void onStart() {
				Log.d(TAG, "onStart");
				progress.setTitle("Loading");
				progress.setMessage("Getting questions.");
				progress.show();
			}


			@SuppressWarnings("null")
			@SuppressLint("NewApi")
			@Override
			public void onSuccess(String response) {
				Log.d(TAG, "onSuccess");
				try {
					JSONObject serverResponse = new JSONObject(response);
					
					//Easy questions
					JSONArray serverEasyQuestions = serverResponse.getJSONArray("easy");
					
					for (int i=0; i < serverEasyQuestions.length(); i++) {
						JSONObject current = serverEasyQuestions.getJSONObject(i);
						JSONArray serverChoices = current.getJSONArray("choices");
						ArrayList <AnswerChoice> answers = new ArrayList<AnswerChoice>();
						
						for (int j = 0; j < serverChoices.length(); j++) {
							JSONObject answer = serverChoices.getJSONObject(j);
							
							answers.add(new AnswerChoice(answer.getInt("_id"), answer.getString("content"), answer.getBoolean("iscorrect")));
						}
						
						easyQuestions.add(new Question(current.getInt("id"), current.getString("question"), answers));
					}
					
					//Medium questions
					JSONArray serverMediumQuestions = serverResponse.getJSONArray("easy");
					
					for (int i=0; i < serverMediumQuestions.length(); i++) {
						JSONObject current = serverMediumQuestions.getJSONObject(i);
						JSONArray serverChoices = current.getJSONArray("choices");
						ArrayList <AnswerChoice> answers = new ArrayList<AnswerChoice>();
						
						for (int j = 0; j < serverChoices.length(); j++) {
							JSONObject answer = serverChoices.getJSONObject(j);
							
							answers.add(new AnswerChoice(answer.getInt("_id"), answer.getString("content"), answer.getBoolean("iscorrect")));
						}
						
						mediumQuestions.add(new Question(current.getInt("id"), current.getString("question"), answers));
					}
					
					
					//Hard questions
					JSONArray serverHardQuestions = serverResponse.getJSONArray("easy");
					
					for (int i=0; i < serverHardQuestions.length(); i++) {
						JSONObject current = serverHardQuestions.getJSONObject(i);
						JSONArray serverChoices = current.getJSONArray("choices");
						ArrayList <AnswerChoice> answers = new ArrayList<AnswerChoice>();
						
						for (int j = 0; j < serverChoices.length(); j++) {
							JSONObject answer = serverChoices.getJSONObject(j);
							
							answers.add(new AnswerChoice(answer.getInt("_id"), answer.getString("content"), answer.getBoolean("iscorrect")));
						}
						
						hardQuestions.add(new Question(current.getInt("id"), current.getString("question"), answers));
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}


			@Override
			public void onFailure(Throwable error, String content) {
				Log.e(TAG, "onFailure");
				Log.e(TAG, content);
				progress.setMessage("Problem accessing Fubio HQ.");
			}


			@Override
			public void onFinish() {
				Log.d(TAG, "onFinish");		
				progress.dismiss();
				final TextView countdownTime = (TextView)findViewById(R.id.loadingtime);
				new CountDownTimer(4000, 1000) {

					public void onTick(long millisUntilFinished) {
						countdownTime.setText(((int) millisUntilFinished / 1000) + "");
					}

					public void onFinish() {
						Intent intent = new Intent(GameLoadingActivity.this, PracticeActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						intent.putExtra("easyQuestions", easyQuestions);
						intent.putExtra("mediumQuestions", mediumQuestions);
						intent.putExtra("hardQuestions", hardQuestions);
						startActivity(intent);
						finish();
					}
				}.start();	  
			}
		});
	}

	
}
