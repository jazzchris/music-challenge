package com.jazzchris.musicchallenge.service;

import com.jazzchris.musicchallenge.quiz.AbstractQuestion;

public interface TestService {

	void create();

	AbstractQuestion getCurrentQuestion();

	int getTotal();

	boolean checkAnswer(String theAnswer);

	boolean cleanup();

	int getResult();

}
