package com.jazzchris.musicchallenge.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.DeleteErrorException;
import com.dropbox.core.v2.files.RestoreErrorException;
import com.dropbox.core.v2.files.UploadErrorException;

public interface FileService {

	void uploadFile(MultipartFile file, String title) throws UploadErrorException, DbxException, IOException;
	
	void deleteFile(String title) throws DeleteErrorException, DbxException;
	
	void renameFile(String oldTitle, String newTitle) throws RestoreErrorException, DbxException;
	
	InputStream streamFile(String title);

	List<String> listFile();
}
