package com.jazzchris.musicchallenge.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.DeleteErrorException;
import com.dropbox.core.v2.files.RestoreErrorException;
import com.dropbox.core.v2.files.UploadErrorException;
import com.jazzchris.musicchallenge.dao.ComposerRepository;
import com.jazzchris.musicchallenge.dao.PieceRepository;
import com.jazzchris.musicchallenge.entity.Composer;
import com.jazzchris.musicchallenge.entity.Piece;

@Service
@Primary
public class MainServiceJpaRepoImpl implements MainService {

	@Autowired
	private ComposerRepository composerRepository;

	@Autowired
	private PieceRepository pieceRepository;

	@Autowired
	private FileService fileService;

	@Override
	public List<Composer> getComposers() {
		return composerRepository.findAll();
	}

	@Override
	public Composer getComposer(int theId) {
		Optional<Composer> theComp = composerRepository.findById(theId);
		if (!theComp.isPresent()) {
			throw new RuntimeException();
		}
		return theComp.get();
	}

	@Override
	public void saveComposer(Composer comp) {
		composerRepository.save(comp);
	}

	@Override
	public void deleteComposer(int id) {
		composerRepository.deleteById(id);
	}

	@Override
	public List<Piece> getPieces() {
		return pieceRepository.findAll();
	}

	@Override
	public List<Piece> getPiecesByComposer(int theId) {
		return pieceRepository.findAllByComposerId(theId);
	}

	@Override
	public Piece getPiece(int id) {
		return pieceRepository.findById(id).orElseThrow();
	}

	@Override
	public void savePiece(Piece piece) {
		pieceRepository.save(piece);
	}

	@Override
	public void deletePiece(int id) {
		pieceRepository.deleteById(id);
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
