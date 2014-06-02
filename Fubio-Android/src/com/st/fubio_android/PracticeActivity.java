package com.st.fubio_android;

import java.util.ArrayList;

import com.st.fubio_android.Models.AnswerChoice;
import com.st.fubio_android.Models.Question;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PracticeActivity extends Activity {
	int currentIndex = 0;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_practice);
		
		ArrayList<Question> easyQuestions = (ArrayList<Question>) getIntent().getSerializableExtra("easyQuestions");

		ArrayList<Question> mediumQuestions = (ArrayList<Question>)getIntent().getSerializableExtra("mediumQuestions");
		
		ArrayList<Question> hardQuestions = (ArrayList<Question>)getIntent().getSerializableExtra("hardQuestions");
		
		Question currentQuestion = easyQuestions.get(currentIndex);
		
		setQuestion(currentQuestion.getQuestion());
		setAnswers(currentQuestion.getChoices());
	}
	
	@Override
	public void onBackPressed() {
		super.finish();
		Intent intent = new Intent(PracticeActivity.this, PractiseCategoriesActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		startActivity(intent);
	}
	
	public void setAnswers(ArrayList<AnswerChoice> answers) {
		Button answer1 = (Button)findViewById(R.id.btnAnswer1);
		Button answer2 = (Button)findViewById(R.id.btnAnswer2);
		Button answer3 = (Button)findViewById(R.id.btnAnswer3);
		Button answer4 = (Button)findViewById(R.id.btnAnswer4);
		
		answer1.setText(answers.get(0).getContent());
		answer2.setText(answers.get(1).getContent());
		answer3.setText(answers.get(2).getContent());
		answer4.setText(answers.get(3).getContent());
	}
	
	public void setQuestion(String question) {
		TextView questionLabel = (TextView)findViewById(R.id.txtQuestion);
		
		questionLabel.setText(question);
	}
	
}
