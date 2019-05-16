package com.jazzchris.musicchallenge.player;

import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class StreamPlayer implements Runnable {

	private final AtomicBoolean running = new AtomicBoolean(false);
	private AdvancedPlayer advancedPlayer = null;
	private InputStream inputStream;
	
	public StreamPlayer() {}
	
	public StreamPlayer(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public void start() {
		Thread player = new Thread(this);
		player.start();
	}
	
	public void stop() {
		running.set(false);
		this.advancedPlayer.close();
	}
	
	@Override
	public void run() {
		try {
			advancedPlayer = new AdvancedPlayer(inputStream);
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
		running.set(true);
		
		while (running.get()) {
			try {
				advancedPlayer.play();
			} catch (JavaLayerException e) {
				e.printStackTrace();
			}
			running.set(false);
		}
	}
}
