package com.jazzchris.musicchallenge.quiz;

public class TextQuestion extends AbstractQuestion {

	public TextQuestion(String question, String answer) {
		super(answer);
		super.setQuestion(question);
	}

	@Override
	public void ask() {}

}
