package com.st.fubio_android;

import java.util.ArrayList;

import com.st.fubio_android.Models.AnswerChoice;
import com.st.fubio_android.Models.Question;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class PracticeActivity extends Activity {
	int currentIndex = 0, lifeLeft = 3, currentPoint = 0, currentQuestionScore;
	
	Question currentQuestion;
	AnswerChoice currentAnswer;
	
	CountDownTimer timer;

	Button answer1, answer2, answer3, answer4;
	TextView totalScore;
	TextView questionScore;
	
	ArrayList<Question> easyQuestions;
	ArrayList<Question> mediumQuestions;
	ArrayList<Question> hardQuestions;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_practice);
		
		this.easyQuestions = (ArrayList<Question>) getIntent().getSerializableExtra("easyQuestions");
		this.mediumQuestions = (ArrayList<Question>)getIntent().getSerializableExtra("mediumQuestions");
		this.hardQuestions = (ArrayList<Question>)getIntent().getSerializableExtra("hardQuestions");
		
		OnClickListener onClickAnswer = new OnClickListener() {

			@Override
			public void onClick(View v) {
				Button clicked = (Button)v;
				String answer = clicked.getText().toString();
				timer.cancel();
				
				if (currentAnswer.getContent() == answer) {
					Toast.makeText(PracticeActivity.this, "Correct!", 5).show();
					currentPoint += currentQuestionScore;
				} else {
					lifeLeft--;
					String res;
					res = "You have " + lifeLeft + " lives left.";
					
					if (lifeLeft == 0) {
						res = "No life left!";
						
						Intent intent = new Intent(PracticeActivity.this, HighScoreActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						intent.putExtra("score", currentPoint + "");
						startActivity(intent);
						onDestroy();
					}
					
					Toast.makeText(PracticeActivity.this, res, 5).show();
				}
				
				currentIndex++;
				changeQuestion();
			}
		};
		
		this.answer1 = (Button)findViewById(R.id.btnAnswer1);
		this.answer1.setOnClickListener(onClickAnswer);
		this.answer2 = (Button)findViewById(R.id.btnAnswer2);
		this.answer2.setOnClickListener(onClickAnswer);
		this.answer3 = (Button)findViewById(R.id.btnAnswer3);
		this.answer3.setOnClickListener(onClickAnswer);
		this.answer4 = (Button)findViewById(R.id.btnAnswer4);
		this.answer4.setOnClickListener(onClickAnswer);
		
		changeQuestion();
	}
	
	@Override
	public void onBackPressed() {
		super.finish();
		Intent intent = new Intent(PracticeActivity.this, PractiseCategoriesActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		startActivity(intent);
	}
	
	
	public void setAnswers(ArrayList<AnswerChoice> answers) {
		this.answer1.setText(answers.get(0).getContent());
		this.answer2.setText(answers.get(1).getContent());
		this.answer3.setText(answers.get(2).getContent());
		this.answer4.setText(answers.get(3).getContent());
	}
	
	public void setQuestion(String question) {
		TextView questionLabel = (TextView)findViewById(R.id.txtQuestion);
		
		questionLabel.setText(question);
		calculateScore(10);
	}
	
	public AnswerChoice getAnswer() {
		ArrayList<AnswerChoice> choices = currentQuestion.getChoices();
		for(int i = 0; i < choices.size(); i++) {
			AnswerChoice answer = choices.get(i);
			if (answer.isCorrect()) {
				return answer;
			}
		}
		return null;
	}
	
	public void changeQuestion() {
		this.currentQuestion = this.easyQuestions.get(currentIndex);
		setQuestion(this.currentQuestion.getQuestion());
		setAnswers(this.currentQuestion.getChoices());
		this.currentAnswer = getAnswer();
		
		this.timer =  new CountDownTimer(10000, 50) {

			public void onTick(long millisUntilFinished) {
				calculateScore((int)millisUntilFinished / 100);
				
				ProgressBar progress = (ProgressBar)findViewById(R.id.progressBar);
				progress.setProgress((int)millisUntilFinished / 100);
			}

			public void onFinish() {
				Toast.makeText(PracticeActivity.this, "Time ended!", 3).show();
				lifeLeft--;
				if (lifeLeft == 0) {
					Toast.makeText(PracticeActivity.this, "No life left!", 5).show();
					Intent intent = new Intent(PracticeActivity.this, HighScoreActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					intent.putExtra("score", currentPoint + "");
					startActivity(intent);
					onDestroy();
				} else {
					currentIndex++;
					changeQuestion();
				}
			}
		}.start();
	}
	
	public void calculateScore(int second) {
		this.currentQuestionScore = (int)(second * 0.5 * 1) + (50 * 1);
		this.questionScore = (TextView)findViewById(R.id.worthpoints);
		this.totalScore = (TextView)findViewById(R.id.currentpoints);
		
		this.questionScore.setText(this.currentQuestionScore + "");
		this.totalScore.setText(this.currentPoint + "");
	}
}
