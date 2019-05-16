package com.jazzchris.musicchallenge.quiz;

public interface Askable {

	void ask();
	
	default void stop() {}
}
