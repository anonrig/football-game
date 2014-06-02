package com.st.fubio_android.Models;

import java.io.Serializable;

public class AnswerChoice implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int _id;
	String content;
	boolean isCorrect;
	public AnswerChoice(int _id, String content, boolean isCorrect) {
		this._id = _id;
		this.content = content;
		this.isCorrect = isCorrect;
	}
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isCorrect() {
		return isCorrect;
	}
	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

}
