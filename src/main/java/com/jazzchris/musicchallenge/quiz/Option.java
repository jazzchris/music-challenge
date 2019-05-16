package com.jazzchris.musicchallenge.quiz;

public class Option {

	private String text;
	private boolean isCorrect;
	
	public Option() {}
		
	public Option(String text, boolean isCorrect) {
		this.text = text;
		this.isCorrect = isCorrect;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	@Override
	public String toString() {
		return "Option [text=" + text + ", isCorrect=" + isCorrect + "]";
	}
	
}
