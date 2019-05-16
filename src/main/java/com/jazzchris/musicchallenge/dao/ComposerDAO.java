package com.jazzchris.musicchallenge.dao;

import java.util.List;

import com.jazzchris.musicchallenge.entity.Composer;

public interface ComposerDAO {

	public List<Composer> getComposers();
	
	public Composer getComposer(int id);
	
	public void saveComposer(Composer comp);
	
	public void deleteComposer(int id);

}
