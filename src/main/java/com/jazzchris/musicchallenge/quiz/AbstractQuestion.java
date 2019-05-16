package com.jazzchris.musicchallenge.quiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractQuestion implements Askable {

	private String question;
	private List<Option> options;
	private final Option correctOption;
	
	public AbstractQuestion(String correctAnswer) {
		correctOption = new Option(correctAnswer, true);
		options = new ArrayList<Option>();
		options.add(correctOption);
	}
	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	public void addWrongOptions(String...strgs) {
		for(String s : strgs) {
			options.add(new Option(s, false));
		}
		shuffleOptions();
	}
	
	public void shuffleOptions() {
		Collections.shuffle(options);
	}
	

	public List<Option> getOptions() {
		return options;
	}

	public Option getCorrectOption() {
		return correctOption;
	}
	
}
