package com.jazzchris.musicchallenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jazzchris.musicchallenge.quiz.AbstractQuestion;
import com.jazzchris.musicchallenge.service.TestService;

@Controller
@RequestMapping("/quiz")
public class QuizController {
	
	@Autowired
	private TestService testService;
		
	@GetMapping
	public String passToCreate() {
		return "redirect:/quiz/create";
	}
	
	@GetMapping("/create")
	public String create() {
		
		testService.create();
		
		return "redirect:/quiz/question";
	}
	
	@GetMapping("/cleanup")
	public String cleanup() {
		
		testService.getCurrentQuestion().stop();
		testService.cleanup();

		return "redirect:/quiz/question";
	}
	
	@GetMapping("/question")
	public String getQuestion(Model theModel) {

		AbstractQuestion theQuestion = testService.getCurrentQuestion();

		theModel.addAttribute("theQuestion", theQuestion);
		
		if (theQuestion == null) {
			theModel.addAttribute("result", testService.getResult());
			theModel.addAttribute("total", testService.getTotal());
			return "quiz/result";
		}
		else {
			theQuestion.ask();
			return "quiz/question";
		}
	}

	@PostMapping("check")
	public String validateQuestion2(@RequestParam("theAnswer") String theAnswer, Model theModel) {

		testService.checkAnswer(theAnswer);

		theModel.addAttribute("toCheck", testService.getCurrentQuestion());
		theModel.addAttribute("theAnswer", theAnswer);
		
		return "quiz/check";
	}
	
}
