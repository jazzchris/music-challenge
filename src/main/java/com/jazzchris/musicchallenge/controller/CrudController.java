package com.jazzchris.musicchallenge.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dropbox.core.DbxException;
import com.jazzchris.musicchallenge.entity.Composer;
import com.jazzchris.musicchallenge.entity.Piece;
import com.jazzchris.musicchallenge.service.MainService;

@Controller
@RequestMapping("/crud")
public class CrudController {

	@Autowired
	private MainService mainService;
	
	@GetMapping("/composer/delete")
	public String deleteComposer(@RequestParam("composerId") int theId) {
		
		mainService.deleteComposer(theId);
		
		return "redirect:/crud/composer/list";
	}
	
	@GetMapping("/piece/delete")
	public String deletePiece(@RequestParam("pieceId") int theId) {
		
		String title = mainService.getPiece(theId).getTitle();
		mainService.deletePiece(theId);
		
		try {
			mainService.deleteFile(title);
		} catch (DbxException e) {
			System.out.println("File of the given name does not exist");
		}
		
		return "redirect:/crud/composer/list";
	}
	
	@GetMapping("/composer/list")
	public String listComposers(Model theModel) {
		
		List<Composer> theComposers = mainService.getComposers();
		
		theModel.addAttribute("composers", theComposers);
		
		return "crud/list-composers";
	}
	
	@GetMapping("/piece")
	public String listPiecesByComposer(@RequestParam("composerId") int theId,
			Model theModel) {
		
		Composer theComp = mainService.getComposer(theId);
		theModel.addAttribute("composer", theComp);
		List<Piece> thePieces = mainService.getPiecesByComposer(theId);
		theModel.addAttribute("piece", thePieces);
		
		return "crud/list-pieces";
	}
	
	@PostMapping("/composer/saveComposer")
	public String saveComposer(@ModelAttribute("composer") Composer theComposer) {
		
		mainService.saveComposer(theComposer);
		
		return "redirect:/crud/composer/list";
	}
	
	@PostMapping("/piece/savePiece")
	public String savePiece(@ModelAttribute("piece") Piece thePiece) {
		
		mainService.savePiece(thePiece);
		
		return "redirect:/crud/composer/list";
	}
	
	@GetMapping("/composer/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		Composer theComposer = new Composer();
		theModel.addAttribute("composer", theComposer);
		
		return "crud/composer-form";
	}
	
	@GetMapping("/piece/showFormForAddPiece")
	public String showFormForAddPiece(@RequestParam("composerId") int theId,
			Model theModel) {
		
		Piece thePiece = new Piece();
		
		Composer theComp = new Composer();
		theComp.setId(theId);
		
		thePiece.setComposer(theComp);
		
		theModel.addAttribute("piece", thePiece);

		
		return "crud/piece-form";
	}
	
	@GetMapping("/composer/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("composerId") int theId,
			Model theModel) {
		
		Composer theComposer = mainService.getComposer(theId);
		
		// set customer as a model attribute to pre-populate the form
		theModel.addAttribute("composer", theComposer);
		
		return "crud/composer-form";
	}
	
	@GetMapping("/piece/showFormForUpdate")
	public String showFormForUpdatePiece(@RequestParam("pieceId") int theId,
			Model theModel) {
		
		Piece thePiece = mainService.getPiece(theId);
		theModel.addAttribute("piece", thePiece);

		return "crud/piece-form";
	}
	
	@GetMapping("/piece/showFormForAddPieceAndUpload")
	public String showFormForAddPieceAndUpload(@RequestParam("composerId") int theId,
			Model theModel) {
		
		Piece thePiece = new Piece();
		
		Composer theComp = new Composer();
		theComp.setId(theId);
		
		thePiece.setComposer(theComp);
		theModel.addAttribute("piece", thePiece);

		return "crud/piece-form-upload";
	}
	
	@PostMapping("/piece/savePieceAndUpload")
	public String savePieceAndUpload(@RequestParam MultipartFile file, @ModelAttribute("piece") Piece thePiece) {
		
		if (file != null) {
			try {
				mainService.uploadFile(file, thePiece.getTitle());
			} catch (DbxException | IOException e) {
				e.printStackTrace();
				return "crud/upload-error";
			}
		}
		mainService.savePiece(thePiece);
		
		String builder = "redirect:/crud/piece?composerId=".concat(""+thePiece.getComposer().getId());

		return builder;
	}
	
}
