package com.jazzchris.musicchallenge.quiz;

import java.io.InputStream;

import com.jazzchris.musicchallenge.player.StreamPlayer;

public class MusicQuestion extends AbstractQuestion {

	private StreamPlayer player;

	public MusicQuestion(InputStream stream, String name) {
		super(name);
		super.setQuestion("What do you hear?");
		player = new StreamPlayer(stream);
	}

	public StreamPlayer getPlayer() {
		return player;
	}

	@Override
	public void ask() {
		player.start();
	}

	@Override
	public void stop() {
		player.stop();
	}
	
}
