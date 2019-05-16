package com.jazzchris.musicchallenge.service;

import java.io.InputStream;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jazzchris.musicchallenge.quiz.AbstractQuestion;
import com.jazzchris.musicchallenge.quiz.MusicQuestion;
import com.jazzchris.musicchallenge.quiz.TextQuestion;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private FileService fileService;
	
	private LinkedList<AbstractQuestion> list = null;
	private int result = 0;
	private int total = 0;
	
	@Override
	public void create() {
		
		if (list != null) {
			list.clear();
		}
		
		list = new LinkedList<AbstractQuestion>();

		// populating hardcoded question list...
		
		AbstractQuestion tempQuest = new TextQuestion("When did Bach born", "31st March 1685");
		tempQuest.addWrongOptions("30th April 1685", "31st March 1695", "30th April 1695");
		list.add(tempQuest);

		InputStream stream = fileService.streamFile("Gor1");
		tempQuest = new MusicQuestion(stream, "Symphony No. 3");
		tempQuest.addWrongOptions("Symphony No. 1", "4'33''", "Krzesany");
		list.add(tempQuest);

		stream = fileService.streamFile("Lut1");
		tempQuest = new MusicQuestion(stream, "Jeux Venitiens");
		tempQuest.addWrongOptions("Eine Kleine Nachtmusic", "Black Angels", "Spring");
		list.add(tempQuest);

		tempQuest = new TextQuestion("What are the names of Bach", "Johann Sebastian");
		tempQuest.addWrongOptions("John Fitzgerald", "Jon Bon", "John Doe");
		list.add(tempQuest);
		
		// ...end populate
		
		total = list.size();
		result = 0;
	}

	@Override
	public AbstractQuestion getCurrentQuestion() {
		return list.peekFirst();
	}

	@Override
	public int getTotal() {
		return total;
	}

	@Override
	public boolean checkAnswer(String theAnswer) {
		boolean isCorrect = theAnswer.equals(list.peekFirst().getCorrectOption().getText());
		if (isCorrect) {
			result++;
		}
		return isCorrect;
	}

	@Override
	public boolean cleanup() {
		if (!list.isEmpty()) {
			list.removeFirst();
			return true;
		}
		return false;
	}

	@Override
	public int getResult() {
		return result;
	}

}
