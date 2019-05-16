package com.jazzchris.musicchallenge.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.DeleteErrorException;
import com.dropbox.core.v2.files.RestoreErrorException;
import com.dropbox.core.v2.files.UploadErrorException;
import com.jazzchris.musicchallenge.dao.ComposerDAO;
import com.jazzchris.musicchallenge.dao.PieceDAO;
import com.jazzchris.musicchallenge.entity.Composer;
import com.jazzchris.musicchallenge.entity.Piece;

@Service
public class MainServiceImpl implements MainService {

	@Autowired
	private ComposerDAO composerDAO;
	
	@Autowired
	private PieceDAO pieceDAO;
	
	@Autowired
	private FileService fileService;
	
	@Override
	@Transactional
	public List<Composer> getComposers() {
		return composerDAO.getComposers();
	}

	@Override
	@Transactional
	public Composer getComposer(int theId) {
		return composerDAO.getComposer(theId);
	}

	@Override
	@Transactional
	public void saveComposer(Composer comp) {
		composerDAO.saveComposer(comp);
	}

	@Override
	@Transactional
	public void deleteComposer(int id) {
		composerDAO.deleteComposer(id);
	}

	@Override
	@Transactional
	public List<Piece> getPieces() {
		return pieceDAO.getPieces();
	}

	@Override
	@Transactional
	public List<Piece> getPiecesByComposer(int theId) {
	
		return pieceDAO.getPiecesByComposer(theId);
	}

	@Override
	@Transactional
	public Piece getPiece(int id) {
		return pieceDAO.getPiece(id);
	}


	@Override
	@Transactional
	public void savePiece(Piece piece) {
		pieceDAO.savePiece(piece);
	}

	@Override
	@Transactional
	public void deletePiece(int id) {

		pieceDAO.deletePiece(id);
	}

	@Override
	public InputStream streamFile(String title) {
		return fileService.streamFile(title);
	}

	@Override
	public void uploadFile(MultipartFile file, String title) throws UploadErrorException, DbxException, IOException {
		fileService.uploadFile(file, title);
	}

	@Override
	public void renameFile(String oldTitle, String newTitle) throws RestoreErrorException, DbxException {
		fileService.renameFile(oldTitle, newTitle);
	}

	@Override
	public void deleteFile(String title) throws DeleteErrorException, DbxException {
		fileService.deleteFile(title);
	}

	@Override
	public List<String> listFile() {
		return fileService.listFile();
	}

}
