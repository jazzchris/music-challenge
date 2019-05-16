package com.jazzchris.musicchallenge.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.DeleteErrorException;
import com.dropbox.core.v2.files.RestoreErrorException;
import com.dropbox.core.v2.files.UploadErrorException;
import com.jazzchris.musicchallenge.entity.Composer;
import com.jazzchris.musicchallenge.entity.Piece;

public interface MainService {

	List<Composer> getComposers();
	
	Composer getComposer(int theId);

	void saveComposer(Composer comp);
	
	void deleteComposer(int id);
	
	List<Piece> getPieces();
	
	List<Piece> getPiecesByComposer(int theId);

	Piece getPiece(int id);
	
	void savePiece(Piece piece);
	
	void deletePiece(int id);

	InputStream streamFile(String title);

	void uploadFile(MultipartFile file, String title) throws UploadErrorException, DbxException, IOException;
	
	void renameFile(String oldTitle, String newTitle) throws RestoreErrorException, DbxException;
	
	void deleteFile(String title) throws DeleteErrorException, DbxException;
	
	List<String> listFile();
}
