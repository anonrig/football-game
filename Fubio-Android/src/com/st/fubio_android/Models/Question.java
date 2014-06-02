package com.st.fubio_android.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int id;
	String question;
	ArrayList <AnswerChoice> choices;
	
	public Question(int id, String question, ArrayList <AnswerChoice> choices) {
		this.id = id;
		this.question = question;
		this.choices = choices;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public ArrayList<AnswerChoice> getChoices() {
		return choices;
	}

	public void setChoices(ArrayList<AnswerChoice> choices) {
		this.choices = choices;
	}

	
}
